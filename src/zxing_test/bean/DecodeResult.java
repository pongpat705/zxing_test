package zxing_test.bean;

import java.util.List;

public class DecodeResult {

	private String filename;
	private List<String> decodeUrl;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public List<String> getDecodeUrl() {
		return decodeUrl;
	}
	public void setDecodeUrl(List<String> decodeUrl) {
		this.decodeUrl = decodeUrl;
	}
}
