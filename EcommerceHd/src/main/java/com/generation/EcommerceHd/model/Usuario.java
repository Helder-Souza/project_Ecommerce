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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@NotNull
	@Size(max = 45)
	private String nome;
	
	@NotNull
	@Size(max = 45)
	private String usuario;
	
	@NotNull
	@Size(max = 45)
	private String senha;
	
	@OneToMany(mappedBy = "criadorLoja", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"usuario", "criadorLoja", "produtos"})
	private List<Loja> minhaLoja;
	
	@ManyToMany
	@JoinTable(
			name = "inscricao",
			joinColumns = @JoinColumn (name ="fk_idUsuario"),
			inverseJoinColumns = @JoinColumn (name = "fk_idLoja"))
	@JsonIgnoreProperties({"usuariosInscritos", "produtos"})
	private List<Loja> inscritoNaloja = new ArrayList<>();

	@OneToMany(mappedBy = "criadoPor", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"criadoPor", "criadoNaLoja"})
	private List<Produto> meusProdutos = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "compras",
			joinColumns = @JoinColumn(name = "comprador_id"),
			inverseJoinColumns = @JoinColumn(name = "produto_id"))
	@JsonIgnoreProperties({"compradoPor", "criadoNaLoja"})
	private List<Produto> minhasCompras = new ArrayList<>();

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Loja> getMinhaLoja() {
		return minhaLoja;
	}

	public void setMinhaLoja(List<Loja> minhaLoja) {
		this.minhaLoja = minhaLoja;
	}

	public List<Loja> getInscritoNaloja() {
		return inscritoNaloja;
	}

	public void setInscritoNaloja(List<Loja> inscritoNaloja) {
		this.inscritoNaloja = inscritoNaloja;
	}

	public List<Produto> getMeusProdutos() {
		return meusProdutos;
	}

	public void setMeusProdutos(List<Produto> meusProdutos) {
		this.meusProdutos = meusProdutos;
	}

	public List<Produto> getMinhasCompras() {
		return minhasCompras;
	}

	public void setMinhasCompras(List<Produto> minhasCompras) {
		this.minhasCompras = minhasCompras;
	}
	


	
	
}
