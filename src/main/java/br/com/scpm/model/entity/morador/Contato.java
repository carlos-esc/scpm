package br.com.scpm.model.entity.morador;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.scpm.model.entity.EntityClass;
import lombok.Data;

@Entity
@Data
public class Contato extends EntityClass{

	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "morador_id")
	private Morador morador;
}
