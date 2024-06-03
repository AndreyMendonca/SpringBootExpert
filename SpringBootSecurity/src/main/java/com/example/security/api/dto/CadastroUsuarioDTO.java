package com.example.security.api.dto;

import java.util.List;

import com.example.security.domain.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CadastroUsuarioDTO {
	private Usuario usuario;
	private List<String> permissoes;
}
