package br.com.scpm.model.entity.morador;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scpm.model.entity.EntityClass;
import lombok.Data;

@Entity
@Data
public class Endereco extends EntityClass{
	
	private String cep;
	
	
	private String estado;
	
	
	private String cidade;
	
	
	private String bairro;
	
	
	private String rua;
	
	
	private String numero;
	
	
	private String complemento;
	
}
