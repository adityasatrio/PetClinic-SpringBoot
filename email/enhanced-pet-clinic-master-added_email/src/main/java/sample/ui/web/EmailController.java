package sample.ui.web;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sample.ui.model.Email;

@Controller
@SessionAttributes(types = Email.class)
@RequestMapping(value = "/email")
public class EmailController {

	private static Log logger = LogFactory.getLog(EmailController.class);

	@RequestMapping(value = "/compose", method = RequestMethod.GET)
	public String composeEmail(Model model) {
		Email email = new Email();
		email.emptyAllFields();

		model.addAttribute(email);
		return "email/emailForm";

	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(Model model) {
		return "email/resultEmail";

	}

	@RequestMapping(value = "/compose", method = RequestMethod.POST)
	public String sendEmail(@Valid Email email, RedirectAttributes redirect, SessionStatus status) {

		final String emailAddress = "aditya.kaha@gmail.com";// email.getEmailAddress();
		final String password = "kaha12345";// email.getPassword();

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		System.out.println("=============== CREATE SESSION =============== ");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailAddress, password);
					}
				});

		try {

			System.out.println("=============== BUILD MESSAGE =============== ");

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailAddress));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email.getRecipientEmail()));

			message.setSubject(email.getSubject());
			message.setText(email.getContents());

			Transport.send(message);

			System.out.println("=============== MESSAGE SEND =============== ");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		status.setComplete();
		redirect.addFlashAttribute("statusMessage", "Email Successfully Sent");

		return "redirect:/email/result";

	}
}
