package com.example.springboot.repositories;  // Pacote que contém o repositório de produtos.

import com.example.springboot.models.ProductModel;  // Importa a classe ProductModel.
import org.springframework.data.jpa.repository.JpaRepository;  // Importa o JpaRepository do Spring Data.
import org.springframework.stereotype.Repository;  // Indica que esta é uma classe de repositório.

import java.util.UUID;  // Importa a classe UUID.

@Repository  // Marca esta interface como um repositório do Spring.
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}  // Interface de repositório para a classe ProductModel usando UUID como chave primária.

