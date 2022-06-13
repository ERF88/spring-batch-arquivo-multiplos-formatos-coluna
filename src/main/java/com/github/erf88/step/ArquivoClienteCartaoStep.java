package com.github.erf88.step;

import com.github.erf88.model.Cartao;
import com.github.erf88.model.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("ALL")
@Configuration
public class ArquivoClienteCartaoStep {

    @Bean
    public Step clienteCartaoStep(
            StepBuilderFactory stepBuilderFactory,
            FlatFileItemReader<Object> arquivoClienteReader,
            ClassifierCompositeItemProcessor clienteCartaoProcessor,
            ClassifierCompositeItemWriter clienteCartaoWriter,
            FlatFileItemWriter<Cliente> clienteWriter,
            FlatFileItemWriter<Cartao> cartaoWriter) {

        return stepBuilderFactory
                .get("clienteCartaoStep")
                .<Object, Object>chunk(50)
                .reader(arquivoClienteReader)
                .processor(clienteCartaoProcessor)
                .writer(clienteCartaoWriter)
                .stream(clienteWriter)
                .stream(cartaoWriter)
                .build();
    }

}
