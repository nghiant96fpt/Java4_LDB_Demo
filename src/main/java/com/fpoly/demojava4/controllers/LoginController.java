package com.fpoly.demojava4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demojava4.dao.UserDAO;
import com.fpoly.demojava4.entities.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		req.setAttribute("email", email);
		req.setAttribute("password", password);

		boolean hasError = false;

		if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$")) {
			req.setAttribute("errEmail", "Email không đúng định dạng");
			hasError = true;
		}

		if (password.length() == 0) {
			req.setAttribute("errPassword", "Mật khẩu không được bỏ trống");
			hasError = true;
		}

		if (!hasError) {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.login(email, password);
			userDAO.closeConnect();
			if (user == null) {
				req.setAttribute("errPassword", "Sai email hoặc mật khẩu");
			} else {
				Cookie cookieUserId = new Cookie("userId", String.valueOf(user.getId()));
				cookieUserId.setMaxAge(60 * 60 * 24 * 10); // giây

				Cookie cookieRole = new Cookie("role", String.valueOf(user.isAdmin() ? "admin" : "user"));
				cookieRole.setMaxAge(60 * 60 * 24 * 10);

				resp.addCookie(cookieUserId);
				resp.addCookie(cookieRole);

				if (user.isAdmin()) {
					resp.sendRedirect(req.getContextPath() + "/admin/video-list");
				} else {
					resp.sendRedirect(req.getContextPath() + "/");
				}

				return;
			}
		}

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
