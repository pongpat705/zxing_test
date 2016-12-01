package zxing_test.service;

import java.util.List;

import zxing_test.bean.DecodeResult;

public interface ComparatorService {

	public List<DecodeResult> findMatchedCode(String code, List<DecodeResult> decodeResultList);
	
}
