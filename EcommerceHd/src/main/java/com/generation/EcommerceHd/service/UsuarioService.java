package com.generation.EcommerceHd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.EcommerceHd.model.Usuario;
import com.generation.EcommerceHd.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	public List<Usuario> pegarUsuarios(String usuario){
		return repositoryUsuario.findAllByUsuarioContainingIgnoreCase(usuario);
	}
	
}
