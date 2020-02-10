package ru.integration.esb.kafka.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.integration.esb.kafka.TestConfig;
import ru.integration.esb.kafka.config.DataJpaConfig;
import ru.integration.esb.kafka.config.KafkaProducerConfig;
import ru.integration.esb.kafka.model.ConductorMessage;
import ru.integration.esb.kafka.service.SequentialDataMessageProducer;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        DataJpaConfig.class,
        KafkaProducerConfig.class,
        TestConfig.class
})
public class ConductorRepositoryTest {

    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private SequentialDataMessageProducer sequentialDataMessageProducer;

    @Test
    public void test25012020() {
        List<ConductorMessage> conductorMessages =
                conductorRepository.findAllByOffsetIsAfterAndOffsetIsBeforeOrderByOffsetAsc(1040409, 1040630);
        log.info(conductorMessages.toString());
        conductorMessages.forEach(conductorMessage ->
                sequentialDataMessageProducer.sendMessage("sequentialData", conductorMessage.getValue()));
    }

    @Test
    public void test17012020() {
        List<ConductorMessage> conductorMessages =
                conductorRepository.findAllByOffsetIsAfterAndOffsetIsBeforeOrderByOffsetAsc(943875, 943918);
        log.info(conductorMessages.toString());
        conductorMessages.forEach(conductorMessage ->
                sequentialDataMessageProducer.sendMessage("sequentialData", conductorMessage.getValue()));
    }

}