package br.com.scpm.model.entity.cpfAutorizado;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CpfAutorizado {
	
	@Id
	private String cpf;
	
	private boolean status;
}
