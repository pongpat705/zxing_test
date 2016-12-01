package zxing_test.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.NotFoundException;

import zxing_test.bean.DecodeResult;

public interface QrService {
	
	public List<DecodeResult> findQrString(List<MultipartFile> images) throws NotFoundException, IOException;
	
	public List<DecodeResult> findQrString() throws IOException, NotFoundException;

}
