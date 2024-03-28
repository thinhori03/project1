package org.project1.nhm8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
import org.project1.nhom8.repository.SPCTModel;

public class TestSPCTModel {

    private SPCTViewModelProvider spctViewModelProvider;

    private SPCTModel spctRepository;

    public TestSPCTModel() {
        this.spctViewModelProvider = new SPCTViewModelProvider();
        this.spctRepository = new SPCTModel();
    }

    public void getrAll() {

        System.out.println(this.spctRepository.findById(1).getMaSPCT());
    }

    @Test
    public void  viewModel() {
        for (SPCTViewModel spctViewModel : this.spctViewModelProvider.SPCTViewModel()) {
            System.out.println(spctViewModel.getMaSPCT());
        }
    }
}
