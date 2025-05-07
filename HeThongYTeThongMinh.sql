CREATE DATABASE HeThongYTeThongMinh
ON
(
    NAME = N'HeThongYTe_Data',
    FILENAME = N'E:\app_code\SQL\db\HeThongYTeThongMinh_Data.mdf',
    SIZE = 20MB,
    FILEGROWTH = 10MB
)
LOG ON
(
    NAME = N'HeThongYTe_Log',
    FILENAME = N'E:\app_code\SQL\db\HeThongYTeThongMinh_Log.ldf',
    SIZE = 10MB,
    FILEGROWTH = 5MB
);
GO

USE HeThongYTeThongMinh
GO

-- Bảng Khoa
CREATE TABLE Khoa (
    id INT PRIMARY KEY IDENTITY,
    tenKhoa NVARCHAR(100),
    moTa NVARCHAR(255)
);

-- Bảng Chuyên Khoa
CREATE TABLE ChuyenKhoa (
    id INT PRIMARY KEY IDENTITY,
    tenChuyenKhoa NVARCHAR(100),
    moTa NVARCHAR(255),
    khoaID INT,
    FOREIGN KEY (khoaID) REFERENCES Khoa(id)
);

-- Bảng Phòng Khám
CREATE TABLE PhongKham (
    id INT PRIMARY KEY IDENTITY,
    tenPhong NVARCHAR(100),
    chuyenKhoaID INT,
    FOREIGN KEY (chuyenKhoaID) REFERENCES ChuyenKhoa(id)
);

-- Bảng Thành Viên
CREATE TABLE ThanhVien (
    id INT PRIMARY KEY IDENTITY,
    soDienThoai NVARCHAR(20),
    hoTen NVARCHAR(100),
    diaChi NVARCHAR(255),
    email NVARCHAR(100),
    tenDangNhap NVARCHAR(50),
    matKhau NVARCHAR(100)
);

-- Bảng Bệnh Nhân
CREATE TABLE BenhNhan (
    id INT PRIMARY KEY,
    ngaySinh DATE,
    danToc NVARCHAR(50),
    FOREIGN KEY (id) REFERENCES ThanhVien(id)
);

-- Bảng Chức Vụ
CREATE TABLE ChucVu (
    id INT PRIMARY KEY IDENTITY,
    tenChucVu NVARCHAR(50)
);

-- Bảng Nhân Viên
CREATE TABLE NhanVien (
    id INT PRIMARY KEY,
    chucVuID INT,
    FOREIGN KEY (id) REFERENCES ThanhVien(id),
    FOREIGN KEY (chucVuID) REFERENCES ChucVu(id)
);

-- Bảng Bác Sĩ
CREATE TABLE BacSi (
    id INT PRIMARY KEY,
    chuyenKhoaID INT,
    phongKhamID INT,
    bangCap NVARCHAR(255),
    kinhNghiem INT,
    FOREIGN KEY (id) REFERENCES NhanVien(id),
    FOREIGN KEY (chuyenKhoaID) REFERENCES ChuyenKhoa(id),
    FOREIGN KEY (phongKhamID) REFERENCES PhongKham(id)
);

-- Bảng Điều Dưỡng
CREATE TABLE DieuDuong (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES NhanVien(id)
);

-- Bảng Lịch Làm Việc
CREATE TABLE LichLamViec (
    id INT PRIMARY KEY IDENTITY,
    bacSiID INT,
    ngay DATE,
    gioBatDau TIME,
    gioKetThuc TIME,
    FOREIGN KEY (bacSiID) REFERENCES BacSi(id)
);

-- Bảng Lịch Hẹn Khám
CREATE TABLE LichHenKham (
    id INT PRIMARY KEY IDENTITY,
    thoiGian DATETIME,
    benhNhanID INT,
    bacSiID INT,
    phongKhamID INT,
    trangThai NVARCHAR(50),
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id),
    FOREIGN KEY (bacSiID) REFERENCES BacSi(id),
    FOREIGN KEY (phongKhamID) REFERENCES PhongKham(id)
);

-- Bảng Cuộc Gọi Video
CREATE TABLE CuocGoiVideo (
    id INT PRIMARY KEY IDENTITY,
    thoiGian DATETIME,
    benhNhanID INT,
    bacSiID INT,
    trangThai NVARCHAR(50),
    linkCuocGoi NVARCHAR(255),
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id),
    FOREIGN KEY (bacSiID) REFERENCES BacSi(id)
);

-- Bảng Lịch Tiêm
CREATE TABLE LichTiem (
    id INT PRIMARY KEY IDENTITY,
    benhNhanID INT,
    ngayTiem DATETIME,
    loaiVaccine NVARCHAR(100),
    diaDiem NVARCHAR(255),
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id)
);

-- Bảng Dịch Vụ Tại Nhà
CREATE TABLE DichVuTaiNha (
    id INT PRIMARY KEY IDENTITY,
    benhNhanID INT,
    loaiDichVu NVARCHAR(100),
    ngayGio DATETIME,
    diaChi NVARCHAR(255),
    trangThai NVARCHAR(50),
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id)
);

-- Bảng Gói Khám
CREATE TABLE GoiKham (
    id INT PRIMARY KEY IDENTITY,
    tenGoi NVARCHAR(100),
    moTa NVARCHAR(255),
    gia MONEY
);

-- Bảng Đặt Gói Khám
CREATE TABLE DatGoiKham (
    id INT PRIMARY KEY IDENTITY,
    benhNhanID INT,
    goiKhamID INT,
    ngayDangKy DATE,
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id),
    FOREIGN KEY (goiKhamID) REFERENCES GoiKham(id)
);

-- Bảng Hồ Sơ Bệnh Án
CREATE TABLE HoSoBenhAn (
    id INT PRIMARY KEY IDENTITY,
    ngay DATE,
    tinhTrang NVARCHAR(255),
    ketLuan NVARCHAR(255),
    benhNhanID INT,
    bacSiID INT,
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id),
    FOREIGN KEY (bacSiID) REFERENCES BacSi(id)
);

-- Bảng Đơn Thuốc
CREATE TABLE DonThuoc (
    id INT PRIMARY KEY IDENTITY,
    hoSoID INT,
    ngayKe DATE,
    FOREIGN KEY (hoSoID) REFERENCES HoSoBenhAn(id)
);

-- Bảng Thuốc
CREATE TABLE Thuoc (
    id INT PRIMARY KEY IDENTITY,
    tenThuoc NVARCHAR(100),
    donVi NVARCHAR(50),
    lieuLuong NVARCHAR(50),
    tacDung NVARCHAR(255)
);

-- Bảng Chi Tiết Đơn Thuốc
CREATE TABLE ChiTietDonThuoc (
    id INT PRIMARY KEY IDENTITY,
    donThuocID INT,
    thuocID INT,
    soLuong INT,
    huongDan NVARCHAR(255),
    FOREIGN KEY (donThuocID) REFERENCES DonThuoc(id),
    FOREIGN KEY (thuocID) REFERENCES Thuoc(id)
);

-- Bảng Xét Nghiệm
CREATE TABLE XetNghiem (
    id INT PRIMARY KEY IDENTITY,
    hoSoID INT,
    loaiXetNghiem NVARCHAR(100),
    ketQua NVARCHAR(255),
    thoiGian DATETIME,
    FOREIGN KEY (hoSoID) REFERENCES HoSoBenhAn(id)
);

-- Bảng Hóa Đơn
CREATE TABLE HoaDon (
    id INT PRIMARY KEY IDENTITY,
    benhNhanID INT,
    ngayLap DATE,
    tongTien MONEY,
    hinhThucThanhToan NVARCHAR(50),
    trangThai NVARCHAR(50),
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id)
);

-- Bảng Đơn Mua Thuốc
CREATE TABLE DonMuaThuoc (
    id INT PRIMARY KEY IDENTITY,
    benhNhanID INT,
    ngayDat DATE,
    tongTien MONEY,
    diaChiGiao NVARCHAR(255),
    trangThai NVARCHAR(50),
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id)
);

-- Bảng Chi Tiết Đơn Mua
CREATE TABLE ChiTietDonMua (
    id INT PRIMARY KEY IDENTITY,
    donMuaID INT,
    thuocID INT,
    soLuong INT,
    donGia MONEY,
    FOREIGN KEY (donMuaID) REFERENCES DonMuaThuoc(id),
    FOREIGN KEY (thuocID) REFERENCES Thuoc(id)
);

-- Bảng Đánh Giá
CREATE TABLE DanhGia (
    id INT PRIMARY KEY IDENTITY,
    benhNhanID INT,
    bacSiID INT,
    diem INT,
    nhanXet NVARCHAR(255),
    ngayDanhGia DATE,
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id),
    FOREIGN KEY (bacSiID) REFERENCES BacSi(id)
);

-- Bảng Bảo Hiểm Y Tế
CREATE TABLE BaoHiemYTe (
    id INT PRIMARY KEY IDENTITY,
    benhNhanID INT,
    maBaoHiem NVARCHAR(50),
    ngayCap DATE,
    ngayHetHan DATE,
    noiDangKy NVARCHAR(255),
    FOREIGN KEY (benhNhanID) REFERENCES BenhNhan(id)
); 