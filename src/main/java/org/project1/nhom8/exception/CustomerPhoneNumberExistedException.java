package org.project1.nhom8.exception;

/**
 * throw when {@link org.project1.nhom8.dto.Cart#getCustomerPhoneNumber()}
 */
public class CustomerPhoneNumberExistedException extends RuntimeException {

    private String message;

    public CustomerPhoneNumberExistedException(String phoneNumber) {
        this.message = phoneNumber;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
