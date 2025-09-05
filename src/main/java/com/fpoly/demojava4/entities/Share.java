package com.fpoly.demojava4.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shares")
public class Share {
	@Id // Khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng 1 đơn vị
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "emails", nullable = false, length = 255)
	private String emails;

	@Column(name = "share_date", nullable = false)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "video_id")
	private Video video;

	public Share() {
		super();
	}

	public Share(int id, String emails, Date date, User user, Video video) {
		super();
		this.id = id;
		this.emails = emails;
		this.date = date;
		this.user = user;
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}
