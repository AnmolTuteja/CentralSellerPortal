package com.acms.CentralSellerPortal.Controllers;
import com.acms.CentralSellerPortal.Entities.Ecommerce;
import com.acms.CentralSellerPortal.Repositories.EcommerceRepository;
import javax.servlet.http.HttpSession;


import com.acms.CentralSellerPortal.Entities.Seller;
import com.acms.CentralSellerPortal.Repositories.SellerRepository;
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
    EcommerceRepository ecommerceRepository;


    @Autowired
    SellerRepository sellerRepository;
    //@Autowired
    //CompanyRepository companyRepository;




    @RequestMapping(value="/verifyseller" , method=RequestMethod.GET)
    public RedirectView create(@RequestParam("s_mobile") String s_mobile,
                               @RequestParam("s_password") String s_password ,
                               RedirectAttributes redirectAttrs,
                               HttpSession session

                               ){
        List<Seller> seller = new ArrayList<Seller>();
        seller = sellerRepository.findAll();

        for(Seller s: seller){
            if((s_password).equals(s.getSellerPassword()) && (s_mobile).equals(s.getSellerContactNo())){
                session.setAttribute("sellerId", s.getSellerId());
                session.setAttribute("shopName", s.getShopName());
                session.setAttribute("sellerContactNo", s.getSellerContactNo());
                session.setAttribute("sellerEmailId", s.getSellerEmailId());
                session.setAttribute("sellerPassword", s.getSellerPassword());
                session.setAttribute("sellerAddress", s.getSellerAddress());
                session.setAttribute("sellerName", s.getSellerName());

                RedirectView rv = new RedirectView();
                String rurl="/SellerDashboard.jsp?id="+Long.toString(s.getSellerId());
                rv.setUrl(rurl);
                return rv;

            }

        }
        RedirectView rv = new RedirectView();
        String failLogin = "/FailedLogin.jsp";
        rv.setUrl(failLogin);

        return rv;
    }
   /* @GetMapping("/company")
  public Company create(@RequestBody Map<String,String> body){
    List<companyRepository> company = companyRepository.findAll(body.get("email")).orElse(null);
    for(company c:list){
      if(body.get("password").equals(c.getPassword())){
      return c;
  }
}
  return null;
}*/

    @RequestMapping(value="/verifyecommerce" , method=RequestMethod.GET)
    public RedirectView createecomm(@RequestParam("c_email") String c_email,
                                    @RequestParam("c_password") String c_password ,
                                    RedirectAttributes redirectAttrs,
                                    HttpSession session
                                    ){
        List<Ecommerce> ecomm = new ArrayList<Ecommerce>();
        ecomm = ecommerceRepository.findAll();

        for(Ecommerce c: ecomm){
            if((c_password).equals(c.getEcommPassword()) && (c_email).equals(c.getEcommEmailId())){
                System.out.println("before setting");
                c.setFirst_login(false);
                //Date date = new Date();
                //c.setDate(date);
                System.out.println(c.isFirst_login());
                //c.setFirst_login(false);
                session.setAttribute("ecommName", c.getEcommName());
                session.setAttribute("ecommEmailId", c.getEcommEmailId());
                session.setAttribute("ecommPassword", c.getEcommPassword());

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