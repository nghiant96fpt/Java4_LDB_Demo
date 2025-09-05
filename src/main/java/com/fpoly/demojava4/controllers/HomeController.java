package com.fpoly.demojava4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeController extends HttpServlet {

//	GET
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		dòng này luôn được viết cuối phương thức 
//		Năm trước dấu ngoặc đóng của phương thức 

		req.setAttribute("msg", "Pass môn Java 4");

		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
