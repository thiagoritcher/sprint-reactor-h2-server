package br.com.ritcher.server.seguranca.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import br.com.ritcher.server.lib.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "seg_usuario")
public class Usuario extends Model<Long>{
	@Id
	private Long id;

	@Column
	private String nome;
	
	@Column
	private String email;
}
