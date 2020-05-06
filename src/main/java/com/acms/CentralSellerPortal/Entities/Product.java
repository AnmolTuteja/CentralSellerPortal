package com.acms.CentralSellerPortal.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


@Entity
@Table (name="product")
public class Product implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long productId;

        @Column
        @NotNull
        private String productName;

        @Column
        @NotNull
        private String productDescription;

        @Column
        @NotNull
        private int price;

        @Column
        @NotNull
        private int discount;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "seller_id") //nullable = false)
        private Seller seller;

    public Product() {
    }

    public Product(int product_id,
                   @NotNull String product_name,
                   @NotNull int price,
                   @NotNull String product_description,
                   @NotNull int discount,
                   Seller seller) {
        this.productId = product_id;
        this.productName = product_name;
        this.price = price;
        this.productDescription = product_description;
        this.discount = discount;
        this.seller = seller;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long product_id) {
        this.productId = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String product_description) {
        this.productDescription = product_description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + productId +
                ", product_name='" + productName + '\'' +
                ", product_description='" + productDescription + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                //", seller=" + seller.toString() +
                '}'+'\n';
    }
}
