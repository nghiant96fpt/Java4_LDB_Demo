package com.fpoly.demojava4.dao;

import com.fpoly.demojava4.entities.Like;

import jakarta.persistence.Query;

public class LikeDAO extends DAO {

	public LikeDAO() {
		super();
	}

	public boolean insert(Like like) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.persist(like); // insert

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}

	public boolean update(Like like) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.merge(like); // update

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		}
	}

	public boolean delete(Like like) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.remove(like); // delete

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}

	public boolean checkUserLikeVideo(int userId, int videoId) {
		try {
			String sql = "SELECT * FROM favorites WHERE user_id=?1 AND video_id=?2";

			Query query = manager.createNativeQuery(sql, Like.class);
			query.setParameter(1, userId);
			query.setParameter(2, videoId);

			return query.getSingleResult() != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Like findById(String id) {
		try {
			Like like = manager.find(Like.class, id);

			return like;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Like findByUserAndVideo(int userId, int videoId) {
		try {
			String sql = "SELECT * FROM favorites WHERE user_id=?1 AND video_id=?2";

			Query query = manager.createNativeQuery(sql, Like.class);
			query.setParameter(1, userId);
			query.setParameter(2, videoId);

			return (Like) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
