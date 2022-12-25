package br.edu.ifpb.projetoum.springbatch.curso.steps.csvtodb.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.PathResource;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;
import br.edu.ifpb.projetoum.springbatch.model.entity.ModalidadeCurso;

@Configuration
public class CsvToCursosIfpbReaderConfiguration {
	private static final String DELIMITADOR = ",";
	private String[] cursosCsvColunas = new String[]{
		"codigo",
		"descricao",
		"diretoria",
		"naturezaParticipacao",
		"eixo",
		"modalidade",
		"resolucaoCriacao",
		"coordenador",
		"cargaHoraria"
	};
	
	@Bean("csvToCurso")
	@StepScope
	public FlatFileItemReader<CursoIfpb> cursoReader(@Value("#{jobParameters['arquivo']}") String filepath) {
        LineMapper<CursoIfpb> cursosCsvMapper = cursoLineMapper();
 
        return new FlatFileItemReaderBuilder<CursoIfpb>()
                .name("cursosReader")
                .resource(new PathResource(filepath))
                .linesToSkip(1)
                .lineMapper(cursosCsvMapper)
                .build();
    }
 
    private LineMapper<CursoIfpb> cursoLineMapper() {
        DefaultLineMapper<CursoIfpb> linhaCsvCursoMapper = new DefaultLineMapper<>();
 
        LineTokenizer csvToken = cursoCsvSetup();
        linhaCsvCursoMapper.setLineTokenizer(csvToken);
 
        FieldSetMapper<CursoIfpb> cursoCsvMapper = cursoCsvMapper();
        linhaCsvCursoMapper.setFieldSetMapper(cursoCsvMapper);
 
        return linhaCsvCursoMapper;
    }
 
    private LineTokenizer cursoCsvSetup() {
        DelimitedLineTokenizer csvDelimitador = new DelimitedLineTokenizer();
        
        csvDelimitador.setDelimiter(DELIMITADOR);
        csvDelimitador.setNames(cursosCsvColunas);
        
        return csvDelimitador;
    }
 
    private FieldSetMapper<CursoIfpb> cursoCsvMapper() {
        BeanWrapperFieldSetMapper<CursoIfpb> cursoInfoMapper = new BeanWrapperFieldSetMapper<>();
        
        cursoInfoMapper.setTargetType(CursoIfpb.class);
        cursoInfoMapper.setConversionService(cursoConversionService());
        
        return cursoInfoMapper;
    }
    
    private ConversionService cursoConversionService() {
    	DefaultConversionService conversionService = new DefaultConversionService();
        DefaultConversionService.addDefaultConverters(conversionService);
        
        conversionService.addConverter(new Converter<String, ModalidadeCurso>() {

			@Override
			public ModalidadeCurso convert(String source) {
				return ModalidadeCurso.from(source);
			}
		});
        
        return conversionService;
    }
    
}
