CREATE TABLE IF NOT EXISTS medico
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255),
    telefone VARCHAR(20),
    email VARCHAR(255),
    endereco VARCHAR(500),
    cpf VARCHAR(14),
    data_nascimento DATE,
    crm VARCHAR(20),
    especialidade VARCHAR(100),
    CONSTRAINT medico_pkey PRIMARY KEY (id)
);

CREATE INDEX idx_medico_nome ON medico (nome);