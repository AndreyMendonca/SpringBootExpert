package com.example.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.api.dto.CadastroUsuarioDTO;
import com.example.security.domain.entity.Usuario;
import com.example.security.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO body){
		Usuario usuarioSalvo = service.salvar(body.getUsuario(), body.getPermissoes());
		return ResponseEntity.ok(usuarioSalvo);
	}
}
