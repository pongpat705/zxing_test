package zxing_test.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import zxing_test.bean.DecodeResult;
import zxing_test.config.ApplicationConstant;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public List<String> getFileInFolder(){
		List<String> fileList = new ArrayList<>();
		
		//get file from directory
		File folder = new File(ApplicationConstant.PathConfig.READ_DIRECTORY);
		//filter it
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(ApplicationConstant.FileExtension.JPG) || name.endsWith(ApplicationConstant.FileExtension.JPEG);
			}
		};
		//make a result
		if (folder.listFiles(filter).length > 0) {
			for (File f : folder.listFiles(filter)) {
				fileList.add(f.getAbsolutePath());
			}
		} else {
			return null;
		}
		
		return fileList;
	}

	@Override
	public void copyFileToFolder(List<DecodeResult> files) throws IOException {

		for (DecodeResult decodeResult : files) {
			File source = new File(decodeResult.getFilename());
			File dest = new File(ApplicationConstant.PathConfig.TARGET_DIRECTORY+"/"+source.getName());
			FileUtils.copyFile(source, dest);
		}

	}

	@Override
	public void renameMovedFile(List<DecodeResult> files) {

		for (DecodeResult decodeResult : files) {
			File source = new File(decodeResult.getFilename());
			File dest = new File(ApplicationConstant.PathConfig.READ_DIRECTORY+"/"+source.getName()+".moved");
			source.renameTo(dest);
		}
		
	}

}
