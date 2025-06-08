-- Create ThanhVien table
CREATE TABLE thanh_vien (
    id INT PRIMARY KEY IDENTITY(1,1),
    so_dien_thoai NVARCHAR(20) NOT NULL UNIQUE,
    ho_ten NVARCHAR(100) NOT NULL,
    dia_chi NVARCHAR(255),
    email NVARCHAR(100) UNIQUE,
    ten_dang_nhap NVARCHAR(50) NOT NULL UNIQUE,
    mat_khau NVARCHAR(255) NOT NULL,
    vai_tro NVARCHAR(50)
);

-- Create BenhNhan table
CREATE TABLE benh_nhan (
    id INT PRIMARY KEY,
    ngay_sinh DATE,
    dan_toc NVARCHAR(50),
    FOREIGN KEY (id) REFERENCES thanh_vien(id)
);

-- Create ChucVu table
CREATE TABLE chuc_vu (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten_chuc_vu NVARCHAR(50) NOT NULL
);

-- Add Khoa table if not exists
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'khoa')
BEGIN
    CREATE TABLE khoa (
        id INT PRIMARY KEY IDENTITY(1,1),
        ten_khoa NVARCHAR(100) NOT NULL,
        mo_ta NVARCHAR(255)
    );
END

-- Add ChuyenKhoa table if not exists
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'chuyen_khoa')
BEGIN
    CREATE TABLE chuyen_khoa (
        id INT PRIMARY KEY IDENTITY(1,1),
        ten_chuyen_khoa NVARCHAR(100) NOT NULL,
        mo_ta NVARCHAR(255),
        khoa_id INT NOT NULL,
        FOREIGN KEY (khoa_id) REFERENCES khoa(id)
    );
END

-- Add PhongKham table if not exists
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'phong_kham')
BEGIN
    CREATE TABLE phong_kham (
        id INT PRIMARY KEY IDENTITY(1,1),
        ten_phong NVARCHAR(100) NOT NULL,
        chuyen_khoa_id INT NOT NULL,
        FOREIGN KEY (chuyen_khoa_id) REFERENCES chuyen_khoa(id)
    );
END

-- Add BacSi table if not exists
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'bac_si')
BEGIN
    CREATE TABLE bac_si (
        id INT PRIMARY KEY IDENTITY(1,1),
        tai_khoan_id INT NOT NULL,
        chuc_vu_id INT NOT NULL,
        ten_chuc_vu NVARCHAR(50) NOT NULL,
        chuyen_khoa_id INT NOT NULL,
        phong_kham_id INT NOT NULL,
        bang_cap NVARCHAR(255),
        kinh_nghiem INT,
        FOREIGN KEY (tai_khoan_id) REFERENCES thanh_vien(id),
        FOREIGN KEY (chuc_vu_id) REFERENCES chuc_vu(id),
        FOREIGN KEY (chuyen_khoa_id) REFERENCES chuyen_khoa(id),
        FOREIGN KEY (phong_kham_id) REFERENCES phong_kham(id)
    );
END

-- Modify ThanhVien table
ALTER TABLE thanh_vien
ALTER COLUMN so_dien_thoai NVARCHAR(20) NOT NULL;

ALTER TABLE thanh_vien
ALTER COLUMN ho_ten NVARCHAR(100) NOT NULL;

ALTER TABLE thanh_vien
ALTER COLUMN ten_dang_nhap NVARCHAR(50) NOT NULL;

ALTER TABLE thanh_vien
ALTER COLUMN mat_khau NVARCHAR(255) NOT NULL;

-- Add unique constraints if not exists
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'UQ_thanh_vien_so_dien_thoai')
BEGIN
    ALTER TABLE thanh_vien ADD CONSTRAINT UQ_thanh_vien_so_dien_thoai UNIQUE (so_dien_thoai);
END

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'UQ_thanh_vien_email')
BEGIN
    ALTER TABLE thanh_vien ADD CONSTRAINT UQ_thanh_vien_email UNIQUE (email);
END

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'UQ_thanh_vien_ten_dang_nhap')
BEGIN
    ALTER TABLE thanh_vien ADD CONSTRAINT UQ_thanh_vien_ten_dang_nhap UNIQUE (ten_dang_nhp);
END

-- Modify BenhNhan table
ALTER TABLE benh_nhan
ALTER COLUMN ngay_sinh DATE;

ALTER TABLE benh_nhan
ALTER COLUMN dan_toc NVARCHAR(50);

-- Tạo bảng dich_vu
CREATE TABLE dich_vu (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten_dich_vu NVARCHAR(100) NOT NULL,
    mo_ta NVARCHAR(255),
    don_vi_tinh NVARCHAR(50) NOT NULL,
    don_gia DECIMAL(10,2) NOT NULL,
    loai_dich_vu NVARCHAR(50) NOT NULL,
    trang_thai NVARCHAR(50) NOT NULL
);

-- Tạo bảng lich_tiem
CREATE TABLE lich_tiem (
    id INT PRIMARY KEY IDENTITY(1,1),
    bac_si_id INT NOT NULL,
    loai_vaccine NVARCHAR(100) NOT NULL,
    ngay_tiem DATE NOT NULL,
    gio_tiem TIME(7) NOT NULL,
    trang_thai NVARCHAR(50) NOT NULL,
    ghi_chu NVARCHAR(255),
    FOREIGN KEY (bac_si_id) REFERENCES bac_si(id)
);

-- Tạo bảng ho_so_benh_nhan
CREATE TABLE ho_so_benh_nhan (
    ma_ho_so INT PRIMARY KEY IDENTITY(1,1),
    nguoi_dung_id INT,
    ho_ten NVARCHAR(100) NOT NULL,
    ngay_sinh NVARCHAR(10),
    gioi_tinh NVARCHAR(10),
    ma_dinh_danh NVARCHAR(20),
    so_dien_thoai NVARCHAR(15),
    nghe_nghiep NVARCHAR(50),
    email NVARCHAR(100),
    noi_thuong_tru NVARCHAR(255),
    ngay_cap NVARCHAR(255),
    FOREIGN KEY (nguoi_dung_id) REFERENCES thanh_vien(id)
);

-- Tạo bảng vaccine
CREATE TABLE vaccine (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    manufacturer NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX) NOT NULL,
    number_of_doses INT NOT NULL,
    interval_between_doses INT NOT NULL,
    contraindications NVARCHAR(MAX) NOT NULL,
    side_effects NVARCHAR(MAX) NOT NULL,
    is_active BIT NOT NULL DEFAULT 1
);

-- Tạo bảng lịch tiêm chủng
CREATE TABLE vaccination_calendar (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    vaccine_id BIGINT NOT NULL,
    vaccination_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    max_patients INT NOT NULL,
    current_patients INT NOT NULL DEFAULT 0,
    notes NVARCHAR(MAX),
    is_active BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (vaccine_id) REFERENCES vaccine(id),
    CONSTRAINT unique_vaccine_date UNIQUE (vaccine_id, vaccination_date, is_active)
);

-- Tạo bảng đăng ký tiêm chủng
CREATE TABLE vaccination_registration (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    calendar_id BIGINT NOT NULL,
    ma_ho_so INT NOT NULL,
    registration_time DATETIME NOT NULL DEFAULT GETDATE(),
    status NVARCHAR(50) NOT NULL, -- PENDING, CONFIRMED, CANCELLED, COMPLETED
    notes NVARCHAR(MAX),
    is_active BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (calendar_id) REFERENCES vaccination_calendar(id),
    FOREIGN KEY (ma_ho_so) REFERENCES ho_so_benh_nhan(ma_ho_so)
);

-- Tạo index cho các trường thường xuyên tìm kiếm
CREATE INDEX idx_vaccine_name ON vaccine(name);
CREATE INDEX idx_vaccination_calendar_date ON vaccination_calendar(vaccination_date);
CREATE INDEX idx_vaccination_calendar_vaccine ON vaccination_calendar(vaccine_id);
CREATE INDEX idx_vaccination_registration_calendar ON vaccination_registration(calendar_id);
CREATE INDEX idx_vaccination_registration_ma_ho_so ON vaccination_registration(ma_ho_so);
CREATE INDEX idx_vaccination_registration_status ON vaccination_registration(status); 