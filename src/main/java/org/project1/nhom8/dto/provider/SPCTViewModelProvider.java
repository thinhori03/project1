package org.project1.nhom8.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.model.SanPhamChiTietModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.MauSacRepository;
import org.project1.nhom8.repository.SanPhamChiTietRespository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.repository.SizeRepository;

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

    private SizeRepository sizeRepository;

    private MauSacRepository mauSacRepository;

    public SPCTViewModelProvider() {

        this.sanPhamRepository = new SanPhamRepository();

        this.spctRespository = new SanPhamChiTietRespository();

        this.giaRepository = new GiaRepository();

        this.sizeRepository = new SizeRepository();

        this.mauSacRepository = new MauSacRepository();
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
                    .size(sizeRepository.findById(spctModel.getMasize()).getTensize())
                    .mauSac(mauSacRepository.findById(spctModel.getMaMauSac()).getTenmau())
                    .build();

        }

        return result;
    }
}
