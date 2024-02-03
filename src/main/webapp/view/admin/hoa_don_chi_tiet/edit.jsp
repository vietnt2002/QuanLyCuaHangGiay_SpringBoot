<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
<h3 class="text-center mt-5 mb-5">Thêm mới hóa đơn chi tiết</h3>
<sf:form method="post" action="/hoa-don-chi-tiet/update/${hoaDonChiTiet.id}" modelAttribute="hoaDonChiTiet">
    <div class="input-group mb-3">
        <span class="input-group-text">ID: </span>
        <sf:input type="text" class="form-control" name="id" path="id" />
    </div>
    <sf:errors path="id" cssClass="text-danger" />

    <div class="input-group mb-3">
        <span class="input-group-text">Hóa đơn: </span>
        <sf:select class="form-select" aria-label="Default select example" name="idHD" path="idHD">
            <c:forEach items="${listHoaDon}" var="hoaDon">
                <option value="${hoaDon.id}"
                        <c:if test="${hoaDon.id == hoaDonChiTiet.idHD.id}">selected</c:if>> ${hoaDon.id}
                </option>
            </c:forEach>
        </sf:select>
    </div>
    <sf:errors path="idHD" cssClass="text-danger" />

    <div class="input-group mb-3">
        <span class="input-group-text">Chi tiết sản phẩm: </span>
        <sf:select class="form-select" aria-label="Default select example" name="idCTSP" path="idCTSP">
            <c:forEach items="${listChiTietSanPham}" var="chiTietSanPham">
                <option value="${chiTietSanPham.id}"
                        <c:if test="${chiTietSanPham.id == hoaDonChiTiet.idCTSP.id}">selected</c:if>> ${chiTietSanPham.idSanPham.ten}
                </option>
            </c:forEach>
        </sf:select>
    </div>
    <sf:errors path="idCTSP" cssClass="text-danger" />

    <div class="input-group mb-3">
        <span class="input-group-text">Số lượng: </span>
        <sf:input type="number" class="form-control" name="soLuong" path="soLuong" />
    </div>
    <sf:errors path="soLuong" cssClass="text-danger" />

    <div class="input-group mb-3">
        <span class="input-group-text">Đơn giá: </span>
        <sf:input type="number" class="form-control" name="donGia" path="donGia" />
    </div>
    <sf:errors path="donGia" cssClass="text-danger" />

    <div class="input-group mb-3">
        <span class="input-group-text">Thời gian: </span>
        <sf:input type="date" class="form-control" name="thoiGian" path="thoiGian" />
    </div>
    <sf:errors path="thoiGian" cssClass="text-danger" />

    <div class="input-group mb-3">
        <span class="input-group-text">Trạng thái: </span>
        <sf:input type="number" class="form-control" name="trangThai" path="trangThai" />
    </div>
    <sf:errors path="trangThai" cssClass="text-danger" />
    <div class="text-center mt-3">
        <button type="submit" class="btn btn-warning">Sửa</button>
    </div>
</sf:form>
</body>
</html>