package com.sqs.springsqspoc.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface SqsService {
    void saveQuote(Map<String, Object> payload, String messageId, String receiveTimestamp) throws JsonProcessingException;
}
