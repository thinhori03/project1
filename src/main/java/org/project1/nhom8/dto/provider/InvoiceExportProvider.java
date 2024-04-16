package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.InvoiceExport;
import org.project1.nhom8.dto.ProductExport;
import org.project1.nhom8.model.HDCTModel;
import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.HDCTRepository;
import org.project1.nhom8.repository.HoaDonRepository;
import org.project1.nhom8.repository.KhachHangConnection;
import org.project1.nhom8.repository.MauSacRepository;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.repository.SizeRepository;
import org.project1.nhom8.repository.VoucherRepository;
import org.project1.nhom8.service.KhuyenMaiService;
import org.project1.nhom8.service.NhanVienService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InvoiceExportProvider {

    private final SPCTRepository spctRepository = new SPCTRepository();

    private final SanPhamRepository sanPhamRepository = new SanPhamRepository();

    private final GiaRepository giaRepository = new GiaRepository();

    private final MauSacRepository mauSacRepository = new MauSacRepository();

    private final SizeRepository sizeRepository = new SizeRepository();

    private final VoucherRepository voucherRepository = new VoucherRepository();

    private final HoaDonRepository hoaDonRepository = new HoaDonRepository();

    private final HDCTRepository hdctRepository = new HDCTRepository();

    private final NhanVienService nhanVienService = new NhanVienService();

    private final KhachHangConnection khachHangConnection = new KhachHangConnection();

    private final KhuyenMaiService khuyenMaiService = new KhuyenMaiService();

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    public InvoiceExportProvider() {

    }

    public InvoiceExport getInvoice(String invoiceId) {

        InvoiceExport invoice = new InvoiceExport();

        HoaDonModel invoiceInfo = hoaDonRepository.findById(invoiceId);

        invoice.setId(invoiceInfo.getMaHoaDon());
        invoice.setCreator(nhanVienService.findById(invoiceInfo.getMaNV()));
        invoice.setSeller(nhanVienService.findById(invoiceInfo.getMaNVXN()));
        invoice.setCustomer(khachHangConnection.findById(invoiceInfo.getMaKH()));
        invoice.setPaymentDate(simpleDateFormat.format(invoiceInfo.getNgayThanhToan()));
        invoice.setPayment(invoiceInfo.getTienThanhToan().intValue());

        if (invoiceInfo.getMaVoucher() != null) {
            invoice.setVoucherModel(voucherRepository.findById(invoiceInfo.getMaVoucher()));
        } else {
            invoice.setVoucherModel(null);
        }


        invoice.setProducts(this.getProducts(invoiceId));
        invoice.setTotalPrice(invoice.getProducts().stream()
                .mapToDouble(o -> o.getSellingPrice())
                .sum());

        return invoice;
    }

    public List<ProductExport> getProducts(String invoiceId) {

        List<ProductExport> result = new ArrayList<>();

        List<HDCTModel> hdcts = hdctRepository.findBymaHD(invoiceId);

        ProductExport pe = null;

        SPCTModel spct = null;

        for (HDCTModel hdct : hdcts) {

            pe = new ProductExport();
            spct = spctRepository.findById(hdct.getMaSPCT());

            pe.setId(spct.getMaSP() + "");
            pe.setName(sanPhamRepository.findById(spct.getMaSP()).getTensp());
            pe.setColor(mauSacRepository.findById(spct.getMaMauSac()).getTenmau());
            pe.setSize(sizeRepository.findById(spct.getMasize()).getTensize());
            pe.setPrice(giaRepository.findById(hdct.getMaLSG()).getGia().intValue());
            pe.setQuantity(hdct.getSoLuong());

            if (hdct.getMaKM() != null) {
                pe.setSellingPrice((int) (pe.getPrice() - khuyenMaiService.findById(hdct.getMaKM()).getGia()));
            } else {
                pe.setSellingPrice(pe.getPrice());
            }

            result.add(pe);
        }

        return result;

    }

}
