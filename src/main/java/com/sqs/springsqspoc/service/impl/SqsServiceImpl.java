package com.sqs.springsqspoc.service.impl;

import com.sqs.springsqspoc.models.QuoteModel;
import com.sqs.springsqspoc.repository.QuoteRepository;
import com.sqs.springsqspoc.service.SqsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
public class SqsServiceImpl implements SqsService {

    private QuoteRepository quoteRepository;

    @Autowired
    public SqsServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void saveQuote(Map<String, Object> payload, String messageId, String receiveTimestamp) throws JsonProcessingException {

        if (quoteRepository.existsByAwsMessageId(messageId)) {
            log.warn("Quote with AWS Message ID {} already exists", messageId);
        } else {
            QuoteModel quote = new QuoteModel(new ObjectMapper().writeValueAsString(payload), messageId, toInstant(receiveTimestamp));
            log.info("Saving quote with AWS Message ID {}", messageId);
            quoteRepository.save(quote);
        }
    }

    private Instant toInstant(String dateAsStr) {
        return Instant.ofEpochMilli(Long.parseLong(dateAsStr));
    }
}
