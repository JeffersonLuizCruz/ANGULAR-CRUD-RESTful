package com.angular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angular.model.Product;
import com.angular.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> listaAll(){
		return repository.findAll();
	}
	
	public Optional<Product> getById(long id){
		return repository.findById(id);
	}
	
	public Optional<Product> getByName(String name){
		return repository.findByName(name);
	}
	
	public void saveByProduct(Product product) {
		repository.save(product);
	}
	
	public void deleteByProduct(Long id) {
		repository.deleteById(id);
	}
	
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	
	public boolean existsByName(String name) {
		return repository.existsByName(name);
	}

}
