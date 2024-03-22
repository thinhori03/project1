CREATE DATABASE Du_An_Nhom_8
go
USE Du_An_Nhom_8
Go

CREATE TABLE NHAN_VIEN
(
    MANV    int PRIMARY KEY identity (1,1),
    TENNV   NVARCHAR(100),
    SDT     CHAR(14),
    EMAIL   VARCHAR(50),
    GIOTINH NVARCHAR(50),
    MATKHAU varchar(20),
    CCCD    VARCHAR(14),
    VAITRO  Nvarchar(50)
)

CREATE TABLE SAN_PHAM
(
    MASP  INT PRIMARY KEY identity (1,1),
    TENSP NVARCHAR(100)
)
CREATE TABLE MAU_SAC
(
    MAMAU  int PRIMARY KEY identity (1,1),
    TENMAU NVARCHAR(100) unique
)

CREATE TABLE SIZE
(
    MASIZE  int PRIMARY KEY identity (1,1),
    TENSIZE NVARCHAR(10) unique
)


CREATE TABLE KHUYEN_MAI
(
    MAKM        VARCHAR(14) PRIMARY KEY,
    NGAYBATDAU  DATE,
    NGAYKETTHUC DATE,
    SOLUONG     INT,
    GIA         FLOAT
)

CREATE TABLE VOUCHER
(
    MAV         VARCHAR(10) PRIMARY KEY,
    GIATRI      FLOAT,
    NGAYBATDAU  DATE,
    NGAYKETTHUC DATE,
    DIEUKIEN    FLOAT,
    ngay_tao DATETIME,
    TRANG_THAI VARCHAR(20)  -- đang hoạt động đã hủy
)

CREATE TABLE KHACH_HANG
(
    MAKH     INT PRIMARY KEY identity (1,1),
    TENKH    NVARCHAR(100),
    SDT      CHAR(14),
    GIOITINH NVARCHAR(10),
    EMAIL    NVARCHAR(100),
    NGAYSINH DATE,
    MATKHAU  varchar(20),
    DIACHI   VARCHAR(100)
)

create table LICH_SU_GIA
(
    MALSG       int primary key,

    GIA         float,
    NGAYBATDAU  datetime,
    NGAYKETTHUC datetime
)
CREATE TABLE SAN_PHAM_CHI_TIET
(
    MASPCT    INT PRIMARY KEY identity (1,1),
    TENSP     NVARCHAR(100),
    SOLUONG   INT,
    MASIZE    int references SIZE (MASIZE),
    MAMAU     int references MAU_SAC (MAMAU),
    MAKM      VARCHAR(14) references KHUYEN_MAI (MAKM),
    MASP      INT references SAN_PHAM (MASP),
    TRANGTHAI NVARCHAR(100),
    MALSG     int references LICH_SU_GIA (MALSG)
)
CREATE TABLE HOA_DON
(
    MAHD          varchar(20) PRIMARY KEY,
    MAKH          INT references KHACH_HANG (MAKH),
    MANV          INT references NHAN_VIEN (MANV),
    NGAYTAO       DATETIME,
    NGAYTHANHTOAN DATETIME,
    TRANGTHAI     VARCHAR(100),
    PHUONGTHUC    VARCHAR(100),
    MAV           VARCHAR(10) references VOUCHER (MAV)
)
CREATE TABLE HOA_DON_CHI_TIET
(
    MAHDCT  INT PRIMARY KEY identity (1,1),
    MAHD    varchar(20) references HOA_DON (MAHD),
    MASPCT  INT references SAN_PHAM_CHI_TIET (MASPCT),
    SOLUONG INT,
    MAKM    varchar(14) references KHUYEN_MAI (MAKM),
    MALSG   int references LICH_SU_GIA (MALSG)
)
insert into NHAN_VIEN(TENNV, SDT, EMAIL, GIOTINH, MATKHAU, CCCD, VAITRO)
values (N'Trịnh Tiến Tuấn', 0827890913, 'tuantt23@gmail.com', N'Nam', 'tuan2004', 03734002912, N'Quản lý'),
       (N'Hoàng Đức Ích', 0866690914, 'ichhoang872gmail.com', N'Nam', 'Hoangich2004', 02700757824, N'Nhân viên'),
       (N'Mai Thị Thư', 0395561663, 'thumt21@gmail.com', N'Nam', 'Hoangich2004', 02700757824, N'Nhân viên')

insert into SAN_PHAM(TENSP)
values (N'Dép LV'),
       (N'Dép Adidas'),
       (N'Dép Gucci')

insert into MAU_SAC(TENMAU)
values (N'Đỏ'),
       (N'Đen'),
       (N'Đỏ vàng')
insert into SIZE(TENSIZE)
values (39),
       (38),
       (40)
insert into KHUYEN_MAI(MAKM, NGAYBATDAU, NGAYKETTHUC, SOLUONG, GIA)
values ('KM1', '2024-02-14', '2024-02-20', 4, 40000),
       ('KM2', '2024-02-20', '2024-02-26', 3, 50000),
       ('KM3', '2024-02-26', '2024-02-28', 6, 60000)

insert into VOUCHER(MAV, GIATRI, NGAYBATDAU, NGAYKETTHUC, DIEUKIEN, ngay_tao, TRANG_THAI)
values ('V1', 20000, '2024-02-14', '2024-02-18', 1000000, GETDATE(), 'đang hoạt động'),
       ('V2', 30000, '2024-02-15', '2024-02-22', 2000000, GETDATE(), 'đang hoạt động'),
       ('V3', 40000, '2024-02-20', '2024-02-26', 3000000, GETDATE(), 'đang hoạt động')


insert into KHACH_HANG(TENKH, SDT, GIOITINH, EMAIL, NGAYSINH, MATKHAU, DIACHI)
values (N'Nguyễn Văn Tuấn', 0856790234, N'Nam', 'Tuannv23@gmail.com', '1999-07-23', 'Tuan1999', N'Hà Nội'),
       (N'Ngyễn Thị Thu Giang', 086678902, N'Nữ', 'Giangxinh@gamil.com', '2004-04-25', 'Giang2004', N'Hà Nội'),
       (N'Vi Công Minh', 0702202307, N'Nam', 'Minhdz@gmail.com', '2003-08-13', 'Minhdz2004', N'Hà Nội')

insert into LICH_SU_GIA(MALSG, GIA, NGAYBATDAU, NGAYKETTHUC)
values ('1', 150000, '2024-02-10', '2024-02-15'),
       ('2', 150000, '2024-02-20', '2024-02-25'),
       ('3', 150000, '2024-02-23', '2024-02-28')

insert into SAN_PHAM_CHI_TIET(TENSP, SOLUONG, MASIZE, MAMAU, MAKM, TRANGTHAI, MALSG)
values (N'Dép LV', 10, '1', '1', 'KM1', N'Đang bán', '1'),
       (N'Dép Adidas', 20, '2', '2', 'KM3', N'Đang bán', '3'),
       (N'Dép Gucci', 10, '3', '3', 'KM2', N'Dừng bán', '2')

insert into HOA_DON(MAHD, MAKH, MANV, NGAYTAO, NGAYTHANHTOAN, TRANGTHAI, PHUONGTHUC, MAV)
values ('HD1', '1', '1', '2024-02-10 00:00:00', '2024-03-10 00:00:00', N'Đã thanh toán', N'Tiền mặt', 'V3'),
       ('HD2', '2', '2', '2024-01-24 00:00:00', '2024-02-25 00:00:00', N'Chưa thanh toán', N'Tiền mặt', 'V2'),
       ('HD3', '3', '3', '2024-03-10 00:00:00', '2024-03-17 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V1')

insert into HOA_DON_CHI_TIET(MAHD, MASPCT, SOLUONG, MAKM, MALSG)
values ('HD1', '1', 6, 'KM1', '1'),
       ('HD2', '2', 4, 'KM3', '3'),
       ('HD3', '3', 5, 'KM2', '2')
/*
INSERT INTO SAN_PHAM_CHI_TIET (TENSP, SOLUONG, MASIZE, MAMAU, MAKM, MASP, TRANGTHAI, MALSG)
SELECT 'Tên sản phẩm', 10, 5, MAMAU, 5, 5, 'Trạng thái', 5
FROM MAU_SAC
WHERE TENMAU = 'While';*/
insert into LICH_SU_GIA(GIA, NGAYBATDAU, NGAYKETTHUC)
values (150000, '2024-02-10', '2024-02-15')
insert into SAN_PHAM_CHI_TIET(TENSP, SOLUONG, MASIZE, MAMAU, MAKM, MASP, TRANGTHAI, MALSG)
values (N'Dép LV', 10, '1', '1', 'KM1', '1', N'Đang bán', '1')

Select MASIZE
from SIZE
where TENSIZE = 39
select*
from LICH_SU_GIA
where MALSG
select COUNT(*)
from LICH_SU_GIA

Select*
from NHAN_VIEN
Select*
from SAN_PHAM
Select*
from SIZE
select*
from MAU_SAC
where TENMAU = N'Đỏ'
Select*
from KHUYEN_MAI
Select*
from VOUCHER
Select*
from KHACH_HANG
Select*
from HOA_DON
Select MASPCT, TENSP, SOLUONG, MASIZE, MAMAU, TRANGTHAI, MALSG
from SAN_PHAM_CHI_TIET
Select*
from HOA_DON_CHI_TIET
INSERT INTO LICH_SU_GIA(MALSG, GIA, NGAYBATDAU, NGAYKETTHUC)
VALUES ('4', 16, '2024-03-01 00:00:00', '2024-03-02 00:00:00')