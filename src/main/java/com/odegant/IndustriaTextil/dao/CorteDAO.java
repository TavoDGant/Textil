package com.odegant.IndustriaTextil.dao;

import com.odegant.IndustriaTextil.entity.Cortes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorteDAO extends JpaRepository<Cortes, Integer> {
}
