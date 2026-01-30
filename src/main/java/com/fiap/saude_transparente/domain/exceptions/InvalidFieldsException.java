package com.fiap.saude_transparente.domain.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class InvalidFieldsException extends RuntimeException {

    public InvalidFieldsException(Set<String> violations) {
        super(violations.stream().collect(Collectors.joining(" | ")));
    }

}
