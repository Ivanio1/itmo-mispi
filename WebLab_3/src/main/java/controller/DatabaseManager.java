package controller;

import model.Point;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseManager {
	public static DatabaseManager instance;
	
	private final EntityManager entityManager;
	
	private DatabaseManager() {
		entityManager = Persistence.createEntityManagerFactory("Point").createEntityManager();
	}
	
	public static DatabaseManager getInstance() {
		if (instance == null)
			instance = new DatabaseManager();
		return instance;
	}
	
	public List<Point> getCollectionFromDataBase() {
		return entityManager.createQuery("SELECT point FROM points point", Point.class).getResultList();
	}
	
	public void addPoint(Point point) {
		if (point.getX() == null || point.getY() == null || point.getR() == null || point.getResult() == null)
			return;
		entityManager.getTransaction().begin();
		entityManager.persist(point);
		entityManager.getTransaction().commit();
	}
	
	public void removeAllPoints() {
		entityManager.getTransaction().begin();
		entityManager.createQuery("delete from points").executeUpdate();
		entityManager.getTransaction().commit();
	}
}
