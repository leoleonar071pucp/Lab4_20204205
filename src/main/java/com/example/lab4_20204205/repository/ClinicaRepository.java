package com.example.lab4_20204205.repository;

import com.example.lab4_20204205.entity.Clinicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  ClinicaRepository extends JpaRepository<Clinicas, Integer>{
    public List<Clinicas> findByName(Clinicas Clinicas);

}
