package com.magoya.challenge.infrastructure.out.persistence.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

    @Document(collection = "accounts")
    @Data
    public class AccountDocument {

        @Id
        private String id;
        @NotBlank
        private String name;
        @NotBlank
        @Indexed(unique = true)
        private String alias;
        @Min(0)
        private BigDecimal balance;
        @DBRef
        private List<TransactionDocument> transactions;

    }
