package com.odegant.IndustriaTextil.dao;

import com.odegant.IndustriaTextil.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado, Integer> {
}
