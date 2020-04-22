package com.acms.CentralSellerPortal.Controllers;

import com.acms.CentralSellerPortal.Entities.Product;
import com.acms.CentralSellerPortal.Entities.Seller;
import com.acms.CentralSellerPortal.Repositories.ProductRepository;
import com.acms.CentralSellerPortal.Repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @RequestMapping(value = "/add/{id}", method=RequestMethod.POST)
    public RedirectView addProduct(@PathVariable(value = "id") Long seller_id,
                                   @RequestParam("p_name") String n ,
                                   @RequestParam("p_description") String d,
                                   @RequestParam("p_price") int p,
                                   @RequestParam("p_discount") int disc)
    {
        Product product = new Product();
        product.setProductName(n);
        product.setProductDescription(d);
        product.setPrice(p);
        product.setDiscount(disc);

        Optional<Seller> optionalSeller = sellerRepository.findById(seller_id);
        if(optionalSeller.isPresent()) {
            Seller seller = optionalSeller.get();
            product.setSeller(seller);
            productRepository.save(product);

            //return "updated..";
        }
        RedirectView rv = new RedirectView();
        String rurl="/SellerDashboard.jsp?id="+Long.toString(seller_id);
        rv.setUrl(rurl);
        return rv;

    }

    @RequestMapping(value = "/update/{p_id}/{id}" , method = RequestMethod.POST)
    public RedirectView updateProduct(@PathVariable(value = "p_id") Long product_id,
                                      @PathVariable(value = "id") Long seller_id,
                                @RequestParam("ep_name") String n ,
                                @RequestParam("ep_description") String d,
                                @RequestParam("ep_price") int p,
                                @RequestParam("ep_discount") int disc)
    {
        Optional<Product> optionalProduct = productRepository.findById(product_id);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(n);
            product.setProductDescription(d);
            product.setPrice(p);
            product.setDiscount(disc);

            productRepository.save(product);
//            return "updated";
            RedirectView rv = new RedirectView();
            String rurl="/SellerDashboard.jsp?id="+seller_id;
            System.out.println(rurl);
            rv.setUrl(rurl);
            return rv;
        }
        else {
//          return "error updating";
            RedirectView rv = new RedirectView();
            String rurl = "/SellerDashboard.jsp?id="+seller_id;
            System.out.println(rurl);
            rv.setUrl(rurl);
            return rv;
        }
    }

    @GetMapping(value = "/displayAll")
    public ResponseEntity<List<Product>> getAllProduct()
    {
        List<Product> productList =productRepository.findAll();
        return ResponseEntity.ok().body(productList);
    }


    @GetMapping(value = "/displayById")
    public ResponseEntity<Product> getProductById(@RequestParam Long  product_id)
    {
        Product product =productRepository.findById(product_id).orElse(null);
        return ResponseEntity.ok().body(product);
    }
    @RequestMapping(value = "/delete/{p_id}/{id}")
    public RedirectView deleteProduct(@PathVariable(value = "p_id") Long product_id,
                                      @PathVariable(value = "id") Long seller_id
                                      ){
        productRepository.deleteById(product_id);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        String rurl = "/SellerDashboard.jsp?id="+seller_id;
        redirectView.setUrl(rurl);
        return redirectView;
    }

    @RequestMapping(value = "/displayBySellerId/{id}", method = RequestMethod.GET)
    public RedirectView getProductBySellerId(@PathVariable(value = "id") long seller_id, HttpSession session)
    {
        List<Product> productList = productRepository.findBySeller_SellerId(seller_id);
        System.out.println("getting Products");
        //System.out.println(productList.get(0));
        //Seller seller = sellerRepository.findById(seller_id).orElse(null);
        System.out.println(productList);
        session.setAttribute("productList",productList);
        System.out.println(session.getAttribute("productList"));
        //String[][] str = new String[productList.size()][5];
//        for(int i = 0; i < productList.size(); i++){
//
//                session.setAttribute("string"+i+"0", productList.get(i).getProductId());
//                session.setAttribute("string"+i+"1", productList.get(i).getProductName());
//                session.setAttribute("string"+i+"2", productList.get(i).getProductDescription());
//                session.setAttribute("string"+i+"3", productList.get(i).getPrice());
//                session.setAttribute("string"+i+"4", productList.get(i).getDiscount());
//
//        }
//        session.setAttribute("listSize", productList.size());
        //System.out.println((String)string[0][1]);
        //System.out.println(session.getAttribute("string00"));
        RedirectView rv = new RedirectView();
        System.out.println(session.getAttributeNames());
        String rurl="/MyProducts.jsp?id="+Long.toString(seller_id);
        System.out.println(rurl);
        rv.setUrl(rurl);
        return rv;



        //return ResponseEntity.ok().body(productList);
    }
}
