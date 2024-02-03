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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/view/css/style.css">
    <style>
        .selected-row {
            background-color: beige; /* Màu nền mong muốn cho hàng được chọn */
        }
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
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Quản lý sản
                    phẩm</a>
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
                <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
                <ul class="collapse list-unstyled" id="pageSubmenu">
                    <li>
                        <a href="#">Page 1</a>
                    </li>
                    <li>
                        <a href="#">Page 2</a>
                    </li>
                    <li>
                        <a href="#">Page 3</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="#">Contact</a>
            </li>
            <li>
                <a href="#pageSubmenu1" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Quản lý tài
                    khoản</a>
                <ul class="collapse list-unstyled" id="pageSubmenu1">
                    <li>
                        <a href="#">Page 1</a>
                    </li>
                    <li>
                        <a href="#">Page 2</a>
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
                    <span><i class="bi bi-filter-left"
                             style="display: flex; font-size: 25px; align-items: center; justify-content: center"></i></span>
                </button>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-align-justify"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul class="nav navbar-nav ml-auto" style="margin-right: 20px;">
                        <li class="nav-item">
                            <form class="d-flex">
                                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                <button class="btn btn-outline-secondary" type="submit"
                                        style="color: black; margin-left: -8px; margin-right: 20px;"><i
                                        class="bi bi-search"
                                        style="display: flex; font-size: 21px; align-items: center"></i></button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" style="margin-top: -3px"><i class="bi bi-person-circle"
                                                                                     style="font-size: 21px"></i> ${nhanVien.ten}
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <%--        Main    --%>

        <c:if test="${checkHD5 == true}">
            <div class="alert alert-warning d-flex align-items-center" role="alert" style="position: relative">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                     class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
                     aria-label="Warning:">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
                <div>
                    Tạo tối đa 5 hoá đơn! <a href="/store/trang-chu"><i class="bi bi-x-octagon-fill"
                                                                        style="position: absolute; top: 3px; right: 5px"></i></a>
                </div>
            </div>
        </c:if>

        <div class="row">
            <div class="col-12 col-md-8">
                <div class="card">
                    <div class="card-header">
                        Hóa đơn
                        <a href="/store/tao-hoa-don">
                            <button class="btn btn-outline-secondary float-end">Tạo hóa đơn</button>
                        </a>
                    </div>
                    <div class="card-body">
                        <table class="table" id="tableId">
                            <thead>
                            <tr>
                                <th scope="col">ID HD</th>
                                <th scope="col">Nhân viên</th>
                                <th scope="col">Khách hàng</th>
                                <th scope="col">Ngày mua hàng</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach varStatus="i" items="${listHoaDon}" var="hoaDon">
                                <tr class="table-row" data-id="${hoaDon.id}" onclick="highlightRow(this)">
                                    <th>${hoaDon.id}</th>
                                    <td>${hoaDon.idNV.ten}</td>
                                    <td>${hoaDon.idKH.ten}</td>
                                    <td>${hoaDon.ngayMuaHang}</td>
                                    <td>${hoaDon.trangThai == 0 ? "<span class='badge bg-warning text-dark'>Chờ thanh toán</span>" : "<span class='badge bg-success text-white'>Đã thanh toán</span>"}</td>
                                    <td>
                                        <a href="/store/hdDelete/${hoaDon.id}">
                                            <button class="btn btn-outline-light"><i
                                                    class="bi bi-x-square text-danger"></i></button>
                                        </a>
                                        <a href="/store/ghDetail/${hoaDon.id}?selectedRowId=${hoaDon.id}" onclick="clearSelectedRow()">
                                            <button class="btn btn-outline-secondary"><i class="bi bi-basket2-fill"></i>
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>


                <div class="card" style="margin-top: 30px">
                    <div class="card-header">
                        Giỏ hàng
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">ID HD</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Màu sắc</th>
                                <th scope="col">Kích thước</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Đơn giá</th>
                                <th scope="col">Tổng tiền</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${check == true}">
                                <c:forEach varStatus="i" items="${listHoaDonChiTiet}" var="hoaDonChiTiet">
                                    <tr>
                                        <th>${i.index+1}</th>
                                        <td>${hoaDonChiTiet.idHD.id}</td>
                                        <td>${hoaDonChiTiet.idCTSP.idSanPham.ten}</td>
                                        <td>${hoaDonChiTiet.idCTSP.idMauSac.ten}</td>
                                        <td>${hoaDonChiTiet.idCTSP.idKichThuoc.ten}</td>
                                        <td>
                                            <div class="btn-group" role="group" aria-label="Basic example">
                                                <a href="/store/tru-so-luong/${hoaDonChiTiet.id}?selectedRowId=${hoaDonChiTiet.idHD.id}" onclick="clearSelectedRow()">
                                                    <button type="button" class="btn btn-secondary"
                                                            style="display: flex; width: 35px; height: 30px; align-items: center; justify-content: center">
                                                        <i class="bi bi-dash-lg"></i></button>
                                                </a>
                                                <input type="text" class="form-control text-center"
                                                       value="${hoaDonChiTiet.soLuong}"
                                                       style="display: flex; width: 45px; height: 30px; align-items: center; justify-content: center"
                                                       disabled>
                                                <a href="/store/cong-so-luong/${hoaDonChiTiet.id}?selectedRowId=${hoaDonChiTiet.idHD.id}" onclick="clearSelectedRow()">
                                                    <button type="button" class="btn btn-secondary"
                                                            style="display: flex; width: 35px; height: 30px; align-items: center; justify-content: center">
                                                        <i class="bi bi-plus-lg"></i></button>
                                                </a>
                                            </div>
                                        </td>
                                        <td>${hoaDonChiTiet.donGia}</td>
                                        <td>${hoaDonChiTiet.soLuong * hoaDonChiTiet.donGia}</td>
                                        <td>
                                            <a href="/store/cthdDelete/${hoaDonChiTiet.id}"><i class="bi bi-trash3"
                                                                                               style="color: red"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>


            </div>

            <div class="col-12 col-md-4">
                <div class="card">
                    <div class="card-header">
                        Thông tin thanh toán
                    </div>

                    <c:if test="${checkHD == true}">
                        <div class="alert alert-warning d-flex align-items-center" role="alert"
                             style="position: relative">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                 class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16"
                                 role="img" aria-label="Warning:">
                                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                            </svg>
                            <div>
                                Hãy chọn hóa đơn cần thêm thông tin khách hàng! <a href="/store/trang-chu"><i
                                    class="bi bi-x-octagon-fill"
                                    style="position: absolute; top: 3px; right: 5px"></i></a>
                            </div>
                        </div>
                    </c:if>

                    <div class="card-body">
                        <div class="row">
                            <div class="col-12">
                                <form class="d-flex" method="post" action="/store/tim-kiem-khach-hang">
                                    <input class="form-control me-2" type="search"
                                           placeholder="Nhập số điện thoại khách hàng" aria-label="Search" name="sdt">
                                    <button class="btn btn-outline-secondary" type="submit"
                                            style="color: black;"><i
                                            class="bi bi-search"
                                            style="display: flex; font-size: 21px; align-items: center"></i></button>
                                </form>
                            </div>
                        </div>
                        <c:if test="${checkKH == true}">
                            <div class="alert alert-warning d-flex align-items-center" role="alert" style="position: relative">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
                                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                </svg>
                                <div>
                                    Không tìm thấy khách hàng nào! <a href="/store/ghDetail/${idhd}?selectedRowId=${idhd}"><i class="bi bi-x-octagon-fill" style="position: absolute; top: 3px; right: 5px"></i></a>
                                </div>
                            </div>
                        </c:if>

                        <form method="post" action="/store/add-kh-hd">
                            <div class="row">
                                <div class="col-12 col-md-10">
                                    <div class="mb-2 mt-2">
                                        <label for="exampleFormControlInput1" class="form-label">Tên khách
                                            hàng: </label>
                                        <input type="text" class="form-control" id="exampleFormControlInput1"
                                               value="${kh.ten}" name="ten">
                                    </div>
                                    <div class="">
                                        <label for="exampleFormControlInput2" class="form-label">Số điện thoại khách
                                            hàng: </label>
                                        <input type="text" class="form-control" id="exampleFormControlInput2"
                                               value="${kh.sdt}" name="sdt">
                                    </div>
                                </div>

                                <div class="col-12 col-md-2">
                                    <button class="btn btn-outline-secondary float-end" style="margin-top: 40px;"><i
                                            class="bi bi-plus-square-fill"></i></button>
                                </div>
                            </div>
                        </form>

                        <form method="post" action="/store/thanh-toan">
                            <div class="input-group mb-3 mt-3" style="border-top: 1px solid gray; padding-top: 15px;">
                                <span class="input-group-text">ID hóa đơn: </span>
                                <input type="text" class="form-control" value="${hoaDon.id}" disabled>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Thành tiền: </span>
                                <input type="text" class="form-control" value="${thanhTien} vnd" disabled>
                                <input type="hidden" class="form-control" name="thanhTien" value="${thanhTien}">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Tiền khách đưa: </span>
                                <input type="number" class="form-control" name="tienKhachDua">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Tiền trả khách: </span>
                                <input type="text" class="form-control" value="${tienTraKhach} vnd" disabled>
                            </div>

                            <c:if test="${checkk == 1}">
                                <div class="alert alert-warning d-flex align-items-center" role="alert"
                                     style="position: relative">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                         class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16"
                                         role="img" aria-label="Warning:">
                                        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                    </svg>
                                    <div>
                                        Hãy chọn hóa đơn cần thanh toán! <a href="/store/trang-chu"><i
                                            class="bi bi-x-octagon-fill"
                                            style="position: absolute; top: 3px; right: 5px"></i></a>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${checkout == true}">
                                <div class="alert alert-warning d-flex align-items-center" role="alert"
                                     style="position: relative">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                         class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16"
                                         role="img" aria-label="Warning:">
                                        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                    </svg>
                                    <div>
                                            ${mess} <a href="/store/ghDetail/${idhd}?selectedRowId=${idhd}"><i class="bi bi-x-octagon-fill"
                                                                                         style="position: absolute; top: 3px; right: 5px"></i></a>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${checkout == false}">
                                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                                    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                    </symbol>
                                </svg>
                                <div class="alert alert-success d-flex align-items-center" role="alert"
                                     style="position: relative">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img"
                                         aria-label="Success:">
                                        <use xlink:href="#check-circle-fill"/>
                                    </svg>
                                    <div>
                                            ${mess} <a href="/store/trang-chu"><i class="bi bi-x-octagon-fill"
                                                                                  style="position: absolute; top: 3px; right: 5px"></i></a>
                                    </div>
                                </div>
                            </c:if>
                            <button class="btn btn-danger" style="width: 100%; border-radius: 0px">Thanh toán</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>

        <c:if test="${checkGH == true}">
            <div class="alert alert-warning d-flex align-items-center" role="alert" style="position: relative">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                     class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
                     aria-label="Warning:">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
                <div>
                    Hãy chọn hóa đơn trước khi thêm sản phẩm! <a href="/store/trang-chu"><i class="bi bi-x-octagon-fill"
                                                                                            style="position: absolute; top: 3px; right: 5px"></i></a>
                </div>
            </div>
        </c:if>

        <div class="row" style="margin-top: 20px">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="fw-bolder">Sản phẩm</h5>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Mã</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Màu sắc</th>
                                <th scope="col">Kích thước</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Đơn giá</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach varStatus="i" items="${pageCTSP.content}" var="chiTietSanPham">
                                <tr>
                                    <td>${chiTietSanPham.id}</td>
                                    <td>${chiTietSanPham.ma}</td>
                                    <td>${chiTietSanPham.idSanPham.ten}</td>
                                    <td>${chiTietSanPham.idMauSac.ten}</td>
                                    <td>${chiTietSanPham.idKichThuoc.ten}</td>
                                    <td>${chiTietSanPham.soLuong}</td>
                                    <td>${chiTietSanPham.donGia}</td>
                                    <td>${chiTietSanPham.trangThai == 1 ? "Còn hàng" : "Hết hàng"}</td>
                                    <td>
                                        <a href="/store/add-gio-hang/${chiTietSanPham.id}">
                                            <button class="btn btn-success"><i class="bi bi-plus-square-fill"
                                                                               style="color: white"></i></button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <div class="float-end">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <c:if test="${pageCTSP.number > 0}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="/store/trang-chu?page=${pageCTSP.number - 1}"><i
                                                class="bi bi-caret-left-square-fill"></i></a></li>
                                    </c:if>
                                    <li class="page-item"><a class="page-link" href="#">${pageCTSP.number + 1}</a></li>
                                    <c:if test="${pageCTSP.number+1 < pageCTSP.totalPages}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="/store/trang-chu?page=${pageCTSP.number + 1}"><i
                                                class="bi bi-caret-right-square-fill"></i></a></li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%--   ----  MAin   ---  --%>
    </div>
</div>
<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
        });
    });
</script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var urlParams = new URLSearchParams(window.location.search);
        var selectedRowId = urlParams.get("selectedRowId");

        if (selectedRowId !== null) {
            var selectedRow = document.querySelector(".table-row[data-id='" + selectedRowId + "']");
            if (selectedRow !== null) {
                selectedRow.classList.add("selected-row");
            }
        }
    });

    function highlightRow(row) {
        var selectedRowId = row.getAttribute("data-id");

        var urlParams = new URLSearchParams(window.location.search);
        urlParams.set("selectedRowId", selectedRowId);

        // Thay đổi URL trình duyệt mà không reload trang
        history.pushState(null, null, "?" + urlParams.toString());

        var selectedRow = document.querySelector(".table-row.selected-row");
        if (selectedRow !== null) {
            selectedRow.classList.remove("selected-row");
        }

        row.classList.add("selected-row");
    }

    // Xử lý sự kiện click trên thẻ <a>
    document.addEventListener("click", function (event) {
        var target = event.target;

        // Kiểm tra nếu là thẻ <a> và chứa trong thẻ có class là 'table-row'
        if (target.tagName === "A" && target.closest(".table-row")) {
            var currentPath = window.location.pathname;
            var linkPath = target.getAttribute("href");

            // Kiểm tra đường dẫn trên thanh URL và xóa màu nền nếu khác đường dẫn trong thẻ <a>
            if (currentPath !== linkPath) {
                clearSelectedRow();
            }
        }
    });

    function clearSelectedRow() {
        var selectedRow = document.querySelector(".table-row.selected-row");
        if (selectedRow !== null) {
            selectedRow.classList.remove("selected-row");
        }

        var urlParams = new URLSearchParams(window.location.search);
        urlParams.delete("selectedRowId");

        // Thay đổi URL trình duyệt mà không reload trang
        history.pushState(null, null, "?" + urlParams.toString());
    }
</script>

</body>
</html>