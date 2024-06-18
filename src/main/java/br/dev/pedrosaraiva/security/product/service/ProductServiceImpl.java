package br.dev.pedrosaraiva.security.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.dev.pedrosaraiva.security.product.model.Product;
import br.dev.pedrosaraiva.security.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository _Repository;

    @Override
    public List<Product> listAll() {
        return _Repository.findAll();
    }

    @Override
    public Product create(Product product) throws Exception {
        if (product.getName() == null) {
            throw new Exception("Impossivel criar produto sem nome");

        }
        return _Repository.save(product);

    }

    @Override
    public Product update(Product product) throws Exception {
        Optional<Product> exisProduct = _Repository.findById(product.getId());

        if (exisProduct.isPresent()) {
            _Repository.deleteById(product.getId());
            return _Repository.save(product);
        }
        throw new Exception("Impossivel alterar produto pois o mesmo não existe");

    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Product> exisProduct = _Repository.findById(id);

        if (!exisProduct.isPresent()) {
            throw new Exception("Produto não cadastrado");
        }

        _Repository.deleteById(id);
    }

}
