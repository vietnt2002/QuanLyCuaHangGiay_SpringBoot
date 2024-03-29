/*
   Friday, January 26, 20246:01:41 PM
   User: sa
   Server: LAPTOP-BTH4R6HK\SQLEXPRESS
   Database: Java5
   Application: 
*/

/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
ALTER TABLE dbo.NhanVien
	DROP CONSTRAINT DF__NhanVien__TrangT__52593CB8
GO
CREATE TABLE dbo.Tmp_NhanVien
	(
	ID int NOT NULL IDENTITY (1, 1),
	Ten varchar(255) NOT NULL,
	Ma varchar(255) NOT NULL,
	TenDangNhap varchar(255) NOT NULL,
	MatKhau varchar(255) NOT NULL,
	TrangThai int NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_NhanVien SET (LOCK_ESCALATION = TABLE)
GO
ALTER TABLE dbo.Tmp_NhanVien ADD CONSTRAINT
	DF__NhanVien__TrangT__52593CB8 DEFAULT ((1)) FOR TrangThai
GO
SET IDENTITY_INSERT dbo.Tmp_NhanVien ON
GO
IF EXISTS(SELECT * FROM dbo.NhanVien)
	 EXEC('INSERT INTO dbo.Tmp_NhanVien (ID, Ten, Ma, TenDangNhap, MatKhau, TrangThai)
		SELECT ID, Ten, Ma, TenDangNhap, MatKhau, TrangThai FROM dbo.NhanVien WITH (HOLDLOCK TABLOCKX)')
GO
SET IDENTITY_INSERT dbo.Tmp_NhanVien OFF
GO
DROP TABLE dbo.NhanVien
GO
EXECUTE sp_rename N'dbo.Tmp_NhanVien', N'NhanVien', 'OBJECT' 
GO
ALTER TABLE dbo.NhanVien ADD CONSTRAINT
	PK__NhanVien__3214EC27943DC82C PRIMARY KEY CLUSTERED 
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
COMMIT
select Has_Perms_By_Name(N'dbo.NhanVien', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.NhanVien', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.NhanVien', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.KhachHang
	DROP CONSTRAINT DF__KhachHang__Trang__4CA06362
GO
CREATE TABLE dbo.Tmp_KhachHang
	(
	ID int NOT NULL IDENTITY (1, 1),
	Ma varchar(255) NOT NULL,
	Ten varchar(255) NOT NULL,
	SDT varchar(255) NOT NULL,
	TrangThai int NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_KhachHang SET (LOCK_ESCALATION = TABLE)
GO
ALTER TABLE dbo.Tmp_KhachHang ADD CONSTRAINT
	DF__KhachHang__Trang__4CA06362 DEFAULT ((1)) FOR TrangThai
GO
SET IDENTITY_INSERT dbo.Tmp_KhachHang ON
GO
IF EXISTS(SELECT * FROM dbo.KhachHang)
	 EXEC('INSERT INTO dbo.Tmp_KhachHang (ID, Ma, Ten, SDT, TrangThai)
		SELECT ID, Ma, Ten, SDT, TrangThai FROM dbo.KhachHang WITH (HOLDLOCK TABLOCKX)')
GO
SET IDENTITY_INSERT dbo.Tmp_KhachHang OFF
GO
DROP TABLE dbo.KhachHang
GO
EXECUTE sp_rename N'dbo.Tmp_KhachHang', N'KhachHang', 'OBJECT' 
GO
ALTER TABLE dbo.KhachHang ADD CONSTRAINT
	PK__KhachHan__3214EC27886203F4 PRIMARY KEY CLUSTERED 
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
COMMIT
select Has_Perms_By_Name(N'dbo.KhachHang', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.KhachHang', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.KhachHang', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.HoaDonChiTiet
	DROP CONSTRAINT DF__HoaDonChi__Trang__4AB81AF0
GO
CREATE TABLE dbo.Tmp_HoaDonChiTiet
	(
	ID int NOT NULL IDENTITY (1, 1),
	IdHoaDon int NOT NULL,
	IdSPCT int NOT NULL,
	SoLuong int NOT NULL,
	DonGia float(53) NOT NULL,
	ThoiGian datetime NOT NULL,
	TrangThai int NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_HoaDonChiTiet SET (LOCK_ESCALATION = TABLE)
GO
ALTER TABLE dbo.Tmp_HoaDonChiTiet ADD CONSTRAINT
	DF__HoaDonChi__Trang__4AB81AF0 DEFAULT ((1)) FOR TrangThai
GO
SET IDENTITY_INSERT dbo.Tmp_HoaDonChiTiet ON
GO
IF EXISTS(SELECT * FROM dbo.HoaDonChiTiet)
	 EXEC('INSERT INTO dbo.Tmp_HoaDonChiTiet (ID, IdHoaDon, IdSPCT, SoLuong, DonGia, ThoiGian, TrangThai)
		SELECT ID, IdHoaDon, IdSPCT, SoLuong, DonGia, ThoiGian, TrangThai FROM dbo.HoaDonChiTiet WITH (HOLDLOCK TABLOCKX)')
GO
SET IDENTITY_INSERT dbo.Tmp_HoaDonChiTiet OFF
GO
DROP TABLE dbo.HoaDonChiTiet
GO
EXECUTE sp_rename N'dbo.Tmp_HoaDonChiTiet', N'HoaDonChiTiet', 'OBJECT' 
GO
ALTER TABLE dbo.HoaDonChiTiet ADD CONSTRAINT
	PK__HoaDonCh__3214EC278F14FF91 PRIMARY KEY CLUSTERED 
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
COMMIT
select Has_Perms_By_Name(N'dbo.HoaDonChiTiet', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.HoaDonChiTiet', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.HoaDonChiTiet', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.HoaDon
	DROP CONSTRAINT DF__HoaDon__TrangTha__48CFD27E
GO
CREATE TABLE dbo.Tmp_HoaDon
	(
	ID int NOT NULL IDENTITY (1, 1),
	IdKH int NOT NULL,
	IdNV int NOT NULL,
	NgayMuaHang date NOT NULL,
	TrangThai int NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_HoaDon SET (LOCK_ESCALATION = TABLE)
GO
ALTER TABLE dbo.Tmp_HoaDon ADD CONSTRAINT
	DF__HoaDon__TrangTha__48CFD27E DEFAULT ((1)) FOR TrangThai
GO
SET IDENTITY_INSERT dbo.Tmp_HoaDon ON
GO
IF EXISTS(SELECT * FROM dbo.HoaDon)
	 EXEC('INSERT INTO dbo.Tmp_HoaDon (ID, IdKH, IdNV, NgayMuaHang, TrangThai)
		SELECT ID, IdKH, IdNV, NgayMuaHang, TrangThai FROM dbo.HoaDon WITH (HOLDLOCK TABLOCKX)')
GO
SET IDENTITY_INSERT dbo.Tmp_HoaDon OFF
GO
DROP TABLE dbo.HoaDon
GO
EXECUTE sp_rename N'dbo.Tmp_HoaDon', N'HoaDon', 'OBJECT' 
GO
ALTER TABLE dbo.HoaDon ADD CONSTRAINT
	PK__HoaDon__3214EC27EA23F87C PRIMARY KEY CLUSTERED 
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
COMMIT
select Has_Perms_By_Name(N'dbo.HoaDon', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.HoaDon', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.HoaDon', 'Object', 'CONTROL') as Contr_Per 