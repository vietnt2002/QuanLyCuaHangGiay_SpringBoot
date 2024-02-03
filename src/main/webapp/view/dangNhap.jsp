<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-
    Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <style>
        .form-control{
            width: 400px;
            margin: auto;
            margin-top: 20px;
        }
        .btn {
            margin-top: 15px;
        }
    </style>
</head>
<body class="container">
    <h3 class="text-center" style="margin-top: 25px">Đăng nhập</h3>
    <h3 class="text-center" style="margin-top: 25px">Xin chào: ${name}</h3>
    <c:if test="${check == false}">
        <div class="alert alert-warning d-flex align-items-center" role="alert" style="position: relative; width: 400px; margin: auto">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <div>
                    Tên tài khoản hoặc mật khẩu không đúng
                        <a href="/store/dang-nhap-view"><i class="bi bi-x-octagon-fill" style="position: absolute; top: 3px; right: 5px"></i></a>
            </div>
        </div>
    </c:if>
    <form method="post" action="/store/dang-nhap">
        <input type="text" class="form-control" placeholder="Email" name="email">
        <input type="password" class="form-control" placeholder="Password" name="password">
        <div class="text-center">
            <button type="submit" class="btn btn-primary">Đăng nhập</button>
        </div>
    </form>
</body>
</html>