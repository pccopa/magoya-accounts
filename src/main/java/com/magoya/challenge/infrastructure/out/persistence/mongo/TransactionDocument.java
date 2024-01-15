package com.magoya.challenge.infrastructure.out.persistence.mongo;

import com.magoya.challenge.domain.TransactionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Document(collection = "transactions")
@Data
public class TransactionDocument {

    @Id
    private String id;
    @NotNull
    private TransactionType type;
    @Min(0)
    private BigDecimal amount;
    @DBRef
    private AccountDocument account;

}
