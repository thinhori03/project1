package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

public class HDCTViewModel {
    @DataHeader(name = "mã sản phẩm")
    private Integer maSPCT;

    @DataHeader(name = "tên sản phẩm")
    private String tenSP;

    @DataHeader(name = "số lượng")
    private Integer soLuong;

    @DataHeader(name = "size")
    private String size;

    @DataHeader(name = "màu sắc")
    private String mauSac;

    @DataHeader(name = "giá")
    private double gia;
}
