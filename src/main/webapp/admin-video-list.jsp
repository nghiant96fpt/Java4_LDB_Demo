<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Tiêu đề</th>
		      <th scope="col">Mô tả</th>
		      <th scope="col">Ảnh</th>
		      <th scope="col">Video</th>
		      <th scope="col">Trạng thái</th>
		      <th scope="col">Hành động</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${videos}" var="item">
		  		<tr>
			      <th>${item.id}</th>
			      <th>${item.title}</th>
			      <th>${item.desc}</th>
			      <th>
			      	<img src="${item.poster}" style="width:100px; height:100px"/>
			      </th>
			      <th>
			      	<iframe width="200" height="150" src="${item.url}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
			      </th>
			      <th>${item.active ? 'Hiển thị' : 'Ẩn'}</th>
			      <th>
			      	<button class="btn btn-warning">Sửa</button>
			      	<form method="POST"
			      		action="${pageContext.request.contextPath}/admin/video-delete">
			      		<input name="id" value="${item.id}" type="hidden"/>
			      		<input type="submit" value="Xoá" class="btn btn-danger"/>
			      	</form>
			      </th>
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>