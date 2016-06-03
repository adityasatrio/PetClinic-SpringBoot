package sample.ui.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "owners")
public class Email extends BaseEntity {

	private String emailAddress;
	private String password;
	private String recipientEmail;
	private String subject;
	private String contents;

	private String smtpAuth;
	private String startTls;
	private String smtpHost;
	private String smtpPort;
	
	
	public String getSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public String getStartTls() {
		return startTls;
	}

	public void setStartTls(String startTls) {
		this.startTls = startTls;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void emptyAllFields() {
		setEmailAddress("");
		setPassword("");
		setRecipientEmail("");
	}

}
