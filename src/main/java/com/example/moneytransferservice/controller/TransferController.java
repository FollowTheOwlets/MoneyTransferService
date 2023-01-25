package com.example.moneytransferservice.controller;

import com.example.moneytransferservice.exception.ErrorTransferOrConfirmException;
import com.example.moneytransferservice.model.ConfirmInfo;
import com.example.moneytransferservice.model.OperationResponse;
import com.example.moneytransferservice.model.Transfer;
import com.example.moneytransferservice.repository.TransferRepository;
import com.example.moneytransferservice.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin(origins = "https://serp-ya.github.io/card-transfer/", allowedHeaders = "*")
public class TransferController {
    private TransferService transferService;

    public TransferController() {
        TransferRepository repository = new TransferRepository();
        this.transferService = new TransferService(repository);
    }

    @PostMapping("transfer")
    public OperationResponse doTransfer(@RequestBody Transfer transfer) {
        return transferService.doTransfer(transfer);
    }

    @PostMapping("confirmOperation")
    public OperationResponse confirmOperation(@RequestBody ConfirmInfo info) {
        return transferService.confirmOperation(info);
    }
}
