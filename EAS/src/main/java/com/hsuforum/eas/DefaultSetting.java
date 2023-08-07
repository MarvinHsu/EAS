package com.hsuforum.eas;

import java.io.Serializable;

import lombok.Data;

@Data
public class DefaultSetting implements Serializable{ 

    private static final long serialVersionUID = 8322268167179906456L;

    private int dataTableRows;
    private String mailFrom;
	private Boolean devMode=true;
	private Integer rowsOfPerPage;
	private String timeZone;
	private String secret;
    
}