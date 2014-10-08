package com.imotion.dslam.bom.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIFile;

@Entity(name="File")
public class CRONIOBOFile implements CRONIOBOIFile {

	private static final long serialVersionUID = 4235149869033046131L;
	
	private Long 	fileId;
	private String 	filename;
	private String 	content;
	private String compileContent;
	private int 	contentType;
	private Date 	savedTime;
	private Date 	creationTime;
	private Long	version; 

	public CRONIOBOFile() {}

	@Id
	@SequenceGenerator(name = "FileIdGenerator", sequenceName = "FileSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "FileIdGenerator")	
	@Override
	public Long getFileId() {
		return fileId;
	}

	@Override
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Override
	public String getFilename() {
		return filename;
	}

	@Override
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Lob 
	@Column(columnDefinition="TEXT")
	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Lob 
	@Column(columnDefinition="TEXT")
	@Override
	public String getCompiledContent() {
		return compileContent;
	}

	@Override
	public void setCompiledContent(String compileContent) {
		this.compileContent = compileContent;
	}

	@Override
	public int getContentType() {
		return contentType;
	}

	@Override
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public Date getSavedTime() {
		return savedTime;
	}

	@Override
	public void setSavedTime(Date savedTime) {
		this.savedTime = savedTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public Date getCreationTime() {
		return creationTime;
	}

	@Override
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
