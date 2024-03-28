package org.project1.nhom8.repository;

import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.VoucherModel;

import java.util.List;

public class GiaRepository extends GeneralRepository<GiaModel, Integer> {

    public GiaRepository() {
        super(GiaModel.class);
    }

    public GiaModel getgiaMoiNhat(int maspct) {
        return this.getQueryGenerator().executeCustomSelectAll(
                this.getConnection()
                , """
                                WHERE
                                    MASPCT = ?
                                    ORDER BY NGAYUPDATE DESC
                            """
                , maspct
        ).get(0);
    }

    public List<GiaModel> getLichSugia(int maspct) {
        return this.getQueryGenerator().executeCustomSelectAll(
                this.getConnection()
                , """
                                WHERE
                                    MASPCT = ?
                            """
                , maspct
        );
    }
}
