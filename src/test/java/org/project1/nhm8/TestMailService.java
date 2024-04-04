package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.service.MailService;
import org.project1.nhom8.util.HTMLResolver;
import org.thymeleaf.context.Context;

public class TestMailService {

    private final MailService mailService;

    private final HTMLResolver htmlResolver;

    public TestMailService() {
        mailService = new MailService();

        htmlResolver = new HTMLResolver();
    }

    @Test
    public void sendMail() {

        mailService.autoAuth();

        Context context = new Context();

        context.setVariable("code", 123456);

        mailService.send("ngtnthori03@gmail.com"
                , "forgot password code"
                , htmlResolver.revolve("forgot-password", context)
        );

        Assertions.assertTrue(mailService.isResult());
    }
}
