package util;

public enum Vietnampattern {

    TEN("^[a á à ả ã ạ ă ắ ằ ẳ ẵ ặ â ấ ầ ẩ ẫ ậ b c d đ e é è ẻ ẽ ẹ ê ế ề ể ễ ệ g h i í ì ỉ ĩ ị k l m n o ó ò ỏ õ ọ ô ố ồ ổ ỗ ộ ơ ớ ờ ở ỡ ợ p q r s t u ú ù ủ ũ ụ ư ứ ừ ử ữ ự v x y A Á À Ả Ã Ạ Ă Ắ Ằ Ẳ Ẵ Ặ Â Ấ Ầ Ẩ Ẫ Ậ B C D Đ E É È Ẻ Ẽ Ẹ Ê Ế Ề Ể Ễ Ệ G H I Í Ì Ỉ Ĩ Ị K L M N O Ó Ó Ỏ Õ Ọ Ô Ố Ồ Ổ Ỗ Ộ Ơ Ớ Ờ Ở Ỡ Ợ P Q R S T U Ú Ù Ủ Ũ Ụ Ư Ứ Ừ Ử Ữ Ự V X Y]+$"),
    DIA_CHI ("^[a á à ả ã ạ ă ắ ằ ẳ ẵ ặ â ấ ầ ẩ ẫ ậ b c d đ e é è ẻ ẽ ẹ ê ế ề ể ễ ệ g h i í ì ỉ ĩ ị k l m n o ó ò ỏ õ ọ ô ố ồ ổ ỗ ộ ơ ớ ờ ở ỡ ợ p q r s t u ú ù ủ ũ ụ ư ứ ừ ử ữ ự v x y A Á À Ả Ã Ạ Ă Ắ Ằ Ẳ Ẵ Ặ Â Ấ Ầ Ẩ Ẫ Ậ B C D Đ E É È Ẻ Ẽ Ẹ Ê Ế Ề Ể Ễ Ệ G H I Í Ì Ỉ Ĩ Ị K L M N O Ó Ó Ỏ Õ Ọ Ô Ố Ồ Ổ Ỗ Ộ Ơ Ớ Ờ Ở Ỡ Ợ P Q R S T U Ú Ù Ủ Ũ Ụ Ư Ứ Ừ Ử Ữ Ự V X Y 0-9 -]+$"),
    MAT_KHAU ("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");

    private final String value;

    Vietnampattern(final String value) {

        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
