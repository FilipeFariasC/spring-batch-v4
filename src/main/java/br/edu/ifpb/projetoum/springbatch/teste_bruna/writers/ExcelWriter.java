package br.edu.ifpb.projetoum.springbatch.teste_bruna.writers;

import java.util.Collections;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import br.edu.ifpb.projetoum.springbatch.teste_bruna.util.ProfessorDTO;

@Configuration
@EnableBatchProcessing
public class ExcelWriter {
    
    /*@Bean
    public ItemWriter<ProfessorDTO> writer() {
        return System.out::println;
    }*/
    
    
    @Bean
    public ItemWriter<ProfessorDTO> writer(Environment environment) {
        String exportFilePath = "C:\\Users\\Bruna\\Documents\\projetos\\spring-batch-v4\\src\\main\\resources\\saida.xml";
        Resource exportFileResource = new FileSystemResource(exportFilePath);
 
        XStreamMarshaller professorMarshaller = new XStreamMarshaller();
        professorMarshaller.setAliases(Collections.singletonMap("professor",ProfessorDTO.class
        ));
 
        return new StaxEventItemWriterBuilder<ProfessorDTO>()
                .name("professorWriter")
                .resource(exportFileResource)
                .marshaller(professorMarshaller)
                .rootTagName("professor")
                .build();
    }
}
