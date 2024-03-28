package org.project1.nhom8.model.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SPCT : SẢN PHẨM CHI TIẾT
 * LSG: lịch sử giá
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SanPhamChiTietLichSuGia {

    private int maSPCT;

    private int maLSG;
}
