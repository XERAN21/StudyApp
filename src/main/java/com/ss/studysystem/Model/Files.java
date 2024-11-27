package Model;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Files {
	private int file;
	private int uploader;
	private String filename;
	private Blob file_path;
	private String file_type;
	private LocalDateTime uploaded_at;
	
	public Files() {
	}

	public int getFile() {
		return file;
	}

	public void setFile(int file) {
		this.file = file;
	}

	public int getUploader() {
		return uploader;
	}

	public void setUploader(int uploader) {
		this.uploader = uploader;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Blob getFile_path() {
		return file_path;
	}

	public void setFile_path(Blob file_path) {
		this.file_path = file_path;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public LocalDateTime getUploaded_at() {
		return uploaded_at;
	}

	public void setUploaded_at(LocalDateTime uploaded_at) {
		this.uploaded_at = uploaded_at;
	}
	
	
}
