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
public class SanPhamViewModel {

    @DataHeader(name = "tên sản phẩm")
    private  int maSanPham;

    @DataHeader(name = "mã sản phẩm")
    private String tenSanPham;
    
    @DataHeader(name = "trạng thái")
    private String trangThai;
}
