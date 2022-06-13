package com.github.erf88.writer;

import com.github.erf88.model.Cartao;
import com.github.erf88.model.Cliente;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@SuppressWarnings("ALL")
@Configuration
public class ArquivoClienteCartaoWriter {

    @Value("${spring.batch.file.clientes.out}")
    private Resource clientesResource;

    @Value("${spring.batch.file.cartoes.out}")
    private Resource cartoesResource;

    @Bean
    public ClassifierCompositeItemWriter clienteCartaoWriter() {
        return new ClassifierCompositeItemWriterBuilder()
                .classifier(itemWriter -> classifier(itemWriter))
                .build();
    }

    private Object classifier(Object objeto) {
        if(objeto instanceof Cliente) {
            return clienteWriter();
        } else {
            return cartaoWriter();
        }
    }

    @Bean
    public FlatFileItemWriter<Cliente> clienteWriter() {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("clienteWriter")
                .resource(clientesResource)
                .headerCallback(header -> header.write("ID;NOME;SOBRENOME;EMAIL;STATUS;DATA_PROCESSAMENTO"))
                .delimited()
                .delimiter(";")
                .names(new String[] { "id", "nome", "sobrenome", "email", "status", "dataProcessamento" })
                .build();
    }

    @Bean
    public FlatFileItemWriter<Cartao> cartaoWriter() {
        return new FlatFileItemWriterBuilder<Cartao>()
                .name("cartaoWriter")
                .resource(cartoesResource)
                .headerCallback(header -> header.write("ID;ID_CLIENTE;NUMERO_CARTAO;TIPO_CARTAO;DATA_VALIDADE;DATA_PROCESSAMENTO"))
                .delimited()
                .delimiter(";")
                .names(new String[] { "id", "idCliente", "numeroCartao", "tipoCartao", "dataValidade", "dataProcessamento" })
                .build();
    }

}
