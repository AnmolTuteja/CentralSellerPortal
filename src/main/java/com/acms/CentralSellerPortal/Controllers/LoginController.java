package com.acms.CentralSellerPortal.Controllers;
import com.acms.CentralSellerPortal.Entities.Ecommerce;
import com.acms.CentralSellerPortal.Repositories.EcommerceRepository;
import javax.servlet.http.HttpSession;


import com.acms.CentralSellerPortal.Entities.Seller;
import com.acms.CentralSellerPortal.Repositories.SellerRepository;
import com.acms.CentralSellerPortal.Services.EcommerceService;
import com.acms.CentralSellerPortal.Services.NotificationService;
import com.acms.CentralSellerPortal.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class LoginController {



    @Autowired
    private EcommerceService ecommerceService;


    @Autowired
    private SellerService sellerService;

    @Autowired
    private NotificationService notificationService;





    @RequestMapping(value="/verifyseller" , method=RequestMethod.GET)
    public RedirectView create(@RequestParam("s_mobile") String s_mobile,
                               @RequestParam("s_password") String s_password ,
                               HttpSession session

                               ){
        List<Seller> seller;
        seller = sellerService.findAll();

        for(Seller s: seller){
            if((s_password).equals(s.getSellerPassword()) && (s_mobile).equals(s.getSellerContactNo())){
                session.setAttribute("sellerContactNo", s.getSellerContactNo());
                session.setAttribute("sellerAddress", s.getSellerAddress());
                session.setAttribute("sellerName", s.getSellerName());
                session.setAttribute("sellerEmailId", s.getSellerEmailId());
                session.setAttribute("shopName", s.getShopName());
                session.setAttribute("sellerPassword",s.getSellerPassword());

                RedirectView rv = new RedirectView();
                String rurl="/SellerDashboard.jsp?id="+s.getSellerId();
                rv.setUrl(rurl);
                return rv;

            }

        }
        RedirectView rv = new RedirectView();
        String failLogin = "/FailedLogin.jsp";
        rv.setUrl(failLogin);

        return rv;
    }


    @RequestMapping(value="/verifyecommerce" , method=RequestMethod.GET)
    public RedirectView createecomm(@RequestParam("c_email") String c_email,
                                    @RequestParam("c_password") String c_password ,
                                    HttpSession session
                                    ){
        List<Ecommerce> ecomm ;
        ecomm = ecommerceService.findAll();

        for(Ecommerce c: ecomm){
            if((c_password).equals(c.getEcommPassword()) && (c_email).equals(c.getEcommEmailId())){
                if(c.isFirst_login()==true)
                {
                    Date d=new Date();
                    c.setDate(d);
                }
                c.setFirst_login(false);

                int count=notificationService.getAllNotification(c.getEcommId());

                session.setAttribute("ecommName", c.getEcommName());
                session.setAttribute("ecommEmailId", c.getEcommEmailId());
                session.setAttribute("ecommPassword", c.getEcommPassword());
                session.setAttribute("ecommCount", count);

                RedirectView rv = new RedirectView();
                String rurl="/EcommDashboard.jsp?e_id="+Long.toString(c.getEcommId());
                rv.setUrl(rurl);
                return rv;

            }

        }
        RedirectView rvf = new RedirectView();
        String furl="/FailedLogin.jsp";
        rvf.setUrl(furl);
        return rvf;
    }
}