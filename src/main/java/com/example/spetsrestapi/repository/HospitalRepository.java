package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    List<Hospital> findAllByStatusIsTrue();

}
