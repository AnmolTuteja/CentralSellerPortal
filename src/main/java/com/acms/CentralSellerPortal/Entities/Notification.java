package com.acms.CentralSellerPortal.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name="notification")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long notification_id;

    @Column
    @NotNull
    private String notification_message;

    @Column
    private long seller_id;

    @Column
    private long product_id;

    @Column
    private Date ndate=new Date(2323223232L);


    public Notification() {
    }

    public Notification(@NotNull String notification_message, long seller_id, long product_id,  Date ndate) {
        this.notification_message = notification_message;
        this.seller_id = seller_id;
        this.product_id = product_id;
        this.ndate = ndate;
    }

    public Notification(@NotNull String notification_message) {
        this.notification_message = notification_message;
    }

    public Notification(@NotNull String notification_message, Date ndate,long seller_id, long product_id) {
        this.notification_message = notification_message;
        this.ndate = ndate;
        this.seller_id = seller_id;
        this.product_id = product_id;
    }

    public long getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(long notification_id) {
        this.notification_id = notification_id;
    }

    public String getNotification_message() {
        return notification_message;
    }

    public void setNotification_message(String notification_message) {
        this.notification_message = notification_message;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public Date getNdate() {
        return ndate;
    }

    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }
}