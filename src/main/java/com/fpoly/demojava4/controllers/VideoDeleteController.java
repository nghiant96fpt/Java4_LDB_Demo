package com.fpoly.demojava4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demojava4.dao.VideoDAO;
import com.fpoly.demojava4.entities.Video;

@WebServlet("/admin/video-delete")
public class VideoDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Cần có id video cần xoá 
		String id = req.getParameter("id");
		VideoDAO videoDAO = new VideoDAO();

		Video video = videoDAO.findById(id);

		videoDAO.delete(video);

		videoDAO.closeConnect();
		resp.sendRedirect(req.getContextPath() + "/admin/video-list");
	}
}
