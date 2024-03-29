package org.project1.nhom8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HoaDonViewModel {

    @DataHeader(name = "ma hóa đơn")
    private String maHoaDon;

    @DataHeader(name = "ngày tạo")
    private Date ngayTao;

    @DataHeader(name = "mã nhân viên")
    private Integer maNV;

    @DataHeader(name = "mã khánh hàng")
    private Integer maKH;

    @DataHeader(name = "tổng tiền")
    private Double tongTien;

    @DataHeader(name = "trạng thái")
    private String TrangThai;
}
