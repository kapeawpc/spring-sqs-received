package com.sqs.springsqspoc.listener;

import com.sqs.springsqspoc.service.SqsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;

import static com.sqs.springsqspoc.constants.Constants.QUOTE_QUEUE;

@Slf4j
@Component
public class ListenerQueues {

    private SqsService sqsService;

    @Autowired
    public ListenerQueues(SqsService sqsService) {
        this.sqsService = sqsService;
    }

    @SqsListener(value = QUOTE_QUEUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void receiveQuote(
            @Valid Map<String, Object> payload,
            @Header("MessageId") String messageId,
            @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) throws JsonProcessingException {

        log.info("Received messageId {} : payload {}", messageId, payload);
        sqsService.saveQuote(payload, messageId, approximateFirstReceiveTimestamp);
    }
}
