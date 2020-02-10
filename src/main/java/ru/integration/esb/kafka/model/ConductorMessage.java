package ru.integration.esb.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "message", schema = "public", catalog = "kafka_message")
public class ConductorMessage {

    @Id
    @Column(name = "timestamp", nullable = false, precision = 0)
    private BigInteger timestamp;
    @Basic
    @Column(name = "partition", nullable = true)
    private Integer partition;
    @Basic
    @Column(name = "offset", nullable = true)
    private Integer offset;
    @Basic
    @Column(name = "key", nullable = true, length = -1)
    private String key;
    @Basic
    @Column(name = "value", nullable = true, length = -1)
    private String value;

}
