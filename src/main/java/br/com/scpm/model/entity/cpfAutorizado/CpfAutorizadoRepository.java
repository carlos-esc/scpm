package br.com.scpm.model.entity.cpfAutorizado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CpfAutorizadoRepository extends JpaRepository<CpfAutorizado, String> {
	CpfAutorizado findByCpf(String cpf);
}
