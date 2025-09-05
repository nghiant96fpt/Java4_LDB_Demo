<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="col-6 offset-3">
			<form method="POST"
			action="${pageContext.request.contextPath}/admin/video-form">
				<div class="mb-3">
				  <label class="form-label">Tiêu đề</label>
				  <input value="${title}" name="title" type="text" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label class="form-label">Mô tả</label>
				  <textarea name="desc" class="form-control"rows="3">${desc}</textarea>
				</div>
				
				<div class="mb-3">
				  <label class="form-label">URL Ảnh</label>
				  <input value="${urlImage}" name="urlImage" type="text" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label class="form-label">URL Video</label>
				  <input value="${urlVideo}" name="urlVideo" type="text" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label class="form-label">Trạng thái</label>
				  <div class="form-check">
					  <input name="status" value="1" checked class="form-check-input" type="radio" id="flexRadioDefault1">
					  <label class="form-check-label" for="flexRadioDefault1">
					    Hiển thị
					  </label>
					</div>
					<div class="form-check">
					  <input name="status" value="0" class="form-check-input" type="radio" id="flexRadioDefault2">
					  <label class="form-check-label" for="flexRadioDefault2">
					   	Ẩn
					  </label>
					</div>
				</div>
				
				<input type="submit" class="btn btn-primary" value="Thêm video"/>
			</form>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>