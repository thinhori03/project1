package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.dto.provider.LSGViewModelProvider;
import org.project1.nhom8.repository.GiaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GiaModel {

    private final GiaRepository giaRepository;

    private final LSGViewModelProvider lsgViewModelProvider;

    public GiaModel() {
        this.giaRepository = new GiaRepository();

        this.lsgViewModelProvider = new LSGViewModelProvider();
    }

    @Test
    public void addGia() {

        new org.project1.nhom8.model.GiaModel();
        org.project1.nhom8.model.GiaModel gia = org.project1.nhom8.model.GiaModel
                .builder()
                .maLSG(giaRepository.count())
                .ngayCapNhat(new Date())
                .maSPCT(1)
                .gia(1)
                .build();

        Assertions.assertTrue(this.giaRepository.update(gia));

        Assertions.assertTrue(Optional
                .ofNullable(gia.getMaLSG()).isPresent());
    }

    @Test
    public void getAll() {
        List<org.project1.nhom8.model.GiaModel> gias = this.giaRepository.findAll();

        for (var gia : gias) {
            System.out.println(gia.getMaLSG());
        }
    }

    @Test
    @DisplayName("lich su gia")
    public void getLSG() {
        lsgViewModelProvider.getLSGViewModels(1)
                .stream()
                .map(o -> o.getGia())
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("gia latest price")
    public void latestPrice() {
        System.out.println(giaRepository.getgiaMoiNhat(1).getGia());
    }
}
