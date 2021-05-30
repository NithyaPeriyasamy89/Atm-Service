package com.atm.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atm.model.Customer;

@FeignClient(value = "customer", url = "http://localhost:8081")
public interface CustomerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/service/customer")
    List<Customer> getCustomers();
    
    @RequestMapping(method = RequestMethod.POST, value = "/service/updateBalance")
    void updateBalance(@RequestParam("requestAmount") long requestAmount,@RequestParam("cardNumber") String cardNumber);
    

  
}