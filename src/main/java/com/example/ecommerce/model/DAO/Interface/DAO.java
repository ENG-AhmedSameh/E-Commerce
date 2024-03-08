package com.example.ecommerce.model.DAO.Interface;

import java.util.List;

interface DAO  <T> {

     boolean insert(T t);
     T get( long id);
     boolean  update(T t);
     boolean delete(T t);
}
