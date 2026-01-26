package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="avaliacao")
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long consultaId;
	private int nota;
	private String comentario;

	public AvaliacaoEntity(Avaliacao avaliacao){
		this.consultaId = avaliacao.getConsultaId();
		this.nota = avaliacao.getNota();
		this.comentario = avaliacao.getComentario();
	}

	public Avaliacao toDomain(){
		var avaliacao = Avaliacao.criar(
				this.consultaId,
				this.nota,
				this.comentario
		);
		avaliacao.setId(this.id);
		return avaliacao;
	}

}
