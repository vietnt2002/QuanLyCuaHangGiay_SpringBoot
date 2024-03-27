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
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/view/css/style.css">
    <style>

    </style>
</head>
<body>
<div class="wrapper">
    <!-- Sidebar  -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>V & Store</h3>
        </div>

        <ul class="list-unstyled components">
            <li>
                <a href="/store/trang-chu">Bán hàng</a>
            </li>

            <li class="active">
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Quản lý sản phẩm</a>
                <ul class="collapse list-unstyled" id="homeSubmenu">
                    <li>
                        <a href="/store/quan-ly-san-pham">Sản phẩm</a>
                    </li>
                    <li>
                        <a href="/kich-thuoc/show">Kích thước</a>
                    </li>
                    <li>
                        <a href="/mau-sac/show">Màu sắc</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="/store/quan-ly-hoa-don">Quản lý hóa đơn</a>
            </li>

            <li>
                <a href="/store/quan-ly-khach-hang">Quản lý khách hàng</a>
            </li>

            <li>
                <a href="/store/quan-ly-nhan-vien">Quản lý nhân viên</a>
            </li>

            <li>
                <a href="#pageSubmenu1" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Quản lý tài khoản</a>
                <ul class="collapse list-unstyled" id="pageSubmenu1">
                    <li>
                        <a href="/store/sua-thong-tin">Sửa thông tin</a>
                    </li>

                    <li>
                        <a href="/store/dang-xuat">Đăng xuất</a>
                    </li>
                </ul>
            </li>
        </ul>

    </nav>

    <!-- Page Content  -->
    <div id="content">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                    <span><i class="bi bi-filter-left" style="display: flex; font-size: 25px; align-items: center; justify-content: center"></i></span>
                </button>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-align-justify"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul class="nav navbar-nav ml-auto" style="margin-right: 20px;">
                        <li class="nav-item">
                            <form class="d-flex">
                                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                <button class="btn btn-outline-secondary" type="submit" style="color: black; margin-left: -8px; margin-right: 20px;"><i class="bi bi-search" style="display: flex; font-size: 21px; align-items: center"></i></button>
                            </form>
                        </li>
                        <li class="nav-item" >
                            <a class="nav-link" href="#" style="margin-top: -3px"><i class="bi bi-person-circle" style="font-size: 21px"></i>  ${nhanVien.ten}</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <%--     ---   Main ---   --%>
        <h4 class="text-center mb-3">Quản lý hóa đơn</h4>
        <table class="table" style="margin-bottom: 20px">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">ID</th>
                <th scope="col">Nhân viên</th>
                <th scope="col">Khách hàng</th>
                <th scope="col">Ngày mua hàng</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach varStatus="i" items="${pageHoaDon.content}" var="hoaDon">
                <tr>
                    <th>${i.index+1}</th>
                    <td>${hoaDon.id}</td>
                    <td>${hoaDon.idNV.ten}</td>
                    <td>${hoaDon.idKH.ten}</td>
                    <td>${hoaDon.ngayMuaHang}</td>
                    <td>${hoaDon.trangThai == 0 ? "Chưa thanh toán" : "Đã thanh toán"}</td>
                    <td>
                        <a href="/store/hdDetail/${hoaDon.id}"><button class="btn btn-primary"><i class="bi bi-eye-fill"></i></button></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="float-end" style=" margin-right: 20px">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${pageHoaDon.number > 0}">
                        <li class="page-item"><a class="page-link" href="/store/quan-ly-hoa-don?page=${pageHoaDon.number - 1}"><i class="bi bi-caret-left-square-fill"></i></a></li>
                    </c:if>
                    <li class="page-item"><a class="page-link" href="#">${pageHoaDon.number + 1}</a></li>
                    <c:if test="${pageHoaDon.number+1 < pageHoaDon.totalPages}">
                        <li class="page-item"><a class="page-link" href="/store/quan-ly-hoa-don?page=${pageHoaDon.number + 1}"><i class="bi bi-caret-right-square-fill"></i></a></li>
                    </c:if>
                </ul>
            </nav>
        </div>

        <c:if test="${check == true}">
            <h4 class="text-center" style="margin-top: 70px;">Chi tiết hóa đơn</h4>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Mã</th>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Màu sắc</th>
                    <th scope="col">Kích thước</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Tổng tiền</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="i" items="${listHoaDonChiTiet}" var="hoaDonChiTiet">
                    <tr>
                        <td>${i.index+1}</td>
                        <td>${hoaDonChiTiet.idHD.id}</td>
                        <td>${hoaDonChiTiet.idCTSP.idSanPham.ten}</td>
                        <td>${hoaDonChiTiet.idCTSP.idMauSac.ten}</td>
                        <td>${hoaDonChiTiet.idCTSP.idKichThuoc.ten}</td>
                        <td>${hoaDonChiTiet.soLuong}</td>
                        <td>${hoaDonChiTiet.donGia}</td>
                        <td>${hoaDonChiTiet.soLuong * hoaDonChiTiet.donGia}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="input-group mb-3" style="width: 300px; margin: auto">
                <span class="input-group-text">Tổng hóa đơn: </span>
                <input type="text" class="form-control text-danger text-center" value="${thanhTien} vnd">
            </div>
            <a href="/store/quan-ly-hoa-don"><button class="btn btn-secondary float-end"> Đóng</button></a>
        </c:if>

        <%--    ---  Main  ---  --%>
    </div>
</div>
<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
        });
    });
</script>
</body>
</html>