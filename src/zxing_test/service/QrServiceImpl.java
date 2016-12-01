package zxing_test.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import zxing_test.bean.DecodeResult;
import zxing_test.config.ApplicationConstant;

@Service
public class QrServiceImpl implements QrService {

	@Autowired FileService fileService;
	
	@Override
	public List<DecodeResult> findQrString(List<MultipartFile> multipartImages) throws NotFoundException, IOException {
		List<DecodeResult> decodeResultList = new ArrayList<>();
		for (MultipartFile m : multipartImages) {
			DecodeResult decodeResult = new DecodeResult();
			BinaryBitmap bitmap = readByteStreamToBitmap(m.getBytes());
			
			if (null == bitmap) return null;
		    
		    decodeResult.setFilename(m.getOriginalFilename());
		    decodeResult.setDecodeUrl(readQrCodeToString(bitmap));
		    
		    decodeResultList.add(decodeResult);
		    
		}
	 
	    return decodeResultList;
	}
	
	@Override
	public List<DecodeResult> findQrString() throws IOException, NotFoundException {
		List<DecodeResult> decodeResultList = new ArrayList<>();
		
		List<String> fileList = fileService.getFileInFolder();
		for (String s : fileList) {
			byte[] img = imageToByte(s);
			
			DecodeResult decodeResult = new DecodeResult();
			BinaryBitmap bitmap = readByteStreamToBitmap(img);
			
			if (null == bitmap) return null;
		    
		    decodeResult.setFilename(s);
		    decodeResult.setDecodeUrl(readQrCodeToString(bitmap));
		    
		    decodeResultList.add(decodeResult);
		}
		return decodeResultList;
	}
	
	
	public static byte[] imageToByte(String fileName) throws IOException{
		byte[] imageInByte;
		
		BufferedImage originalImage = ImageIO.read(new File(fileName));

		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();
		
		return imageInByte;
	}
	
	public BinaryBitmap readByteStreamToBitmap(byte[] byteArray) throws IOException{
		InputStream in = null;
	    BufferedImage image = null;
	    BinaryBitmap bitmap = null;
		in = new ByteArrayInputStream(byteArray);
    	image = ImageIO.read(in);
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
        bitmap = new BinaryBitmap(new HybridBinarizer(source));

		return bitmap;
	}
	
	public List<String> readQrCodeToString(BinaryBitmap bitmap) throws NotFoundException{
		List<String> decodeStringList = null;
		Result[] result = null;
		
		QRCodeMultiReader reader = new QRCodeMultiReader();
        	decodeStringList = new ArrayList<String>();
			result = reader.decodeMultiple(bitmap);
		    for (int i = 0; i < result.length; i++) {
				Result r = result[i];
				String resultToString = r.getText();
				decodeStringList.add(resultToString);
			}

		return decodeStringList;
	}

}
