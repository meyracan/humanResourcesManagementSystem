package kodlamaio.hrms.core.confirm;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployeeConfirmManager implements EmployeeConfirmService{
	
	public boolean employeeConfirm(Employee employee, Employer employer) {
		
		System.out.println(employer.getCompanyName()+ " confirmed by " + employee.getFirstName() + " " + employee.getLastName());
		return true;
	}

}
