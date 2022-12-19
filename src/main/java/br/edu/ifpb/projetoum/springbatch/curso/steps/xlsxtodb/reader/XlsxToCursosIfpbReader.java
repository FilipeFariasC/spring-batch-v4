package br.edu.ifpb.projetoum.springbatch.curso.steps.xlsxtodb.reader;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;

@Configuration
public class XlsxToCursosIfpbReader {
	

	   @Bean("cursoXlsReader")
	   ItemReader<CursoIfpb> cursoXlsReader() {
	        PoiItemReader<CursoIfpb> reader = new PoiItemReader<>();
	        reader.setLinesToSkip(1);
	        reader.setResource(new PathResource("data/cursos.xlsx"));
	        reader.setRowMapper(excelRowMapper());
	        return reader;
	    }

	    private RowMapper<CursoIfpb> excelRowMapper() {
	        BeanWrapperRowMapper<CursoIfpb> rowMapper = new BeanWrapperRowMapper<>();
	        rowMapper.setTargetType(CursoIfpb.class);
	        return rowMapper;
	    }
}
