package org.paymentinvoicemapper.entities;

public class Payment <T, U, V> {
    private final T paymentId;
    private final U paymentAmount;
    private final V invoiceId;

    public Payment(T paymentId, U paymentAmount, V invoiceId) {
        this.invoiceId = invoiceId;
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
    }

    public T getPaymentId() {
        return this.paymentId;
    }

    public U getPaymentAmount() {
        return this.paymentAmount;
    }

    public V getInvoiceId() {
        return this.invoiceId;
    }
}
