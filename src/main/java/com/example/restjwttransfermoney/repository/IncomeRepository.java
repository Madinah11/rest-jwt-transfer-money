package com.example.restjwttransfermoney.repository;


import com.example.restjwttransfermoney.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "income")
public interface IncomeRepository extends JpaRepository<Income,Integer> {

}
