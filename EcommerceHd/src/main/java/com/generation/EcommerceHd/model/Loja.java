package com.generation.EcommerceHd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tb_loja")
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLoja;
	
	@NotNull
	@Size(max = 45)
	private String nomeLoja; 
	
	@NotNull
	@Size(max = 100)
	private String descricao;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "criador")
	@JsonIgnoreProperties({"meusProdutos", "minhasCompras", "minhaLoja", "inscritoNaloja"})
	private Usuario criadorLoja;
	
	@ManyToMany(mappedBy = "inscritoNaloja", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"inscritoNaloja", "usuariosInscritos", "meusProdutos", "minhasCompras"})
	private List<Usuario> usuariosInscritos = new ArrayList<>();
	
	@OneToMany(mappedBy = "criadoNaLoja", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("criadoNaLoja")
	public List<Produto> produtos;

	public Long getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Long idLoja) {
		this.idLoja = idLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getCriadorLoja() {
		return criadorLoja;
	}

	public void setCriadorLoja(Usuario criadorLoja) {
		this.criadorLoja = criadorLoja;
	}

	public List<Usuario> getUsuariosInscritos() {
		return usuariosInscritos;
	}

	public void setUsuariosInscritos(List<Usuario> usuariosInscritos) {
		this.usuariosInscritos = usuariosInscritos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
