package by.itsm.chat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import by.itsm.chat.dto.ChatCreateDTO;
import by.itsm.chat.dto.ChatReadDTO;
import by.itsm.chat.dto.ChatUpdateDTO;
import by.itsm.chat.entity.Chat;
import by.itsm.chat.repository.ChatRepository;
import by.itsm.chat.service.mapper.ChatMapper;
import by.itsm.exception.DataNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock private ChatRepository chatRepository;
    @Mock private ChatMapper chatMapper;
    @InjectMocks private ChatService chatService;

    private Chat chat;
    private ChatCreateDTO chatCreateDTO;
    private ChatUpdateDTO chatUpdateDTO;
    private ChatReadDTO chatReadDTO;

    @BeforeEach
    void setUp() {
        chat = new Chat();
        chatCreateDTO = new ChatCreateDTO();
        chatUpdateDTO = new ChatUpdateDTO();
        chatReadDTO = new ChatReadDTO();
    }

    @Test
    void createChat_success() {
        when(chatMapper.toEntity(chatCreateDTO)).thenReturn(chat);
        when(chatRepository.save(chat)).thenReturn(chat);
        when(chatMapper.toDto(chat)).thenReturn(chatReadDTO);

        ChatReadDTO result = chatService.createChat(chatCreateDTO);
        assertNotNull(result);
        verify(chatRepository).save(chat);
    }

    @Test
    void updateChat_success() {
        String id = "123";
        when(chatRepository.findById(id)).thenReturn(Optional.of(chat));
        when(chatMapper.toDto(chat)).thenReturn(chatReadDTO);

        ChatReadDTO result = chatService.updateChat(id, chatUpdateDTO);
        assertNotNull(result);
        verify(chatRepository).save(chat);
    }

    @Test
    void updateChat_notFound() {
        String id = "123";
        when(chatRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> chatService.updateChat(id, chatUpdateDTO));
    }

    @Test
    void getChat_success() {
        String id = "123";
        when(chatRepository.findById(id)).thenReturn(Optional.of(chat));
        when(chatMapper.toDto(chat)).thenReturn(chatReadDTO);

        ChatReadDTO result = chatService.getChat(id);
        assertNotNull(result);
    }

    @Test
    void getChat_notFound() {
        String id = "123";
        when(chatRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> chatService.getChat(id));
    }

    @Test
    void getAllChats_success() {
        when(chatRepository.findAll()).thenReturn(List.of(chat));
        when(chatMapper.toDto(chat)).thenReturn(chatReadDTO);

        List<ChatReadDTO> result = chatService.getAllChats();
        assertFalse(result.isEmpty());
    }

    @Test
    void deleteChat_success() {
        String id = "123";
        when(chatRepository.existsById(id)).thenReturn(true);
        doNothing().when(chatRepository).deleteById(id);

        assertDoesNotThrow(() -> chatService.deleteChat(id));
    }

    @Test
    void deleteChat_notFound() {
        String id = "123";
        when(chatRepository.existsById(id)).thenReturn(false);

        assertThrows(DataNotFoundException.class, () -> chatService.deleteChat(id));
    }
}
