package com.fpoly.demojava4.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(value = { "/admin/*", "/user/*" })
public class HandleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

//		user có đăng nhập chưa có vai trò đúng với url truy cập không?
//		Cần lấy thông từ cookie đã lưu trước đó

		String userId = null;
		String role = null;

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();

		if (cookies == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		for (Cookie cookie : cookies) {
			if ("userId".equals(cookie.getName())) {
				userId = cookie.getValue();
			}

			if ("role".equals(cookie.getName())) {
				role = cookie.getValue();
			}
		}

		if (userId == null || role == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String path = req.getRequestURI();
//		/Java4_LDB_Demo/admin/video

		if (path.contains("/admin/") && role.equals("user")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		if (path.contains("/user/") && role.equals("admin")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

//		khi phương thức này chạy
//		Controller mới được kích hoạt 
		chain.doFilter(request, response);
	}
}