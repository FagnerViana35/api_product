package com.example.springboot.controllers;  // Pacote que contém a classe de controle ProductController.

import com.example.springboot.dtos.ProductRecordDto;  // Importa a classe ProductRecordDto para uso no controle.
import com.example.springboot.models.ProductModel;  // Importa a classe ProductModel para uso no controle.
import com.example.springboot.repositories.ProductRepository;  // Importa a classe ProductRepository para uso no controle.
import jakarta.validation.Valid;  // Importa a anotação @Valid de validação.
import org.springframework.beans.BeanUtils;  // Importa a classe BeanUtils para copiar propriedades entre objetos.
import org.springframework.beans.factory.annotation.Autowired;  // Importa a anotação @Autowired para injeção de dependência.
import org.springframework.http.HttpStatus;  // Importa a classe HttpStatus para gerenciar status HTTP.
import org.springframework.http.ResponseEntity;  // Importa a classe ResponseEntity para encapsular a resposta HTTP.
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController  // Indica que esta classe é um controlador REST que lida com solicitações HTTP.
public class ProductController {
    // private ProductRepository repository;  // Declaração de campo privado não utilizada (comentada).

    @Autowired  // Anotação que injeta automaticamente uma instância de ProductRepository.
    ProductRepository repository;  // Declaração de campo para acessar o repositório de produtos.

    @PostMapping("/product")  // Mapeia o método para lidar com solicitações POST na rota "/product".
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var producModel = new ProductModel();  // Cria uma instância de ProductModel.

        BeanUtils.copyProperties(productRecordDto, producModel);  // Copia as propriedades de productRecordDto para producModel.

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(producModel));  // Retorna uma resposta com status HTTP 201 (Created) e o produto salvo.
    }

    @GetMapping("/products")  // Mapeia o método para lidar com solicitações GET na rota "/products".
    public ResponseEntity<List<ProductModel>> getAllProduct() {
        // Retorna uma resposta HTTP com status OK (200) e o corpo contendo todos os produtos encontrados no repositório.
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/products/{id}")  // Mapeia o método para lidar com solicitações GET na rota "/products/{id}".
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        // Tenta encontrar um produto no repositório com o ID especificado.
        Optional<ProductModel> product0 = repository.findById(id);

        // Verifica se o produto não foi encontrado (a Optional está vazia).
        if (product0.isEmpty()) {
            // Retorna uma resposta HTTP com status NOT FOUND (404) e uma mensagem de erro no corpo.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        // Se o produto foi encontrado, retorna uma resposta HTTP com status OK (200) e o produto no corpo da resposta.
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    @PutMapping("/products/{id}")  // Mapeia o método para lidar com solicitações PUT na rota "/products/{id}".
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {
        // Tenta encontrar um produto no repositório com o ID especificado.
        Optional<ProductModel> product0 = repository.findById(id);

        // Verifica se o produto não foi encontrado (a Optional está vazia).
        if (product0.isEmpty()) {
            // Retorna uma resposta HTTP com status NOT FOUND (404) e uma mensagem de erro no corpo.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        // Obtém o produto encontrado do Optional.
        var productModel = product0.get();

        // Copia as propriedades do DTO recebido para o objeto do produto existente.
        BeanUtils.copyProperties(productRecordDto, productModel);

        // Retorna uma resposta HTTP com status OK (200) e o produto atualizado no corpo da resposta.
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(productModel));
    }

    @DeleteMapping("/products/{id}")  // Mapeia o método para lidar com solicitações DELETE na rota "/products/{id}".
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        // Tenta encontrar um produto no repositório com o ID especificado.
        Optional<ProductModel> product0 = repository.findById(id);

        // Verifica se o produto não foi encontrado (a Optional está vazia).
        if (product0.isEmpty()) {
            // Retorna uma resposta HTTP com status NOT FOUND (404) e uma mensagem de erro no corpo.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        // Se o produto foi encontrado, remove-o do repositório.
        repository.deleteById(id);

        // Retorna uma resposta HTTP com status NO CONTENT (204) para indicar que o produto foi excluído com sucesso.
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

