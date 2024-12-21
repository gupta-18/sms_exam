package com.mkipts.bank.repository;



import com.mkipts.bank.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StateRepository extends JpaRepository<State,Integer> {
    public State findStateByName(String name);
}
