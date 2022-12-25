package br.edu.ifpb.projetoum.springbatch.resources;

import java.io.UncheckedIOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.batch.runtime.JobExecution;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.projetoum.springbatch.model.service.ArquivoService;

@RestController
@RequestMapping("/arquivo")
public class ArquivoResources {
	
	@Autowired
	@Qualifier("cursosIfpbJob")
	private Job job;
	
	@Autowired
	private JobLauncher jobLaumcher;
	

	private final ArquivoService arquivoService;
	
	public ArquivoResources(final ArquivoService arquivoService) {
		this.arquivoService = arquivoService;
	}
	
	
	@PostMapping("/upload")
	public ResponseEntity<ArquivoResponse> upload(@RequestParam("arquivo") MultipartFile file) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		String caminho = arquivoService.save(file);
		Map<String, JobParameter> param = new HashMap<>();
		param.put("cursosIfpbJob",new JobParameter(new Date().getTime()));
		JobParameters jobParameters = new JobParameters(param);
		org.springframework.batch.core.JobExecution jobExecution = jobLaumcher.run(job, jobParameters);
		return ResponseEntity.ok(ArquivoResponse.of(caminho));
	}
	
	@DeleteMapping("/delete/{filename}")
	public ResponseEntity<ArquivoResponse> upload(@PathVariable("filename") String filename) {
		try {
			arquivoService.delete(filename);
			return ResponseEntity.noContent().build();
		} catch (UncheckedIOException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
