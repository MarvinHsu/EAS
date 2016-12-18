package com.hsuforum.eas.web.jsf.validator;

import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.hsuforum.common.web.util.MessageUtils;
import com.hsuforum.eas.common.ErrorCode;


/**
 * Taiwan identity id validator
 * @author Marvin
 *
 */
@FacesValidator("taiwanIdentityIdValidator")
public class TaiwanIdentityIdValidator implements Validator {
	
    /**
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean validate = false;
        if (value != null) {
            String identityId = ((String) value).trim();
            if (identityId.length() == 10) {
                if (checkIdentityId(identityId)) {
                    validate = true;
                }
            }
        }
        if (!validate) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getAccessor().getMessage(ErrorCode.P10009.toString()),
                    null);
            throw new ValidatorException(message);
        }
    }

    
    /**
     * 檢核身分證字號/居留證號檢核程式
     * 
     * @return
     */
    private boolean checkIdentityId(String str) {
    	str=str.toUpperCase();
    	if (str == null || "".equals(str)) {
    		return false;
    	}
    		
    	final char[] pidCharArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    	// 原身分證英文字應轉換為10~33，這裡直接作個位數*9+10
    	final int[] pidIDInt = { 1, 10, 19, 28, 37, 46, 55, 64, 39, 73, 82, 2, 11, 20, 48, 29, 38, 47, 56, 65, 74, 83, 21, 3, 12, 30 };

    	// 原居留證第一碼英文字應轉換為10~33，十位數*1，個位數*9，這裡直接作[(十位數*1) mod 10] + [(個位數*9) mod 10]
    	final int[] pidResidentFirstInt = { 1, 10, 9, 8, 7, 6, 5, 4, 9, 3, 2, 2, 11, 10, 8, 9, 8, 7, 6, 5, 4, 3, 11, 3, 12, 10 };
    	
    	// 原居留證第二碼英文字應轉換為10~33，並僅取個位數*6，這裡直接取[(個位數*6) mod 10]
    	final int[] pidResidentSecondInt = {0, 8, 6, 4, 2, 0, 8, 6, 2, 4, 2, 0, 8, 6, 0, 4, 2, 0, 8, 6, 4, 2, 6, 0, 8, 4};
    		
    	str = str.toUpperCase();// 轉換大寫
    	final char[] strArr = str.toCharArray();// 字串轉成char陣列
    	int verifyNum = 0;

    	/* 檢查身分證字號 */
    	if (str.matches("[A-Z]{1}[1-2]{1}[0-9]{8}")) {
    		// 第一碼
    		verifyNum = verifyNum + pidIDInt[Arrays.binarySearch(pidCharArray, strArr[0])];
    		// 第二~九碼
    		for (int i = 1, j = 8; i < 9; i++, j--) {
    			verifyNum += Character.digit(strArr[i], 10) * j;
    		}
    		// 檢查碼
    		verifyNum = (10 - (verifyNum % 10)) % 10;
    		
    		return verifyNum == Character.digit(strArr[9], 10);
    	}

    		/* 檢查統一證(居留證)編號 */
    		verifyNum = 0;
    	if (str.matches("[A-Z]{1}[A-D]{1}[0-9]{8}")) {
    		// 第一碼
    		verifyNum += pidResidentFirstInt[Arrays.binarySearch(pidCharArray, strArr[0])];
    		// 第二碼
    		verifyNum += pidResidentSecondInt[Arrays.binarySearch(pidCharArray, strArr[1])];
    		// 第三~八碼
    		for (int i = 2, j = 7; i < 9; i++, j--) {
    			verifyNum += Character.digit(strArr[i], 10) * j;
    		}
    		// 檢查碼
    		verifyNum = (10 - (verifyNum % 10)) % 10;
    		
    		return verifyNum == Character.digit(strArr[9], 10);
    	}
    	
    	return false;
    }
}
