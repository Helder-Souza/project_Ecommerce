package com.generation.EcommerceHd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.EcommerceHd.model.Loja;
import com.generation.EcommerceHd.repository.LojaRepository;
import com.generation.EcommerceHd.service.LojaService;

@RestController
@RequestMapping("/loja")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LojaController {

	@Autowired
	private LojaRepository repository;
	
	@Autowired
	private LojaService service;
	
	@GetMapping
	public ResponseEntity<List<Loja>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idLoja}")
	public ResponseEntity<Loja> getById(@PathVariable Long idLoja){
		return repository.findById(idLoja)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Loja>> pegarLojaPorNome(@RequestParam(defaultValue = "") String nomeLoja){
		return new ResponseEntity<List<Loja>>(service.pegarLojaPorNome(nomeLoja), HttpStatus.OK);
	}
	
}
