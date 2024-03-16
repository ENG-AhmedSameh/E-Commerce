package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.mappers.ProductMapper;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductServices {
    public List<ProductDto> getFirstOneHundredProducts(EntityManager em) {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getOneHundredsProducts(em);
        return ProductMapper.INSTANCE.toListDto(products);
    }
}
