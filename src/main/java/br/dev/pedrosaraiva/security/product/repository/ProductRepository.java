package br.dev.pedrosaraiva.security.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.pedrosaraiva.security.product.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
