package br.dev.pedrosaraiva.security.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.pedrosaraiva.security.product.model.Product;
import br.dev.pedrosaraiva.security.product.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl _Service;

    @PreAuthorize("hasRole('PRODUCT_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) throws Exception {

        return new ResponseEntity<Product>(_Service.create(product), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PRODUCT_uPDATE')")
    @PostMapping("/update")
    public ResponseEntity<Product> update(@RequestBody Product product) throws Exception {

        return new ResponseEntity<Product>(_Service.update(product), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @GetMapping("/list")
    public ResponseEntity<List<Product>> listAll(@RequestBody Product product) throws Exception {

        return new ResponseEntity<List<Product>>(_Service.listAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Long id) throws Exception {

        _Service.delete(id);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

}
