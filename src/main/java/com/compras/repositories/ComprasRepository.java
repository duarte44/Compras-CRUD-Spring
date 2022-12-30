package com.compras.repositories;

import com.compras.entities.Cliente;
import com.compras.entities.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Long> {
}
