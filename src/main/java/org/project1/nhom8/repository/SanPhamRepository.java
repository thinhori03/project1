package org.project1.nhom8.repository;

import org.project1.nhom8.model.SanPhamModel;

public class SanPhamRepository extends GeneralRepository<SanPhamModel, Integer>{

    public SanPhamRepository() {
        super(SanPhamModel.class);
    }

}
