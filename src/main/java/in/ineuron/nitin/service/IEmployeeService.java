package in.ineuron.nitin.service;

import java.util.List;

import in.ineuron.nitin.model.Employee;

public interface IEmployeeService {
	public List<Employee> findAllEmployees();
	public Employee saveEmployee(Employee employee);
	public Employee findEmployeeById(Integer id);
	public void deleteEmployeeById(Integer id);
}
