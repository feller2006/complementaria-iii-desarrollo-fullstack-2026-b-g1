package com.fullstack.productapi.repository;

import com.fullstack.productapi.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
