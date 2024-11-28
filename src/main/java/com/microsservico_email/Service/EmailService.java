package com.microsservico_email.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.microsservico_email.enums.StatusEmail;
import com.microsservico_email.models.EmailModel;
import com.microsservico_email.repositories.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;

	@Autowired
	private JavaMailSender emailSender;

	@Value("${spring.mail.username}")
	private String emailTo;
	
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	public EmailModel sendEmail(EmailModel emailModel) throws UnsupportedEncodingException, MessagingException {

		emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            logger.info("Tentando enviar e-mail para {}", emailTo);
            
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(emailModel.getFromName(), emailModel.getFromEmail()));
			helper.setTo(emailTo);
			helper.setSubject(emailModel.getSubject());
			helper.setText(emailModel.getText(), false);
			emailSender.send(message);
			emailModel.setStatusEmail(StatusEmail.SENT);
			
			 logger.info("Email enviado com sucesso para {}", emailTo);
 
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);	
			logger.error("Erro ao enviar email para {}: {}", emailTo, e.getMessage());
		}

		return emailRepository.save(emailModel);
	}
}
