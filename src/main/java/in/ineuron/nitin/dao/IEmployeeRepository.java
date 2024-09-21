package in.ineuron.nitin.dao;

import org.springframework.data.repository.CrudRepository;

import in.ineuron.nitin.model.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Integer> {

}
