package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.CategoryDAO;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Category;
import com.example.ecommerce.model.mappers.ProductMapper;

import java.util.*;

public class CategoryServices {


    public Set<ProductDto> getProductsOfCategoryById(int id){
        return Database.doInTransaction(em->{
            CategoryDAO categoryDAO = new CategoryDAO();
            Optional <Category> category  = categoryDAO.get(id ,em);
            Set<ProductDto> products = new HashSet<>();

            category.ifPresentOrElse(

                    (value) -> {
                        products.addAll(ProductMapper.INSTANCE.toSetDto(value.getProducts()));
                    },
                    () -> {}
            );
            return products;
        });
    }


}
