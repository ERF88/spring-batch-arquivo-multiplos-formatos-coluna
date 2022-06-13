package com.github.erf88.processor;

import com.github.erf88.model.Cliente;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("ALL")
@Configuration
public class ArquivoClienteCartaoProcessor {

    @Bean
    public ClassifierCompositeItemProcessor clienteCartaoProcessor() {
        return new ClassifierCompositeItemProcessorBuilder()
                .classifier(itemProcessor -> classifier(itemProcessor))
                .build();
    }

    private Object classifier(Object objeto) {
        if(objeto instanceof Cliente) {
            return new ClienteProcessor();
        } else {
            return new CartaoProcessor();
        }
    }

}
