package com.example.restjwttransfermoney.controller;

import com.example.restjwttransfermoney.payload.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class TransferController {
    @Autowired
    TransferMoney transferMoney;

    @PostMapping("/transfer")
    public HttpEntity<?> transferMoney(@RequestBody TransferDto transferDto){
        String result = transferMoney.transfer(transferDto.getAmount(), transferDto.getIdOf2Card());
        return ResponseEntity.ok(result);
    }
}
