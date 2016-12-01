package zxing_test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import zxing_test.bean.DecodeResult;

@Service
public class ComparatorServiceImpl implements ComparatorService {

	@Override
	public List<DecodeResult> findMatchedCode(String code, List<DecodeResult> decodeList) {
		List<DecodeResult> decodeResultList = new ArrayList<>();
		
		for (DecodeResult d : decodeList) {
			DecodeResult decodeResult = new DecodeResult();
			List<String> decodeStringList = new ArrayList<>();
			
			for (String urlDecode : d.getDecodeUrl()) {
				if( code.equals(urlDecode)){
					decodeResult.setFilename(d.getFilename());
					decodeStringList.add(urlDecode);
				}
			}
			if (0 != decodeStringList.size()){
				decodeResult.setDecodeUrl(decodeStringList);
				decodeResultList.add(decodeResult);
			}
		}
		
		return decodeResultList;
	}

}
