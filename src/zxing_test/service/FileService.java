package zxing_test.service;

import java.io.IOException;
import java.util.List;

import zxing_test.bean.DecodeResult;

public interface FileService {

	public List<String> getFileInFolder();
	
	public void copyFileToFolder(List<DecodeResult> files) throws IOException;
	
	public void renameMovedFile(List<DecodeResult> files);
	
}
