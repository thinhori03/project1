package org.project1.nhom8.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.model.SanPhamChiTietModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.SanPhamChiTietRespository;
import org.project1.nhom8.repository.SanPhamRepository;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Setter
@Getter
@Builder
public class SPCTViewModelProvider {

    private SanPhamRepository sanPhamRepository;

    private SanPhamChiTietRespository spctRespository;

    private GiaRepository giaRepository;

    public SPCTViewModelProvider() {

        this.sanPhamRepository = new SanPhamRepository();

        this.spctRespository = new SanPhamChiTietRespository();

        this.giaRepository = new GiaRepository();
    }

    public List<SPCTViewModel> getSanPhamViewModel() {
        List<SPCTViewModel> result = new ArrayList<>();

        SPCTViewModel spctViewModel = null;

        List<SanPhamChiTietModel> spctModels = spctRespository
                .findAll();

        for (SanPhamChiTietModel spctModel: spctModels) {
            spctViewModel = new SPCTViewModel().builder()
                    .maSPCT(spctModel.getMaSPCT())
                    .gia(giaRepository.getgiaMoiNhat(spctModel.getMaSP()).getGia())
                    .size()
                    .build();

        }

        return result;
    }
}
