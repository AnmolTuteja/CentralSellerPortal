package com.acms.CentralSellerPortal.Repositories;

import com.acms.CentralSellerPortal.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.acms.CentralSellerPortal.Entities.Seller;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySeller_SellerId(Long seller_id);

        Product findByProductId(Long productId);

//    @Query(value = "select p from Product where p.product_id = ?1")
//    Product findSellerIdByProductId(Long product_id);
}