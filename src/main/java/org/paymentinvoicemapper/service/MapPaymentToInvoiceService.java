package org.paymentinvoicemapper.service;

import org.paymentinvoicemapper.entities.Invoice;
import org.paymentinvoicemapper.entities.Payment;
import org.paymentinvoicemapper.parser.Parser;

import java.util.List;
import java.util.Optional;

public class MapPaymentToInvoiceService implements ServiceInterface{

    private final String payment;
    private final List<String> invoices;
    private final Parser<String, Payment<String, Integer, String>> paymentParser;
    private final Parser<String, Invoice<String, String, Double>> invoiceParser;

    public MapPaymentToInvoiceService(final String payment,
                                      final List<String> invoices,
                                      final Parser<String, Payment<String, Integer, String>> paymentParser,
                                      final Parser<String, Invoice<String, String, Double>> invoiceParser) {
        this.invoices = invoices;
        this.payment = payment;
        this.paymentParser = paymentParser;
        this.invoiceParser = invoiceParser;

    }

    @Override
    public String getPaymentToInvoiceMapping() {
        final Payment<String, Integer, String> paymentObject = paymentParser.parseString(payment);
        final List<Invoice<String, String, Double>> invoiceList = invoices.stream()
                .map(invoiceParser::parseString)
                .toList();
        Optional<Invoice<String, String, Double>> matchedInvoice = invoiceList.stream()
                .filter(invoice -> invoice.getInvoiceId().equals(paymentObject.getInvoiceId()))
                .findFirst();
        return matchedInvoice.map(stringStringDoubleInvoice -> paymentObject.getPaymentId() + " pays off "
                + paymentObject.getPaymentAmount()
                + " for "
                + matchedInvoice.get().getInvoiceId()
                + " due on "
                + stringStringDoubleInvoice.getInvoiceDueDate()).orElse("Match not found");
    }

}
