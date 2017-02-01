package com.amaysim.entity;

public enum ProductRef {
	
	ULT_SMALL("ult_small","Unlimited 1GB"),
	ULT_MEDIUM("ult_medium","Unlimited 2GB"),
	ULT_LARGE("ult_large","Unlimited 5GB"),
	ONE_GB("1gb","Unlimited 1GB");
	
	private String productCode;
	private String productName;
	
	private ProductRef(String productCode, String productName) {
		this.productCode = productCode;
		this.productName = productName;
	}

	public static String getProductName(String productCode){
		for (ProductRef p : ProductRef.values()) {
		    if(p.productCode.equals(productCode)){
		    	return p.productName;
		    }
		}
		return "";
	}
	

}
