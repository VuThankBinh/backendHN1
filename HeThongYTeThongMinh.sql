USE [HeThongDatLichKhamBenh]
GO
/****** Object:  Table [dbo].[nguoi_dung]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nguoi_dung](
	[id_nguoi_dung] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[mat_khau] [varchar](255) NOT NULL,
	[ho_ten] [nvarchar](100) NOT NULL,
	[so_dien_thoai] [varchar](15) NOT NULL,
	[dia_chi] [nvarchar](255) NULL,
	[ngay_sinh] [date] NULL,
	[gioi_tinh] [varchar](10) NULL,
	[cccd] [varchar](20) NULL,
	[vai_tro] [varchar](20) NOT NULL,
	[da_kich_hoat] [bit] NULL,
	[ngay_tao] [datetime] NULL,
	[ngay_cap_nhat] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_nguoi_dung] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[cccd] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHOA]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHOA](
	[id_khoa] [int] IDENTITY(1,1) NOT NULL,
	[ten_khoa] [nvarchar](100) NOT NULL,
	[mo_ta] [text] NULL,
	[da_kich_hoat] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_khoa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHUYEN_KHOA]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHUYEN_KHOA](
	[id_chuyen_khoa] [int] IDENTITY(1,1) NOT NULL,
	[id_khoa] [int] NOT NULL,
	[ten_chuyen_khoa] [nvarchar](100) NOT NULL,
	[mo_ta] [text] NULL,
	[da_kich_hoat] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_chuyen_khoa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BAC_SI]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BAC_SI](
	[id_bac_si] [int] IDENTITY(1,1) NOT NULL,
	[id_nguoi_dung] [int] NOT NULL,
	[id_chuyen_khoa] [int] NOT NULL,
	[id_khoa] [int] NOT NULL,
	[chuyen_mon] [nvarchar](100) NULL,
	[so_giay_phep] [varchar](50) NULL,
	[so_nam_kinh_nghiem] [int] NULL,
	[dang_lam_viec] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_bac_si] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[id_nguoi_dung] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[so_giay_phep] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LICH_LAM_VIEC]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LICH_LAM_VIEC](
	[id_lich_lam_viec] [int] IDENTITY(1,1) NOT NULL,
	[loai_nhan_vien] [varchar](20) NOT NULL,
	[id_nhan_vien] [int] NOT NULL,
	[ngay_lam_viec] [date] NOT NULL,
	[gio_bat_dau] [time](7) NOT NULL,
	[gio_ket_thuc] [time](7) NOT NULL,
	[trang_thai] [varchar](20) NULL,
	[ghi_chu] [text] NULL,
	[id_nguoi_tao] [int] NOT NULL,
	[ngay_tao] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_lich_lam_viec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  View [dbo].[vw_lich_bac_si]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- View xem lịch bác sĩ
CREATE VIEW [dbo].[vw_lich_bac_si] AS
SELECT 
    b.id_bac_si,
    n.ho_ten AS ten_bac_si,
    c.ten_chuyen_khoa,
    k.ten_khoa,
    l.ngay_lam_viec,
    l.gio_bat_dau,
    l.gio_ket_thuc,
    l.trang_thai
FROM BAC_SI b
JOIN NGUOI_DUNG n ON b.id_nguoi_dung = n.id_nguoi_dung
JOIN CHUYEN_KHOA c ON b.id_chuyen_khoa = c.id_chuyen_khoa
JOIN KHOA k ON b.id_khoa = k.id_khoa
LEFT JOIN LICH_LAM_VIEC l ON b.id_bac_si = l.id_nhan_vien AND l.loai_nhan_vien = 'BAC_SI'
WHERE n.da_kich_hoat = 1 AND b.dang_lam_viec = 1;
GO
/****** Object:  Table [dbo].[LE_TAN]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LE_TAN](
	[id_le_tan] [int] IDENTITY(1,1) NOT NULL,
	[id_nguoi_dung] [int] NOT NULL,
	[ca_lam_viec] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_le_tan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[id_nguoi_dung] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHONG]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHONG](
	[id_phong] [int] IDENTITY(1,1) NOT NULL,
	[id_khoa] [int] NOT NULL,
	[so_phong] [varchar](20) NOT NULL,
	[tang] [int] NULL,
	[trang_thai] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_phong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LICH_LE_TAN]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LICH_LE_TAN](
	[id_lich_le_tan] [int] IDENTITY(1,1) NOT NULL,
	[id_le_tan] [int] NOT NULL,
	[ngay_lam_viec] [date] NOT NULL,
	[gio_bat_dau] [time](7) NOT NULL,
	[gio_ket_thuc] [time](7) NOT NULL,
	[id_phong_duoc_phan] [int] NULL,
	[trang_thai] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_lich_le_tan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[vw_lich_le_tan]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- View xem lịch lễ tân
CREATE VIEW [dbo].[vw_lich_le_tan] AS
SELECT 
    l.id_le_tan,
    n.ho_ten AS ten_le_tan,
    lt.ngay_lam_viec,
    lt.gio_bat_dau,
    lt.gio_ket_thuc,
    p.so_phong,
    p.tang,
    lt.trang_thai
FROM LE_TAN l
JOIN NGUOI_DUNG n ON l.id_nguoi_dung = n.id_nguoi_dung
JOIN LICH_LE_TAN lt ON l.id_le_tan = lt.id_le_tan
LEFT JOIN PHONG p ON lt.id_phong_duoc_phan = p.id_phong
WHERE n.da_kich_hoat = 1;
GO
/****** Object:  Table [dbo].[BENH_NHAN]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BENH_NHAN](
	[id_benh_nhan] [int] IDENTITY(1,1) NOT NULL,
	[id_nguoi_dung] [int] NOT NULL,
	[ma_bhyt] [varchar](20) NULL,
	[ten_nguoi_lien_he] [nvarchar](100) NULL,
	[sdt_nguoi_lien_he] [varchar](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_benh_nhan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[id_nguoi_dung] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHI_TIET_DON_THUOC]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHI_TIET_DON_THUOC](
	[id_chi_tiet_don_thuoc] [int] IDENTITY(1,1) NOT NULL,
	[id_don_thuoc] [int] NOT NULL,
	[id_thuoc] [int] NOT NULL,
	[lieu_luong] [nvarchar](100) NOT NULL,
	[so_luong] [int] NOT NULL,
	[huong_dan] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_chi_tiet_don_thuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DANH_GIA]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DANH_GIA](
	[id_danh_gia] [int] IDENTITY(1,1) NOT NULL,
	[id_lich_hen] [int] NOT NULL,
	[id_benh_nhan] [int] NOT NULL,
	[id_bac_si] [int] NOT NULL,
	[diem] [tinyint] NULL,
	[binh_luan] [text] NULL,
	[ngay_tao] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_danh_gia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DICH_VU]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DICH_VU](
	[id_dich_vu] [int] IDENTITY(1,1) NOT NULL,
	[ten_dich_vu] [nvarchar](100) NOT NULL,
	[id_chuyen_khoa] [int] NULL,
	[mo_ta] [text] NULL,
	[gia] [decimal](12, 2) NOT NULL,
	[thoi_gian_du_kien] [int] NULL,
	[da_kich_hoat] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_dich_vu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DON_THUOC]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DON_THUOC](
	[id_don_thuoc] [int] IDENTITY(1,1) NOT NULL,
	[id_lich_hen] [int] NOT NULL,
	[id_bac_si] [int] NOT NULL,
	[chan_doan] [text] NULL,
	[ghi_chu] [text] NULL,
	[ngay_tao] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_don_thuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HO_SO_BENH_AN]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HO_SO_BENH_AN](
	[id_ho_so_benh_an] [int] IDENTITY(1,1) NOT NULL,
	[id_benh_nhan] [int] NOT NULL,
	[id_lich_hen] [int] NULL,
	[trieu_chung] [text] NULL,
	[chan_doan] [text] NULL,
	[dieu_tri] [text] NULL,
	[ghi_chu] [text] NULL,
	[ngay_tao] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_ho_so_benh_an] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KET_QUA_XET_NGHIEM]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KET_QUA_XET_NGHIEM](
	[id_ket_qua_xet_nghiem] [int] IDENTITY(1,1) NOT NULL,
	[id_lich_hen] [int] NOT NULL,
	[loai_xet_nghiem] [nvarchar](100) NOT NULL,
	[ket_qua] [text] NULL,
	[ghi_chu] [text] NULL,
	[duong_dan_file] [varchar](255) NULL,
	[ngay_tao] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_ket_qua_xet_nghiem] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LICH_BAC_SI]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LICH_BAC_SI](
	[id_lich_bac_si] [int] IDENTITY(1,1) NOT NULL,
	[id_bac_si] [int] NOT NULL,
	[thu] [varchar](10) NULL,
	[gio_bat_dau] [time](7) NOT NULL,
	[gio_ket_thuc] [time](7) NOT NULL,
	[dang_kha_dung] [bit] NULL,
	[la_lich_lap] [bit] NULL,
	[cac_ngay_lap] [varchar](20) NULL,
	[ngay_bat_dau] [date] NULL,
	[ngay_ket_thuc] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_lich_bac_si] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LICH_HEN]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LICH_HEN](
	[id_lich_hen] [int] IDENTITY(1,1) NOT NULL,
	[id_benh_nhan] [int] NOT NULL,
	[id_bac_si] [int] NULL,
	[id_dich_vu] [int] NOT NULL,
	[id_khoa] [int] NOT NULL,
	[id_phong] [int] NULL,
	[ngay_hen] [date] NOT NULL,
	[gio_bat_dau] [time](7) NOT NULL,
	[gio_ket_thuc] [time](7) NOT NULL,
	[trang_thai] [varchar](20) NULL,
	[ghi_chu] [text] NULL,
	[la_kham_online] [bit] NULL,
	[link_video_call] [varchar](255) NULL,
	[ngay_tao] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_lich_hen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QUAN_TRI]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QUAN_TRI](
	[id_quan_tri] [int] IDENTITY(1,1) NOT NULL,
	[id_nguoi_dung] [int] NOT NULL,
	[muc_quyen] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_quan_tri] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[id_nguoi_dung] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[THANH_TOAN]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THANH_TOAN](
	[id_thanh_toan] [int] IDENTITY(1,1) NOT NULL,
	[id_lich_hen] [int] NOT NULL,
	[so_tien] [decimal](12, 2) NOT NULL,
	[phuong_thuc] [varchar](20) NULL,
	[trang_thai] [varchar](20) NULL,
	[ma_giao_dich] [varchar](100) NULL,
	[ngay_thanh_toan] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_thanh_toan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[THUOC]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THUOC](
	[id_thuoc] [int] IDENTITY(1,1) NOT NULL,
	[ten_thuoc] [nvarchar](100) NOT NULL,
	[mo_ta] [text] NULL,
	[don_vi] [varchar](20) NULL,
	[gia] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_thuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[BAC_SI] ADD  DEFAULT ((1)) FOR [dang_lam_viec]
GO
ALTER TABLE [dbo].[CHUYEN_KHOA] ADD  DEFAULT ((1)) FOR [da_kich_hoat]
GO
ALTER TABLE [dbo].[DANH_GIA] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[DICH_VU] ADD  DEFAULT ((30)) FOR [thoi_gian_du_kien]
GO
ALTER TABLE [dbo].[DICH_VU] ADD  DEFAULT ((1)) FOR [da_kich_hoat]
GO
ALTER TABLE [dbo].[DON_THUOC] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[HO_SO_BENH_AN] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[KET_QUA_XET_NGHIEM] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[KHOA] ADD  DEFAULT ((1)) FOR [da_kich_hoat]
GO
ALTER TABLE [dbo].[LICH_BAC_SI] ADD  DEFAULT ((1)) FOR [dang_kha_dung]
GO
ALTER TABLE [dbo].[LICH_BAC_SI] ADD  DEFAULT ((0)) FOR [la_lich_lap]
GO
ALTER TABLE [dbo].[LICH_HEN] ADD  DEFAULT ((0)) FOR [la_kham_online]
GO
ALTER TABLE [dbo].[LICH_HEN] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[LICH_LAM_VIEC] ADD  DEFAULT ('SAN_SANG') FOR [trang_thai]
GO
ALTER TABLE [dbo].[LICH_LAM_VIEC] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[LICH_LE_TAN] ADD  DEFAULT ('DANG_LAM') FOR [trang_thai]
GO
ALTER TABLE [dbo].[nguoi_dung] ADD  DEFAULT ((1)) FOR [da_kich_hoat]
GO
ALTER TABLE [dbo].[nguoi_dung] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[nguoi_dung] ADD  DEFAULT (getdate()) FOR [ngay_cap_nhat]
GO
ALTER TABLE [dbo].[THANH_TOAN] ADD  DEFAULT (getdate()) FOR [ngay_thanh_toan]
GO
ALTER TABLE [dbo].[BAC_SI]  WITH CHECK ADD FOREIGN KEY([id_chuyen_khoa])
REFERENCES [dbo].[CHUYEN_KHOA] ([id_chuyen_khoa])
GO
ALTER TABLE [dbo].[BAC_SI]  WITH CHECK ADD FOREIGN KEY([id_khoa])
REFERENCES [dbo].[KHOA] ([id_khoa])
GO
ALTER TABLE [dbo].[BAC_SI]  WITH CHECK ADD FOREIGN KEY([id_nguoi_dung])
REFERENCES [dbo].[nguoi_dung] ([id_nguoi_dung])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BENH_NHAN]  WITH CHECK ADD FOREIGN KEY([id_nguoi_dung])
REFERENCES [dbo].[nguoi_dung] ([id_nguoi_dung])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CHI_TIET_DON_THUOC]  WITH CHECK ADD FOREIGN KEY([id_don_thuoc])
REFERENCES [dbo].[DON_THUOC] ([id_don_thuoc])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CHI_TIET_DON_THUOC]  WITH CHECK ADD FOREIGN KEY([id_thuoc])
REFERENCES [dbo].[THUOC] ([id_thuoc])
GO
ALTER TABLE [dbo].[CHUYEN_KHOA]  WITH CHECK ADD FOREIGN KEY([id_khoa])
REFERENCES [dbo].[KHOA] ([id_khoa])
GO
ALTER TABLE [dbo].[DANH_GIA]  WITH CHECK ADD FOREIGN KEY([id_bac_si])
REFERENCES [dbo].[BAC_SI] ([id_bac_si])
GO
ALTER TABLE [dbo].[DANH_GIA]  WITH CHECK ADD FOREIGN KEY([id_benh_nhan])
REFERENCES [dbo].[BENH_NHAN] ([id_benh_nhan])
GO
ALTER TABLE [dbo].[DANH_GIA]  WITH CHECK ADD FOREIGN KEY([id_lich_hen])
REFERENCES [dbo].[LICH_HEN] ([id_lich_hen])
GO
ALTER TABLE [dbo].[DICH_VU]  WITH CHECK ADD FOREIGN KEY([id_chuyen_khoa])
REFERENCES [dbo].[CHUYEN_KHOA] ([id_chuyen_khoa])
GO
ALTER TABLE [dbo].[DON_THUOC]  WITH CHECK ADD FOREIGN KEY([id_bac_si])
REFERENCES [dbo].[BAC_SI] ([id_bac_si])
GO
ALTER TABLE [dbo].[DON_THUOC]  WITH CHECK ADD FOREIGN KEY([id_lich_hen])
REFERENCES [dbo].[LICH_HEN] ([id_lich_hen])
GO
ALTER TABLE [dbo].[HO_SO_BENH_AN]  WITH CHECK ADD FOREIGN KEY([id_benh_nhan])
REFERENCES [dbo].[BENH_NHAN] ([id_benh_nhan])
GO
ALTER TABLE [dbo].[HO_SO_BENH_AN]  WITH CHECK ADD FOREIGN KEY([id_lich_hen])
REFERENCES [dbo].[LICH_HEN] ([id_lich_hen])
GO
ALTER TABLE [dbo].[KET_QUA_XET_NGHIEM]  WITH CHECK ADD FOREIGN KEY([id_lich_hen])
REFERENCES [dbo].[LICH_HEN] ([id_lich_hen])
GO
ALTER TABLE [dbo].[LE_TAN]  WITH CHECK ADD FOREIGN KEY([id_nguoi_dung])
REFERENCES [dbo].[nguoi_dung] ([id_nguoi_dung])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LICH_BAC_SI]  WITH CHECK ADD FOREIGN KEY([id_bac_si])
REFERENCES [dbo].[BAC_SI] ([id_bac_si])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LICH_HEN]  WITH CHECK ADD FOREIGN KEY([id_bac_si])
REFERENCES [dbo].[BAC_SI] ([id_bac_si])
GO
ALTER TABLE [dbo].[LICH_HEN]  WITH CHECK ADD FOREIGN KEY([id_benh_nhan])
REFERENCES [dbo].[BENH_NHAN] ([id_benh_nhan])
GO
ALTER TABLE [dbo].[LICH_HEN]  WITH CHECK ADD FOREIGN KEY([id_dich_vu])
REFERENCES [dbo].[DICH_VU] ([id_dich_vu])
GO
ALTER TABLE [dbo].[LICH_HEN]  WITH CHECK ADD FOREIGN KEY([id_khoa])
REFERENCES [dbo].[KHOA] ([id_khoa])
GO
ALTER TABLE [dbo].[LICH_HEN]  WITH CHECK ADD FOREIGN KEY([id_phong])
REFERENCES [dbo].[PHONG] ([id_phong])
GO
ALTER TABLE [dbo].[LICH_LAM_VIEC]  WITH CHECK ADD FOREIGN KEY([id_nguoi_tao])
REFERENCES [dbo].[QUAN_TRI] ([id_quan_tri])
GO
ALTER TABLE [dbo].[LICH_LE_TAN]  WITH CHECK ADD FOREIGN KEY([id_le_tan])
REFERENCES [dbo].[LE_TAN] ([id_le_tan])
GO
ALTER TABLE [dbo].[LICH_LE_TAN]  WITH CHECK ADD FOREIGN KEY([id_phong_duoc_phan])
REFERENCES [dbo].[PHONG] ([id_phong])
GO
ALTER TABLE [dbo].[PHONG]  WITH CHECK ADD FOREIGN KEY([id_khoa])
REFERENCES [dbo].[KHOA] ([id_khoa])
GO
ALTER TABLE [dbo].[QUAN_TRI]  WITH CHECK ADD FOREIGN KEY([id_nguoi_dung])
REFERENCES [dbo].[nguoi_dung] ([id_nguoi_dung])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[THANH_TOAN]  WITH CHECK ADD FOREIGN KEY([id_lich_hen])
REFERENCES [dbo].[LICH_HEN] ([id_lich_hen])
GO
ALTER TABLE [dbo].[DANH_GIA]  WITH CHECK ADD CHECK  (([diem]>=(1) AND [diem]<=(5)))
GO
ALTER TABLE [dbo].[LE_TAN]  WITH CHECK ADD CHECK  (([ca_lam_viec]='TOI' OR [ca_lam_viec]='CHIEU' OR [ca_lam_viec]='SANG'))
GO
ALTER TABLE [dbo].[LICH_BAC_SI]  WITH CHECK ADD CHECK  (([thu]='CN' OR [thu]='THU_7' OR [thu]='THU_6' OR [thu]='THU_5' OR [thu]='THU_4' OR [thu]='THU_3' OR [thu]='THU_2'))
GO
ALTER TABLE [dbo].[LICH_HEN]  WITH CHECK ADD CHECK  (([trang_thai]='KHONG_DEN' OR [trang_thai]='DA_HUY' OR [trang_thai]='HOAN_THANH' OR [trang_thai]='DA_XAC_NHAN' OR [trang_thai]='CHO_XAC_NHAN'))
GO
ALTER TABLE [dbo].[LICH_LAM_VIEC]  WITH CHECK ADD CHECK  (([loai_nhan_vien]='LE_TAN' OR [loai_nhan_vien]='BAC_SI'))
GO
ALTER TABLE [dbo].[LICH_LAM_VIEC]  WITH CHECK ADD CHECK  (([trang_thai]='BAN' OR [trang_thai]='NGHI_PHEP' OR [trang_thai]='SAN_SANG'))
GO
ALTER TABLE [dbo].[LICH_LE_TAN]  WITH CHECK ADD CHECK  (([trang_thai]='NGHI' OR [trang_thai]='NGHI_GIAI_LAO' OR [trang_thai]='DANG_LAM'))
GO
ALTER TABLE [dbo].[nguoi_dung]  WITH CHECK ADD CHECK  (([gioi_tinh]='KHAC' OR [gioi_tinh]='NU' OR [gioi_tinh]='NAM'))
GO
ALTER TABLE [dbo].[nguoi_dung]  WITH CHECK ADD CHECK  (([vai_tro]='QUAN_TRI' OR [vai_tro]='LE_TAN' OR [vai_tro]='BAC_SI' OR [vai_tro]='BENH_NHAN'))
GO
ALTER TABLE [dbo].[PHONG]  WITH CHECK ADD CHECK  (([trang_thai]='BAO_TRI' OR [trang_thai]='DANG_SU_DUNG' OR [trang_thai]='SAN_SANG'))
GO
ALTER TABLE [dbo].[QUAN_TRI]  WITH CHECK ADD CHECK  (([muc_quyen]='HAN_CHE' OR [muc_quyen]='DAY_DU'))
GO
ALTER TABLE [dbo].[THANH_TOAN]  WITH CHECK ADD CHECK  (([phuong_thuc]='BHYT' OR [phuong_thuc]='VNPAY' OR [phuong_thuc]='MOMO' OR [phuong_thuc]='CHUYEN_KHOAN' OR [phuong_thuc]='TIEN_MAT'))
GO
ALTER TABLE [dbo].[THANH_TOAN]  WITH CHECK ADD CHECK  (([trang_thai]='HOAN_TIEN' OR [trang_thai]='THAT_BAI' OR [trang_thai]='HOAN_THANH' OR [trang_thai]='CHO_XU_LY'))
GO
/****** Object:  StoredProcedure [dbo].[sp_kiem_tra_trung_lich]    Script Date: 5/17/2025 2:27:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Stored procedure kiểm tra trùng lịch
CREATE PROCEDURE [dbo].[sp_kiem_tra_trung_lich]
    @id_nhan_vien INT,
    @loai_nhan_vien VARCHAR(20),
    @ngay_lam_viec DATE,
    @gio_bat_dau TIME,
    @gio_ket_thuc TIME,
    @id_lich_lam_viec INT = NULL -- Dùng cho trường hợp cập nhật
AS
BEGIN
    DECLARE @so_luong_trung INT;
    
    SELECT @so_luong_trung = COUNT(*)
    FROM LICH_LAM_VIEC
    WHERE id_nhan_vien = @id_nhan_vien
      AND loai_nhan_vien = @loai_nhan_vien
      AND ngay_lam_viec = @ngay_lam_viec
      AND id_lich_lam_viec <> ISNULL(@id_lich_lam_viec, -1)
      AND (
          (@gio_bat_dau < gio_ket_thuc AND @gio_ket_thuc > gio_bat_dau)
      );
    
    SELECT 
        CASE 
            WHEN @so_luong_trung > 0 THEN 1 
            ELSE 0 
        END AS co_trung_lich,
        @so_luong_trung AS so_luong_trung;
END;
GO
