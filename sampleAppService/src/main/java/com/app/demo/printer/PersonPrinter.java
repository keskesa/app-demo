/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/printer/Printer.e.vm.java
 */
package com.app.demo.printer;

import java.util.Locale;
import javax.inject.Named;
import javax.inject.Singleton;
import com.app.demo.domain.Person;
import com.app.demo.printer.DiscoverablePrinter;

/**
 * {@link org.springframework.format.Printer} for {@link Person} 
 *
 * @see org.springframework.format.Printer
 * @see DiscoverablePrinter
 * @see TypeAwarePrinter
 */
@Named
@Singleton
public class PersonPrinter extends DiscoverablePrinter<Person> {
    public PersonPrinter() {
        super(Person.class);
    }

    @Override
    public String print(Person person, Locale locale) {
        if (person == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder();
        appendIfNotEmpty(ret, person.getEmail());
        appendIfNotEmpty(ret, person.getFirstName());
        appendIfNotEmpty(ret, person.getLastName());
        return ret.toString();
    }
}