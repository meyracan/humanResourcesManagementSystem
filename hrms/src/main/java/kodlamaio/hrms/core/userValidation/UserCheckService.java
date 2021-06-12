package kodlamaio.hrms.core.userValidation;


import kodlamaio.hrms.entities.concretes.Candidate;


public interface UserCheckService {
	
	public boolean checkIfRealPerson(Candidate candidate);

}
