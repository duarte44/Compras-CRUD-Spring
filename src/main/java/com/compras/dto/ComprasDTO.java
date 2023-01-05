package com.compras.dto;


import com.compras.entities.Cliente;
import com.compras.entities.Compras;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ComprasDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String item;
    private Double preco;
    private Integer quantidade;

    private Cliente cliente;

    public ComprasDTO(Compras obj) {
        this.id = obj.getId();
        this.item = obj.getItem();
        this.preco = obj.getPreco();
        this.quantidade = obj.getQuantidade();
        this.cliente = obj.getCliente();

    }




}
