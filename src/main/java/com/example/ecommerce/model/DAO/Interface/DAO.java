package com.example.ecommerce.model.DAO.Interface;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

interface DAO  <T> {

     void save(T t, EntityManager em);
     Optional<T> get(long id, EntityManager em);
     void  update(T t, EntityManager em);
     void delete(T t, EntityManager em);
}
