package br.edu.ifpb.projetoum.springbatch.resources;

import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
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
import br.edu.ifpb.projetoum.springbatch.model.service.FileUtils;

@RestController
@RequestMapping("/arquivo")
public class ArquivoResources {
	
	private final Job cursosCsvJob;
	
	private JobLauncher jobLauncher;

	private final ArquivoService arquivoService;
	
	
	public ArquivoResources(
			final ArquivoService arquivoService,
			@Qualifier("cursosIfpbJob")
			final Job cursosCsvJob,
			final JobLauncher jobLauncher
			) {
		this.arquivoService = arquivoService;
		this.cursosCsvJob = cursosCsvJob;
		this.jobLauncher = jobLauncher;
	}
	
	
	@PostMapping("/upload")
	public ResponseEntity<JobResponse> upload(@RequestParam("arquivo") MultipartFile file) throws Exception {
		String caminho = arquivoService.save(file);
		Map<String, JobParameter> param = new HashMap<>();
		param.put("arquivo", new JobParameter(caminho));
		JobParameters jobParameters = new JobParameters(param);
		JobExecution jobExecution = jobLauncher.run(cursosCsvJob, jobParameters);
		
		return ResponseEntity.ok(JobResponse.of(caminho, FileUtils.Extension.CSV, FileUtils.Extension.JSON, jobExecution.getCreateTime(), jobExecution.getStartTime(), jobExecution.getEndTime(), jobExecution.getExitStatus()));
	}
	
	@DeleteMapping("/delete/{filename}")
	public ResponseEntity<JobResponse> upload(@PathVariable("filename") String filename) {
		try {
			arquivoService.delete(filename);
			return ResponseEntity.noContent().build();
		} catch (UncheckedIOException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
