package com.hsuforum.eas;

import java.io.Serializable;

import lombok.Data;
/**
 * Default setting class
 * @author Marvin
 */
@Data
public class DefaultSetting implements Serializable{ 

    private static final long serialVersionUID = 8322268167179906456L;

    private int dataTableRows;
    private String mailFrom;
	private Boolean devMode=true;
	private String rowsOfPerPage;
	private String timeZone;
	private String secret;
    
}