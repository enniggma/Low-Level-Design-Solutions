package org.paymentinvoicemapper;

import org.paymentinvoicemapper.entities.Invoice;
import org.paymentinvoicemapper.entities.Payment;
import org.paymentinvoicemapper.parser.InvoiceParser;
import org.paymentinvoicemapper.parser.Parser;
import org.paymentinvoicemapper.parser.PaymentParser;
import org.paymentinvoicemapper.service.MapPaymentToInvoiceService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        final List<String> invoicesList = List.of(
                "invoiceA,2024-01-01,100",
                "invoiceB,2024-02-01,200",
                "invoiceC,2023-01-30,1000"
        );
        final Parser<String, Payment<String, Integer, String>> paymentParser = new PaymentParser();
        final Parser<String, Invoice<String, String, Double>> invoiceParser = new InvoiceParser();
        final MapPaymentToInvoiceService service = new MapPaymentToInvoiceService("payment5,1000,Paying off: invoiceC",
                invoicesList, paymentParser, invoiceParser);
        final String result = service.getPaymentToInvoiceMapping();
        System.out.println(result);


        // Verify results
        final String expected = "payment5 pays off 1000 for invoiceC due on 2023-01-30";
        if(expected.equals(result)) {
            System.out.println("Tests passed");
        } else {
            throw new RuntimeException("Tests failed");
        }
    }
}