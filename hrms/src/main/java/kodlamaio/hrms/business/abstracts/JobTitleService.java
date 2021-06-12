package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobTitle;

public interface JobTitleService {
	
	public List<JobTitle> getAll();
	public Result add(JobTitle jobTitle);

}
