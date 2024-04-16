package org.project1.nhom8.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.project1.nhom8.dto.Cart;
import org.project1.nhom8.dto.CartDetail;
import org.project1.nhom8.dto.provider.InvoiceExportProvider;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
import org.project1.nhom8.model.HDCTModel;
import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.model.KhuyenMai;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.HDCTKMRepository;
import org.project1.nhom8.repository.HDCTRepository;
import org.project1.nhom8.repository.HoaDonRepository;
import org.project1.nhom8.repository.KhachHangConnection;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.VoucherRepository;
import org.project1.nhom8.util.HTMLResolver;
import org.project1.nhom8.util.TrangThaiHoaDon;
import org.thymeleaf.context.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HoaDonService {

    private final HoaDonRepository hoaDonRepository;

    private final HDCTRepository hdctRepository;

    private final HDCTKMRepository hdctkmRepository;

    private final SimpleDateFormat simpleDateFormat;

    private final SPCTRepository spctRepository;

    private final KhuyenMaiService khuyenMaiService;

    private final VoucherRepository voucherRepository;

    private final KhachHangConnection khachHangConnection;

    public HoaDonService() {

        hoaDonRepository = new HoaDonRepository();

        hdctRepository = new HDCTRepository();

        hdctkmRepository = new HDCTKMRepository();

        simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

        spctRepository = new SPCTRepository();

        khuyenMaiService = new KhuyenMaiService();

        voucherRepository = new VoucherRepository();

        khachHangConnection = new KhachHangConnection();


    }

    public String taoHoaDon(Cart cart, TrangThaiHoaDon trangThai) {

        VoucherModel voucherModel = new VoucherModel();

        HoaDonModel hoaDonModel = new HoaDonModel();
        hoaDonModel.setMaHoaDon(cart.getInvoiceId());
        hoaDonModel.setNgayTao(cart.getCreationDate());
        hoaDonModel.setNgayThanhToan(new Date());
        hoaDonModel.setMaNV(cart.getCreator().getMa());
        hoaDonModel.setMaKH(khachHangConnection.findByPhoneNumber(cart.getCustomerPhoneNumber()).getMaKH());
        hoaDonModel.setTrangThai(trangThai.getValue());
        hoaDonModel.setMaNVXN(cart.getSaver().getMa());
        hoaDonModel.setTienThanhToan(cart.getPayment());

        if (cart.getVoucherId() != null) {
            hoaDonModel.setMaVoucher(cart.getVoucherId());

            if (trangThai == TrangThaiHoaDon.DA_THANH_TOAN) {
                voucherModel = voucherRepository.findById(cart.getVoucherId());

                voucherModel.setSoLuong(voucherModel.getSoLuong() - 1);
                voucherRepository.update(voucherModel);
            }
        }

        hoaDonModel.setPhuongThuc(cart.getPaymentMethod());

        hoaDonRepository.add(hoaDonModel);

        HDCTModel hdct = null;

        SPCTModel spct = null;

        KhuyenMai khuyenMai = null;

        for (CartDetail cd : cart.getProducts().values().stream().toList()) {
            hdct = new HDCTModel();

            hdct.setMaHDCT(taoMaHDCT());

            hdct.setMaSPCT(cd.getProduct().getMaSPCT());

            if (trangThai == TrangThaiHoaDon.DA_THANH_TOAN) {
                spct = cd.getProduct();
                spct.setSoluong(spct.getSoluong() - cd.getQuantity());
                spctRepository.update(spct);
            }

            hdct.setMaLSG(cd.getPrice().getMaLSG());
            hdct.setSoLuong(cd.getQuantity());
            hdct.setMaHoaDon(cart.getInvoiceId());

            if (cd.getCoupon() != null) {
                hdct.setMaKM(cd.getCoupon().getMakm());

                if (trangThai == TrangThaiHoaDon.DA_THANH_TOAN) {
                    khuyenMai = cd.getCoupon();
                    khuyenMai.setSoluong(khuyenMai.getSoluong() - 1);
                    khuyenMaiService.UpdateKhuyenMai(khuyenMai.getMakm(), khuyenMai);
                }
            }

            hdctRepository.add(hdct);
        }

        return cart.getInvoiceId();
    }

    /**
     * @return mã mã hoa đơn mới
     */
    public String taoMaHoaDon() {
        return "HD" + (this.simpleDateFormat.format(new Date()));
    }

    public Integer taoMaHDCT() {
        return hdctRepository.count() + 1;
    }

    public void export(Cart cart, String targetDir) throws IOException {
        HTMLResolver htmlResolver = new HTMLResolver();

        Context invoiceContext = new Context();
        invoiceContext.setVariable("invoice", cart);
        invoiceContext.setVariable("user", LoginService.lg);
        invoiceContext.setVariable("prodViewProv", new SPCTViewModelProvider());

        String html = htmlResolver.revolve("invoice-export", invoiceContext);
        System.out.println(html);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(String.valueOf(this
                    .getClass().getResourceAsStream("/_.html")))));
            bw.write(html);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConverterProperties convProp = new ConverterProperties();

        FileOutputStream outputFile = new FileOutputStream(targetDir + "/" + cart.getInvoiceId() + ".pdf");

        HtmlConverter.convertToPdf(html
                , outputFile
                , convProp
        );


        // Desktop.getDesktop().browseFileDirectory(new File(targetDir + "/" + cart.getInvoiceId() + ".pdf"));
    }

    public void export(String invoiceId, String targetDir) throws IOException {
        HTMLResolver htmlResolver = new HTMLResolver();

        Context invoiceContext = new Context();
        invoiceContext.setVariable("invoice", new InvoiceExportProvider().getInvoice(invoiceId));

        String html = htmlResolver.revolve("invoice-export", invoiceContext);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(String.valueOf(this
                    .getClass().getResourceAsStream("/_.html")))));
            bw.write(html);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConverterProperties convProp = new ConverterProperties();

        FileOutputStream outputFile = new FileOutputStream(targetDir + "/" + invoiceId + ".pdf");

        HtmlConverter.convertToPdf(html
                , outputFile
                , convProp
        );


        // Desktop.getDesktop().browseFileDirectory(new File(targetDir + "/" + cart.getInvoiceId() + ".pdf"));
    }
}
