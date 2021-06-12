package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.confirm.EmployeeConfirmService;
import kodlamaio.hrms.core.emailValidation.EmailValidationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{
	
	private EmailValidationService emailValidationService;
	private UserDao userDao;
	private EmployerDao employerDao;
	private EmployeeConfirmService employeeConfirmService;
	
    @Autowired
	public EmployerManager(EmailValidationService emailValidationService,UserDao userDao,EmployerDao employerDao,EmployeeConfirmService employeeConfirmService) {
		super();
		this.emailValidationService = emailValidationService;
		this.userDao = userDao;
		this.employerDao = employerDao;
		this.employeeConfirmService = employeeConfirmService;
	}
	

	@Override
	public Result register(Employer employer, Employee employee) {
		if (Objects.isNull(employer.getCompanyName()) || Objects.isNull(employer.getWebAddress())
				|| Objects.isNull(employer.getEmail()) || Objects.isNull(employer.getPhoneNumber())
				|| Objects.isNull(employer.getPassword())) {
			return new ErrorResult("You must enter all informations!");
		}
		if(!emailValidationService.isValid(employer.getEmail())) {
			return new ErrorResult("Email address is not valid!");
		}
		
		if(!emailValidationService.isVerificationMailClicked(employer.getEmail())){
			return new ErrorResult("You must click the verification mail on time!");
		}
		
		if(this.userDao.findByEmailEquals(employer.getEmail()) != null) {
			 return new ErrorResult("Email address already in use!");
		}
		if(this.emailValidationService.webAddressDomainControl(employer.getWebAddress(), employer.getEmail())==false) {
			return new ErrorResult("Web address and email address must have the same domain!");
		}
		
		if(this.employeeConfirmService.employeeConfirm(employee, employer) != true) {
			return new ErrorResult("It is not confirmed by an employee!");
			
		}
		
		this.employerDao.save(employer);
		return new SuccessResult("Employer registered!");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employers listed!");
	}

}
