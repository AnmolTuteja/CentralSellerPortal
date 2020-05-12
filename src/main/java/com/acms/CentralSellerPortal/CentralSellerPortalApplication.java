package com.acms.CentralSellerPortal;



import com.acms.CentralSellerPortal.Entities.Ecommerce;
import com.acms.CentralSellerPortal.Repositories.EcommerceRepository;
import com.acms.CentralSellerPortal.Services.EmailService;
import com.acms.CentralSellerPortal.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@SpringBootApplication
@EnableJpaRepositories
@RequestMapping("/HomePage")
public class CentralSellerPortalApplication {

	private static EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		CentralSellerPortalApplication.emailService=emailService;
}

	private static EcommerceRepository ecommerceRepository;
	@Autowired
	public void setEcommerceRepository(EcommerceRepository ecommerceRepository) {
		CentralSellerPortalApplication.ecommerceRepository=ecommerceRepository;
	}




	public static void main(String[] args) throws Exception{
		SpringApplication.run(CentralSellerPortalApplication.class, args);

		Timer t = new Timer();
		TimerTask task=new TimerTask(){
			@Override
			public void run()  {
				//System.out.println("Reached run");
				if(NotificationService.flag==true){
					List<Ecommerce> ecommerce = new ArrayList<Ecommerce>();
					ecommerce = ecommerceRepository.findAll();

					for(Ecommerce e: ecommerce) {

//						try {
//							emailService.sendMail(e.getEcommEmailId(),"Updates From Central Seller Portal",
//									true);
//						} catch (MessagingException | FileNotFoundException ex) {
//							ex.printStackTrace();
//						}
						System.out.println("mail sent");

					}
					NotificationService.flag=false;
				}

			};
		};


		t.scheduleAtFixedRate(task, 1000, 100000);



	}




}
