package br.com.scpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.usuario.Role;
import br.com.scpm.model.entity.usuario.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> listar(){
		return (List<Role>) roleRepository.findAll();
	}
	
}
