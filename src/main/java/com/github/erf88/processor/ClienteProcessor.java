package com.github.erf88.processor;

import com.github.erf88.model.Cliente;
import org.springframework.batch.item.ItemProcessor;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate dataProcessamento = LocalDate.now();

    @Override
    public Cliente process(Cliente cliente) {
        cliente.setNome(removeAcentos(cliente.getNome()));
        cliente.setSobrenome(removeAcentos(cliente.getSobrenome()));
        cliente.setDataProcessamento(dataProcessamento.format(formatter));
        return cliente;
    }

    public static String removeAcentos(final String str) {
        String strSemAcentos = Normalizer.normalize(str, Normalizer.Form.NFD);
        return strSemAcentos.replaceAll("[^\\p{ASCII}]", "");
    }

}

