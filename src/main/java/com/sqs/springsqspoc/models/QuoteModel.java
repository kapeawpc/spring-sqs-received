package com.sqs.springsqspoc.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quotes")
public class QuoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String text;

    @NotNull
    private String awsMessageId;

    private Instant dateReceived;

    public QuoteModel(@NotNull String text, @NotNull String awsMessageId, Instant dateReceived) {
        this.text = text;
        this.awsMessageId = awsMessageId;
        this.dateReceived = dateReceived;
    }
}
