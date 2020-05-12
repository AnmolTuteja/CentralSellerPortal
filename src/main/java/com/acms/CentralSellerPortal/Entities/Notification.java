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
    private long notificationId;

    @Column
    @NotNull
    private String notificationMessage;

    @Column
    private long sellerId;

    @Column
    private long productId;

    @Column
    private Date ndate=new Date(2323223232L);

    @Column
    private boolean isUpdate;


    public Notification() {
    }

    public Notification(@NotNull String notificationMessage, long sellerId, long productId,  Date ndate) {
        this.notificationMessage = notificationMessage;
        this.sellerId = sellerId;
        this.productId = productId;
        this.ndate = ndate;
        this.isUpdate = isUpdate;
    }

    public Notification(@NotNull String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Notification(@NotNull String notificationMessage, Date ndate,long sellerId, long productId, boolean update) {
        this.notificationMessage = notificationMessage;
        this.ndate = ndate;
        this.sellerId = sellerId;
        this.productId = productId;
        this.isUpdate = update;
    }

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Date getNdate() {
        return ndate;
    }

    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }

    public boolean isUpdate() { return isUpdate; }

    public void setUpdate(boolean update) { isUpdate = update; }
}