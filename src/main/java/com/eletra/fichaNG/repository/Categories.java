package com.eletra.fichaNG.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eletra.fichaNG.model.Category;

public interface Categories extends JpaRepository<Category, Long> {

}
