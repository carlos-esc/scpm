package br.com.scpm.model.entity.morador;

import org.springframework.data.repository.CrudRepository;

public interface MoradorRepository extends CrudRepository<Morador, String>{
	Morador findByCpf(String cpf);
	
	Morador findByEmail(String email);
}
