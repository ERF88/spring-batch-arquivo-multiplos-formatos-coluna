package com.github.erf88.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Cliente implements Serializable {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String status;
    private String dataProcessamento;

}
