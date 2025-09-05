package com.fpoly.demojava4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/video-form")
public class VideoFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/admin-video-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		encode tiếng việt 
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

//		Lấy dữ liệu từ các ô input 
		String title = req.getParameter("title");
		String desc = req.getParameter("desc");
		String urlImage = req.getParameter("urlImage");
		String urlVideo = req.getParameter("urlVideo");
		String status = req.getParameter("status");

//		Gửi dữ liệu đã nhập từ các ô input để hiển thị lại 
		req.setAttribute("title", title);
		req.setAttribute("desc", desc);
		req.setAttribute("urlImage", urlImage);
		req.setAttribute("urlVideo", urlVideo);
		req.setAttribute("status", status);

		req.getRequestDispatcher("/admin-video-form.jsp").forward(req, resp);
	}
}
