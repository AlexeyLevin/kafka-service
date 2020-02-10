package ru.integration.esb.kafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.integration.esb.kafka.model.ElasticMessage;

import java.math.BigInteger;
import java.util.List;

public interface ElasticRepository extends JpaRepository<ElasticMessage, BigInteger> {

    public List<ElasticMessage> findAllByDateIsGreaterThanEqualAndDateLessThanEqualOrderByDateAsc(BigInteger after, BigInteger before);
}
