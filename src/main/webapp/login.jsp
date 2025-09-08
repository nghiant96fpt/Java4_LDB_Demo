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
			action="${pageContext.request.contextPath}/login">
				<div class="mb-3">
				  <label class="form-label">Email</label>
				  <input name="email" type="text" class="form-control">
				  <small class="text-danger">${errEmail}</small>
				</div>
				
				<div class="mb-3">
				  <label class="form-label">Mật khẩu</label>
				  <input name="password" type="password" class="form-control">
				  <small class="text-danger">${errPassword}</small>
				</div>
				
				<input type="submit" class="btn btn-primary" value="Đăng nhập"/>
			</form>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>