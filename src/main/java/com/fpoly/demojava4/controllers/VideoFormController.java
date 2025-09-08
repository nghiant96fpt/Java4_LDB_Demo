package com.fpoly.demojava4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.demojava4.dao.VideoDAO;
import com.fpoly.demojava4.entities.Video;

@WebServlet("/admin/video-form")
public class VideoFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		if (id != null) {
//			chức năng sửa 

			VideoDAO videoDAO = new VideoDAO();

			Video video = videoDAO.findById(id);

			if (video != null) {
//				Thực hiện gửi dữ liệu 

				req.setAttribute("id", video.getId());
				req.setAttribute("title", video.getTitle());
				req.setAttribute("desc", video.getDesc());
				req.setAttribute("urlImage", video.getPoster());
				req.setAttribute("urlVideo", video.getUrl());
				req.setAttribute("status", video.isActive() ? "1" : "0");
			} else {
				resp.sendRedirect(req.getContextPath() + "/admin/video-list");
				return;
			}

		}

//		Chức năng thêm 

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
		String id = req.getParameter("id");

//		Gửi dữ liệu đã nhập từ các ô input để hiển thị lại 
		req.setAttribute("title", title.trim());
		req.setAttribute("desc", desc.trim());
		req.setAttribute("urlImage", urlImage);
		req.setAttribute("urlVideo", urlVideo);
		req.setAttribute("status", status);
		req.setAttribute("id", id);

//		Thực hiện kiểm tra lỗi của form 
//		- tiêu đề không rỗng
//		- mô tả có ít nhất 10 ký tự và tối đa 500 ký tự
//		- Url Ảnh phải đúng định dạng (https://.....)
//		- Url Video phải đúng định dạng url embed của youtube 
//		"                " => ""

		boolean hasError = false;

		if (title.isBlank()) {
			req.setAttribute("errTitle", "Tiêu đề không được bỏ trống");
			hasError = true;
		}
//		"               "
		if (desc.trim().length() < 10 || desc.trim().length() > 500) {
			req.setAttribute("errDesc", "Mô tả có độ dài từ 10 -> 500 ký tự");
			hasError = true;
		}

		if (!urlImage.matches("^https?:\\/\\/[^\\s/$.?#].[^\\s]*$")) {
			req.setAttribute("errImage", "Đường đẫn ảnh không đúng định dạng");
			hasError = true;
		}

		if (!urlVideo.matches("^https?:\\/\\/(www\\.)?youtube\\.com\\/embed\\/[a-zA-Z0-9_-]{11}$")) {
			req.setAttribute("errVideo", "Đường đẫn embed không đúng định dạng");
			hasError = true;
		}

//		Kiểm tra có lỗi không. Nếu không lỗi thực hiện lưu db 

		if (!hasError) {
			VideoDAO videoDAO = new VideoDAO();

			Video video = new Video();
			video.setTitle(title);
			video.setDesc(desc);
			video.setPoster(urlImage);
			video.setUrl(urlVideo);
			video.setActive(status.equals("1"));
			video.setViews(0);

			if (id != null) {
				Video videoDB = videoDAO.findById(id);
				video.setViews(videoDB.getViews());

				video.setId(Integer.parseInt(id));

				videoDAO.update(video);
			} else {
				videoDAO.insert(video);
			}

			videoDAO.closeConnect();
			resp.sendRedirect(req.getContextPath() + "/admin/video-list");
			return;
		}

		req.getRequestDispatcher("/admin-video-form.jsp").forward(req, resp);
	}
}
