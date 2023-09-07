package com.example.springboot.models;

import jakarta.persistence.*; // Importações relacionadas à JPA (Java Persistence API)

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity // Anotação que indica que essa classe é uma entidade JPA
@Table(name = "TB_PRODUCTS") // Nome da tabela no banco de dados associada a esta entidade
public class ProductModel implements Serializable { // Classe de modelo para produtos, implementando Serializable para serialização
    @Serial
    private static final long serialVersionUID = 1L; // Número de série para serialização

    @Id // Indica que o campo a seguir é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.AUTO) // Configuração de geração automática de valor para a chave primária
    private UUID idProduct; // Campo que representa o ID do produto
    private String name; // Campo que representa o nome do produto
    private BigDecimal value; // Campo que representa o valor do produto

    public ProductModel() {}

    public UUID getIdProduct() { // Método getter para obter o ID do produto
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) { // Método setter para definir o ID do produto
        this.idProduct = idProduct;
    }

    public String getName() { // Método getter para obter o nome do produto
        return name;
    }

    public void setName(String name) { // Método setter para definir o nome do produto
        this.name = name;
    }

    public BigDecimal getValue() { // Método getter para obter o valor do produto
        return value;
    }

    public void setValue(BigDecimal value) { // Método setter para definir o valor do produto
        this.value = value;
    }

    public ProductModel(UUID idProduct, String name, BigDecimal value) { // Construtor da classe
        this.idProduct = idProduct;
        this.name = name;
        this.value = value;
    }
}

