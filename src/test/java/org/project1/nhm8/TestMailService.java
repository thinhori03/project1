package org.project1.nhm8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.service.MailService;

public class TestMailService {

    private final MailService mailService;

    public TestMailService() {
        mailService = new MailService();
    }

    @Test
    public void sendMail() {

        mailService.autoAuth();

        mailService.send("ngtnthori03@gmail.com"
                , "forgot password code"
                , "your code: 19762"
        );

        Assertions.assertTrue(mailService.isResult());
    }
}
