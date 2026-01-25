CREATE TABLE IF NOT EXISTS avaliacao
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    consulta_id BIGINT NOT NULL,
    nota INT NOT NULL,
    comentario VARCHAR(1000),
    CONSTRAINT avaliacao_pkey PRIMARY KEY (id),
    CONSTRAINT fk_avaliacao_consulta FOREIGN KEY (consulta_id) REFERENCES consulta(id)
);