<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body class="container">
<h3 class="text-center mt-5">Danh sách khách hàng</h3>
<a href="/khach-hang/create"><button class="btn btn-success float-end mt-3">+ Thêm</button></a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Mã</th>
        <th scope="col">Tên</th>
        <th scope="col">SDT</th>
        <th scope="col">Trạng thái</th>
        <th scope="col">Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach varStatus="i" items="${listKhachHang}" var="khachHang">
        <tr>
            <td>${khachHang.id}</td>
            <td>${khachHang.ma}</td>
            <td>${khachHang.ten}</td>
            <td>${khachHang.sdt}</td>
            <td>${khachHang.trangThai}</td>
            <td>
                <a href="/khach-hang/edit/${khachHang.id}"><button class="btn btn-warning">Sửa</button></a>
                <a href="/khach-hang/delete/${khachHang.id}"><button class="btn btn-danger">Xóa</button></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>