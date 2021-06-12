package kodlamaio.hrms.core.confirm;

import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployeeConfirmService {
	public boolean employeeConfirm(Employee employee, Employer employer);

}
