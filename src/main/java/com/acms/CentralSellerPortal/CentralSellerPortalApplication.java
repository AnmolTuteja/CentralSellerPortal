package com.acms.CentralSellerPortal;
import com.acms.CentralSellerPortal.Repositories.EcommerceRepository;
import com.acms.CentralSellerPortal.Entities.Ecommerce;

import com.acms.CentralSellerPortal.Services.EmailService;
import com.acms.CentralSellerPortal.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@SpringBootApplication
@EnableJpaRepositories
public class CentralSellerPortalApplication {

	private static EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		CentralSellerPortalApplication.emailService=emailService;
	}

	private static EcommerceRepository ecommerceRepository;
	@Autowired
	public void setEcommerceRepository(EcommerceRepository ecommerceRepository) {

		CentralSellerPortalApplication.ecommerceRepository = ecommerceRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(CentralSellerPortalApplication.class, args);

		Timer t = new Timer();
		TimerTask task=new TimerTask(){
			@Override
			public void run()  {
				System.out.println("Reached run");
				if(NotificationService.flag==true){
					List<Ecommerce> ecommerce = new ArrayList<Ecommerce>();
					ecommerce = ecommerceRepository.findAll();

					for(Ecommerce e: ecommerce) {


						emailService.sendMail(e.getEcommEmailId(), "New Update", "Have a look at our new update!");
						System.out.println("emails sent");
					}
					NotificationService.flag=false;
				}

			};
		};


		t.scheduleAtFixedRate(task, 1000, 100000);
	}

}
