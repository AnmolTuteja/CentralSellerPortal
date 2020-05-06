package com.acms.CentralSellerPortal.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name="seller")
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sellerId;

    @Column
    @Size(max=100)
    @NotNull
    private String sellerName;

    @Column
    @Size(max=300)
    @NotNull
    private String sellerAddress;

    @Column
    @Size(max=100)
    @NotNull
    private String shopName;

    @Column(unique= true)
    @Size(max=10)
    @NotNull
    private String sellerContactNo;

    @Column(unique= true)
    @Size(max=100)
    @NotNull
    private String sellerEmailId;

    @Column
    @NotNull
    private String sellerPassword;

    @JsonIgnore
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> product;

    public Seller() {}


    public Seller(@NotNull String seller_name, @NotNull String seller_address, @NotNull String shop_name, @NotNull String seller_contactNo, @NotNull String seller_emailId , @NotNull String seller_password ) {
        this.sellerName = seller_name;
        this.sellerAddress = seller_address;
        this.shopName = shop_name;
        this.sellerContactNo = seller_contactNo;
        this.sellerEmailId = seller_emailId;
        this.sellerPassword = seller_password;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long seller_id) {
        this.sellerId = seller_id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String seller_name) {
        this.sellerName = seller_name;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String seller_address) {
        this.sellerAddress = seller_address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shop_name) {
        this.shopName = shop_name;
    }

    public String getSellerContactNo() {
        return sellerContactNo;
    }

    public void setSellerContactNo(String seller_contactNo) {
        this.sellerContactNo = seller_contactNo;
    }

    public String getSellerEmailId() {
        return sellerEmailId;
    }

    public void setSellerEmailId(String seller_emailId) {
        this.sellerEmailId = seller_emailId;
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String seller_password) {
        this.sellerPassword = seller_password;
    }


    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

//    @Override
//    public String toString() {
//        return "Seller{" +
//                "sellerId=" + sellerId +
//                ", sellerName='" + sellerName + '\'' +
//                ", sellerAddress='" + sellerAddress + '\'' +
//                ", shopName='" + shopName + '\'' +
//                ", sellerContactNo='" + sellerContactNo + '\'' +
//                ", sellerEmailId='" + sellerEmailId + '\'' +
//                ", sellerPassword='" + sellerPassword + '\'' +
//                //", product=" + product +
//                '}';
//    }
}
