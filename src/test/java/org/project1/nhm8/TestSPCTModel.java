package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.repository.SPCTRepository;

public class TestSPCTModel {

    private SPCTViewModelProvider spctViewModelProvider;

    private SPCTRepository spctRepository;

    public TestSPCTModel() {
        this.spctViewModelProvider = new SPCTViewModelProvider();
        this.spctRepository = new SPCTRepository();
    }

//    @Test
    public void getrAll() {

        System.out.println(this.spctRepository.findById(1).getMaSPCT());
    }

//    @Test
    public void  viewModel() {
        for (SPCTViewModel spctViewModel : this.spctViewModelProvider.SPCTViewModel()) {
            System.out.println(spctViewModel.getMaSPCT());
        }
    }

    @Test
    @DisplayName("test update SPCT")
    public void updateSPCT() {

        Assertions.assertTrue(spctRepository.update(
                SPCTModel.builder()
                        .maSPCT(1)
                        .maSP(1)
                        .soluong(1)
                        .maMauSac(1)
                        .masize(1)
                        .build()
        ));
    }

}
