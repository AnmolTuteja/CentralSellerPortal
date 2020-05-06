package com.acms.CentralSellerPortal.Services;

import com.acms.CentralSellerPortal.Entities.Ecommerce;
import com.acms.CentralSellerPortal.Repositories.EcommerceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EcommerceService {

    @Autowired
    EcommerceRepository ecommerceRepository;



    public void save(Ecommerce ecommerce)
    {
        ecommerceRepository.save(ecommerce);
    }

    public Optional<Ecommerce> findById(Long ecommId){
        Optional<Ecommerce> ecommerce = ecommerceRepository.findById(ecommId);
        return ecommerce;
    }

    public List<Ecommerce> findAll() {
        List<Ecommerce> ecommerceList = ecommerceRepository.findAll();
        return  ecommerceList;
    }
}