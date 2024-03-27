package com.example.sd18203.controllers;

import com.example.sd18203.config.UserInfor;
import com.example.sd18203.dto.ChiTietSanPhamRequest;
import com.example.sd18203.dto.LoginRequest;
import com.example.sd18203.dto.SanPhamRequest;
import com.example.sd18203.entities.*;
import com.example.sd18203.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("store")
public class BanHangController {
    int count = 1;
    int soLuong = 1;
    LocalDate ngayHienTai = LocalDate.now();
    LocalDateTime ngayGioHienTai = LocalDateTime.now();

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepo;
    @Autowired
    private KichThuocRepository kichThuocRepo;
    @Autowired
    private MauSacRepository mauSacRepo;
    @Autowired
    private SanPhamRepository sanPhamRepo;
    @Autowired
    private HoaDonRepository hoaDonRepo;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepo;
    @Autowired
    private NhanVienRepository nhanVienRepo;
    @Autowired
    private KhachHangRepository khachHangRepo;

    @GetMapping("dang-nhap-view")
    public String getDangNhap() {
        return "dangNhap";
    }

    @PostMapping("dang-nhap")
    public String dangNhap(
            LoginRequest lgReq,
            Model model
    ) {
        boolean check = false;
        //Xác thực thông tin đăng nhập của nhân viên có trạng thái đang làm việc
        NhanVien nhanVien = nhanVienRepo.findByTenDangNhapAndMatKhauAndTrangThai(lgReq.getEmail(), lgReq.getPassword(), NhanVienRepository.DANG_LAM_VIEC);
        if (nhanVien != null) {
            //Nếu tìm thấy thì lưu lại thông tin
            UserInfor.idNV = nhanVien.getId();
        } else {
            model.addAttribute("check", check);
            return "dangNhap";
        }
        return "redirect:/store/trang-chu";
    }

    @GetMapping("dang-xuat")
    public String dangXuat() {
        UserInfor.idNV = -1;
        UserInfor.idHD = -1;
        return "redirect:/store/dang-nhap-view";
    }

    @GetMapping("trang-chu")
    public String getTrangChu(
            Model model,
            @RequestParam("page") Optional<Integer> pageParam
    ) {
        if (UserInfor.idNV < 0) {
            return "admin/ban_hang/trangChu";
        }
        // Hiển thị thông tin nhân viên đăng nhập
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
        model.addAttribute("nhanVien", nhanVien);

        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);

        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamRepo.findByTrangThai(ChiTietSanPhamRepository.CON_HANG, pageable);
        List<HoaDon> listHoaDonByTT = hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN);

        model.addAttribute("pageCTSP", pageCTSP);
        model.addAttribute("listHoaDon", listHoaDonByTT);
        return "admin/ban_hang/trangChu";
    }


    //   ------------ Quản lý sản phẩm -------------------
    @GetMapping("quan-ly-san-pham")
    public String getQLSanPham(
            Model model,
            @RequestParam("page") Optional<Integer> pageParam
    ) {
        if (UserInfor.idNV < 0) {
            return "admin/ban_hang/qlSanPham";
        }
        // Hiển thị thông tin nhân viên đăng nhập
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
        model.addAttribute("nhanVien", nhanVien);

        //Phân trang sản phẩm
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 5);
        Page<SanPham> pageSanPham = sanPhamRepo.findAll(pageable);

        boolean check = false;
        model.addAttribute("check", check);
        model.addAttribute("pageSanPham", pageSanPham);
        return "admin/ban_hang/qlSanPham";
    }

    @GetMapping("san-pham-create")
    public String getSanPham(Model model) {
        SanPhamRequest sanPham = new SanPhamRequest();
        model.addAttribute("sanPham", sanPham);
        return "admin/ban_hang/addSanPham";
    }

    //Thêm sản phẩm
    @PostMapping("san-pham-store")
    public String SanPhamstore(
            @Valid @ModelAttribute("sanPham") SanPhamRequest spReq,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/ban_hang/addSanPham";
        }
        SanPham sanPham = new SanPham(null, spReq.getMa(), spReq.getTen(), spReq.getTrangThai());
        sanPhamRepo.save(sanPham);
        return "redirect:/store/quan-ly-san-pham";
    }

    @GetMapping("san-pham-edit/{id}")
    public String spEdit(
            @PathVariable("id") SanPham sanPham,
            Model model
    ) {
        model.addAttribute("sanPham", sanPham);
        return "admin/ban_hang/editSanPham";
    }

    //Sửa sản phẩm
    @PostMapping("san-pham-update/{id}")
    public String spUpdate(
            @PathVariable("id") SanPham sanPham,
            @Valid @ModelAttribute("sanPham") SanPhamRequest spReq,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/ban_hang/editSanPham";
        }

        sanPham.setMa(spReq.getMa());
        sanPham.setTen(spReq.getTen());
        sanPham.setTrangThai(spReq.getTrangThai());
        sanPhamRepo.save(sanPham);
        return "redirect:/store/quan-ly-san-pham";
    }


    //Xóa sản phẩm
    @GetMapping("san-pham-delete/{id}")
    public String spDelete(
            @PathVariable("id") int id
    ) {
        sanPhamRepo.deleteById(id);
        return "redirect:/store/quan-ly-san-pham";
    }

    //Hiển thị chi tiết sản phẩm
    @GetMapping("spDetail/{id}")
    public String getChiTietSanPham(
            @PathVariable("id") int idSP,
            @RequestParam("page") Optional<Integer> pageParam,
            Model model
    ) {
        // Hiển thị thông tin nhân viên đăng nhập
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
        model.addAttribute("nhanVien", nhanVien);

        //Phân trang sản phẩm
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 5);
        Page<SanPham> pageSanPham = sanPhamRepo.findAll(pageable);

        UserInfor.idSP = idSP;
        boolean check = false;
        List<ChiTietSanPham> listCTSPByID = chiTietSanPhamRepo.findByIdSanPham(idSP);
        if (!listCTSPByID.isEmpty()) {
            check = true;
        }
        model.addAttribute("check", check);
        model.addAttribute("pageSanPham", pageSanPham);
        model.addAttribute("listChiTietSanPham", listCTSPByID);

        return "admin/ban_hang/qlSanPham";
    }

    @GetMapping("chi-tiet-san-pham-create")
    public String getChiTietSanPham(Model model) {
        ChiTietSanPhamRequest ctsp = new ChiTietSanPhamRequest();
        model.addAttribute("chiTietSanPham", ctsp);
        model.addAttribute("listSanPham", sanPhamRepo.findAll());
        model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
        model.addAttribute("listMauSac", mauSacRepo.findAll());
        return "admin/ban_hang/addChiTietSanPham";
    }

    //Thêm chi tiết sản phẩm
    @PostMapping("chi-tiet-san-pham-store")
    public String CTSPstore(
            @Valid @ModelAttribute("chiTietSanPham") ChiTietSanPhamRequest ctspReq,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/ban_hang/addChiTietSanPham";
        }
        KichThuoc kichThuoc = kichThuocRepo.findById(ctspReq.getIdKichThuoc()).get();
        MauSac mauSac = mauSacRepo.findById(ctspReq.getIdMauSac()).get();
        SanPham sanPham = sanPhamRepo.findById(ctspReq.getIdSanPham()).get();

        //check trùng chi tiết sản phẩm
        ChiTietSanPham chiTietSanPhamCanTim = chiTietSanPhamRepo.findByIdSanPhamAndIdKichThuocAndIdMauSac(ctspReq.getIdSanPham(), ctspReq.getIdKichThuoc(), ctspReq.getIdMauSac());
        if (chiTietSanPhamCanTim == null){
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setMa(ctspReq.getMa());
            chiTietSanPham.setIdKichThuoc(kichThuoc);
            chiTietSanPham.setIdSanPham(sanPham);
            chiTietSanPham.setIdMauSac(mauSac);
            chiTietSanPham.setSoLuong(ctspReq.getSoLuong());
            chiTietSanPham.setDonGia(ctspReq.getDonGia());
            chiTietSanPham.setTrangThai(ctspReq.getTrangThai());
            chiTietSanPhamRepo.save(chiTietSanPham);
        }else {
            chiTietSanPhamCanTim.setId(chiTietSanPhamCanTim.getId());
            chiTietSanPhamCanTim.setSoLuong(chiTietSanPhamCanTim.getSoLuong() + ctspReq.getSoLuong());
            chiTietSanPhamRepo.save(chiTietSanPhamCanTim);
        }
        return "redirect:/store/spDetail/" + UserInfor.idSP;
    }

    //Xóa chi tiết sản phẩm
    @GetMapping("chi-tiet-san-pham-delete/{id}")
    public String ctspDelete(
            @PathVariable("id") int id
    ) {
        chiTietSanPhamRepo.deleteById(id);
        return "redirect:/store/spDetail/" + UserInfor.idSP;
    }

    @GetMapping("chi-tiet-san-pham-edit/{id}")
    public String ctspEdit(
            @PathVariable("id") ChiTietSanPham chiTietSanPham,
            Model model
    ) {
        model.addAttribute("chiTietSanPham", chiTietSanPham);
        model.addAttribute("listMauSac", mauSacRepo.findAll());
        model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
        model.addAttribute("listSanPham", sanPhamRepo.findAll());
        return "admin/ban_hang/editChiTietSanPham";
    }

    //Sửa chi tiết sản phẩm
    @PostMapping("chi-tiet-san-pham-update/{id}")
    public String update(
            @PathVariable("id") ChiTietSanPham chiTietSanPham,
            @Valid @ModelAttribute("chiTietSanPham") ChiTietSanPhamRequest ctspReq,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/ban_hang/editChiTietSanPham";
        }
        KichThuoc kichThuoc = kichThuocRepo.findById(ctspReq.getIdKichThuoc()).get();
        MauSac mauSac = mauSacRepo.findById(ctspReq.getIdMauSac()).get();
        SanPham sanPham = sanPhamRepo.findById(ctspReq.getIdSanPham()).get();

        chiTietSanPham.setMa(ctspReq.getMa());
        chiTietSanPham.setIdKichThuoc(kichThuoc);
        chiTietSanPham.setIdMauSac(mauSac);
        chiTietSanPham.setIdSanPham(sanPham);
        chiTietSanPham.setSoLuong(ctspReq.getSoLuong());
        chiTietSanPham.setDonGia(ctspReq.getDonGia());
        chiTietSanPham.setTrangThai(ctspReq.getTrangThai());

        chiTietSanPhamRepo.save(chiTietSanPham);
        return "redirect:/store/spDetail/" + UserInfor.idSP;
    }


    //--------------- Bán hàng --------------------
    @GetMapping("tao-hoa-don")
    public String taoHoaDon(
            Model model,
            @RequestParam("page") Optional<Integer> pageParam
    ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamRepo.findByTrangThai(ChiTietSanPhamRepository.CON_HANG, pageable);

        //Check tạo tối đa 5 hóa đơn
        List<HoaDon> listHoaDonByTT0 = hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN);
        if (listHoaDonByTT0.size() < 5) {
            //Lấy thông tin nhân viên đăng nhập
            //Lấy thông tin khách hàng nếu có
            NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
            if (UserInfor.idKH < 0) {
                try {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setIdNV(nhanVien);
                    hoaDon.setNgayMuaHang(sdf.parse(ngayHienTai + ""));
                    hoaDon.setTrangThai(HoaDonRepository.CHO_THANH_TOAN);
                    hoaDonRepo.save(hoaDon);
                    UserInfor.idHD = hoaDon.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                KhachHang khachHang = khachHangRepo.findById(UserInfor.idKH).get();
                try {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setIdNV(nhanVien);
                    hoaDon.setIdKH(khachHang);
                    hoaDon.setNgayMuaHang(sdf.parse(ngayHienTai + ""));
                    hoaDon.setTrangThai(HoaDonRepository.CHO_THANH_TOAN);
                    hoaDonRepo.save(hoaDon);
                    UserInfor.idHD = hoaDon.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            boolean checkHD5 = true;
            model.addAttribute("checkHD5", checkHD5);
            model.addAttribute("listHoaDon", listHoaDonByTT0);
            model.addAttribute("pageCTSP", pageCTSP);
            return "admin/ban_hang/trangChu";
        }


        UserInfor.idKH = -1;
        return "redirect:/store/trang-chu";
    }

    @GetMapping("hdDelete/{id}")
    public String hoaDonDelete(
            @PathVariable("id") int id
    ) {
        hoaDonRepo.deleteById(id);
        UserInfor.idHD = -1;
        return "redirect:/store/trang-chu";
    }

    //Hiển thị giỏ hàng theo idHD
    @GetMapping("ghDetail/{idHD}")
    public String gioHangDetail(
            @PathVariable("idHD") int idHD,
            @RequestParam("page") Optional<Integer> pageParam,
            Model model
    ) {
        // Hiển thị thông tin nhân viên đăng nhập
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
        model.addAttribute("nhanVien", nhanVien);

        //Lưu lại idHD đơn
        UserInfor.idHD = idHD;

        //Phân trang chi tiết sản phẩm
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamRepo.findByTrangThai(ChiTietSanPhamRepository.CON_HANG, pageable);
        model.addAttribute("pageCTSP", pageCTSP);

        //Lấy danh sách hóa đơn chi tiết theo idHD
        List<HoaDonChiTiet> listHDCTByIdHD = hoaDonChiTietRepo.findByIdHD(idHD);

        //Hiển thị thông tin khách hàng nếu có
        if (UserInfor.idKH < 0){
            boolean check = false;
            BigDecimal tongTien = null;
            BigDecimal thanhTien = new BigDecimal(0);

            HoaDon hoaDon = hoaDonRepo.findById(idHD).get();
            if (hoaDon.getIdKH() == null) {
                check = true;
                //Tính tổng tiền của hóa đơn chi tiết
                for (HoaDonChiTiet hdct : listHDCTByIdHD) {
                    tongTien = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
                    thanhTien = thanhTien.add(tongTien);
                }

                model.addAttribute("thanhTien", thanhTien);
                model.addAttribute("hoaDon", hoaDon);
                model.addAttribute("check", check);
                model.addAttribute("listHoaDon", hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN));
                model.addAttribute("pageCTSP", pageCTSP);
                model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
                return "admin/ban_hang/trangChu";
            } else {
                KhachHang khachHang = khachHangRepo.findById(hoaDon.getIdKH().getId()).get();
                model.addAttribute("kh", khachHang);
                check = true;

                //Tính tổng tiền của hóa đơn chi tiết
                for (HoaDonChiTiet hdct : listHDCTByIdHD) {
                    tongTien = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
                    thanhTien = thanhTien.add(tongTien);
                }

                model.addAttribute("thanhTien", thanhTien);
                model.addAttribute("hoaDon", hoaDon);
                model.addAttribute("check", check);
                model.addAttribute("listHoaDon", hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN));
                model.addAttribute("listChiTietSanPham", chiTietSanPhamRepo.findAll());
                model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
                return "admin/ban_hang/trangChu";
            }
        }else {
            //Nếu tìm thấy khách hàng theo sdt thì hiển thị
            KhachHang khachHang = khachHangRepo.findById(UserInfor.idKH).get();
            model.addAttribute("kh", khachHang);

            boolean check = false;
            BigDecimal tongTien = null;
            BigDecimal thanhTien = new BigDecimal(0);

            HoaDon hoaDon = hoaDonRepo.findById(idHD).get();
            if (hoaDon.getIdKH() == null) {
                check = true;
                //Tính tổng tiền của hóa đơn chi tiết
                for (HoaDonChiTiet hdct : listHDCTByIdHD) {
                    tongTien = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
                    thanhTien = thanhTien.add(tongTien);
                }

                model.addAttribute("thanhTien", thanhTien);
                model.addAttribute("hoaDon", hoaDon);
                model.addAttribute("check", check);
                model.addAttribute("listHoaDon", hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN));
                model.addAttribute("pageCTSP", pageCTSP);
                model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
                return "admin/ban_hang/trangChu";
            } else {
                KhachHang khSdt = khachHangRepo.findById(hoaDon.getIdKH().getId()).get();
                model.addAttribute("kh", khSdt);
                check = true;

                //Tính tổng tiền của hóa đơn chi tiết
                for (HoaDonChiTiet hdct : listHDCTByIdHD) {
                    tongTien = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
                    thanhTien = thanhTien.add(tongTien);
                }

                model.addAttribute("thanhTien", thanhTien);
                model.addAttribute("hoaDon", hoaDon);
                model.addAttribute("check", check);
                model.addAttribute("listHoaDon", hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN));
                model.addAttribute("listChiTietSanPham", chiTietSanPhamRepo.findAll());
                model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
                return "admin/ban_hang/trangChu";
            }
        }


    }


    //Thêm sản phẩm vào giỏ hàng
    @GetMapping("add-gio-hang/{idCTSP}")
    public String addCTSP(
            @PathVariable("idCTSP") int idCTSP,
            @RequestParam("page") Optional<Integer> pageParam,
            Model model
    ) {
        List<HoaDon> listHoaDonByTT = hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN);

        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamRepo.findByTrangThai(ChiTietSanPhamRepository.CON_HANG, pageable);

        boolean checkGH = false;
        if (UserInfor.idHD < 0) {
            checkGH = true;
            model.addAttribute("checkGH", checkGH);
            model.addAttribute("listHoaDon", listHoaDonByTT);
            model.addAttribute("pageCTSP", pageCTSP);
            return "admin/ban_hang/trangChu";
        } else {
            HoaDon hoaDon = hoaDonRepo.findById(UserInfor.idHD).get();

            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepo.findById(idCTSP).get();

            boolean check = true;
            for (HoaDonChiTiet hdct : hoaDonChiTietRepo.findByIdHD(hoaDon.getId())) {
                if (hdct.getIdCTSP().getId() == idCTSP) {
                    check = false;
                    hdct.setId(hdct.getId());
                    hdct.setSoLuong(hdct.getSoLuong() + 1);
                    hoaDonChiTietRepo.save(hdct);

                    //Trừ số lượng chi tiết sản phẩm
                    chiTietSanPham.setId(chiTietSanPham.getId());
                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
                    if (chiTietSanPham.getSoLuong() == 0){
                        chiTietSanPham.setTrangThai(ChiTietSanPhamRepository.HET_HANG);
                    }
                    chiTietSanPhamRepo.save(chiTietSanPham);

                }
            }
            if (check) {
                try {
                    Date ngayHT = new Date();
                    Timestamp ngayGioHT = new Timestamp(ngayHT.getTime());
                    //Thêm sản phẩm vào giỏ hàng
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    hoaDonChiTiet.setIdHD(hoaDon);
                    hoaDonChiTiet.setIdCTSP(chiTietSanPham);
                    hoaDonChiTiet.setSoLuong(1);
                    hoaDonChiTiet.setDonGia(chiTietSanPham.getDonGia());
                    hoaDonChiTiet.setThoiGian(ngayGioHT);
                    hoaDonChiTiet.setTrangThai(HoaDonChiTietRepository.CHO_THANH_TOAN);
                    hoaDonChiTietRepo.save(hoaDonChiTiet);

                    //Trừ số lượng chi tiết sản phẩm
                    chiTietSanPham.setId(chiTietSanPham.getId());
                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
                    if (chiTietSanPham.getSoLuong() == 0){
                        chiTietSanPham.setTrangThai(ChiTietSanPhamRepository.HET_HANG);
                    }
                    chiTietSanPhamRepo.save(chiTietSanPham);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "redirect:/store/ghDetail/" + UserInfor.idHD+"?selectedRowId="+UserInfor.idHD;
    }

    //Cộng số lượng sản phẩm trong giỏ hàng
    @GetMapping("cong-so-luong/{idHDCT}")
    public String congSoLuong(
            @PathVariable("idHDCT") int idHDCT
    ) {

        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findById(idHDCT).get();
        if (hoaDonChiTiet.getIdCTSP().getSoLuong() > 0) {
            hoaDonChiTiet.setId(hoaDonChiTiet.getId());
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + 1);
            hoaDonChiTietRepo.save(hoaDonChiTiet);

            // Update lại số lượng chiTietSanPham
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepo.findById(hoaDonChiTiet.getIdCTSP().getId()).get();
            chiTietSanPham.setId(chiTietSanPham.getId());
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
            if (chiTietSanPham.getSoLuong() == 0){
                chiTietSanPham.setTrangThai(ChiTietSanPhamRepository.HET_HANG);
            }
            chiTietSanPhamRepo.save(chiTietSanPham);
        }else {
            hoaDonChiTiet.setId(hoaDonChiTiet.getId());
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong());
            hoaDonChiTietRepo.save(hoaDonChiTiet);
        }

        return "redirect:/store/ghDetail/" + UserInfor.idHD+"?selectedRowId="+UserInfor.idHD;
    }

    //Trừ số lượng sản phẩm trong giỏ hàng
    @GetMapping("tru-so-luong/{idHDCT}")
    public String truSoLuong(
            @PathVariable("idHDCT") int idHDCT
    ) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findById(idHDCT).get();
        if (hoaDonChiTiet.getSoLuong() > 1) {
            hoaDonChiTiet.setId(hoaDonChiTiet.getId());
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() - 1);
            hoaDonChiTietRepo.save(hoaDonChiTiet);

            // Update lại số lượng chiTietSanPham
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepo.findById(hoaDonChiTiet.getIdCTSP().getId()).get();
            chiTietSanPham.setId(chiTietSanPham.getId());
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + 1);
            if (chiTietSanPham.getSoLuong() > 0){
                chiTietSanPham.setTrangThai(ChiTietSanPhamRepository.CON_HANG);
            }
            chiTietSanPhamRepo.save(chiTietSanPham);
        } else {
            hoaDonChiTiet.setId(hoaDonChiTiet.getId());
            hoaDonChiTiet.setSoLuong(1);
            hoaDonChiTietRepo.save(hoaDonChiTiet);
        }
        return "redirect:/store/ghDetail/" + UserInfor.idHD+"?selectedRowId="+UserInfor.idHD;
    }

    //Xóa sản phẩm khỏi giỏ hàng
    @GetMapping("cthdDelete/{id}")
    public String cthdDelete(
            @PathVariable("id") int id
    ) {
        //Trả lại số lượng sản phẩm trước khi xóa
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findById(id).get();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepo.findById(hoaDonChiTiet.getIdCTSP().getId()).get();
        chiTietSanPham.setId(chiTietSanPham.getId());
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiet.getSoLuong());
        if (chiTietSanPham.getSoLuong() > 0){
            chiTietSanPham.setTrangThai(ChiTietSanPhamRepository.CON_HANG);
        }
        chiTietSanPhamRepo.save(chiTietSanPham);

        hoaDonChiTietRepo.deleteById(id);
        return "redirect:/store/ghDetail/" + UserInfor.idHD+"?selectedRowId="+UserInfor.idHD;
    }

    //Tìm kiếm khách hàng
    @PostMapping("tim-kiem-khach-hang")
    public String timKiemKH(
            Model model,
            @RequestParam("sdt") String sdt,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        //Hiển thị danh sách hóa đơn có trạng thái chờ thanh toán
        List<HoaDon> listHoaDonByTT = hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN);

        //Hiển thị danh sách chi tiết sản phẩm theo trạng thái còn hàng và phân trang
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamRepo.findByTrangThai(ChiTietSanPhamRepository.CON_HANG, pageable);

        //Hiển thị list hóa đơn chi tiết theo idHD
        List<HoaDonChiTiet> listHDCTByIdHD = hoaDonChiTietRepo.findByIdHD(UserInfor.idHD);

        //Hiển thị idHD và thành tiền
        BigDecimal tongTien = null;
        BigDecimal thanhTien = new BigDecimal(BigInteger.ZERO);

        HoaDon hoaDon = hoaDonRepo.findById(UserInfor.idHD).get();
        for (HoaDonChiTiet hdct : listHDCTByIdHD) {
            tongTien = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
            thanhTien = thanhTien.add(tongTien);
        }
        model.addAttribute("thanhTien", thanhTien);
        model.addAttribute("hoaDon", hoaDon);

        KhachHang khachHang = khachHangRepo.findBySdt(sdt);
        if (khachHang == null){
            boolean checkKH = true;
            int idhd = UserInfor.idHD;
            model.addAttribute("idhd", idhd);
            model.addAttribute("checkKH", checkKH);
            model.addAttribute("check", checkKH);
            model.addAttribute("pageCTSP", pageCTSP);
            model.addAttribute("listHoaDon", listHoaDonByTT);
            model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
            UserInfor.idKH = -1;
            return "admin/ban_hang/trangChu";
        }else {
            UserInfor.idKH = khachHang.getId();
            model.addAttribute("kh", khachHang);
            model.addAttribute("pageCTSP", pageCTSP);
            model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
            model.addAttribute("listHoaDon", listHoaDonByTT);
            return "redirect:/store/ghDetail/"+UserInfor.idHD+"?selectedRowId="+UserInfor.idHD;
        }
    }


    //Thêm thông tin khách hàng vào hóa đơn
    @PostMapping("add-kh-hd")
    public String addKHVaoHD(
            @RequestParam("ten") String ten,
            @RequestParam("sdt") String sdt,
            @RequestParam("page") Optional<Integer> pageParam,
            Model model
    ) {
        //Hiển thị danh sách hóa đơn có trạng thái chờ thanh toán
        List<HoaDon> listHoaDonByTT = hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN);

        //Hiển thị danh sách chi tiết sản phẩm theo trạng thái còn hàng và phân trang
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamRepo.findByTrangThai(ChiTietSanPhamRepository.CON_HANG, pageable);

        //Check đã chọn hóa đơn chưa
        if (UserInfor.idHD < 0) {
            //Thông báo chưa chọn hóa đơn
            boolean checkHD = true;
            model.addAttribute("checkHD", checkHD);
            model.addAttribute("pageCTSP", pageCTSP);
            model.addAttribute("listHoaDon", listHoaDonByTT);
            return "admin/ban_hang/trangChu";
        }

        //Kiểm tra xem đã có thông tin khách hàng chưa
        KhachHang khachHang = khachHangRepo.findBySdt(sdt);
        if (khachHang == null) {
            //Thêm mới khách hàng và add khách hàng vào hóa đơn
            //Tạo mã khách hàng tự động
            Random random = new Random();
            String[] mangKyTu = {
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                    "0123456789", "0123456789", "0123456789", "0123456789", "0123456789"
            };
            StringBuilder ma = new StringBuilder();
            for (String mang : mangKyTu) {
                char randomChar = mang.charAt(random.nextInt(mang.length()));
                ma.append(randomChar);
            }

            KhachHang khachHang1 = new KhachHang();
            khachHang1.setMa(ma + "");
            khachHang1.setTen(ten);
            khachHang1.setSdt(sdt);
            khachHang1.setTrangThai(1);
            khachHangRepo.save(khachHang1);

            //Add khách hàng vào hóa đơn
            HoaDon hoaDon = hoaDonRepo.findById(UserInfor.idHD).get();
            hoaDon.setId(hoaDon.getId());
            hoaDon.setIdKH(khachHang1);
            hoaDonRepo.save(hoaDon);

            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("kh", khachHang1);
            model.addAttribute("pageCTSP", pageCTSP);

            UserInfor.idKH = -1;
            return "redirect:/store/ghDetail/" + UserInfor.idHD;
        } else {
            //Nếu tìm thấy khách hàng thì add khách hàng vào hóa đơn
            //Add khách hàng vào hóa đơn
            HoaDon hoaDon = hoaDonRepo.findById(UserInfor.idHD).get();
            hoaDon.setId(hoaDon.getId());
            hoaDon.setIdKH(khachHang);
            hoaDonRepo.save(hoaDon);

            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("kh", khachHang);
            model.addAttribute("pageCTSP", pageCTSP);

            UserInfor.idKH = -1;
            return "redirect:/store/ghDetail/" + UserInfor.idHD;
        }
    }


    //Thanh toán
    @PostMapping("thanh-toan")
    public String thanhToan(
            @RequestParam("tienKhachDua") String tienKhachDua,
            @RequestParam("thanhTien") String thanhTien,
            @RequestParam("page") Optional<Integer> pageParam,
            Model model
    ) {
        // Hiển thị thông tin nhân viên đăng nhập
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
        model.addAttribute("nhanVien", nhanVien);

        int idhd;
        boolean checkout = false;
        String mess = "";
        //Hiển thị danh sách hóa đơn có trạng thái chờ thanh toán
        List<HoaDon> listHoaDonByTT = hoaDonRepo.findByTrangThai(HoaDonRepository.CHO_THANH_TOAN);

        //Hiển thị danh sách chi tiết sản phẩm theo trạng thái còn hàng và phân trang
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamRepo.findByTrangThai(ChiTietSanPhamRepository.CON_HANG, pageable);

        if (UserInfor.idHD < 0) {
            //Thông báo chưa chọn hóa đơn
            int checkk = 1;
            model.addAttribute("checkk", checkk);
            model.addAttribute("pageCTSP", pageCTSP);
            model.addAttribute("listHoaDon", listHoaDonByTT);
            return "admin/ban_hang/trangChu";
        }

        //Lấy ra thông tin hóa đơn để hiển thị
        HoaDon hoaDon = hoaDonRepo.findById(UserInfor.idHD).get();

        //Lấy ra thông tin chi tiết hóa đơn theo idHD hiển thị
        List<HoaDonChiTiet> listHDCTByIdHD = hoaDonChiTietRepo.findByIdHD(UserInfor.idHD);

        if (tienKhachDua.equals("")) {
            //Lấy ra thông tin thành tiền để hiển thị
            BigDecimal tongT = null;
            BigDecimal thanhT = new BigDecimal(BigInteger.ZERO);
            for (HoaDonChiTiet hdct : hoaDonChiTietRepo.findByIdHD(UserInfor.idHD)) {
                tongT = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
                thanhT = thanhT.add(tongT);
            }
            model.addAttribute("thanhTien", thanhTien);

            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);

            checkout = true;
            boolean check = true;
            idhd = UserInfor.idHD;
            mess = "Hãy nhập tiền khách đưa";

            model.addAttribute("checkout", checkout);
            model.addAttribute("check", check);
            model.addAttribute("mess", mess);
            model.addAttribute("idhd", idhd);
            model.addAttribute("pageCTSP", pageCTSP);
            model.addAttribute("listHoaDon", listHoaDonByTT);
            return "admin/ban_hang/trangChu";
        }

        if (Double.parseDouble(tienKhachDua) - Double.parseDouble(thanhTien) < 0) {
            //Lấy ra thông tin thành tiền để hiển thị
            BigDecimal tongT = null;
            BigDecimal thanhT = new BigDecimal(BigInteger.ZERO);
            for (HoaDonChiTiet hdct : hoaDonChiTietRepo.findByIdHD(UserInfor.idHD)) {
                tongT = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
                thanhT = thanhT.add(tongT);
            }
            model.addAttribute("thanhTien", thanhTien);

            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);

            checkout = true;
            boolean check = true;
            idhd = UserInfor.idHD;
            mess = "Khách hàng chưa trả đủ tiền";
            model.addAttribute("checkout", checkout);
            model.addAttribute("check", check);
            model.addAttribute("mess", mess);
            model.addAttribute("idhd", idhd);
            model.addAttribute("pageCTSP", pageCTSP);
            model.addAttribute("listHoaDon", listHoaDonByTT);
            return "admin/ban_hang/trangChu";
        }

        BigDecimal tt = new BigDecimal(thanhTien);
        BigDecimal tkd = new BigDecimal(tienKhachDua);
        BigDecimal tienTraKhach = BigDecimal.ZERO;

        //Thanh toán thành công và set lại trạng thái hóa đơn
        mess = "Thanh toán thành công";
        hoaDon.setId(hoaDon.getId());
        hoaDon.setTrangThai(HoaDonRepository.DA_THANH_TOAN);
        hoaDonRepo.save(hoaDon);
        tienTraKhach = tkd.subtract(tt);
        model.addAttribute("hoaDon", hoaDon);

        //Tính tổng tiền của hóa đơn chi tiết
        BigDecimal tongT = null;
        BigDecimal thanhT = new BigDecimal(BigInteger.ZERO);
        for (HoaDonChiTiet hdct : listHDCTByIdHD) {
            tongT = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
            thanhT = thanhT.add(tongT);
        }
        model.addAttribute("thanhTien", thanhT);


        //update lại danh sách hóa đơn chi tiết  = 1
        for (HoaDonChiTiet hdct : listHDCTByIdHD) {
            hdct.setId(hdct.getId());
            hdct.setTrangThai(HoaDonChiTietRepository.DA_THANH_TOAN);
            hoaDonChiTietRepo.save(hdct);
        }

        model.addAttribute("checkout", checkout);
        model.addAttribute("mess", mess);
        model.addAttribute("tienTraKhach", tienTraKhach);
        model.addAttribute("pageCTSP", pageCTSP);
        model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
        model.addAttribute("listHoaDon", listHoaDonByTT);
        UserInfor.idHD = -1;
        return "admin/ban_hang/trangChu";
    }


    //    ------------ Quản lý hóa đơn ------------------
    @GetMapping("quan-ly-hoa-don")
    public String getQLHoaDon(
            Model model,
            @RequestParam("page") Optional<Integer> pageParam
    ) {
        if (UserInfor.idNV < 0) {
            return "admin/ban_hang/qlHoaDon";
        }
        // Hiển thị thông tin nhân viên đăng nhập
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
        model.addAttribute("nhanVien", nhanVien);

        //Phân trang hóa đơn
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 5);
        Page<HoaDon> pageHoaDon = hoaDonRepo.findByTrangThai(HoaDonRepository.DA_THANH_TOAN, pageable);

        model.addAttribute("pageHoaDon", pageHoaDon);
        return "admin/ban_hang/qlHoaDon";
    }

    @GetMapping("hdDetail/{idHD}")
    public String getHoaDonChiTiet(
            @PathVariable("idHD") int idHD,
            @RequestParam("page") Optional<Integer> pageParam,
            Model model
    ) {

        // Hiển thị thông tin nhân viên đăng nhập
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNV).get();
        model.addAttribute("nhanVien", nhanVien);

        //Phân trang hóa đơn
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 5);
        Page<HoaDon> pageHoaDon = hoaDonRepo.findByTrangThai(HoaDonRepository.DA_THANH_TOAN, pageable);

        boolean check = true;
        BigDecimal tongTien = null;
        BigDecimal thanhTien = new BigDecimal(0);

        List<HoaDon> listHoaDonTT1 = hoaDonRepo.findByTrangThai(HoaDonRepository.DA_THANH_TOAN);

        List<HoaDonChiTiet> listHDCTByIdHD = hoaDonChiTietRepo.findByIdHD(idHD);
        for (HoaDonChiTiet hdct : listHDCTByIdHD) {
            tongTien = hdct.getDonGia().multiply(new BigDecimal(hdct.getSoLuong()));
            thanhTien = thanhTien.add(tongTien);
        }
        model.addAttribute("check", check);
        model.addAttribute("thanhTien", thanhTien);
        model.addAttribute("pageHoaDon", pageHoaDon);
        model.addAttribute("listHoaDonChiTiet", listHDCTByIdHD);
        return "admin/ban_hang/qlHoaDon";
    }
}
