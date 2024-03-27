package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Category;
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

    public List<ProductDto> getProductsAllExist() {
        try{
            return Database.doInTransaction(em -> {
                ProductDAO productDAO = new ProductDAO();

                List<Product> products = productDAO.getProductsAllExist(em);
                return ProductMapper.INSTANCE.toListDto(products);
            });
        }catch (Exception e) {
            System.err.println("Error in getProductsAllExist Method: " + e.getMessage());
            e.printStackTrace();
             return null;
        }

    }

    public Product getProductById(int productId) {
        return Database.doInTransaction(em -> {
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId, em);
            return product;
        });
    }
//    public Product getProductById(int productId) {
//        return Database.doInTransaction(em -> {
//            ProductDAO productDAO = new ProductDAO();
//
//            Product product = productDAO.getProductById(productId, em);
//            return product;
//        });
//    }

    //

    public void updateProduct(Product product) {
        try {
            // Ensure the referenced Category is managed or saved
            Category category = product.getCategory();
            if (category.getId() == null) {
                // Category is transient, save it first
                saveCategory(category);
            }

            // Now update the product
            Database.doInTransactionWithoutResult(em -> {
                new ProductDAO().editProduct(em, product);
            });

            System.out.println("Product updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating product: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveCategory(Category category) {
        try {
            Database.doInTransactionWithoutResult(em -> {
                em.persist(category);
            });
            System.out.println("Category saved successfully.");
        } catch (Exception e) {
            System.err.println("Error saving category: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteProduct(Product product) {
        try {
            Database.doInTransactionWithoutResult(em -> {
                new ProductDAO().deleteProduct(em, product);
            });
            System.out.println("Product deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting product: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ProductDto getProductDTOById(int id) {
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

    public List<ProductDto> getTenProducts(int startId) {
        return Database.doInTransaction(em -> {
            ProductDAO productDAO = new ProductDAO();

            List<Product> products = productDAO.getTenProducts(em, startId); // Pass the startId to your DAO method

            return ProductMapper.INSTANCE.toListDto(products);
        });
    }



}

