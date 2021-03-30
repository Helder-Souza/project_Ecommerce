package com.generation.EcommerceHd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.EcommerceHd.model.Loja;
import com.generation.EcommerceHd.model.Produto;
import com.generation.EcommerceHd.model.Usuario;
import com.generation.EcommerceHd.repository.LojaRepository;
import com.generation.EcommerceHd.repository.ProdutoRepository;
import com.generation.EcommerceHd.repository.UsuarioRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repositoryLoja;
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	@Autowired
	private ProdutoRepository repositoryProduto;
	
	public List<Loja> pegarLojaPorNome(String nomeLoja){
		return repositoryLoja.findAllByNomeLojaContainingIgnoreCase(nomeLoja);
	}
	
	public Loja cadastrarLoja(Loja novaLoja, Long idUsuario) {
		Loja lojaExistente = repositoryLoja.save(novaLoja);
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		if(usuarioExistente.isPresent()) {
			lojaExistente.setCriadorLoja(usuarioExistente.get());
			return repositoryLoja.save(lojaExistente);
		}
		return null;
	}
	
	public Usuario inscreverEmLoja(Long idUsuario, Long idLoja) {
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		Optional<Loja> lojaExistente = repositoryLoja.findById(idLoja);
		if(usuarioExistente.isPresent() && lojaExistente.isPresent()){
			usuarioExistente.get().getInscritoNaloja().add(lojaExistente.get());
			return repositoryUsuario.save(usuarioExistente.get());
		}
		return null;
	}
	
	public Optional<Loja> editarLoja(Long idUsuario, Loja loja){
		Optional<Loja>lojaExistente = repositoryLoja.findById(loja.getIdLoja());
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		if(usuarioExistente.isPresent() && lojaExistente.isPresent()) {
			lojaExistente.get().setNomeLoja(loja.getNomeLoja());
			lojaExistente.get().setDescricao(loja.getDescricao());
		}
		return Optional.ofNullable(repositoryLoja.save(lojaExistente.get()));
	}
	
	public Usuario deletarLoja ( Long idLoja, Long idUsuario) {
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		Optional<Loja> LojaExistente = repositoryLoja.findById(idLoja);
		if(usuarioExistente.isPresent() && LojaExistente.isPresent()){
			LojaExistente.get().setCriadorLoja(null);
			repositoryLoja.save(LojaExistente.get());
			repositoryLoja.deleteById(LojaExistente.get().getIdLoja());
			return repositoryUsuario.findById(usuarioExistente.get().getIdUsuario()).get();
		}
		return null;
		
	}
	
	public Produto cadastrarProduto(Produto novoProduto, Long idUsuario, Long idLoja) {
		Produto produtoExistente = repositoryProduto.save(novoProduto);
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		Optional<Loja> lojaExistente = repositoryLoja.findById(idLoja);
		if (usuarioExistente.isPresent() && lojaExistente.isPresent()) {
			produtoExistente.setCriadoPor(usuarioExistente.get());
			produtoExistente.setCriadoNaLoja(lojaExistente.get());
			return repositoryProduto.save(produtoExistente);
		} 
		return null;
		
		}
	
	public Optional<Produto> editarProduto(Long idUsuario, Produto produto){
		Optional<Produto>produtoExistente = repositoryProduto.findById(produto.getIdProduto());
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		if(usuarioExistente.isPresent() && produtoExistente.isPresent()) {
			produtoExistente.get().setTituloProduto(produto.getTituloProduto());
			produtoExistente.get().setValor(produto.getValor());
			produtoExistente.get().setMarca(produto.getMarca());
		}
		return Optional.ofNullable(repositoryProduto.save(produtoExistente.get()));
	}
	
	public Usuario comprarProduto(Long idUsuario, Long idProduto) {
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		Optional<Produto> produtoExistente = repositoryProduto.findById(idProduto);
		if(usuarioExistente.isPresent() && produtoExistente.isPresent()){
			usuarioExistente.get().getMinhasCompras().add(produtoExistente.get());
			return repositoryUsuario.save(usuarioExistente.get());
		}
		return null;
	}
	
	public Usuario deletarProduto ( Long idProduto, Long idUsuario) {
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		Optional<Produto> produtoExistente = repositoryProduto.findById(idProduto);
		if(usuarioExistente.isPresent() && produtoExistente.isPresent()){
			produtoExistente.get().setCriadoPor(null);
			repositoryProduto.save(produtoExistente.get());
			repositoryProduto.deleteById(produtoExistente.get().getIdProduto());
			return repositoryUsuario.findById(usuarioExistente.get().getIdUsuario()).get();
		}
		return null;
		
	}
}
