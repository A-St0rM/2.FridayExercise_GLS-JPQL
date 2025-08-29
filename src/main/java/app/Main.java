package app;

import app.DAO.ParcelDAO;
import app.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = HibernateConfig.getEntityManagerFactory();

        ParcelDAO parcelDAO = new ParcelDAO(emf);

        emf.close();
    }
}