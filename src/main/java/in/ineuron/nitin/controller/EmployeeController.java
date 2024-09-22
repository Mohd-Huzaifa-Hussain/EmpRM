package in.ineuron.nitin.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.nitin.model.Employee;
import in.ineuron.nitin.service.IEmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	// GET ALL EMPLOYEES :: http://localhost:9999/api/v1/employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return service.findAllEmployees();
	}

	// CREATE AN EMPLOYEE :: http://localhost:9999/api/v1/employees
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	// GETTING AN EMPOYEE BASED ON ID VALUE
	// ::http://localhost:9999/api/v1/employees/{id}
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
		Employee employee = service.findEmployeeById(id);
		return ResponseEntity.ok(employee);
	}

	// Updating an Employee Based on ID value ::
	// http://localhost:9999/api/v1/employees/{id}
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		Employee empDb = service.findEmployeeById(id);

		empDb.setFirstName(employee.getFirstName());
		empDb.setLastName(employee.getLastName());
		empDb.setEmailId(employee.getEmailId());

		Employee updateEmployee = service.saveEmployee(empDb);
		return ResponseEntity.ok(updateEmployee);

	}

	// Deleting an Employee Based on ID value ::
	// http://localhost:9999/api/v1/employees/{id}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
		service.deleteEmployeeById(id);
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
