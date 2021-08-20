package com.example.restjwttransfermoney.repository;
import com.example.restjwttransfermoney.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "outcome")
public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {

}
