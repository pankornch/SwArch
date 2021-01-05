package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class CustomerAPI {
	@Autowired
	CustomerRepository repo;

	@GetMapping("/customer")
	public List<Customer> getCustomer() {
		return repo.findAll();
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable("id") Integer id) {
		return repo.findById(id);
	}
	
	@PostMapping("/customer")
	public Customer listByLName(@RequestBody Customer customer) {
		return repo.save(customer);
	}

	@PutMapping("/customer")
	public Customer editCustomer(@RequestBody Customer customer) {
		// ค่าที่ส่งมาเป็นเพียง JSON Object จึงต้องดึง Entity Object ออกมาก่อน
		Customer editCustomer = repo.findById(customer.getCustomerId());
		// ย้ายค่าจาก JSON Object ลง Entity Object
		editCustomer.setFirstName(customer.getFirstName());
		editCustomer.setLastName(customer.getLastName());
		return repo.save(editCustomer);
	}
	
	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable("id") Integer id) {
		Customer customer = repo.findById(id);
		repo.delete(customer);
	}
}
