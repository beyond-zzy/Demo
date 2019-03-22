package com.ujiuye.cus.service;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;

import java.util.List;

public interface CustomerService {

    public boolean saveInfo(Customer cus);

    public List<Customer> showInfo(CustomerExample ex);
}
