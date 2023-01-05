package com.compras.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String telefone;

    private Long id;
    private String item;
    private Double preco;
    private Integer quantidade;
}
