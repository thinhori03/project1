package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.repository.GiaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TestGia {

    private GiaRepository giaRepository;

    public TestGia() {
        this.giaRepository = new GiaRepository();
    }

    @Test
    public void addGia() {

        GiaModel gia = new GiaModel()
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
        List<GiaModel> gias = this.giaRepository.findAll();

        for (var gia : gias) {
            System.out.println(gia.getMaLSG());
        }
    }
}
