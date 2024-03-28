package org.project1.nhom8.repository;

import org.project1.nhom8.model.SanPhamChiTietModel;

public class SanPhamChiTietRespository extends GeneralRepository<SanPhamChiTietModel, Integer> {

    public SanPhamChiTietRespository() {
        super(SanPhamChiTietModel.class);
    }
}
