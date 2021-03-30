package com.generation.EcommerceHd.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.EcommerceHd.model.Loja;
import com.generation.EcommerceHd.model.Produto;
import com.generation.EcommerceHd.model.Usuario;
import com.generation.EcommerceHd.repository.UsuarioRepository;
import com.generation.EcommerceHd.service.LojaService;
import com.generation.EcommerceHd.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Autowired
	private LojaService serviceLoja;
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> getById(@PathVariable Long idUsuario){
		return repository.findById(idUsuario)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<List<Usuario>> pegarUsuario(@RequestParam(defaultValue = "") String usuario){
		return new ResponseEntity<List<Usuario>>(serviceUsuario.pegarUsuarios(usuario), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
	
	@DeleteMapping("/{idUsuario}")
	public void delete(@PathVariable Long idUsuario) {
		repository.deleteById(idUsuario);
	}
	
	@PostMapping("/loja/nova/{id_Usuario}")
	public ResponseEntity<?> novaLoja(
			@PathVariable(value = "id_Usuario") Long idUsuario, 
			@Valid @RequestBody Loja novaLoja){
		Loja cadastro = serviceLoja.cadastrarLoja(novaLoja, idUsuario);
		if(cadastro == null) {
			return new ResponseEntity<String>("Falha no cadastro", HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<Loja>(cadastro, HttpStatus.CREATED);
	}
	
	@PutMapping("/loja/edite/{id_Usuario}")
	public ResponseEntity<?> editarLoja(
			@PathVariable(value = "id_Usuario") Long idUsuario,
			@Valid @RequestBody Loja loja){
		Optional<Loja> alterada = serviceLoja.editarLoja(idUsuario, loja);
		if(alterada.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja inexistente");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(alterada.get());
		}
	}
	
	@PutMapping("/loja/inscricao/{id_Loja}/{id_Usuario}")
	public ResponseEntity<?> novainscricao(
			@PathVariable(value = "id_Loja") Long idLoja, 
			@PathVariable(value = "id_Usuario") Long idUsuario){
		Usuario inscreve = serviceLoja.inscreverEmLoja(idUsuario, idLoja);
		if (inscreve == null) {
			return new ResponseEntity<String>("Loja ou Usuario invalido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(inscreve, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/loja/delete/{id_Loja}/{id_Usuario}")
	public ResponseEntity<?> removerLoja(
			@PathVariable(value = "id_Loja")Long idLoja, 
			@PathVariable(value = "id_Usuario")Long idUsuario){
		Usuario retorno = serviceLoja.deletarLoja(idLoja, idUsuario);
		if(retorno == null) {
			return new ResponseEntity<String>("Loja ou Usuario invalido",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(retorno, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/produto/novo/{id_Usuario}/{id_Loja}")
	public ResponseEntity<?> novoProduto(
			@PathVariable(value = "id_Usuario") Long idUsuario,
			@PathVariable(value = "id_Loja") Long idLoja,
			@Valid @RequestBody Produto novoProduto){
		Produto cadastro = serviceLoja.cadastrarProduto(novoProduto, idUsuario, idLoja);
		if(cadastro == null) {
			return new ResponseEntity<String>("Falha no cadastro", HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<Produto>(cadastro, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/produto/edite/{id_Usuario}")
	public ResponseEntity<?> editarProduto(
			@PathVariable(value = "id_Usuario") Long idUsuario,
			@Valid @RequestBody Produto produto){
		Optional<Produto> alterado = serviceLoja.editarProduto(idUsuario,produto);
		if(alterado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto inexistente");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(alterado.get());
		}
	}
	
	@PutMapping("/produto/compra/{id_Produto}/{id_Usuario}")
	public ResponseEntity<?> novaCompra(
			@PathVariable(value = "id_Produto") Long idProduto, 
			@PathVariable(value = "id_Usuario") Long idUsuario){
		Usuario compra = serviceLoja.comprarProduto(idUsuario, idProduto);
		if (compra == null) {
			return new ResponseEntity<String>("Produto ou Usuario invalido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(compra, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/produto/delete/{id_Produto}/{id_Usuario}")
	public ResponseEntity<?> removerProduto(
			@PathVariable(value = "id_Produto")Long idProduto, 
			@PathVariable(value = "id_Usuario")Long idUsuario){
		Usuario retorno = serviceLoja.deletarProduto(idProduto, idUsuario);
		if(retorno == null) {
			return new ResponseEntity<String>("Produto ou Usuario invalido",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(retorno, HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
