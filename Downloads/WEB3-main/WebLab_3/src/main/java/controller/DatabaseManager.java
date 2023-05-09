package controller;

import model.Point;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseManager implements model.DatabaseManager {
    public DatabaseManager instance;

    private final EntityManager entityManager = Persistence.createEntityManagerFactory("Point").createEntityManager();

    public DatabaseManager(){

    }
    public DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    @Override
    public List<Point> getCollectionFromDataBase() {
        return entityManager.createQuery("SELECT point FROM points point", Point.class).getResultList();
    }

    @Override
    public void addPoint(Point point) {
        if (point.getX() == null || point.getY() == null || point.getR() == null || point.getResult() == null)
            return;
        entityManager.getTransaction().begin();
        entityManager.persist(point);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeAllPoints() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from points").executeUpdate();
        entityManager.getTransaction().commit();
    }
}
