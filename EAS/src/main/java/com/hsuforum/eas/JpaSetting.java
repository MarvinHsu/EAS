package com.hsuforum.eas;

import java.io.Serializable;

import lombok.Data;

@Data
public class JpaSetting  implements Serializable{ 
	private static final long serialVersionUID = 2349539934240258333L;
	private String physicalStrategy;
	private String ddlAuto;
	private Boolean showSql;
	private String entityCopyObserver;
	private String formatSql;
	private String dialect;
}
