package org.project1.nhom8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.convert.DateFormat;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoucherViewModel {

    @DataHeader(name = "mã voucher")
    private String maVoucher;

    @DataHeader(name = "giá")
    private Double gia;

    @DataHeader(name = "điều kiện ")
    private Double dieuKien;

    @DataHeader(name = "ngày tạo")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayTao;

    @DataHeader(name = "ngày bắt đầu")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayBatDau;

    @DataHeader(name = "ngày kết thúc")
    @DateFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date ngayKetThuc;

    @DataHeader(name = "trang thái")
    private String trangThai;
}
