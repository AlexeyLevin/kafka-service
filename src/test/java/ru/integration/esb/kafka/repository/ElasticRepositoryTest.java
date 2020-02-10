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
import ru.integration.esb.kafka.model.ElasticMessage;
import ru.integration.esb.kafka.service.SequentialDataMessageProducer;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        DataJpaConfig.class,
        KafkaProducerConfig.class,
        TestConfig.class
})
public class ElasticRepositoryTest {

    @Autowired
    private ElasticRepository elasticRepository;
    @Autowired
    private SequentialDataMessageProducer sequentialDataMessageProducer;

    @Test
    public void test13012020() {
        List<ElasticMessage> entityList =
                elasticRepository.findAllByDateIsGreaterThanEqualAndDateLessThanEqualOrderByDateAsc(BigInteger.valueOf(1578931290503L), BigInteger.valueOf(1578932480748L));
        log.info(entityList.toString());
        entityList.forEach(exceptionEntity ->
                sequentialDataMessageProducer.sendMessage("sequentialData", exceptionEntity.getBody()));
    }

}