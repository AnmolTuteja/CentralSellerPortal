package com.acms.CentralSellerPortal.Controllers;

import com.acms.CentralSellerPortal.Entities.Product;
import com.acms.CentralSellerPortal.Entities.Seller;
import com.acms.CentralSellerPortal.Repositories.ProductRepository;
import com.acms.CentralSellerPortal.Repositories.SellerRepository;
import com.acms.CentralSellerPortal.Services.NotificationService;
import com.acms.CentralSellerPortal.Services.ProductService;
import com.acms.CentralSellerPortal.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    SellerService sellerService;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/postProduct/{id}", method=RequestMethod.POST)
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

        //this can be wrong
        Optional<Seller> optionalSeller = Optional.ofNullable(sellerService.findById(seller_id));
        if(optionalSeller.isPresent()) {
            Seller seller = optionalSeller.get();
            product.setSeller(seller);
            productService.save(product);
            long p_id=product.getProductId();
            Date dw=new Date();
            notificationService.save(seller.getSellerName()+" has added a product a new product "+product.getProductName(),dw ,0,p_id);


            //return "updated..";
        }

        RedirectView rv = new RedirectView();
        String rurl="/SellerDashboard.jsp?id="+Long.toString(seller_id);
        rv.setUrl(rurl);
        return rv;

    }
    @RequestMapping(value="/getProductsBySeller/{id}/{e_id}" ,method=RequestMethod.GET)
    public RedirectView getProductBySeller(
            @PathVariable(value = "id") long sellerId,
            @PathVariable(value = "e_id") long ecommId,
            HttpSession session)
    {
        List<Product> productList = productService.findBySeller_SellerId(sellerId);
        System.out.println("getting Products");
        System.out.println(productList);
        session.setAttribute("productList",productList);
        System.out.println(session.getAttribute("productList"));
        //this can be wrong
        Optional<Seller> optionalSeller = Optional.ofNullable(sellerService.findById(sellerId));
        if(optionalSeller.isPresent()){
            Seller seller = optionalSeller.get();
            String sellerName = seller.getSellerName();
            RedirectView rv = new RedirectView();
            String rurl="/ProductsBySellerId.jsp?e_id="+Long.toString(ecommId)+"&s_name="+ sellerName;
            System.out.println(rurl);
            rv.setUrl(rurl);
            return rv;
        }else{
            RedirectView rv = new RedirectView();
            String rurl = "/index.html";
            System.out.println(rurl);
            rv.setUrl(rurl);
            return rv;
        }



    }

    @RequestMapping(value = "/postUpdatedProduct/{p_id}/{id}" , method = RequestMethod.POST)
    public RedirectView updateProduct(@PathVariable(value = "p_id") Long product_id,@PathVariable(value = "id") Long seller_id,
                                @RequestParam("ep_name") String n ,
                                @RequestParam("ep_description") String d,
                                @RequestParam("ep_price") int p,
                                @RequestParam("ep_discount") int disc)
    {
        //this can be wrong
        Optional<Product> optionalProduct = Optional.ofNullable(productService.findById(product_id));
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(n);
            product.setProductDescription(d);
            product.setPrice(p);
            product.setDiscount(disc);

            productService.save(product);
            Seller seller=product.getSeller();
            long p_id=product.getProductId();

            Date dw=new Date();
            notificationService.save(seller.getSellerName()+" has updated a product a product "+product.getProductName(),dw ,0,p_id);


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

    @GetMapping(value = "/getAllProducts/{e_id}")
    public RedirectView getAllProduct(
            @PathVariable("e_id") Long ecommId,
            HttpSession session
    )
    {
        List<Product> allProductList =productService.findAll();
        session.setAttribute("allProductList",allProductList );
        RedirectView rv = new RedirectView();
        System.out.println(session.getAttributeNames());
        String rurl="/DisplayAllProducts.jsp?e_id="+Long.toString(ecommId);
        System.out.println(rurl);
        rv.setUrl(rurl);
        return rv;

//        return ResponseEntity.ok().body(productList);
    }


//    @GetMapping(value = "/displayById")
//    public ResponseEntity<Product> getProductById(@RequestParam Long  product_id)
//    {
//        Product product =productService.findById(product_id);
//        return ResponseEntity.ok().body(product);
//    }
    @RequestMapping(value = "/delete/{p_id}/{id}")
    public RedirectView deleteProduct(@PathVariable(value = "p_id") Long product_id,
                                      @PathVariable(value = "id") Long seller_id
                                      ){
        productService.deleteById(product_id);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        String rurl = "/SellerDashboard.jsp?id="+seller_id;
        redirectView.setUrl(rurl);
        return redirectView;
    }

    @RequestMapping(value = "/getBySellerId/{id}", method = RequestMethod.GET)
    public RedirectView getProductBySellerId(@PathVariable(value = "id") long seller_id, HttpSession session)
    {
        List<Product> productList = productService.findBySeller_SellerId(seller_id);
        System.out.println("getting Products");
        System.out.println(productList);
        session.setAttribute("productList",productList);
        System.out.println(session.getAttribute("productList"));

        RedirectView rv = new RedirectView();
        System.out.println(session.getAttributeNames());
        String rurl="/MyProducts.jsp?id="+Long.toString(seller_id);
        System.out.println(rurl);
        rv.setUrl(rurl);
        return rv;



        //return ResponseEntity.ok().body(productList);
    }
}
