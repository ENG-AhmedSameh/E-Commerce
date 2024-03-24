package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.mappers.ProductMapper;

import java.util.List;
import java.util.Optional;

public class ProductServices {
    public List<ProductDto> getFirstTenProducts() {
        return Database.doInTransaction(em -> {
            ProductDAO productDAO = new ProductDAO();

            List<Product> products = productDAO.getTenProducts(em);

            return ProductMapper.INSTANCE.toListDto(products);
        });
    }

    public List<String> getProductImagesByProductId(int productId) {
        return Database.doInTransaction(em -> new ProductDAO().getProductImagesByProductId(productId, em));
    }

    public List<ProductDto> getProductsAll() {
        return Database.doInTransaction(em -> {
            ProductDAO productDAO = new ProductDAO();

            List<Product> products = productDAO.getProductsAll(em);
            return ProductMapper.INSTANCE.toListDto(products);
        });
    }


    public ProductDto getProductById(int id) {
        return Database.doInTransaction(em -> {
            ProductDAO productDAO = new ProductDAO();

            Optional<Product> productOptional = productDAO.get(id, em);
            if (productOptional.isEmpty()) {
                return null;
            }
            Product product = productOptional.get();
            return ProductMapper.INSTANCE.toDto(product);
        });
    }
}

