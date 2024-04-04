/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import org.project1.nhom8.util.HTMLResolver;
import org.thymeleaf.context.Context;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ngtnthori03
 */
public class ForgotPasswordService {

    private MailService mailService;

    private Integer code;

    public static final Integer MIN_CODE = 100000;

    public static final Integer MAX_CODE = 999999;

    public static Callable<Boolean> createMailSenderTask(ForgotPasswordService fPService, String mailReceiver) {
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                MailService mailService = fPService.getMailService();
                HTMLResolver htmlResolver = new HTMLResolver();

                /// authenticated
                if (mailService.getAuthenticated()) {
                    mailService.send(mailReceiver, "mã xác thực dổi mật khẩu"
                            , htmlResolver.revolve("forgot-password", fPService.getMailContext()));
                }

                return mailService.isResult();
            }
        };
    }

    public ForgotPasswordService() {
        this.code = 0;
        this.mailService = new MailService();
    }

    public Integer getCode() {

        if (code == 0) {
            genCode();
        }

        return this.code;
    }

    public Integer genCode() {
        this.code = ThreadLocalRandom.current()
                .nextInt(MIN_CODE, MAX_CODE + 1);
        return code;
    }

    public MailService getMailService() {
        return this.mailService;
    }

    public Context getMailContext() {
        Context context = new Context();

        context.setVariable("code", genCode());

        return context;
    }
}
