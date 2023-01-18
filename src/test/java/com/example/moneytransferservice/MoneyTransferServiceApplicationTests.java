package com.example.moneytransferservice;

import com.example.moneytransferservice.model.*;
import com.example.moneytransferservice.repository.TransferRepository;
import com.example.moneytransferservice.repository.TransferState;
import com.example.moneytransferservice.service.TransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class MoneyTransferServiceApplicationTests {

    static TransferService transferService;
    static TransferRepository transferRepository;

    @BeforeAll
    static void  init() {
        transferRepository = new TransferRepository();
        transferService = new TransferService(transferRepository);
    }

    @Test
    void serviceDoTransferTest() {

        Transfer transfer = new Transfer(
                new Amount(Currency.RUR, 500L),
                "345",
                "0000111122223333",
                "12/24",
                "0000111122224444"
        );

        OperationResponse id = transferService.doTransfer(transfer);

        Assertions.assertEquals(id.getOperationId(), 1 + "");
    }

    @Test
    void serviceConfirmOperationTest() {
        ConfirmInfo confirmInfo = Mockito.spy(ConfirmInfo.class);
        Mockito.when(confirmInfo.getOperationId()).thenReturn("1");
        Mockito.when(confirmInfo.getCode()).thenReturn("0000");

        OperationResponse id = transferService.confirmOperation(confirmInfo);

        Assertions.assertEquals(id.getOperationId(), 1 + "");
    }

    @Test
    void serviceConfirmTransferStateTest() {
        TransferState state = transferRepository.getTransferState("1");
        Assertions.assertEquals(state, TransferState.OK);
    }

}
