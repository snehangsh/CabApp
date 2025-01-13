package com.project.cab.cabApp;

import com.project.cab.cabApp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CabAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;


	@Test
	void contextLoads() {
		emailSenderService.sendEmail("pohona3831@wirelay.com",
				"This is a Testing Email",
				"This is the Body of the Email"
				);
	}
	@Test
	void sendEmailMultiple(){
		String emails[] = {
				"pohona3831@wirelay.com",
				"barmon.sneh@gmail.com",
				"choudhuryayesha22@gmail.com"
		};
		emailSenderService.sendEmail(emails,
				"Test Email",
				"CabApp Test Email");
	}

}
