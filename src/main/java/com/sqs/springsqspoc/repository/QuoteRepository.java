package com.sqs.springsqspoc.repository;

import com.sqs.springsqspoc.models.QuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteModel, Long> {

    boolean existsByAwsMessageId(String awsMessageId);
}
