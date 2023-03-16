package br.edu.ifpb.projetoum.springbatch.readerTest;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;


@SpringBootTest
@SpringBatchTest
public class CursoReader {
	
	@Autowired
	private ItemReader<CursoIfpb> reader;
	
	@Test
	public void test_reader() throws Exception {
		FlatFileItemReader<CursoIfpb> flatFileReader = (FlatFileItemReader<CursoIfpb>) reader;
		flatFileReader.open(new ExecutionContext());
		CursoIfpb curso;
		assertNotNull(curso = flatFileReader.read());
	}
	
	@Test
	public void test_reader_codigo() throws Exception {
		FlatFileItemReader<CursoIfpb> flatFileReader = (FlatFileItemReader<CursoIfpb>) reader;
		flatFileReader.open(new ExecutionContext());
		CursoIfpb curso = flatFileReader.read();
		flatFileReader.close();
		flatFileReader.open(new ExecutionContext());
		assertEquals(curso.getCodigo(), flatFileReader.read().getCodigo());
	}
	
	@Test
	public void test_reader_type_context() throws Exception {
		FlatFileItemReader<CursoIfpb> flatFileReader = (FlatFileItemReader<CursoIfpb>) reader;
		flatFileReader.open(new ExecutionContext());
		assertEquals(CursoIfpb.class, flatFileReader.read().getClass());
	}
	
	

}
