package dto;

/**
 * @author ngtnthori03
*/
@DataParam()
public class VoucherFilter {
    private String voucherId;

    private int minimumPrice;
    private int maximumPrice;

    private int minimumComdPrice;
    private int maximumCondPrice;

    private Date minimumStartDate;
    private Date maximumStartDate;

    private Date minimumEndDate;
    private Date maximumEndDate;

    private TrangThaiVoucher voucherStatus;
}
