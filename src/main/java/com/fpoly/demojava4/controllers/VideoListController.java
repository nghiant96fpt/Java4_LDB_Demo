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

@WebServlet("/admin/video-list")
public class VideoListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VideoDAO videoDAO = new VideoDAO();

		List<Video> videos = videoDAO.findAll();

		req.setAttribute("videos", videos);

		videoDAO.closeConnect();

		req.getRequestDispatcher("/admin-video-list.jsp").forward(req, resp);
	}
}
