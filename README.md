# ITSM-Solution
Репозиторий для дипломного проектирования

## Система для управления инцидентами

## Оглавление

1. [Описание проекта](#описание-проекта)
2. [Установка](#установка)
3. [Использование](#использование)
4. [Архитектура](#архитектура)
    - [Нотация C4](#нотация-c4)
    - [UML диаграммы](#uml)
    - [Схема БД](#схема-бд)
    - [Диаграмма классов](#диаграмма-классов)
    - [Дизайн](#дизайн)
5. [Пользовательский интерфейс](#пользовательский-интерфейс)
    - [User-Flow](#user-flow)
6. [Документация](#документация)
    - [API](#api)
7. [Тестирование](#тестирование)

## Описание проекта

## Установка

## Использование

## Архитектура

### Нотация C4

Контейнерный уровень:
![image](https://github.com/user-attachments/assets/44effde6-dce5-41d9-8af5-4056439ed4c4)

Компонентный уровень:
![image](https://github.com/user-attachments/assets/91768d9c-54e4-4dc6-9295-151b624bcef7)

### UML
![image](https://github.com/user-attachments/assets/5898af2a-cbd2-4743-bb18-169795e19783)
![image](https://github.com/user-attachments/assets/b1d6193e-17d5-4eea-9436-a3bee833efd1)
![image](https://github.com/user-attachments/assets/3e0aef96-bb87-413e-b364-4f3a79fceabe)

### Схема БД
![image](https://github.com/user-attachments/assets/0324e6cf-add6-4045-bbe1-99a8c7dcd441)

### Диаграмма классов
![image](https://github.com/user-attachments/assets/93cf20f6-2fa0-4604-ac97-58e290fa3857)

### Дизайн
![image](https://github.com/user-attachments/assets/3e498278-fd77-498c-b78e-223f23afc22d)

## Пользовательский интерфейс
### User-Flow
![image](https://github.com/user-attachments/assets/9a23cfac-1008-4152-8b08-806ebd596354)
![image](https://github.com/user-attachments/assets/12c111f0-e1e3-42b6-b49f-f4c590e1783e)
![image](https://github.com/user-attachments/assets/6498001c-7bad-4ecd-bf3b-cdea4ff0aea2)
![image](https://github.com/user-attachments/assets/b9120105-aef4-4f71-a1bc-d7bfc6a59995)

## Документация

### API
https://github.com/Lightning-Andrey/ITSM-Solution/blob/develop/api-docs.json

## Тестирование

Пример модульного тестирования:

```java
package by.itsm.incident;

@ExtendWith(MockitoExtension.class)
class IncidentServiceTests {

    @Mock
    private IncidentRepository incidentRepository;

    @Mock
    private IncidentMapper incidentMapper;

    @InjectMocks
    private IncidentService incidentService;

    private Incident incident;
    private IncidentReadDTO incidentReadDTO;
    private IncidentCreateDTO incidentCreateDTO;
    private IncidentUpdateDTO incidentUpdateDTO;

    @BeforeEach
    void setUp() {
        incident = new Incident();
        incident.setId("1");

        incidentReadDTO = new IncidentReadDTO();
        incidentCreateDTO = new IncidentCreateDTO();
        incidentUpdateDTO = new IncidentUpdateDTO();
    }

    @Test
    void createIncident_ShouldReturnIncidentReadDTO() {
        when(incidentMapper.toEntity(incidentCreateDTO)).thenReturn(incident);
        when(incidentRepository.save(incident)).thenReturn(incident);
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        IncidentReadDTO result = incidentService.createIncident(incidentCreateDTO);

        assertNotNull(result);
        verify(incidentRepository).save(incident);
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void updateIncident_ShouldReturnUpdatedIncidentReadDTO() {
        when(incidentRepository.findById("1")).thenReturn(Optional.of(incident));
        doNothing().when(incidentMapper).updateEntity(incidentUpdateDTO, incident);
        when(incidentRepository.save(incident)).thenReturn(incident);
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        IncidentReadDTO result = incidentService.updateIncident("1", incidentUpdateDTO);

        assertNotNull(result);
        verify(incidentRepository).findById("1");
        verify(incidentMapper).updateEntity(incidentUpdateDTO, incident);
        verify(incidentRepository).save(incident);
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void updateIncident_ShouldThrowException_WhenIncidentNotFound() {
        when(incidentRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> incidentService.updateIncident("1", incidentUpdateDTO));

        verify(incidentRepository).findById("1");
        verifyNoMoreInteractions(incidentRepository, incidentMapper);
    }

    @Test
    void getIncident_ShouldReturnIncidentReadDTO() {
        when(incidentRepository.findById("1")).thenReturn(Optional.of(incident));
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        IncidentReadDTO result = incidentService.getIncident("1");

        assertNotNull(result);
        verify(incidentRepository).findById("1");
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void getIncident_ShouldThrowException_WhenIncidentNotFound() {
        when(incidentRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> incidentService.getIncident("1"));

        verify(incidentRepository).findById("1");
        verifyNoMoreInteractions(incidentRepository, incidentMapper);
    }

    @Test
    void getAllIncidents_ShouldReturnListOfIncidentReadDTOs() {
        List<Incident> incidents = List.of(incident);
        List<IncidentReadDTO> dtos = List.of(incidentReadDTO);

        when(incidentRepository.findAll()).thenReturn(incidents);
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        List<IncidentReadDTO> result = incidentService.getAllIncidents();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(incidentRepository).findAll();
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void deleteIncident_ShouldDeleteIncident_WhenExists() {
        when(incidentRepository.existsById("1")).thenReturn(true);
        doNothing().when(incidentRepository).deleteById("1");

        assertDoesNotThrow(() -> incidentService.deleteIncident("1"));

        verify(incidentRepository).existsById("1");
        verify(incidentRepository).deleteById("1");
    }

    @Test
    void deleteIncident_ShouldThrowException_WhenIncidentNotFound() {
        when(incidentRepository.existsById("1")).thenReturn(false);

        assertThrows(DataNotFoundException.class, () -> incidentService.deleteIncident("1"));

        verify(incidentRepository).existsById("1");
        verifyNoMoreInteractions(incidentRepository);
    }
}

```

Описание модульных тестов:
1. createIncident_ShouldReturnIncidentReadDTO()
Описание:

Проверяет, что инцидент успешно создается.
Сначала DTO маппится в сущность Incident.
Затем инцидент сохраняется в репозитории.
После этого маппится обратно в DTO и возвращается.
Проверки:
✅ Вызов метода save у incidentRepository.
✅ Вызов метода toDto у incidentMapper.
✅ Убедиться, что возвращаемый объект не null.

2. updateIncident_ShouldReturnUpdatedIncidentReadDTO()
Описание:

Проверяет обновление инцидента.
Ищет инцидент в репозитории.
Если найден, обновляет его данными из DTO.
Сохраняет изменения и возвращает обновленный объект.
Проверки:
✅ Вызов findById для поиска инцидента.
✅ Вызов updateEntity для обновления данных.
✅ Вызов save для сохранения изменений.
✅ Вызов toDto для преобразования в DTO.

3. updateIncident_ShouldThrowException_WhenIncidentNotFound()
Описание:

Проверяет, что метод updateIncident выбрасывает исключение DataNotFoundException, если инцидент не найден.
Проверки:
✅ Вызов findById, который возвращает Optional.empty().
✅ Ожидание исключения DataNotFoundException.
✅ Отсутствие вызова updateEntity, save, toDto.

4. getIncident_ShouldReturnIncidentReadDTO()
Описание:

Проверяет получение инцидента по ID.
Если найден, преобразуется в DTO и возвращается.
Проверки:
✅ Вызов findById.
✅ Вызов toDto.
✅ Проверка, что объект не null.

5. getIncident_ShouldThrowException_WhenIncidentNotFound()
Описание:

Проверяет, что при попытке получить несуществующий инцидент выбрасывается DataNotFoundException.
Проверки:
✅ Вызов findById, который возвращает Optional.empty().
✅ Ожидание исключения DataNotFoundException.
✅ Отсутствие вызова toDto.

6. getAllIncidents_ShouldReturnListOfIncidentReadDTOs()
Описание:

Проверяет получение всех инцидентов.
Возвращает список объектов IncidentReadDTO.
Проверки:
✅ Вызов findAll у incidentRepository.
✅ Вызов toDto для каждого найденного инцидента.
✅ Проверка, что размер списка соответствует ожиданиям.

7. deleteIncident_ShouldDeleteIncident_WhenExists()
Описание:

Проверяет успешное удаление инцидента.
Перед удалением проверяет, существует ли запись.
Проверки:
✅ Вызов existsById, который возвращает true.
✅ Вызов deleteById.

8. deleteIncident_ShouldThrowException_WhenIncidentNotFound()
Описание:

Проверяет, что метод deleteIncident выбрасывает DataNotFoundException, если инцидента нет в базе.
Проверки:
✅ Вызов existsById, который возвращает false.
✅ Ожидание исключения DataNotFoundException.
✅ Отсутствие вызова deleteById.
