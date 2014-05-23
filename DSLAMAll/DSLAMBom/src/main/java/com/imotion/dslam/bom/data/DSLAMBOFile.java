package com.imotion.dslam.bom.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.DSLAMBOIFile;

@Entity(name="File")
public class DSLAMBOFile implements DSLAMBOIFile {

	private static final long serialVersionUID = 4235149869033046131L;
	
	@Id
	@SequenceGenerator(name = "FileIdGenerator", sequenceName = "FileSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "FileIdGenerator")
	private Long 		fileId;
	private String 		filename;
	private String 		content;
	private String 		contentType;
	@Temporal(TemporalType.TIMESTAMP)
	private Date 		savedTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date 		creationTime;
	@Version
	private 	Long	version; 

	public DSLAMBOFile() {}

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

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public Date getSavedTime() {
		return savedTime;
	}

	@Override
	public void setSavedTime(Date savedTime) {
		this.savedTime = savedTime;
	}

	@Override
	public Date getCreationTime() {
		return creationTime;
	}

	@Override
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

}
