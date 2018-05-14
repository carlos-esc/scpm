package br.com.scpm.model.entity;

import java.io.Serializable;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class EntityClass implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
}
