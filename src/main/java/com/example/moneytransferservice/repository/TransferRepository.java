package com.example.moneytransferservice.repository;

import com.example.moneytransferservice.model.Transfer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferRepository {
    private Map<String, TransferState> transferStateMap;
    private Map<String, Transfer> transferList;
    private AtomicInteger id;

    public TransferRepository() {
        this.transferStateMap = new HashMap<>();
        this.transferList = new HashMap<>();
        id = new AtomicInteger(0);
    }

    public String addTransfer(Transfer transfer) {
        String transferId = id.incrementAndGet() + "";
        transferStateMap.put(transferId + "", TransferState.LOAD);
        transferList.put(transferId + "", transfer);
        return transferId;

    }

    public Transfer confirmOperation(String id) {
        if(!transferStateMap.containsKey(id)){
            return null;
        }
        transferStateMap.put(id, TransferState.OK);
        return transferList.get(id);
    }

    public Transfer errorConfirmOperation(String id) {
        if(!transferStateMap.containsKey(id)){
            return null;
        }
        transferStateMap.put(id, TransferState.ERROR);
        return transferList.get(id);
    }

    public TransferState getTransferState(String id){
        return transferStateMap.get(id);
    }
}
