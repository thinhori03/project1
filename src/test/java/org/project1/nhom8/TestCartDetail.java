package org.project1.nhom8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project1.nhom8.dto.CartDetail;
import org.project1.nhom8.repository.SPCTRepository;

public class TestCartDetail {

    private final SPCTRepository spctRepository = new SPCTRepository();

    public TestCartDetail() {

    }

    @Test
    public void compare() {

        CartDetail cart = new CartDetail();
        cart.setProduct(spctRepository.findById(2));

        CartDetail cart2 = new CartDetail();
        cart2.setProduct(spctRepository.findById(2));

        Assertions.assertEquals(cart2, cart);
    }
}
