package com.fiap.saude_transparente.domain.enums;

import com.fiap.saude_transparente.domain.exceptions.EspecialidadeException;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Especialidades {
    GERAL("GERAL"),
    ORTOPEDIA("ORTOPEDIA"),
    CARDIOLOGIA("CARDIOLOGIA"),
    DERMATOLOGIA("DERMATOLOGIA"),
    NEUROLOGIA("NEUROLOGIA"),
    PEDIATRIA("PEDIATRIA"),
    GINECOLOGIA("GINECOLOGIA"),
    ENDOCRINOLOGIA("ENDOCRINOLOGIA"),
    PSIQUIATRIA("PSIQUIATRIA"),
    ONCOLOGIA("ONCOLOGIA");

    private final String valor;

    private static final Map<String, Especialidades> BY_VALUE =
            Arrays.stream(values())
                    .collect(Collectors.toMap(e -> e.valor.toUpperCase(Locale.ROOT), e -> e));

    Especialidades(String valor) {
        this.valor = valor;
    }

    public String getValue() {
        return valor;
    }


    public static Especialidades from(String value) {
        if (value == null) {
            throw new EspecialidadeException("Valor de especialidade é nulo");
        }
        Especialidades e = BY_VALUE.get(value.trim().toUpperCase(Locale.ROOT));
        if (e == null) {
            throw new EspecialidadeException("Especialidade desconhecida: " + value);
        }
        return e;
    }

    public static Optional<Especialidades> fromNullable(String value) {
        if (value == null) return Optional.empty();
        return Optional.ofNullable(BY_VALUE.get(value.trim().toUpperCase(Locale.ROOT)));
    }

}
