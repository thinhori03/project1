DROP DATABASE Du_An_Nhom_8

CREATE DATABASE Du_An_Nhom_8
GO

USE Du_An_Nhom_8
Go
CREATE TABLE NHAN_VIEN
(
    MANV      int PRIMARY KEY identity (1,1),
    TENNV     NVARCHAR(100),
    SDT       VARCHAR(14),
    EMAIL     VARCHAR(50),
    GIOTINH   NVARCHAR(50),
    MATKHAU   varchar(20),
    CCCD      VARCHAR(14),
    VAITRO    Nvarchar(50),
    TRANGTHAI NVARCHAR(50)
)

CREATE TABLE SAN_PHAM
(
    MASP      INT PRIMARY KEY identity (1,1),
    TENSP     NVARCHAR(100),
    TRANGTHAI NVARCHAR(100)
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


CREATE TABLE KHUYEN_MAI_COUPON
(
    MAKM        VARCHAR(14) PRIMARY KEY,
    NGAYBATDAU  DATE,
    NGAYKETTHUC DATE,
    SOLUONG     INT,
    GIA         FLOAT,
)

CREATE TABLE VOUCHER
(
    MAV         VARCHAR(10) PRIMARY KEY,
    GIATRI      FLOAT,
    NGAYBATDAU  DATE,
    NGAYKETTHUC DATE,
    DIEUKIEN    FLOAT,
    ngay_tao    DATETIME,
    TRANG_THAI  VARCHAR(20) -- đang hoạt động đã hủy
)

CREATE TABLE KHACH_HANG
(
    MAKH     INT PRIMARY KEY identity (1,1),
    TENKH    NVARCHAR(100),
    SDT      Varchar(14),
    GIOITINH NVARCHAR(10),
    EMAIL    NVARCHAR(100),
    NGAYSINH DATE,
    MATKHAU  varchar(20),
    DIACHI   VARCHAR(100)
)


CREATE TABLE SAN_PHAM_CHI_TIET
(
    MASPCT  INT PRIMARY KEY identity (1,1),
    SOLUONG INT,
    MASIZE  int references SIZE (MASIZE),
    MAMAU   int references MAU_SAC (MAMAU),
    MASP    INT references SAN_PHAM (MASP)
)

CREATE TABLE KHUYEN_MAI_COUPON_CT
(
    MAKM   varchar(14) references KHUYEN_MAI_COUPON (MAKM),
    MASPCT int references SAN_PHAM_CHI_TIET (MASPCT),
)

create table LICH_SU_GIA
(
    MALSG      int primary key,
    MASPCT     int references SAN_PHAM_CHI_TIET (MASPCT),
    GIA        float,
    NGAYUPDATE datetime,
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
    MALSG   int -- giá hiện tại
)
go

CREATE TABLE hoa_don_chi_tiet__khuyen_mai
(
    maHDCT int references HOA_DON_CHI_TIET (MAHDCT),
    maKM   VARCHAR(14) references KHUYEN_MAI_COUPON (maKM)
)
GO

insert into NHAN_VIEN
values (N'Trịnh Tiến Tuấn', '0827890913', 'tuantt23@gmail.com', N'Nam', 'tuan2004', 03734002912, N'Quản lý',
        N'Đang làm việc'),
       (N'Hoàng Đức Ích', '0866690914', 'ichhoang872gmail.com', N'Nam', 'Hoangich2004', 02700757824, N'Nhân viên',
        N'Đang làm việc'),
       (N'Mai Thị Thư', '0395561663', 'thumt21@gmail.com', N'Nam', 'Hoangich2004', 02700757824, N'Nhân viên',
        N'Nghỉ việc')

insert into SAN_PHAM(TENSP, TRANGTHAI)
values (N'Dép LV', N'Đang bán'),
       (N'Dép Adidas', N'Đang bán'),
       (N'Dép Gucci', N'Đang bán')

insert into MAU_SAC(TENMAU)
values (N'Đỏ'),
       (N'Đen'),
       (N'Đỏ vàng')
insert into SIZE(TENSIZE)
values (39),
       (38),
       (40)
insert into KHUYEN_MAI_COUPON(MAKM, NGAYBATDAU, NGAYKETTHUC, SOLUONG, GIA)
values ('KM1', '2024-02-14', '2024-02-20', 4, 40000),
       ('KM2', '2024-02-20', '2024-02-26', 3, 50000),
       ('KM3', '2024-02-26', '2024-02-28', 6, 60000)


insert into VOUCHER(MAV, GIATRI, NGAYBATDAU, NGAYKETTHUC, DIEUKIEN, ngay_tao, TRANG_THAI)
values ('V1', 20000, '2024-02-14', '2024-02-18', 1000000, GETDATE(), 'đang hoạt động'),
       ('V2', 30000, '2024-02-15', '2024-02-22', 2000000, GETDATE(), 'đang hoạt động'),
       ('V3', 40000, '2024-02-20', '2024-02-26', 3000000, GETDATE(), 'đang hoạt động')

insert into KHACH_HANG(TENKH, SDT, GIOITINH, EMAIL, NGAYSINH, MATKHAU, DIACHI)
values (N'Nguyễn Văn Tuấn', '0856790234', N'Nam', 'Tuannv23@gmail.com', '1999-07-23', 'Tuan1999', N'Hà Nội'),
       (N'Ngyễn Thị Thu Giang', '086678902', N'Nữ', 'Giangxinh@gamil.com', '2004-04-25', 'Giang2004', N'Hà Nội'),
       (N'Vi Công Minh', '0702202307', N'Nam', 'Minhdz@gmail.com', '2003-08-13', 'Minhdz2004', N'Hà Nội'),
       (N' Minh', '0702202307', N'Nam', 'Minhdz@gmail.com', '2003-08-13', 'Minhdz2004', N'Hà Nội')


insert into SAN_PHAM_CHI_TIET(SOLUONG, MASIZE, MAMAU, MASP)
values (10, '1', '1', 1),
       (20, '2', '2', 2),
       (10, '3', '3', 3)

insert into KHUYEN_MAI_COUPON_CT(MAKM, MASPCT)
values ('KM1', 1),
       ('KM2', 2),
       ('KM3', 3)

insert into LICH_SU_GIA(MALSG, MASPCT, GIA, NGAYUPDATE)
values ('1', '1', 150000, '2024-02-10'),
       ('2', '2', 150000, '2024-02-20'),
       ('3', '3', 150000, '2024-02-23')

insert into HOA_DON(MAHD, MAKH, MANV, NGAYTAO, NGAYTHANHTOAN, TRANGTHAI, PHUONGTHUC, MAV)
values ('HD1', '1', '1', '2024-02-10 00:00:00', '2024-03-10 00:00:00', N'Đã thanh toán', N'Tiền mặt', 'V3'),
       ('HD2', '2', '2', '2024-01-24 00:00:00', '2024-02-25 00:00:00', N'Chưa thanh toán', N'Tiền mặt', 'V2'),
       ('HD3', '3', '3', '2024-03-10 00:00:00', '2024-03-17 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V1')

insert into HOA_DON_CHI_TIET(MAHD, MASPCT, SOLUONG, MALSG)
values ('HD1', '1', 6, '1'),
       ('HD2', '2', 4, '3'),
       ('HD3', '3', 5, '2')

INSERT INTO hoa_don_chi_tiet__khuyen_mai(maHDCT, maKM)
VALUES ('1', 'KM1'),
       ('2', 'KM2'),
       ('3', 'KM3')
----------------------------------------------------------------------------------------------------------------
/*
INSERT INTO SAN_PHAM_CHI_TIET (TENSP, SOLUONG, MASIZE, MAMAU, MAKM, MASP, TRANGTHAI, MALSG)
SELECT 'Tên sản phẩm', 10, 5, MAMAU, 5, 5, 'Trạng thái', 5
FROM MAU_SAC
WHERE TENMAU = 'While';*/
/*
INSERT INTO SAN_PHAM_CHI_TIET (TENSP, SOLUONG, MASIZE, MAMAU, MAKM, MASP, TRANGTHAI, MALSG)
SELECT 'Tên sản phẩm', 10, 5, MAMAU, 5, 5, 'Trạng thái', 5
FROM MAU_SAC
WHERE TENMAU = 'While';*/

Select*
from NHAN_VIEN
Select*
from SAN_PHAM
Select*
from SIZE
select*
from MAU_SAC
Select*
from KHUYEN_MAI_COUPON
Select*
from KHUYEN_MAI_COUPON_CT

Select*
from VOUCHER
Select*
from KHACH_HANG
Select*
from HOA_DON
Select*
from SAN_PHAM_CHI_TIET
Select*
from HOA_DON_CHI_TIET
--
--     insert into KHACH_HANG (  TENKH, SDT,GIOITINH,EMAIL,NGAYSINH,DIACHI)
-- values ( 'Nguyen Van A' ,'0123456789','Nam', 'nguyenvana@gmail.com','02/02/2004','Kieu Mai , Ha Noi')
--
-- update KHACH_HANG
-- set TENKH = 'Nguyen Van A',
--     SDT = '0123456798',
--     GIOITINH=N'Nữ',
--     EMAIl = 'nguyenvana@gmail.com',
--     NGAYSINH = '02/02/2004',
--     NGAYSINH = '02/02/2004',
--     DIACHI = 'Kieu Mai , Ha Noi'
-- where MAKH = 2
--
--
--
--
--
-- select HOA_DON_CHI_TIET.MASPCT, TENSP ,COUNT(HOA_DON_CHI_TIET.SOLUONG),GIA
-- from HOA_DON_CHI_TIET
--          join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT= HOA_DON_CHI_TIET.MASPCT
--          join LICH_SU_GIA on LICH_SU_GIA.MALSG= HOA_DON_CHI_TIET.MALSG
-- group by TENSP
--
-- select HOA_DON_CHI_TIET.MASPCT, TENSP ,HOA_DON_CHI_TIET.SOLUONG,GIA,NGAYTHANHTOAN
-- from HOA_DON_CHI_TIET
--          join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT= HOA_DON_CHI_TIET.MASPCT
--          join LICH_SU_GIA on LICH_SU_GIA.MALSG= HOA_DON_CHI_TIET.MALSG
--          join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD
--          join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP
-- where NGAYTHANHTOAN ='2024-03-10 00:00:00.000'
--
--
--
-- select * from HOA_DON
--
-- select * from HOA_DON_CHI_TIET
--
-- select * from SAN_PHAM_CHI_TIET
-- select * from LICH_SU_GIA
--
-- select HOA_DON_CHI_TIET.MASPCT, TENSP ,HOA_DON_CHI_TIET.SOLUONG,GIA,NGAYTHANHTOAN
-- from HOA_DON_CHI_TIET
--          join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT= HOA_DON_CHI_TIET.MASPCT
--          join LICH_SU_GIA on LICH_SU_GIA.MALSG= HOA_DON_CHI_TIET.MALSG
--          join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD
--          join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP
