package br.dev.pedrosaraiva.security.product.service;

import java.util.List;

import br.dev.pedrosaraiva.security.product.model.Product;

public interface ProductService {

    List<Product> listAll();
    Product create(Product product) throws Exception;
    Product update(Product product)throws Exception;
    void delete (Long id )throws Exception;

}
