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
<h3 class="text-center mt-5">Danh sách màu sắc</h3>
<a href="/mau-sac/create"><button class="btn btn-success float-end mt-3">+ Thêm</button></a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Mã</th>
        <th scope="col">Tên</th>
        <th scope="col">Trạng thái</th>
        <th scope="col" colspan="2">Thao tác</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach varStatus="i" items="${pageMauSac.content}" var="mauSac">
            <tr>
                <td>${mauSac.id}</td>
                <td>${mauSac.ma}</td>
                <td>${mauSac.ten}</td>
                <td>${mauSac.trangThai==1?"Hoạt động" : "Không hoạt động"}</td>
                <td>
                    <a href="/mau-sac/edit/${mauSac.id}"><button class="btn btn-warning">Sửa</button></a>
                    <a href="/mau-sac/delete/${mauSac.id}"><button class="btn btn-danger">Xóa</button></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div class="float-end">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:if test="${pageMauSac.number > 0}">
                <li class="page-item"><a class="page-link" href="/mau-sac/show?page=${pageMauSac.number-1}">Previous</a></li>
            </c:if>
            <li class="page-item"><a class="page-link">${pageMauSac.number+1}</a></li>

            <c:if test="${pageMauSac.number+1 < pageMauSac.totalPages}">
                <li class="page-item"><a class="page-link" href="/mau-sac/show?page=${pageMauSac.number+1}">Next</a></li>
            </c:if>

        </ul>
    </nav>
</div>
</body>
</html>