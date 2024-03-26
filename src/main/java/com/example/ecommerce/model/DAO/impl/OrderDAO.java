package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.OrderDAOInt;
import com.example.ecommerce.model.entities.Order;
import com.example.ecommerce.model.entities.OrderItem;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.entitiesID.OrderItemId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class OrderDAO implements OrderDAOInt {

    @Override
    public void save(Order order, EntityManager em) {
        em.persist(order);
    }

    @Override
    public Optional<Order> get(Integer id, EntityManager em) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    @Override
    public void update(Order order, EntityManager em) {
        em.persist(order);
    }

    @Override
    public void delete(Order order, EntityManager em) {
        em.remove(order);
    }

//    public List<Order> getAllOrders(EntityManager em) {
//        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
//    }

    public List<Order> getAllOrders(EntityManager em) {
        return em.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems", Order.class)
                .getResultList();
    }


//    public List<OrderItem> getOrderItemsByOrderId(Integer orderId, EntityManager em) {
//        return em.createQuery(
//                        "SELECT oi FROM OrderItem oi WHERE oi.id.orderId = :orderId",
//                        OrderItem.class
//                )
//                .setParameter("orderId", orderId)
//                .getResultList();
//    }
}

