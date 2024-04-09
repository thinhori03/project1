package org.project1.nhom8;

import org.project1.nhom8.model.Login;
import org.project1.nhom8.service.CartService;
import org.project1.nhom8.service.LoginService;

/**
 * singleton
 */
public class Store {

    private final static CartService cartService;

    private final static Login loggedIn;

    private Store() {

    }

    static {
        cartService = new CartService();
        loggedIn = LoginService.lg;
    }

    public static CartService getCartService() {
        return cartService;
    }

    public static Login getLoggedIn() {
        return loggedIn;
    }
}
