package com.github.erf88.processor;

import com.github.erf88.model.Cartao;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CartaoProcessor implements ItemProcessor<Cartao, Cartao> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate dataProcessamento = LocalDate.now();

    @Override
    public Cartao process(Cartao cartao) {
        cartao.setDataProcessamento(dataProcessamento.format(formatter));
        return cartao;
    }

}
