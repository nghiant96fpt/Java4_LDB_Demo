package com.fpoly.demojava4.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id // Khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng 1 đơn vị
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "email", length = 100, nullable = false)
	private String email;

//	columnDefinition dùng với trường hợp cột là nvarchar 
	@Column(name = "full_name", nullable = false, columnDefinition = "NVARCHAR (100)")
	private String fullName;

	@Column(name = "admin", nullable = false)
	private boolean admin;

	@OneToMany(mappedBy = "user")
	private List<Like> likes;

	@OneToMany(mappedBy = "user")
	private List<Share> shares;

	public User() {
		super();
	}

	public User(int id, String password, String email, String fullName, boolean admin, List<Like> likes,
			List<Share> shares) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.admin = admin;
		this.likes = likes;
		this.shares = shares;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}