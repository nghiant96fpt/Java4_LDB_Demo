package com.fpoly.demojava4.dao;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.demojava4.entities.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class VideoDAO {
	private EntityManager manager;

	public VideoDAO() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dbConnect");
		manager = entityManagerFactory.createEntityManager();
	}

	public void closeConnect() {
		manager.close();
	}

	public boolean insert(Video video) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.persist(video); // insert

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}

	public boolean update(Video video) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.merge(video); // update

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		}
	}

	public boolean delete(Video video) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.remove(video); // delete

			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
	}

	public Video findById(String id) {
		try {
			Video video = manager.find(Video.class, id);

			return video;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Video> findAll() {
		List<Video> videos = new ArrayList<Video>();

		try {
			String sqlNative = "SELECT * FROM videos";

//			String sqlJPA = "SELECT * FROM Video v WHERE v.id";

			Query query = manager.createNativeQuery(sqlNative, Video.class);

			videos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return videos;
	}

	public List<Video> findByName(String name) {
		List<Video> videos = new ArrayList<Video>();

		try {

			String[] keyword = name.trim().split(" ");
//			AI sssa,jd Agent as n8n

//			=> ["AI", "Agent", "n8n"]

//			SELECT * FROM videos WHERE title LIKE '%AI%'

			String sqlNative = "SELECT * FROM videos WHERE title LIKE '%" + keyword[0] + "%'";

			if (keyword.length > 1) {
				for (int index = 1; index < keyword.length; index++) {
					sqlNative += " OR title LIKE '%" + keyword[index] + "%'";
				}
			}

			System.out.println(sqlNative);

			Query query = manager.createNativeQuery(sqlNative, Video.class);

			videos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return videos;
	}

	public void addViews(String id) {
		try {
			Video video = this.findById(id);
//			tăng lượt xem lên 1
			video.setViews(video.getViews() + 1);

			this.update(video);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
