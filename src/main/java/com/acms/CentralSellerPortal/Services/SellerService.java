package com.acms.CentralSellerPortal.Services;

import com.acms.CentralSellerPortal.Entities.Seller;
import com.acms.CentralSellerPortal.Repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public void save(Seller seller) {
        sellerRepository.save(seller);
    }

    public List<Seller> findAll() {
        List<Seller> seller;
        seller = sellerRepository.findAll();
        return seller;
    }

    public Seller findById(long seller_id)
    {
        Seller seller = sellerRepository.findById(seller_id).orElse(null);
        return seller;
    }

}
