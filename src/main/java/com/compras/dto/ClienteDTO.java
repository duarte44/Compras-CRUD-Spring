package com.compras.dto;

import com.compras.entities.Cliente;
import com.compras.entities.Compras;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private String telefone;


    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();

    }
}
