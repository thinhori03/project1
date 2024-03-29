package org.project1.nhm8;

import org.junit.jupiter.api.Test;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
import org.project1.nhom8.repository.SPCTRepository;

public class TestSPCTModel {

    private SPCTViewModelProvider spctViewModelProvider;

    private SPCTRepository spctRepository;

    public TestSPCTModel() {
        this.spctViewModelProvider = new SPCTViewModelProvider();
        this.spctRepository = new SPCTRepository();
    }

    @Test
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
