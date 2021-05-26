package com.odegant.IndustriaTextil.dao;

import com.odegant.IndustriaTextil.entity.Cortes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorteDAO extends JpaRepository<Cortes, Integer> {

    List<Cortes> findByFkec(Integer fkec);
}
