package com.mkipts.bank.repository;



import com.mkipts.bank.entity.District;
import com.mkipts.bank.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictRepository extends JpaRepository<District,Integer> {
   public   District findDistrictByName(String name);
}
