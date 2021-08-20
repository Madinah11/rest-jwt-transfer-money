package com.example.restjwttransfermoney.payload;

import lombok.Data;

@Data
public class TransferDto {
    private Double amount;
    private Integer idOf2Card;
}
