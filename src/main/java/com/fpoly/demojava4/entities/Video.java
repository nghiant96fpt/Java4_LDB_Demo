package com.fpoly.demojava4.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
	@Id // Khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng 1 đơn vị
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "description", nullable = false, columnDefinition = "NVARCHAR (2000)")
	private String desc;

	@Column(name = "poster", length = 255, nullable = false)
	private String poster;

	@Column(name = "title", nullable = false, columnDefinition = "NVARCHAR (255)")
	private String title;

	@Column(name = "views", nullable = false)
	private int views;

	@Column(name = "url", nullable = false, columnDefinition = "NVARCHAR (255)")
	private String url;

	@OneToMany(mappedBy = "video", fetch = FetchType.EAGER)
	private List<Like> likes;

	@OneToMany(mappedBy = "video")
	private List<Share> shares;

	public Video() {
		super();
	}

	public Video(int id, boolean active, String desc, String poster, String title, int views, String url,
			List<Like> likes, List<Share> shares) {
		super();
		this.id = id;
		this.active = active;
		this.desc = desc;
		this.poster = poster;
		this.title = title;
		this.views = views;
		this.url = url;
		this.likes = likes;
		this.shares = shares;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setVideos(List<Like> likes) {
		this.likes = likes;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
