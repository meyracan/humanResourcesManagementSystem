package kodlamaio.hrms.business.abstracts;

import java.util.List;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;


public interface CandidateService {
	
	public Result register(Candidate candidate);
	public DataResult<List<Candidate>> getAll();

}
