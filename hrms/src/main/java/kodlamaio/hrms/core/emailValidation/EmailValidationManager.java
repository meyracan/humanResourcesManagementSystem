package kodlamaio.hrms.core.emailValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidationManager implements EmailValidationService {

	@Override
	public boolean isValid(String email) {
		Pattern pattern = Pattern.compile(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		
		if(matcher.matches())sendVerificationMail(email);
		
		return matcher.matches();
	}

	@Override
	public void sendVerificationMail(String email) {
		System.out.println("Validation mail sent: "+email);
		
	}

	@Override
	public boolean isVerificationMailClicked(String email) {
		System.out.println("Email verified: "+email);
		return true;
	}

	@Override
	public boolean webAddressDomainControl(String webAddress, String email) {
        String emailSplit= email.split("@")[1];
		
		if (!webAddress.contains(emailSplit)) {
			return false;
		}
		return true;
	}

}
