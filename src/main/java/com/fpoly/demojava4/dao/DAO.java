package com.fpoly.demojava4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAO {
	public EntityManager manager;

	public DAO() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dbConnect");
		manager = entityManagerFactory.createEntityManager();
	}

	public void closeConnect() {
		manager.close();
	}
}
