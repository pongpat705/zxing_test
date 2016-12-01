package zxing_test.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.tomcat.util.codec.binary.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class maintest {

	public static void main(String[] args) throws IOException, ChecksumException, FormatException, NotFoundException {
		// TODO Auto-generated method stub
//		String file = "C:/QrCode/A1.jpg";
//		byte[] img = imageToByte(file);
//		System.out.println(readQRCode(img));
//		
//		String file2 = "C:/QrCode/ac2.jpg";
//		byte[] img2 = imageToByte(file2);
//		Result[] result = readMultipleQRCode(img2);
//		for (Result r : result) {
//			System.out.println(r.getText());
//		}
//		createQRImage();
//		readFilesInFolder();
		
//		String a = "999111999"; // 9
//		String b = "00001502221114";
//		System.out.println(String.format("%010d", Integer.parseInt(b)));
		System.out.println(readQRCode(imageToByte("C:/Qrcode/pdf2img/exam2.jpg")));
//		createQRImage();
//		readQrcodeFromPdf();
	}
	
	public static void readFilesInFolder(){
		File folder = new File("C:/Qrcode/pdf2img");
		listFilesForFolder(folder); 
	}
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jpg") || name.endsWith(".jpeg");
			}
		})) {
				System.out.println(fileEntry.getAbsolutePath());
//	        if (fileEntry.isDirectory()) {
//	            listFilesForFolder(fileEntry);
//	        } else { 
//	            System.out.println(fileEntry.getAbsolutePath());
//	        } 
	    } 
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
	
	public static String readQRCode(byte[] img) {
//		    File file = new File(fileName);
			InputStream in = new ByteArrayInputStream(img);
		    BufferedImage image = null;
		    BinaryBitmap bitmap = null;
		    Result result = null;
		 
		    try {
//		        image = ImageIO.read(file);
		    	image = ImageIO.read(in);
		        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		        bitmap = new BinaryBitmap(new HybridBinarizer(source));
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		 
		    if (bitmap == null)
		        return null;
		 
		    Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
		    hints.put(DecodeHintType.PURE_BARCODE, true);
		    
		    MultiFormatReader reader = new MultiFormatReader();   
		    try {
		        result = reader.decode(bitmap, hints);
		        return result.getText();
		    } catch (NotFoundException e) {
		        e.printStackTrace();
		    }
		 
		    return null;
		}
	
	public static Result[] readMultipleQRCode(byte[] img) throws ChecksumException, FormatException {
		InputStream in = new ByteArrayInputStream(img);
	    BufferedImage image = null;
	    BinaryBitmap bitmap = null;
	    Result[] result = null;
	 
	    try {
	    	image = ImageIO.read(in);
	        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
	        RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
	        bitmap = new BinaryBitmap(new HybridBinarizer(source));
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	 
	    if (bitmap == null)
	        return null;
	 
	    QRCodeMultiReader reader = new QRCodeMultiReader();
	    try {
	        result = reader.decodeMultiple(bitmap);
	        return result;
	    } catch (NotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	 
	    return null;
	}
	
	public static void createQRImage() {
		String myCodeText = "789AccNumber";
		String filePath = "C:/Qrcode/ac3.jpg";
		int size = 250;
		String fileType = "jpg";
		File myFile = new File(filePath);
		try {
			
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
					BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
 
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);
 
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, myFile);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\nYou have successfully created QR Code.");
	} 
	
	public static void readFromPdfWriteJpg() throws IOException {
		//load
		File file = new File("C:/Qrcode/TCSBIS59080000079.pdf");
		PDDocument doc = PDDocument.load(file);
		
		//get page 1
		PDPage docPage = doc.getPage(0);
		docPage.getContents();
		
		//render Image from pdf
		PDFRenderer pdfRender = new PDFRenderer(doc);
		BufferedImage bim = pdfRender.renderImageWithDPI(0, 300, ImageType.RGB);
		
		ImageIO.write(bim, "jpg", new File("C:/Qrcode/pdf2img/exam1.jpg"));
	
		doc.close();
	}
	
	public static void readQrcodeFromPdf() throws IOException, NotFoundException, ChecksumException, FormatException {
		String ans = "";
		//load
		File file = new File("C:/Qrcode/TCSBIS59080000079.pdf");
		PDDocument doc = PDDocument.load(file);
		
		//get page 1
		PDPage docPage = doc.getPage(0);
		
		PDFRenderer pdfRender = new PDFRenderer(doc);
		BufferedImage image = pdfRender.renderImageWithDPI(0, 300, ImageType.RGB);
//		BufferedImage cropedImage = image.getSubimage(0, 0, 1000, 1000);
		
		BinaryBitmap bitmap = null;
		Result result = null;
		
		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
	    RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
	    bitmap = new BinaryBitmap(new HybridBinarizer(source));
		
	    if (bitmap == null)
	    	ans = "null";
	 
	    Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
	    hints.put(DecodeHintType.PURE_BARCODE, true);
	    
	    MultiFormatReader reader = new MultiFormatReader();   
	    try {
	        result = reader.decode(bitmap, hints);
	        ans =  result.getText();
	    } catch (NotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println(ans);
	    
		doc.close();
	}
}
