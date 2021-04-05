package com.generation.EcommerceHd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.EcommerceHd.model.Produto;
import com.generation.EcommerceHd.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idProduto}")
	public ResponseEntity<Produto> getById(@PathVariable Long idProduto){
		return repository.findById(idProduto)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tituloProduto/{tituloProduto}")
	public ResponseEntity<List<Produto>> getByTituloProduto(@PathVariable String tituloProduto){
		return ResponseEntity.ok(repository.findAllByTituloProdutoContainingIgnoreCase(tituloProduto));
	}
	
	@GetMapping("/valorMenor/{valor}")
	public ResponseEntity<List<Produto>> getAllByValorLess(@PathVariable float valor){
		return ResponseEntity.ok(repository.findAllByValorLessThanEqual(valor));
	}
	
	@GetMapping("/valorMaior/{valor}")
	public ResponseEntity<List<Produto>> getAllByValorGreater(@PathVariable float valor){
		return ResponseEntity.ok(repository.findAllByValorGreaterThanEqual(valor));
	}
	
	@GetMapping("/loja/{nomeLoja}")
	public ResponseEntity<List<Produto>> getAllProdutoByLoja(@PathVariable String nomeLoja){
		return ResponseEntity.ok(repository.findAllProdutoByLoja(nomeLoja));
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Produto>> getAllProdutoByMarca(@PathVariable String marca){
		return ResponseEntity.ok(repository.findAllProdutoByMarca(marca));
	}
}
