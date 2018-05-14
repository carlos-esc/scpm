package br.com.scpm.model.entity.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	Usuario findByLogin(String login);
	
	Usuario findById(Long id);
	
	//@Query(value = "SELECT morador.nome FROM usuario INNER JOIN morador ON usuario.morador_id = morador.id WHERE usuario.login = ?1", nativeQuery = true)
	//String findByUsuarioNome(String usuarioNome);
}
