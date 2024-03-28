package org.project1.nhom8.repository;

import org.project1.nhom8.model.MauSacModel;
import org.project1.nhom8.model.SizeModel;

public class MauSacRepository extends GeneralRepository<MauSacModel, Integer> {

    public MauSacRepository() {
        super(MauSacModel.class);
    }
}
