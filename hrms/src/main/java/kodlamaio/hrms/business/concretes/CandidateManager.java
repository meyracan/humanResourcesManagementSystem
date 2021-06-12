package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.emailValidation.EmailValidationService;
import kodlamaio.hrms.core.userValidation.UserCheckService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {
	
	private UserDao userDao;
	private CandidateDao candidateDao;
	private EmailValidationService emailValidationService;
	private UserCheckService userCheckService;
	
	@Autowired
	public CandidateManager(UserDao userDao, CandidateDao candidateDao, EmailValidationService emailValidationService,UserCheckService userCheckService) {
		super();
		this.userDao = userDao;
		this.candidateDao = candidateDao;
		this.emailValidationService = emailValidationService;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result register(Candidate candidate) {
		
		if(candidate.getBirthYear() == 0 || candidate.getEmail().isEmpty() || candidate.getFirstName().isEmpty() 
				|| candidate.getIdentityNumber().isEmpty() || candidate.getLastName().isEmpty() || 
				candidate.getPassword().isEmpty()) {
			return new ErrorResult("You must enter all informations!");
		}
		
		
		if(userDao.findByEmailEquals(candidate.getEmail()) != null) {
			return new ErrorResult("Email address already in use!");	
		}
		
		if(candidateDao.findByIdentityNumberEquals(candidate.getIdentityNumber()) != null) {
			return new ErrorResult("Identity number already in use!");
		}
		
		if(!emailValidationService.isValid(candidate.getEmail()))
		{
			return new ErrorResult("Email adderss is not valid!");			
		}	
		if(!emailValidationService.isVerificationMailClicked(candidate.getEmail()))
		{
			return new ErrorResult("You must click the verification mail on time!");
		}
		
		if(!this.userCheckService.checkIfRealPerson(candidate)) {
			
			return new ErrorResult("It is not real person!");
		}
		
		this.candidateDao.save(candidate);
		return new SuccessResult("Candidate registered!");
		
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Candidates listed!");
	}
	
	
	
}
