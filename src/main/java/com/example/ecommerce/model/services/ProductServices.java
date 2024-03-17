package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.mappers.ProductMapper;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductServices {
    public List<ProductDto> getFirstTenProducts(EntityManager em) {
        ProductDAO productDAO = new ProductDAO();
        System.out.println("ProductServices getFirstTenProducts() called"  );

        List<Product> products = productDAO.getTenProducts(em);
        System.out.println("getFirstTenProducts() end: " +products.size());

        return ProductMapper.INSTANCE.toListDto(products);
    }
}
