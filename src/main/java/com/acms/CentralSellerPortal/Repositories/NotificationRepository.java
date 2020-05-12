package com.acms.CentralSellerPortal.Repositories;

import com.acms.CentralSellerPortal.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.acms.CentralSellerPortal.Entities.Product;
import com.acms.CentralSellerPortal.Entities.Seller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface  NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value="SELECT * FROM notification n where n.ndate>=?1" , nativeQuery = true)
    List<Notification> findByDate(Date date);

    Notification findBySellerIdAndProductIdAndIsUpdate(long s_id, long p_id, boolean update);

    @Query(value="select count(*) from notification where ndate>=(select date from ecommerce where ecomm_id=?1)" , nativeQuery = true)
    Integer getAllNotification(long eid);

}