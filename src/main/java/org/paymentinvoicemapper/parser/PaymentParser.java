package org.paymentinvoicemapper.parser;

import org.paymentinvoicemapper.entities.Payment;

import java.util.Arrays;
import java.util.List;

public class PaymentParser implements Parser<String, Payment<String, Integer, String>> {

    @Override
    public Payment<String, Integer, String> parseString(final String payment) {
        List<String> parsedList = Arrays.stream(payment.split(","))
                .map(String::trim)
                .toList();
        final String invoiceId = parsedList.get(2).split(":")[1].trim();
        return new Payment<>(parsedList.get(0), Integer.parseInt(parsedList.get(1)), invoiceId);
    }
}
