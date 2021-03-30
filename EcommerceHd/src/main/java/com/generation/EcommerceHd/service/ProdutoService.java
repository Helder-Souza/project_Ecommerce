package com.generation.EcommerceHd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.EcommerceHd.model.Loja;
import com.generation.EcommerceHd.model.Produto;
import com.generation.EcommerceHd.repository.ProdutoRepository;

/*@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repositoryProduto;
	
	public List<Produto> pegarProdutosPorNome(String tituloProduto){
		return repositoryProduto.findAllByTituloProdutoContainingIgnoreCase(tituloProduto);
	}
	
	public List<Produto> pegarProdutosPorLoja(Loja loja){
		return repositoryProduto.findAllByLojaContainingIgnoreCase(loja);
	}

}
*/