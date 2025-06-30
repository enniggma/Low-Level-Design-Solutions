package org.paymentinvoicemapper.parser;


public interface Parser <T, U> {
    U parseString(T rawString);
}
