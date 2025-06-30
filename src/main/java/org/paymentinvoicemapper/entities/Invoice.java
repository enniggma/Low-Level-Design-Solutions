package org.paymentinvoicemapper.entities;

public class Invoice <T, U, V> {
    private T invoiceId;
    private U invoiceDueDate;
    private V invoiceAmount;

    public Invoice(T invoiceId, U invoiceDueDate, V invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
        this.invoiceDueDate = invoiceDueDate;
        this.invoiceId = invoiceId;
    }

    public T getInvoiceId() {
        return this.invoiceId;
    }

    public U getInvoiceDueDate() {
        return this.invoiceDueDate;
    }

    public V getInvoiceAmount() {
        return this.invoiceAmount;
    }
}
