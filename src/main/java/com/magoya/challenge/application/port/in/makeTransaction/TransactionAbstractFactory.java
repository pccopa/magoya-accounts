package com.magoya.challenge.application.port.in.makeTransaction;

import com.magoya.challenge.application.service.makeTransaction.MakeTransaction;
import com.magoya.challenge.domain.TransactionType;
import com.magoya.challenge.common.FactoryUseCase;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@FactoryUseCase
public class TransactionAbstractFactory {

    public static BeanFactory beanFactory;

    @Autowired
    public TransactionAbstractFactory(BeanFactory bean) {
        beanFactory = bean;
    }

    public static MakeTransaction getTransactionUseCase (TransactionType tx) {
        return beanFactory.getBean(tx.name(), MakeTransaction.class);
    }


}
