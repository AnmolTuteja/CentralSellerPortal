package com.acms.CentralSellerPortal.Controllers;
import com.acms.CentralSellerPortal.Entities.Product;
import com.acms.CentralSellerPortal.Entities.Seller;
import com.acms.CentralSellerPortal.Repositories.ProductRepository;
import com.acms.CentralSellerPortal.Repositories.SellerRepository;
import com.acms.CentralSellerPortal.Services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;



import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    EcommerceService ecommerceService;

    @Autowired
    ProductService productService;

    @Autowired
    private EmailService emailService;


    @RequestMapping(value="/postSeller"   , method=RequestMethod.POST)
    public RedirectView postSeller(
                                @RequestParam("s_name") String seller_name,
                               @RequestParam("s_address") String seller_address,
                               @RequestParam("s_shop") String shop_name,
                               @RequestParam("s_mail") String seller_emailId,
                               @RequestParam("s_mobile") String seller_contactNo,
                               @RequestParam("s_password") String password,
                                HttpSession session
                                //Model model

                               ) {

        session.removeAttribute("sellerContactNo");
        session.removeAttribute("sellerEmailId");

        List<Seller> seller;
        seller = sellerService.findAll();


        //checking for if entry already exist for unique attribute
        for(Seller s: seller) {
            if ((seller_contactNo).equals(s.getSellerContactNo()) && (seller_emailId).equals(s.getSellerEmailId()) ) {

                session.setAttribute("sellerContactNo", s.getSellerContactNo());
                session.setAttribute("sellerEmailId", s.getSellerEmailId());
                RedirectView redirectView = new RedirectView();
                redirectView.setContextRelative(true);
                redirectView.setUrl("/FailedSellerSignup.jsp");
                return redirectView;
            }
            else
            if ((seller_contactNo).equals(s.getSellerContactNo()) ) {

                session.setAttribute("sellerContactNo", s.getSellerContactNo());
                RedirectView redirectView = new RedirectView();
                redirectView.setContextRelative(true);
                redirectView.setUrl("/FailedSellerSignup.jsp");
                return redirectView;
            }
            else
            if ((seller_emailId).equals(s.getSellerEmailId())) {

                session.setAttribute("sellerEmailId", s.getSellerEmailId());
                RedirectView redirectView = new RedirectView();
                redirectView.setContextRelative(true);
                redirectView.setUrl("/FailedSellerSignup.jsp");
                return redirectView;
            }
        }

        Seller seller1=new Seller();
        seller1.setSellerName(seller_name);
        seller1.setSellerAddress(seller_address);
        seller1.setShopName(shop_name);
        seller1.setSellerEmailId(seller_emailId);
        seller1.setSellerContactNo(seller_contactNo);
        seller1.setSellerPassword(password);
        System.out.println("i got here...");

        sellerService.save(seller1);
        emailService.sendMail("shriyakabra97@gmail.com",
                "A new seller has registered",
                seller_name+" has signed up." +
                        "This is a system generated e-mail. Please do not reply.");

        //model.addAttribute("name", seller_name);
        long s_id=seller1.getSellerId();

        Date dw=new Date();
        notificationService.save(seller1.getSellerName()+" has signed up to this portal ",dw ,s_id,0);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/AddSellerSuccessful.jsp");
        return redirectView;


         //return "Updated Database";
    }
    @GetMapping("/getProductOrSeller/{e_id}/{s_id}/{p_id}")
    public RedirectView getSellerOrProduct(
            @PathVariable("e_id") Long ecommId,
            @PathVariable("s_id") Long sellerId,
            @PathVariable("p_id") Long productId,
            HttpSession session
    ){
        if(sellerId==0){
            //get product
            Product notificationProduct= productService.findByProductId(productId);
            session.setAttribute("notificationProduct", notificationProduct);
            RedirectView rv = new RedirectView();
            String rurl="/ViewProductNotification.jsp?e_id="+Long.toString(ecommId);
            System.out.println(rurl);
            rv.setUrl(rurl);
            return rv;


        }else {
            //get seller
            Seller notificationSeller = sellerService.findById(sellerId);
            session.setAttribute("notificationSeller", notificationSeller);
            RedirectView rv = new RedirectView();
            String rurl="/ViewSellerNotification.jsp?e_id="+Long.toString(ecommId);
            System.out.println(rurl);
            rv.setUrl(rurl);
            return rv;
        }

    }

    @GetMapping("/getAllSellers/{e_id}")
    public RedirectView getAllSellers(
            @PathVariable("e_id") Long ecommId,
            HttpSession session)
    {
        List<Seller> sellerList = sellerService.findAll();
        session.setAttribute("sellerList", sellerList);

        //return ResponseEntity.ok().body(sellerList);
        RedirectView rv = new RedirectView();
        System.out.println(session.getAttributeNames());
        //String rurl="/DisplayAllProducts.jsp?e_id="+Long.toString(ecommId);
        String rurl="/DisplayAllSellers.jsp?e_id="+Long.toString(ecommId);
        System.out.println(rurl);
        rv.setUrl(rurl);
        return rv;
    }

//    @RequestMapping(value="/viewSeller/{id}" , method=RequestMethod.GET)
//
//    public ResponseEntity<Seller>    getSellerById(@PathVariable(value = "id") Long seller_id)
//    {
//        Seller seller =sellerRepository.findById(seller_id).orElse(null);
//        return ResponseEntity.ok().body(seller);
//    }
    @RequestMapping(value="/getSellerByProductId/{p_id}/{e_id}" ,method=RequestMethod.GET)
    public RedirectView getSellerByProductId(
            @PathVariable(value = "p_id") long productId,
            @PathVariable(value = "e_id") long ecommId,
            HttpSession session)
    {
        //Seller seller = sellerRepository.findByProductId_ProductId(productId);
        Product product= productService.findByProductId(productId);
        Seller seller = product.getSeller();
        //System.out.println(product.getSeller().getSellerId());
        //Seller seller = sellerRepository.findById(seller_id).orElse(null);
        //Seller seller = sellerRepository.findById(seller_id);
        session.setAttribute("sellerName", seller.getSellerName());
        session.setAttribute("sellerAddress", seller.getSellerAddress());
        session.setAttribute("sellerEmailId", seller.getSellerEmailId());
        session.setAttribute("sellerContactNo", seller.getSellerContactNo());
        session.setAttribute("shopName", seller.getShopName());
        RedirectView rv = new RedirectView();
        String rurl="/SellerByProductId.jsp?e_id="+Long.toString(ecommId)+"&p_name="+product.getProductName();
        System.out.println(rurl);
        rv.setUrl(rurl);
        return rv;

    }

    @RequestMapping(value="/getSellerById/{id}" ,method=RequestMethod.GET)
    public RedirectView getSellerById(@PathVariable(value = "id") long seller_id, HttpSession session)
    {
        System.out.println("Getting your details seller..please wait!!");
        System.out.println(seller_id);
        Seller seller = sellerService.findById(seller_id);

        session.setAttribute("sellerContactNo", seller.getSellerContactNo());
        session.setAttribute("sellerAddress", seller.getSellerAddress());
        session.setAttribute("sellerName", seller.getSellerName());
        session.setAttribute("sellerEmailId", seller.getSellerEmailId());
        session.setAttribute("shopName", seller.getShopName());
        session.setAttribute("sellerPassword",seller.getSellerPassword());

        //session.setAttribute("seller", seller);



        RedirectView rv = new RedirectView();
        String rurl="/ViewYourProfile.jsp?id="+Long.toString(seller.getSellerId());
        System.out.println(rurl);
        rv.setUrl(rurl);
        return rv;
    }



    @RequestMapping(value="/postUpdatedSeller/{id}" , method=RequestMethod.POST)
    public RedirectView updateUser(
            @PathVariable(value = "id") Long seller_id,
            @RequestParam("s_name") String seller_name,
            @RequestParam("s_address") String seller_address,
            @RequestParam("s_shop") String shop_name,
            @RequestParam("s_mail") String seller_emailId,
            @RequestParam("s_mobile") String seller_contactNo,
            @RequestParam("s_password") String password,
            HttpSession session
            )

    {
        Seller seller =sellerService.findById(seller_id);
        System.out.println("hi................................");
        seller.setSellerName(seller_name);
        seller.setSellerAddress(seller_address);
        seller.setShopName(shop_name);
        seller.setSellerEmailId(seller_emailId);
        seller.setSellerContactNo(seller_contactNo);
        seller.setSellerPassword(password);

        sellerService.save(seller);
        session.setAttribute("sellerId", seller.getSellerId());
        session.setAttribute("shopName", seller.getShopName());
        session.setAttribute("sellerContactNo", seller.getSellerContactNo());
        session.setAttribute("sellerEmailId", seller.getSellerEmailId());
        session.setAttribute("sellerPassword", seller.getSellerPassword());
        session.setAttribute("sellerAddress", seller.getSellerAddress());
        session.setAttribute("sellerName", seller.getSellerName());
//notification mechanism..
        long s_id=seller.getSellerId();
        Date dw=new Date();
        notificationService.save(seller.getSellerName()+" has updated profile",dw ,s_id,0);
        RedirectView rv = new RedirectView();
        String rurl="/SellerDashboard.jsp?id="+Long.toString(seller.getSellerId());
        rv.setUrl(rurl);
        return rv;
    }

}
