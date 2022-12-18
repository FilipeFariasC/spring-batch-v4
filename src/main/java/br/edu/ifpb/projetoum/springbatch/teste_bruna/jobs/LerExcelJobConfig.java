package br.edu.ifpb.projetoum.springbatch.teste_bruna.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.transaction.PlatformTransactionManager;

import br.edu.ifpb.projetoum.springbatch.teste_bruna.util.ProfessorDTO;

@Configuration
@EnableBatchProcessing
public class LerExcelJobConfig {

    @Autowired
    private JobBuilderFactory jobfactory;
    

    @Bean
    public Job lerExcelJob(@Qualifier("excelLeitura")Step step){
        return jobfactory.get("lerExcelJob").start(step).incrementer(new RunIdIncrementer()).build();

    }

    


    @Bean
    ItemReader<ProfessorDTO> professorExcelReader() {
        PoiItemReader<ProfessorDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new PathResource("C:\\Users\\Bruna\\Documents\\projetos\\spring-batch-v4\\src\\main\\resources\\doc.xlsx"));
        reader.setRowMapper(excelRowMapper());
        return reader;
    }

    private RowMapper<ProfessorDTO> excelRowMapper() {
        BeanWrapperRowMapper<ProfessorDTO> rowMapper = new BeanWrapperRowMapper<>();
        rowMapper.setTargetType(ProfessorDTO.class);
        return rowMapper;
    }
}
