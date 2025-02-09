package by.itsm.incident.repository;

import by.itsm.incident.entity.IncidentInJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentInJournalRepository extends JpaRepository<IncidentInJournal, String> {}
