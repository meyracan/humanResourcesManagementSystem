package kodlamaio.hrms.core.emailValidation;

public interface EmailValidationService {
	
	public boolean isValid(String email);
	public void sendVerificationMail(String email);
	public boolean isVerificationMailClicked(String email);
	public boolean webAddressDomainControl(String webAddress, String email);

}
