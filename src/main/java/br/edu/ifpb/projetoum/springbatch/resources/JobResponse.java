package br.edu.ifpb.projetoum.springbatch.resources;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JobResponse {
	
	private String path;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date creationTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date finishTime;
	private ExitStatus status;
	
	private JobResponse(String path, Date creationTime, Date startTime, Date finishTime, ExitStatus status) {
		setPath(path);
		setCreationTime(creationTime);
		setStartTime(startTime);
		setFinishTime(finishTime);
		setStatus(status);
	}
	
	public static JobResponse of(String path, Date creationTime, Date startTime, Date finishTime, ExitStatus status) {
		return new JobResponse(path, creationTime, startTime, finishTime, status);
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
