package com.acms.CentralSellerPortal.Services;

import com.acms.CentralSellerPortal.Entities.Ecommerce;
import com.acms.CentralSellerPortal.Entities.Notification;
import com.acms.CentralSellerPortal.Repositories.EcommerceRepository;
import com.acms.CentralSellerPortal.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EcommerceRepository ecommerceRepository;

    public static boolean flag = false;

    public void save(String message,
                     Date date,long s_id,long p_id, boolean update)
    {

        Notification notification=new Notification(message,date,s_id,p_id , update);

        Notification old = notificationRepository.findBySellerIdAndProductIdAndIsUpdate(s_id , p_id, update);
        if(old != null) {
            System.out.println("entered old wala if");
            notificationRepository.delete(old);
        }

        notificationRepository.save(notification);
        flag = true;


    }

    public List<Notification> findByE_Id(long e_id)
    {
        Ecommerce ecommerce=ecommerceRepository.findById(e_id).orElse(null);
        Date date=ecommerce.getDate();
        System.out.println(date+",,,,,,,,,,,,,,,,,,,,,,,");
        List<Notification> notificationList=notificationRepository.findByDate(date);
        return notificationList;

    }
}