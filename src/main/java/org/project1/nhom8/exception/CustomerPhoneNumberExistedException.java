package org.project1.nhom8.exception;

/**
 * throw when {@link Cart#getCustomerPhoneNumber()}
 */
public class CustomerPhoneNumberExistedException extends RuntimeException {

    private String message;

    public CustomerPhoneNumberExistedException(String phoneNumber) {
        this.message = phoneNumber;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
