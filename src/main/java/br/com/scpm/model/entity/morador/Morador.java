package br.com.scpm.model.entity.morador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.scpm.model.entity.EntityClass;
import br.com.scpm.model.entity.assinatura.Assinatura;
import br.com.scpm.model.entity.fatura.Fatura;
import lombok.Data;

@Data
@Entity
public class Morador {
	
	@NotNull
	private String nome;
	
	@NotNull
	@Id
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
	
	@OneToMany(mappedBy = "morador", cascade = CascadeType.ALL)
	private List<Fatura> faturas;
}
