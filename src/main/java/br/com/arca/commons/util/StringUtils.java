package br.com.arca.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.Base64;

import javax.swing.text.MaskFormatter;
import javax.xml.bind.DatatypeConverter;

import br.com.arca.commons.util.log.CommonLogs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtils {
	public static String removeAccents(String str) {
		if(str == null) {
			return str;
		}
		
		var normalizedStr = Normalizer.normalize(str, Normalizer.Form.NFD);
	    
	    return normalizedStr.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	}
	
	public static String removeAllWhiteSpaces(String str) {
		return isNotBlank(str) ? str.replaceAll("\\s", "") : "";
	}
	
	public static boolean isEquivalent(String first, String second) {
		var treatedFirst = removeAccents(removeAllWhiteSpaces(first)).toLowerCase();
		var treatedSecond = removeAccents(removeAllWhiteSpaces(second)).toLowerCase();
		
		return treatedFirst.equals(treatedSecond);
	}

	public static String textToMd5(String text) {
		if (isNotBlank(text)) {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(text.getBytes());
				byte[] digest = md.digest();
				return DatatypeConverter.printHexBinary(digest).toUpperCase();
			} catch (NoSuchAlgorithmException e) {
				log.error(CommonLogs.MD5_ERROR.text(), text, e.getMessage());
			}
		}

		return null;
	}

	public static String textToBase64(String text) {
		if (isNotBlank(text)) {
			return Base64.getEncoder().encodeToString(text.getBytes());
		}

		return null;
	}
	
	public static boolean isBlank(String text) {
		return org.apache.commons.lang.StringUtils.isBlank(text);
	}
	
	public static boolean isNotBlank(String text) {
		return org.apache.commons.lang.StringUtils.isNotBlank(text);
	}
	
	public static boolean hasNumbersInSequence(String text, Long quantity) {
    	var counter = 0L;
    	Character previousDigit = null;
    	
    	for(Character actual : text.toCharArray()) {
    		if(counter == quantity) {
    			return true;
    		}
    		
    		if(Character.isDigit(actual)) {
    			if(previousDigit == null) {
    				previousDigit = actual;
    				counter = 1L;
    				continue;
    			}
    			
    			if(actual == previousDigit + 1) {
    				counter++;
    			} else {
    				counter = 0L;
    			}
    			
    			previousDigit = actual;
    		} else {
    			previousDigit = null;
    			counter = 0L;
    		}
    	}
    	
    	return false;
	}

	public static String maskCpf(String cpf) {
		var cpfFormated = cpf;
		try {
			var mask = new MaskFormatter("###.###.###-##");
			mask.setValueContainsLiteralCharacters(false);
			cpfFormated =  mask.valueToString(cpf);
		}catch (ParseException ex){
			log.error(CommonLogs.PARSE_ERROR.text(), cpf, ex.getMessage());
		}
		return cpfFormated;
	}

	public static String maskPhone(String phone) {
		var phoneFormated = phone;
		try {
			var mask = phone.length() == 11 ?  new MaskFormatter("(##)#.####.####") : new MaskFormatter("(##)####.####");
			mask.setValueContainsLiteralCharacters(false);
			phoneFormated =  mask.valueToString(phone);
		}catch (ParseException ex){
			log.error(CommonLogs.PARSE_ERROR.text(), phone, ex.getMessage());
		}
		return phoneFormated;
	}
}
