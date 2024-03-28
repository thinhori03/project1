package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
import org.project1.nhom8.model.SanPhamChiTietModel;
import org.project1.nhom8.repository.SanPhamChiTietRespository;

public class TestSPCTModel {

    private SPCTViewModelProvider spctViewModelProvider;

    private SanPhamChiTietRespository spctRepository;

    public TestSPCTModel() {
        this.spctViewModelProvider = new SPCTViewModelProvider();
        this.spctRepository = new SanPhamChiTietRespository();
    }

    public void getrAll() {

        System.out.println(this.spctRepository.findById(1).getMaSPCT());
    }

    @Test
    public void  viewModel() {
        for (SPCTViewModel spctViewModel : this.spctViewModelProvider.getSanPhamViewModel()) {
            System.out.println(spctViewModel.getMaSPCT());
        }
    }
}
