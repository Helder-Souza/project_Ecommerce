package com.generation.EcommerceHd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.EcommerceHd.model.Loja;


public interface LojaRepository extends  JpaRepository<Loja, Long> { 
	public List<Loja> findAllByNomeLojaContainingIgnoreCase(String nomeLoja);
}
