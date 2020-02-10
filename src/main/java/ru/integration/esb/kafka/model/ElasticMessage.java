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
@Table(name = "exceptions", schema = "public", catalog = "kafka_message")
public class ElasticMessage {

    @Id
    @Column(name = "date", nullable = false, precision = 0)
    private BigInteger date;
    @Basic
    @Column(name = "context", nullable = true, length = -1)
    private String context;
    @Basic
    @Column(name = "route", nullable = true, length = -1)
    private String route;
    @Basic
    @Column(name = "endpoint", nullable = true, length = -1)
    private String endpoint;
    @Basic
    @Column(name = "exception", nullable = true, length = -1)
    private String exception;
    @Basic
    @Column(name = "stack", nullable = true, length = -1)
    private String stack;
    @Basic
    @Column(name = "history", nullable = true, length = -1)
    private String history;
    @Basic
    @Column(name = "headers", nullable = true, length = -1)
    private String headers;
    @Basic
    @Column(name = "body", nullable = true, length = -1)
    private String body;
    @Basic
    @Column(name = "response", nullable = true, length = -1)
    private String response;


}
