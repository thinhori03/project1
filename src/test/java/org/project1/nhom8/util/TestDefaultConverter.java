package org.project1.nhom8.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.util.data.convert.DefaultConverter;

import java.util.Date;

public class TestDefaultConverter {

    @Test
    @DisplayName("test default converter")
    public void convertNow() {
        System.out.println(new DefaultConverter().VietnameseDateFormat(new Date()));
    }

}
