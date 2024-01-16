package helpers;

import dataentities.akaunting.Customer;

public class HelpersAkaunting {

    public void addNewCustomer( String name, String email){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);

    }
}
