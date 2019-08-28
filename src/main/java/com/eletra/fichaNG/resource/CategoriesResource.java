package com.eletra.fichaNG.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletra.fichaNG.model.Category;
import com.eletra.fichaNG.repository.Categories;

@RestController
@RequestMapping("/categories")
public class CategoriesResource {
	
	@Autowired
	private Categories categories;
	
	@PostMapping
	public Category adicionar(@Valid @RequestBody Category category) {
		return categories.save(category);
	}
	
	@GetMapping("/")
	public List<Category> listar() {
		return categories.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> buscar(@PathVariable Long id) {
		Category category = categories.findOne(id);
		
		if (category == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Category category) {
		Category existente = categories.findOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(category, existente, "id");
		
		existente = categories.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Category category = categories.findOne(id);
		
		if (category == null) {
			return ResponseEntity.notFound().build();
		}
		
		categories.delete(category);
		
		return ResponseEntity.noContent().build();
	}
}











