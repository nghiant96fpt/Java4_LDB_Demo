package com.fpoly.demojava4.dao;

import com.fpoly.demojava4.entities.User;

import jakarta.persistence.Query;

public class UserDAO extends DAO {

	public UserDAO() {
		super();
	}

	public boolean insert(User user) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.persist(user); // insert

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}

	public boolean update(User user) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.merge(user); // update

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		}
	}

	public boolean delete(User user) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.remove(user); // delete

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}

	public User findById(String id) {
		try {
			User user = manager.find(User.class, id);

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkEmailExist(String email) {
		try {
			String sql = "SELECT * FROM users WHERE email=?1";

			Query query = manager.createNativeQuery(sql, User.class);
			query.setParameter(1, email);

			return query.getSingleResult() != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public User login(String email, String password) {
		try {
			String sql = "SELECT * FROM users WHERE email=?1 AND password=?2";

			Query query = manager.createNativeQuery(sql, User.class);
			query.setParameter(1, email);
			query.setParameter(2, password);

			return (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
