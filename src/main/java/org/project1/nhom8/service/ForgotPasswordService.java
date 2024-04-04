/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.project1.nhom8.service;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ngtnthori03
 */
public class ForgotPasswordService {
    
    private MailService mailService;
    
    private Integer code;
    
    public static final Integer MIN_CODE = 100000;
    
    public static final Integer MAX_CODE = 999999;
    
    public ForgotPasswordService() {
        this.mailService = new MailService();
    }
    
    public Integer getCode() {
            return this.code;
    }
    
    public Integer genCode() {
            return ThreadLocalRandom.current()
                    .nextInt(MIN_CODE, MAX_CODE + 1);
    }
    
    public MailService getMailService() {
        return this.mailService;
    }
}
