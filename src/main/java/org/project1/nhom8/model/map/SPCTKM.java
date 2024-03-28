package org.project1.nhom8.model.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SPCT: sản phẩm chi tiết
 * KM: khuyến mại
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SPCTKM {

    private int maSPCT;


    private String maKM;
}
