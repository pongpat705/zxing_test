package zxing_test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.NotFoundException;

import zxing_test.bean.DecodeResult;
import zxing_test.bean.FileUpload;
import zxing_test.service.ComparatorService;
import zxing_test.service.FileService;
import zxing_test.service.QrService;

@Controller
public class ComparatorController {
	
	@Autowired QrService qrService;
	
	@Autowired ComparatorService comparatorService;
	
	@Autowired FileService fileService;

	@RequestMapping(value="/test", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	@ResponseBody
	public Map<String,Object> fileUpload(@ModelAttribute("file") FileUpload uploadFile, HttpServletRequest req){
		Map<String,Object> result = new HashMap<>();
		
		List<MultipartFile> files = uploadFile.getFiles();
		List<DecodeResult> decodeResult;
		
		try {
			decodeResult = qrService.findQrString(files);
			result.put("code", "00");
			result.put("result", decodeResult);
		} catch (NotFoundException | IOException e) {
			result.put("code", "01");
			result.put("result", e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/findMatchedFile", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> findMatchedFile(@RequestParam("code") String code, HttpServletRequest req){
		Map<String,Object> result = new HashMap<>();
		
		List<DecodeResult> decodeResult;
		
		try {
			
			List<DecodeResult> allFile = qrService.findQrString();
			decodeResult = comparatorService.findMatchedCode(code, allFile);
			
			result.put("code", "00");
			result.put("result", decodeResult);
		} catch (NotFoundException | IOException e) {
			result.put("code", "01");
			result.put("result", e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/uploadSelectedFiles", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> uploadSelectedFiles(@RequestBody List<DecodeResult> files, HttpServletRequest req){
		Map<String,Object> result = new HashMap<>();
		
		try {
			fileService.copyFileToFolder(files);
			fileService.renameMovedFile(files);
			result.put("code", "00");
		} catch (IOException e) {
			result.put("code", "01");
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
}
