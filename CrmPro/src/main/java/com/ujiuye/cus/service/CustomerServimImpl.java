package com.ujiuye.cus.service;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServimImpl implements CustomerService  {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public boolean saveInfo(Customer cus) {
        int insert = customerMapper.insert(cus);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> showInfo(CustomerExample ex) {
        return customerMapper.selectByExample(ex);
    }
}
