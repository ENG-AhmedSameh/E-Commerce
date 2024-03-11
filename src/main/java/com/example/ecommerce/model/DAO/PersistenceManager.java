package com.example.ecommerce.model.DAO;

//import com.example.ecommerce.model.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class PersistenceManager {

    private static EntityManagerFactory entityManagerFactory;

    static {

        String puName = "pu-name";

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");

//         entityManagerFactory = new HibernatePersistenceProvider()
//                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}

