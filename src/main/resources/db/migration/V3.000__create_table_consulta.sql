CREATE TABLE IF NOT EXISTS consulta
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    data_consulta DATETIME NOT NULL,
    motivo VARCHAR(500),
    CONSTRAINT consulta_pkey PRIMARY KEY (id),
    CONSTRAINT fk_consulta_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT fk_consulta_medico FOREIGN KEY (medico_id) REFERENCES medico(id)
);

CREATE INDEX idx_consulta_data ON consulta (data_consulta);