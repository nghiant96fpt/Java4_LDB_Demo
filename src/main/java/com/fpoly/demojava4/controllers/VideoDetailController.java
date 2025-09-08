package com.fpoly.demojava4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demojava4.dao.LikeDAO;
import com.fpoly.demojava4.dao.VideoDAO;
import com.fpoly.demojava4.entities.Video;

@WebServlet("/video-detail")
public class VideoDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		if (id != null) {

			VideoDAO videoDAO = new VideoDAO();

			videoDAO.addViews(id);

			Video video = videoDAO.findById(id);

			videoDAO.closeConnect();

			if (video == null) {
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			}

			req.setAttribute("video", video);
		}

		Cookie[] cookies = req.getCookies();

		if (cookies == null) {
			req.setAttribute("isLogin", false);
		} else {
			String userId = null;
			String role = null;

			for (Cookie cookie : cookies) {
				if ("userId".equals(cookie.getName())) {
					userId = cookie.getValue();
				}

				if ("role".equals(cookie.getName())) {
					role = cookie.getValue();
				}
			}

			if (userId == null || role == null) {
				req.setAttribute("isLogin", false);
			} else {
				req.setAttribute("isLogin", true);

				LikeDAO likeDAO = new LikeDAO();

				boolean checkLike = likeDAO.checkUserLikeVideo(Integer.parseInt(userId), Integer.parseInt(id));

				req.setAttribute("isLike", checkLike);
			}
		}

		req.getRequestDispatcher("/video-detail.jsp").forward(req, resp);
	}
}
