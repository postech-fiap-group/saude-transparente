CREATE TABLE IF NOT EXISTS paciente
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255),
    telefone VARCHAR(20),
    email VARCHAR(255),
    endereco VARCHAR(500),
    cpf VARCHAR(14),
    data_nascimento DATE,
    CONSTRAINT paciente_pkey PRIMARY KEY (id)
);

CREATE INDEX idx_paciente_nome ON paciente (nome);