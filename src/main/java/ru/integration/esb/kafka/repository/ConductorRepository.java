package ru.integration.esb.kafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.integration.esb.kafka.model.ConductorMessage;

import java.util.List;

public interface ConductorRepository extends JpaRepository<ConductorMessage, Long> {

    List<ConductorMessage> findAllByOffsetIsAfterAndOffsetIsBeforeOrderByOffsetAsc(int after, int before);
}