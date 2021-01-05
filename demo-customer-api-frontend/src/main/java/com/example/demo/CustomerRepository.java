package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
	@PersistenceContext
	private EntityManager entityManager; // ใช้เรียกเมธอดจัดการฐานข้อมูล ที่สร้างมาให้แล้ว

	public Customer findById(Integer id) {
		return entityManager.find(Customer.class, id); // ค้นหา Customer ตาม id
	}

	public List<Customer> findAll() {
		Query query = entityManager.createQuery("from Customer"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ
	}

	@Transactional
	public Customer save(Customer customer) {
		entityManager.persist(customer); // insert กรณีไม่มีค่า id ใน object หรือ update กรณีมีค่า id ใน object
		return customer;
	}

	@Transactional
	public void delete(Customer customer) {
		entityManager.remove(customer);
	}
}
