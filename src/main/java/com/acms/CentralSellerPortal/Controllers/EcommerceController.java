package com.acms.CentralSellerPortal.Controllers;

import com.acms.CentralSellerPortal.Entities.Ecommerce;
import com.acms.CentralSellerPortal.Entities.Product;
import com.acms.CentralSellerPortal.Entities.Seller;
//import com.acms.CentralSellerPortal.Services.EcommerceService;
//import com.acms.CentralSellerPortal.Services.SellerService;

import com.acms.CentralSellerPortal.Repositories.EcommerceRepository;
import com.acms.CentralSellerPortal.Repositories.ProductRepository;
import com.acms.CentralSellerPortal.Services.EcommerceService;
import com.acms.CentralSellerPortal.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ecomm")
public class EcommerceController {

    @Autowired
    EcommerceService ecommerceService;

    @Autowired
    ProductService productService;

    @RequestMapping(value="/postEcommerce")
    public RedirectView addecommerce(@RequestParam("cName") String ecommerce_name,
                                     @RequestParam("cEmailId") String ecommerce_emailId,
                                     @RequestParam("cPassword") String ecommerce_password)
    {

        Ecommerce ecommerce=new Ecommerce();
        ecommerce.setEcommName(ecommerce_name);
        ecommerce.setEcommEmailId(ecommerce_emailId);
        ecommerce.setEcommPassword(ecommerce_password);

        ecommerceService.save(ecommerce);
        System.out.println("inside ecomm controller after saving data in db..");
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/index.html");
        return redirectView;
    }
    @GetMapping("/getAllEcommerce")
    public ResponseEntity<List<Ecommerce>> getAllEcommerce() {
        System.out.println("Evaluating emmorece : ");
        List<Ecommerce> ecommerceList =ecommerceService.findAll();
        System.out.println("Displaying emmorece : ");
        return ResponseEntity.ok().body(ecommerceList);
    }

//    @GetMapping(value = "/displayAllProducts")
//    public ResponseEntity<List<Product>> getAllProduct()
//    {
//        List<Product> productList =productService.findAll();
//        return ResponseEntity.ok().body(productList);
//    }

    @RequestMapping(value="/getEcommerce/{e_id}" ,method=RequestMethod.GET)
    public RedirectView getSellerById(@PathVariable(value = "e_id") long ecomm_id, HttpSession session)
    {
        System.out.println("Getting your details ecomm..please wait!!");
        System.out.println(ecomm_id);
        //EcommerceRepository ecommerceRepository;
        Ecommerce ecommerce = ecommerceService.findById(ecomm_id).orElse(null);

        session.setAttribute("ecommEmailId", ecommerce.getEcommEmailId());
        session.setAttribute("ecommName", ecommerce.getEcommName());
        //session.setAttribute("ecommassword",seller.getSellerPassword());

        //session.setAttribute("seller", seller);



        RedirectView rv = new RedirectView();
        String rurl="/ViewEcommProfile.jsp?e_id="+Long.toString(ecommerce.getEcommId());
        System.out.println(rurl);
        rv.setUrl(rurl);
        return rv;
    }

    @RequestMapping(value="/postUpdatedEcommerce/{e_id}" , method=RequestMethod.POST)
    public RedirectView updateUser(
            @PathVariable(value = "e_id") Long ecommId,
            @RequestParam("c_name") String ecommName,
            @RequestParam("c_mail") String ecommEmailId,
            @RequestParam("c_password") String password,
            HttpSession session)
    {
        Ecommerce ecommerce=ecommerceService.findById(ecommId).orElse(null);
        System.out.println("hi................................");
        ecommerce.setEcommName(ecommName);
        ecommerce.setEcommEmailId(ecommEmailId);
        ecommerce.setEcommPassword(password);
        session.setAttribute("ecommName", ecommerce.getEcommName());
        session.setAttribute("ecommEmailId", ecommerce.getEcommEmailId());
        session.setAttribute("ecommPassword", ecommerce.getEcommPassword());

        ecommerceService.save(ecommerce);
        RedirectView rv = new RedirectView();
        String rurl="/EcommDashboard.jsp?e_id="+Long.toString(ecommerce.getEcommId());
        rv.setUrl(rurl);
        return rv;
    }


}