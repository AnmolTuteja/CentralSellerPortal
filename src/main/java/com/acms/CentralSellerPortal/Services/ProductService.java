package com.acms.CentralSellerPortal.Services;

import com.acms.CentralSellerPortal.Entities.Product;
import com.acms.CentralSellerPortal.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public void save(Product product)
    {
        productRepository.save(product);
    }

    public Product findById(long product_id)
    {
        Product product= productRepository.findById(product_id).orElse(null);
        return product;
    }
    public void deleteById(long product_id)
    {
        productRepository.deleteById(product_id);
    }

    public List<Product> findBySeller_Id(Long seller_id)
    {
        List<Product> productList = productRepository.findBySeller_SellerId(seller_id);
        return productList;
    }

    public Product findByProductId(Long productId){
        Product product = productRepository.findByProductId(productId);
        return  product;
    }

    public List<Product> findBySeller_SellerId(Long seller_id){
        List<Product> productList = productRepository.findBySeller_SellerId(seller_id);
        return  productList;
    }
    public  List<Product> findAll(){
        List<Product> allProductList = productRepository.findAll();
        return  allProductList;
    }
}