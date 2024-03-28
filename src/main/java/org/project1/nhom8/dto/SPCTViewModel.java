package org.project1.nhom8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.visual.DataHeader;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SPCTViewModel {

    @DataHeader(name = "mã sản phẩm chi tiết")
    private Integer maSPCT;

    @DataHeader(name = "tên sản phẩm")
    private String tenSP;

    @DataHeader(name = "số lượng")
    private Integer soLuong;

    @DataHeader(name = "size")
    private String size;

    @DataHeader(name = "màu sắc")
    private String mauSac;

    @DataHeader(name = "trạng thái")
    private String trangThai;

    @DataHeader(name = "giá")
    private Float gia;
}
