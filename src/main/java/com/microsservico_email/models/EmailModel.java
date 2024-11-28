package com.microsservico_email.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.microsservico_email.enums.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table (name = "TB_EMAIL")
public class EmailModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_EMAIL")
	private UUID emailId;
	
	@Column(name = "REMETENTE")
	private String fromName;

	@Column(name = "REMETENTE_EMAIL")
	private String fromEmail;
	
//	@Value("${spring.mail.username}")
//	private String emailTo;
	@Column(name = "ASSUNTO")
	private String subject;
	
	@Column(name = "TEXTO")
	private String text;
	
	@Column(name = "DATA_ENVIO")
	private LocalDateTime sendDateEmail; 
	
	@Column(name = "STATUS_ENVIO")
	private StatusEmail statusEmail;
	
	
}
