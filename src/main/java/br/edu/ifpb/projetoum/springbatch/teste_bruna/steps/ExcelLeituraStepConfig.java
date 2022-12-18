package br.edu.ifpb.projetoum.springbatch.teste_bruna.steps;



import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.teste_bruna.util.ProfessorDTO;

@Configuration
@EnableBatchProcessing
public class ExcelLeituraStepConfig {
    
        
    @Autowired
    private StepBuilderFactory stepfactory;
    

    @Bean
    public Step excelLeitura(ItemReader<ProfessorDTO> reader, ItemWriter<ProfessorDTO> writer){
        

        return stepfactory.get("excelLeitura").<ProfessorDTO, ProfessorDTO>chunk(100).reader(reader)
        .writer(writer).build();
        
    }
}
