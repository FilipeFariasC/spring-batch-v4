package br.edu.ifpb.projetoum.springbatch.resources;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.ifpb.projetoum.springbatch.model.service.FileUtils;

public class JobResponse {
	
	private String originalFile;
	private String resultFile;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date creationTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date finishTime;
	private ExitStatus status;
	
	private JobResponse(String originalFile, String resultFile, Date creationTime, Date startTime, Date finishTime, ExitStatus status) {
		setOriginalFile(originalFile);
		setResultFile(resultFile);
		setCreationTime(creationTime);
		setStartTime(startTime);
		setFinishTime(finishTime);
		setStatus(status);
	}
	
	public static JobResponse of(String file, FileUtils.Extension in, FileUtils.Extension out,  Date creationTime, Date startTime, Date finishTime, ExitStatus status) {
		
		String originalFile = FileUtils.replaceExtension(file, in);
		String resultFile = FileUtils.replaceExtension(file, out);
		return new JobResponse(
			FileUtils.UPLOAD_PATH.resolve(originalFile).toAbsolutePath().toString(),
			FileUtils.RESULT_PATH.resolve(resultFile).toAbsolutePath().toString(),
			creationTime, 
			startTime, 
			finishTime, 
			status);
	}
	
	public String getOriginalFile() {
		return originalFile;
	}
	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
	public String getResultFile() {
		return resultFile;
	}
	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public ExitStatus getStatus() {
		return status;
	}
	public void setStatus(ExitStatus status) {
		this.status = status;
	}
	
}
