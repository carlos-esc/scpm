package br.com.scpm.model.entity.fatura;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.scpm.model.entity.EntityClass;
import br.com.scpm.model.entity.morador.Morador;
import lombok.Data;

@Data
@Entity
public class Fatura extends EntityClass {
	
	private String descricao;
	private String parcela;
	private String dataVencimento;
	private String valor;
	private String dataPagamento;
	
	@ManyToOne
	@JoinColumn(name = "morador_cpf")
	private Morador morador;
}
