package dto;

/**
 * @author ngtnthori03
*/
public class VoucherFilter {
    
    @DataParam(name = "vid")
    private String voucherId;

    @DataParam(name = "minPrice")
    private int minimumPrice;
    @DataParam(name = "maxPrice")
    private int maximumPrice;

    @DataParam(name = "minCondPrice")
    private int minimumComdPrice;
    @DataParam(name = "maxCondPrice")
    private int maximumCondPrice;

    @DataParam(name = "minStartDate")
    private Date minimumStartDate;
    @DataParam(name = "maxStartDate")
    private Date maximumStartDate;

    @DataParam(name = "minEndDate")
    private Date minimumEndDate;
    @DataParam(name = "maxEndDate")
    private Date maximumEndDate;

    @DataParam(name = "trangThai")
    private TrangThaiVoucher voucherStatus;
}
