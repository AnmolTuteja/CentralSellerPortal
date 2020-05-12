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
    private EcommerceService ecommerceService;

    @Autowired
    private ProductService productService;

    @PostMapping("/postEcommerce")
    public RedirectView addecommerce(@RequestParam("cName") String ecommerce_name,
                                     @RequestParam("cEmailId") String ecommerce_emailId,
                                     @RequestParam("cPassword") String ecommerce_password,
                                     HttpSession session)
    {

        int flag=0;
        session.removeAttribute("sellerContactNo");
        session.removeAttribute("sellerEmailId");

        List<Ecommerce> ecommerces=ecommerceService.findAll();
        for(Ecommerce e:ecommerces){

            if ((ecommerce_emailId).equals(e.getEcommEmailId())  ) {

                session.setAttribute("ecommEmailId", e.getEcommEmailId());
                flag=1;
            }

        }

        if(flag==1)
        {
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/SignUpAsCompany.jsp");
            return redirectView;
        }

        ecommerceService.save(new Ecommerce(ecommerce_name,ecommerce_emailId,ecommerce_password));

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/index.html");
        return redirectView;
    }


    @GetMapping("/getAllEcommerce")
    public ResponseEntity<List<Ecommerce>> getAllEcommerce() {

        List<Ecommerce> ecommerceList =ecommerceService.findAll();
        return ResponseEntity.ok().body(ecommerceList);
    }

    @GetMapping("/getEcommerce/{e_id}" )
    public RedirectView getSellerById(@PathVariable(value = "e_id") long ecomm_id, HttpSession session)
    {
        Ecommerce ecommerce = ecommerceService.findById(ecomm_id).orElse(null);

        session.setAttribute("ecommEmailId", ecommerce.getEcommEmailId());
        session.setAttribute("ecommName", ecommerce.getEcommName());


        RedirectView rv = new RedirectView();
        String rurl="/ViewEcommProfile.jsp?e_id="+ecommerce.getEcommId();
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

        int flag=0;
        session.removeAttribute("sellerContactNo");
        session.removeAttribute("sellerEmailId");

        List<Ecommerce> ecommerces=ecommerceService.findAll();
        for(Ecommerce e:ecommerces){

            if ((ecommEmailId).equals(e.getEcommEmailId())  && !(ecommId).equals(e.getEcommId()) ) {

                session.setAttribute("ecommEmailId", e.getEcommEmailId());
                flag=1;
            }

        }

        if(flag==1)
        {
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/FailedCompanySignup.jsp");
            return redirectView;
        }


        Ecommerce ecommerce=ecommerceService.findById(ecommId).orElse(null);
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