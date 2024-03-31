package org.project1.nhom8.util.data.convert;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultConverter {

    public static String VietnameseDateFormat(Date date) {
        Format dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        return dateFormat.format(date).toString();
    }
}
