package org.project1.nhom8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project1.nhom8.util.data.convert.CurrntCyFormat;
import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LSGViewModel {

    @DataHeader(name = "giá")
    @CurrntCyFormat
    private double gia;

    @DataHeader(name = "ngày cập nhật")
    private Date ngayCapNhat;
}
