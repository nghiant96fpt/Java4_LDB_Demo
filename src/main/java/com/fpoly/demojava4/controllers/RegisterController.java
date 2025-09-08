package com.fpoly.demojava4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demojava4.dao.UserDAO;
import com.fpoly.demojava4.entities.User;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

//		Lấy dữ liệu từ form 
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");

//		Gửi lại dữ liệu qua form 
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		req.setAttribute("name", name);

//		Kiểm tra lỗi 
		boolean hasError = false;
		if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$")) {
			req.setAttribute("errEmail", "Email không đúng định dạng");
			hasError = true;
		}

		if (password.length() < 6) {
			req.setAttribute("errPassword", "Mật khẩu tối thiểu 6 ký tự");
			hasError = true;
		}

		if (name.isBlank()) {
			req.setAttribute("errName", "Họ và tên không bỏ trống");
			hasError = true;
		}

		if (!hasError) {
			UserDAO userDAO = new UserDAO();
			boolean checkEmail = userDAO.checkEmailExist(email);

			if (checkEmail) {
				req.setAttribute("errEmail", "Email đã tồn tại");
			} else {
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				user.setFullName(name);
				user.setAdmin(false);

				userDAO.insert(user);
				userDAO.closeConnect();
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}

			userDAO.closeConnect();
		}

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
}
