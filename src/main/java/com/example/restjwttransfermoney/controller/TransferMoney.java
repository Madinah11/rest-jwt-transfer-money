package com.example.restjwttransfermoney.controller;

import com.example.restjwttransfermoney.entity.Card;
import com.example.restjwttransfermoney.entity.Income;
import com.example.restjwttransfermoney.entity.Outcome;
import com.example.restjwttransfermoney.repository.CardRepository;
import com.example.restjwttransfermoney.repository.IncomeRepository;
import com.example.restjwttransfermoney.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TransferMoney {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    OutcomeRepository outcomeRepository;


    public String transfer(Double amountOfTransfer, Integer idOf2Card) {
        Double percent = 0.01;// Bank foizi
        //Sistemaga kirgan username ni aniqlash
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String systemClientName = authentication.getName();
        // DB dan cartani username boyicha qidirish
        Optional<Card> optionalFromCard = cardRepository.findByUsername(systemClientName);
        if (!optionalFromCard.isPresent())
            return "Bunday carta mavjud emas";
        Card fromCard = optionalFromCard.get();

        // Pul otqizilayotgan kartani DB dan ID boyica topish
        Optional<Card> optionalToCard = cardRepository.findById(idOf2Card);
        if (!optionalToCard.isPresent())
            return "Pul o'tkazmoxchi bo'lgan karta topilmadi";
        Card toCard = optionalToCard.get();

        if (!(fromCard.getBalance() >= amountOfTransfer))
            return "Balansda yetarli pul mavjud emas";
        if (!fromCard.getCurrency().equals(toCard.getCurrency()))
            return "Valyuta mos emas";

        //Birinci kartadan pulni yechish
        Double balance = fromCard.getBalance();
        balance = balance - amountOfTransfer - (amountOfTransfer * percent);
        fromCard.setBalance(balance);
        cardRepository.save(fromCard);

        //Ikkinchi kartaga otkazish
        Double balanceOf2Card = toCard.getBalance();
        balanceOf2Card += amountOfTransfer;
        toCard.setBalance(balanceOf2Card);
        cardRepository.save(toCard);

        //Chiqim
        Outcome outcome = new Outcome();
        outcome.setAmount(amountOfTransfer);
        outcome.setDate(new Date());
        outcome.setCommisionAmount(percent * amountOfTransfer);
        outcome.setFromCard(fromCard);
        outcome.setToCard(toCard);
        outcomeRepository.save(outcome);
        //Kirim
        Income income = new Income();
        income.setFromCard(fromCard);
        income.setToCard(toCard);
        income.setDate(new Date());
        income.setAmount(amountOfTransfer);
        incomeRepository.save(income);
        return "Pul o'tqazildi. Sizning hisobingiz: " + balance;
    }
}









