package com.github.erf88.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Cartao implements Serializable {

    private String id;
    private Integer idCliente;
    private String numeroCartao;
    private String tipoCartao;
    private String dataValidade;
    private String dataProcessamento;

}
