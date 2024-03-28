package org.project1.nhom8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.model.SanPhamModel;
import org.project1.nhom8.model.SanPhamChiTietModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamVietModel {

    SanPhamModel sanPham;

    SanPhamChiTietModel sanPhamCt;

}
