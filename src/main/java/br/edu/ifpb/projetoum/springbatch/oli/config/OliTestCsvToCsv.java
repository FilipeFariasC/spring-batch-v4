package br.edu.ifpb.projetoum.springbatch.oli.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import br.edu.ifpb.projetoum.springbatch.oli.model.Curso;
import br.edu.ifpb.projetoum.springbatch.oli.process.CursoProcessor;

@Configuration
@EnableBatchProcessing
public class OliTestCsvToCsv {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	private String file = "C:\\Users\\filip\\OneDrive\\Documentos\\data\\csv_entrada.csv";
	
	private String saida = "C:\\Users\\filip\\OneDrive\\Documentos\\data\\csv_saida.csv";

	
	@Bean
	public FlatFileItemReader<Curso> readDataFromCsv(){
		FlatFileItemReader<Curso> reader = new FlatFileItemReader<Curso>();
		reader.setResource(new FileSystemResource(file));
		reader.setLineMapper(new DefaultLineMapper<Curso>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(Curso.fields());
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Curso>() {
					{
						setTargetType(Curso.class);
					}
				});
			}
		});
		return reader;
	}
	
	@Bean
	public CursoProcessor processor() {
		return new CursoProcessor();
	}
	@Bean
	public FlatFileItemWriter<Curso> writer(){
		FlatFileItemWriter<Curso> writer = new FlatFileItemWriter<Curso>();
		writer.setResource(new FileSystemResource(saida));
		DelimitedLineAggregator<Curso> aggregator = new DelimitedLineAggregator<>();
		BeanWrapperFieldExtractor<Curso> fieldExtract = new BeanWrapperFieldExtractor<>();
		fieldExtract.setNames(Curso.fields());
		aggregator.setFieldExtractor(fieldExtract);
		writer.setLineAggregator(aggregator);
		return writer;
	}
	
	
	@Bean
	public Step executeCursoStep() {
		return stepBuilderFactory.get("executeCursoStep").<Curso,Curso>chunk(5)
				.reader(readDataFromCsv()).processor(processor()).writer(writer()).build();
	}
	
	@Bean
	public Job processCursoJob() {
		return jobBuilderFactory.get("processCursoJob").flow(executeCursoStep()).end().build();
	}
	
	
	@Bean 
	public Job job(Step step) {
		return jobBuilderFactory.get("job").start(step).build();
	}
	
	@Bean
	public Step step(ItemReader<Curso> reader, ItemWriter<Curso> writer) {
		return stepBuilderFactory.get("step")
				.<Curso,Curso>chunk(1)
				.reader(reader)
				.writer(writer)
				.build();
		
	}
	
	
}
