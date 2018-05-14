package br.com.scpm.model.entity.assinatura;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.com.scpm.model.entity.EntityClass;
import lombok.Data;

@Entity
@Data
public class Assinatura extends EntityClass{
	
	@NotNull
	public boolean status;//Ativo, Inativo
	
	@NotNull
	public String descricao;
	
	@NotNull
	public String tipo;//Valor único, Valor Parcelado, Valor Mensal
	
	@NotNull
	public String parcelas;//Quantidade parcelas
	
	@NotNull
	public String vencimento;//Data ou Dia do vencimento (Data para valor único e Dia para Valor parcelado e Valor mensal)
	
	@NotNull
	public float valorTotal;
}
