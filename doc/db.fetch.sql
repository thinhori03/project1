Drop DATABASE Du_An_Nhom_8

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
    MATKHAU   varchar(32),
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
    SOLUONG     int,
    ngay_tao    DATETIME,
    NGAYCAPNHAT DATETIME,
    TRANG_THAI  NVARCHAR(20) -- đang hoạt động, đã hủy
)

CREATE TABLE KHACH_HANG
(
    MAKH     INT PRIMARY KEY identity (1,1),
    TENKH    NVARCHAR(100),
    SDT      Varchar(14),
    GIOITINH NVARCHAR(10),
    EMAIL    NVARCHAR(100),
    NGAYSINH DATE,
    DIACHI   NVARCHAR(100)
)


CREATE TABLE SAN_PHAM_CHI_TIET
(
    MASPCT  INT PRIMARY KEY,
    SOLUONG INT,
    MASIZE  int references SIZE (MASIZE),
    MAMAU   int references MAU_SAC (MAMAU),
    MASP    INT references SAN_PHAM (MASP)
)

CREATE TABLE KHUYEN_MAI_COUPON_CT
(
    MAKM_CPCT int primary key identity (1,1),
    MAKM      varchar(14) references KHUYEN_MAI_COUPON (MAKM),
    MASPCT    int references SAN_PHAM_CHI_TIET (MASPCT),
)

create table LICH_SU_GIA
(
    MALSG       int primary key,
    MASPCT      int references SAN_PHAM_CHI_TIET (MASPCT),
    GIA         float,
    NGAYbATDAU  datetime,
    NGAYkETtHUC DATETIME
)

CREATE TABLE HOA_DON
(
    MAHD            varchar(20) PRIMARY KEY,
    MAKH            INT references KHACH_HANG (MAKH),
    MANV            INT references NHAN_VIEN (MANV),
    MA_NV_XAC_NHAN  int REFERENCES NHAN_VIEN (MANV),
    NGAYTAO         DATETIME,
    NGAYTHANHTOAN   DATETIME,
    TRANGTHAI       NVARCHAR(100),
    PHUONGTHUC      NVARCHAR(100),
    MAV             VARCHAR(10) references VOUCHER (MAV),
    TIEN_THANH_TOAN FLOAT
)
CREATE TABLE HOA_DON_CHI_TIET
(
    MAHDCT  INT PRIMARY KEY identity (1,1),
    MAHD    varchar(20) references HOA_DON (MAHD),
    MASPCT  INT references SAN_PHAM_CHI_TIET (MASPCT),
    SOLUONG INT,
    MALSG   int, -- giá hiện tại
    MAKM    VARCHAR(14) FOREIGN KEY REFERENCES KHUYEN_MAI_COUPON (MAKM)
)
go

INSERT INTO NHAN_VIEN (TENNV, SDT, EMAIL, GIOTINH, MATKHAU, CCCD, VAITRO, TRANGTHAI)
VALUES (N'Trịnh Tiến Tuấn', '0827890913', 'tuanttph45460@fpt.edu.vn', N'Nam',
        CONVERT(VARCHAR(32), HASHBYTES('MD5', 'tuan2004'), 2), '03734002912', N'Quản lý', N'Đang làm việc'),
       (N'Hoàng Đức Ích', '0866690914', 'ichhdph45048@fpt.edu.vn', N'Nam',
        CONVERT(VARCHAR(32), HASHBYTES('MD5', 'Hoangich2004'), 2), '02700757824', N'Nhân viên', N'Đang làm việc'),
       (N'Mai Thị Thư', '0395561663', 'thumtph45638@fpt.edu.vn', N'Nữ',
        CONVERT(VARCHAR(32), HASHBYTES('MD5', 'thu2004'), 2), '02700757824', N'Nhân viên', N'Nghỉ việc'),
       (N'Nguyễn Thịnh', '0395561663', 'ngtnthori03@gmail.com', N'Nam',
        CONVERT(VARCHAR(32), HASHBYTES('MD5', 'password123'), 2), '02700757824', N'Nhân viên', N'Nghỉ việc');

insert into SAN_PHAM(TENSP, TRANGTHAI)
values (N'Dép LV', N'Đang bán'),
       (N'Dép Adidas', N'Đang bán'),
       (N'Dép Gucci', N'Đang bán'),
       (N'Dép Thời Trang', N'Dừng bán '),
       (N'Dép B', N'Dừng bán ')

insert into MAU_SAC(TENMAU)
values (N'Đỏ'),
       (N'Đen'),
       (N'Đỏ vàng'),
       (N'Xám'),
       (N'Trắng'),
       (N'Xanh lá')
insert into SIZE(TENSIZE)
values (37),
       (38),
       (39),
       (40),
       (41),
       (42),
       (43)
insert into KHUYEN_MAI_COUPON(MAKM, NGAYBATDAU, NGAYKETTHUC, SOLUONG, GIA)
values ('KM1', '2024-02-14', '2024-02-20', 4, 40000),
       ('KM2', '2024-02-20', '2024-02-26', 3, 50000),
       ('KM3', '2024-02-26', '2024-02-28', 6, 60000),
       ('KM4', '2024-01-26', '2024-01-28', 16, 60000),
       ('KM5', '2024-02-20', '2024-02-28', 7, 160000)


insert into VOUCHER(MAV, GIATRI, NGAYBATDAU, NGAYKETTHUC, DIEUKIEN, SOLUONG, ngay_tao, NGAYCAPNHAT, TRANG_THAI)
values ('V1', 20000, '2024-02-14', '2024-02-18', 1000000, 2, GETDATE(), GETDATE(), N'đang hoạt động'),
       ('V2', 30000, '2024-02-15', '2024-02-22', 2000000, 3, GETDATE(), GETDATE(), N'đang hoạt động'),
       ('V3', 40000, '2024-02-20', '2024-02-26', 3000000, 1, GETDATE(), GETDATE(), N'đang hoạt động'),
       ('V4', 50000, '2024-01-30', '2024-02-10', 1000000, 2, GETDATE(), GETDATE(), N'đang hoạt động'),
       ('V5', 50000, '2024-01-10', '2024-01-29', 1000000, 1, GETDATE(), GETDATE(), N'đang hoạt động')


insert into KHACH_HANG(TENKH, SDT, GIOITINH, EMAIL, NGAYSINH, DIACHI)
values (N'Nguyễn Văn Tuấn', '0856790234', N'Nam', 'Tuannv23@gmail.com', '1999-07-23', N'Hà Nội'),
       (N'Ngyễn Thị Thu Giang', '086678902', N'Nữ', 'Giangxinh@gamil.com', '2004-04-25', N'Hà Nội'),
       (N'Vi Công Minh', '0702202307', N'Nam', 'Minhdz@gmail.com', '2003-08-13', N'Hà Nội'),
       (N' Minh', '0702202307', N'Nam', 'Minhdz@gmail.com', '2003-08-13', N'Hà Nội')


insert into SAN_PHAM_CHI_TIET(MASPCT, SOLUONG, MASIZE, MAMAU, MASP)
values ('1', 10, '1', '1', 1),
       ('2', 20, '2', '2', 2),
       ('3', 10, '3', '3', 3),
       ('4', 11, '2', '1', 2),
       ('5', 12, '1', '2', 3)

insert into KHUYEN_MAI_COUPON_CT(MAKM, MASPCT)
values ('KM1', 1),
       ('KM2', 2),
       ('KM3', 3),
       ('KM4', 4),
       ('KM5', 3)

insert into LICH_SU_GIA(MALSG, MASPCT, GIA, NGAYbATDAU, NGAYkETtHUC)
values ('1', '1', 150000, '2024-02-10', NULL),
       ('2', '2', 150000, '2024-02-20', '2024-01-22'),
       ('3', '3', 150000, '2024-02-23', NULL),
       ('4', '2', 250000, '2024-01-22', NULL),
       ('5', '4', 450000, '2024-02-25', NULL),
       ('6', '5', 450000, '2024-02-25', NULL)


insert into HOA_DON(MAHD, MAKH, MANV, MA_NV_XAC_NHAN, NGAYTAO, NGAYTHANHTOAN, TRANGTHAI, PHUONGTHUC, MAV,
                    TIEN_THANH_TOAN)
values ('HD1', '1', '1', '1', '2024-02-10 00:00:00', '2024-03-10 00:00:00', N'Đã thanh toán', N'Tiền mặt', 'V3',
        5000000),
       ('HD2', '2', '2', '1', '2024-01-24 00:00:00', '2024-02-25 00:00:00', N'đã hủy', N'Tiền mặt', 'V2', 5000000),
       ('HD3', '3', '4', '1', '2024-03-10 00:00:00', '2024-03-17 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V1',
        5000000),
       ('HD4', '1', '2', '1', '2024-02-10 00:00:00', '2024-02-10 00:20:00', N'Đã thanh toán', N'Chuyển khoản', 'V4',
        5000000),
       ('HD5', '4', '3', '1', '2024-01-01 00:00:00', '2024-01-01 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V5',
        5000000),
       ('HD6', '3', '3', '1', '2024-04-01 00:00:00', '2024-04-01 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V5',
        5000000),
       ('HD7', '1', '3', '1', '2024-01-01 00:00:00', '2024-01-01 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V5',
        5000000),
       ('HD8', '4', '1', '1', '2024-04-01 00:00:00', '2024-04-01 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V5',
        5000000),
       ('HD9', '1', '3', '1', '2024-02-01 00:00:00', '2024-02-01 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V5',
        5000000),
       ('HD10', '1', '3', '1', '2024-02-01 00:00:00', '2024-02-01 00:00:00', N'Đã thanh toán', N'Chuyển khoản', 'V5',
        5000000)


insert into HOA_DON_CHI_TIET(MAHD, MASPCT, SOLUONG, MALSG, MAKM)
values ('HD1', '1', 6, '1', 'KM1'),
       ('HD2', '2', 4, '3', 'KM2'),
       ('HD3', '3', 5, '2', 'KM3'),
       ('HD4', '4', 2, '4', 'KM4'),
       ('HD5', '5', 2, '4', 'KM5'),
       ('HD6', '1', 1, '1', 'KM1'),
       ('HD7', '2', 2, '3', 'KM2'),
       ('HD8', '3', 3, '2', 'KM3'),
       ('HD9', '4', 4, '4', 'KM4'),
       ('HD10', '5', 5, '4', 'KM5'),
       ('HD1', '1', 2, '1', 'KM1'),
       ('HD2', '2', 3, '3', 'KM2'),
       ('HD3', '3', 4, '2', 'KM3'),
       ('HD4', '4', 6, '4', 'KM4'),
       ('HD5', '5', 6, '4', 'KM5'),
       ('HD6', '1', 2, '1', 'KM1'),
       ('HD7', '2', 2, '3', 'KM2'),
       ('HD8', '3', 3, '2', 'KM3'),
       ('HD9', '4', 1, '4', 'KM4'),
       ('HD10', '5', 4, '4', 'KM5')
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
from LICH_SU_GIA
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

-- end ò execution

insert into KHACH_HANG (TENKH, SDT, GIOITINH, EMAIL, NGAYSINH, DIACHI)
values ('Nguyen Van A', '0123456789', 'Nam', 'nguyenvana@gmail.com', '02/02/2004', 'Kieu Mai , Ha Noi')

update KHACH_HANG
set TENKH    = N'Nguyễn Văn B',
    SDT      = '0123456798',
    GIOITINH=N'Nam',
    EMAIl    = 'nguyenvana@gmail.com',
    NGAYSINH = '02/02/2004',
    DIACHI   = 'Kieu Mai , Ha Noi'
where MAKH = 2


select *
from KHACH_HANG


select HOA_DON_CHI_TIET.MASPCT, TENSP, HOA_DON_CHI_TIET.SOLUONG, GIA, NGAYTHANHTOAN
from HOA_DON_CHI_TIET
         join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT = HOA_DON_CHI_TIET.MASPCT
         join LICH_SU_GIA on LICH_SU_GIA.MALSG = HOA_DON_CHI_TIET.MALSG
         join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD
         join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP
where NGAYTHANHTOAN = '2024-03-10 00:00:00.000'


select *
from HOA_DON

select *
from HOA_DON_CHI_TIET

select *
from SAN_PHAM_CHI_TIET
select *
from LICH_SU_GIA

select HOA_DON_CHI_TIET.MASPCT, TENSP, HOA_DON_CHI_TIET.SOLUONG, GIA, NGAYTHANHTOAN
from HOA_DON_CHI_TIET
         join SAN_PHAM_CHI_TIET on SAN_PHAM_CHI_TIET.MASPCT = HOA_DON_CHI_TIET.MASPCT
         join LICH_SU_GIA on LICH_SU_GIA.MALSG = HOA_DON_CHI_TIET.MALSG
         join HOA_DON on HOA_DON.MAHD = HOA_DON_CHI_TIET.MAHD
         join SAN_PHAM on SAN_PHAM.MASP = SAN_PHAM_CHI_TIET.MASP

select *
from LICH_SU_GIA

insert into SAN_PHAM_CHI_TIET(SOLUONG, MASIZE, MAMAU, MASP)
values (10, 1, 3, 2)
insert into LICH_SU_GIA(MALSG, GIA)
values ('4', 150000)

insert into SIZE(TENSIZE)
values (43)