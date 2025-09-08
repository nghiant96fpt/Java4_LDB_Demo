package com.fpoly.demojava4.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demojava4.dao.VideoDAO;
import com.fpoly.demojava4.entities.Video;

@WebServlet("/")
public class HomeController extends HttpServlet {

//	GET
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		dòng này luôn được viết cuối phương thức 
//		Năm trước dấu ngoặc đóng của phương thức 

		String q = req.getParameter("q");

		VideoDAO videoDAO = new VideoDAO();

		if (q == null || q.isBlank()) {
//			Không có tìm kiếm và hiển thị tất cả video trong db
			List<Video> videos = videoDAO.findAll();

			req.setAttribute("videos", videos);
		} else {
//			Có tìm kiếm video theo tên

			List<Video> videos = videoDAO.findByName(q);
			req.setAttribute("videos", videos);
			req.setAttribute("q", q);
		}

		videoDAO.closeConnect();

		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
