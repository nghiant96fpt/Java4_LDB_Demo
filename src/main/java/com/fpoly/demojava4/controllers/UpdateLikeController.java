package com.fpoly.demojava4.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demojava4.dao.LikeDAO;
import com.fpoly.demojava4.dao.UserDAO;
import com.fpoly.demojava4.dao.VideoDAO;
import com.fpoly.demojava4.entities.Like;
import com.fpoly.demojava4.entities.User;
import com.fpoly.demojava4.entities.Video;

@WebServlet("/user/update-like")
public class UpdateLikeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String videoId = req.getParameter("videoId");

//		Kiểm tra user đã yêu thích video chưa?
//		Nếu đã yêu thích thực hiện xoá dòng yêu thích ở db
//		nếu chưa thì insert mới 

		Cookie[] cookies = req.getCookies();

		String userId = null;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userId = cookie.getValue();
				break;
			}
		}

		LikeDAO likeDAO = new LikeDAO();

		Like like = likeDAO.findByUserAndVideo(Integer.parseInt(userId), Integer.parseInt(videoId));

		if (like == null) {
//			Chưa yêu thích => insert 

			UserDAO userDAO = new UserDAO();
			User user = userDAO.findById(userId);
			userDAO.closeConnect();

			VideoDAO videoDAO = new VideoDAO();
			Video video = videoDAO.findById(videoId);
			videoDAO.closeConnect();

			Like likeNewDB = new Like();
			likeNewDB.setDate(new Date());
			likeNewDB.setUser(user);
			likeNewDB.setVideo(video);

			likeDAO.insert(likeNewDB);
		} else {
//			Đã yêu thích => delete 

			likeDAO.delete(like);
		}

		likeDAO.closeConnect();

		resp.sendRedirect(req.getContextPath() + "/video-detail?id=" + videoId);
	}
}
