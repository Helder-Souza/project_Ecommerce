package com.generation.EcommerceHd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generation.EcommerceHd.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findAllByTituloProdutoContainingIgnoreCase(String tituloProduto);
	
	@Query(value = "SELECT * FROM tb_produto INNER JOIN tb_loja ON tb_loja.id_loja = tb_produto.criado_na_loja_id_loja WHERE tb_loja.nome_loja = ?1", nativeQuery = true)
	public List<Produto> findAllProdutoByLoja(String nomeLoja);
	
	@Query(value = "SELECT * FROM tb_produto WHERE marca = ?1", nativeQuery = true)
	  public List<Produto> findAllProdutoByMarca(String marca);
	
	public List<Produto> findAllByValorLessThanEqual(float valor);
	public List<Produto> findAllByValorGreaterThanEqual(float valor);
}
