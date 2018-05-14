package br.com.scpm.model.entity.morador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.scpm.model.entity.EntityClass;
import br.com.scpm.model.entity.assinatura.Assinatura;
import lombok.Data;

@Data
@Entity
public class Morador extends EntityClass{
	
	@NotNull
	private String nome;
	
	@NotNull
	//@CPF
	private String cpf;
	
	//@Email
	private String email;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@OneToMany(mappedBy="morador", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> contatos;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Assinatura> assinaturas;
}
