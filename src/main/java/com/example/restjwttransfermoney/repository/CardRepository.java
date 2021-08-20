package com.example.restjwttransfermoney.repository;

import com.example.restjwttransfermoney.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(path = "card")
public interface CardRepository extends JpaRepository<Card,Integer> {
    Optional<Card> findByUsername(String username);

}
