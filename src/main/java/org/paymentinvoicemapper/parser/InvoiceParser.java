package org.paymentinvoicemapper.parser;

import org.paymentinvoicemapper.entities.Invoice;

import java.util.Arrays;
import java.util.List;

public class InvoiceParser implements Parser<String, Invoice<String, String, Double>> {
    @Override
    public Invoice<String, String, Double> parseString(final String invoice) {
        List<String> parsedList = Arrays.stream(invoice.split(","))
                .map(String::trim)
                .toList();
        return new Invoice<>(
                parsedList.get(0),
                parsedList.get(1),
                Double.parseDouble(parsedList.get(2))
        );
    }
}
