package com.acms.CentralSellerPortal.Repositories;

import com.acms.CentralSellerPortal.Entities.Ecommerce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EcommerceRepository extends JpaRepository< Ecommerce, Long> {
}
