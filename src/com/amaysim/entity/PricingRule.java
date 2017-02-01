package com.amaysim.entity;

import java.math.BigDecimal;

public class PricingRule {
//public enum PricingRule {
	//ult_small ("ult_small", new BigDecimal("24.90"),false), 
	//ult_medium ("ult_medium", new BigDecimal("29.90"),false), 
	//ult_large ("ult_large", new BigDecimal("44.90"),false), 
	//one_gb("1gb", new BigDecimal("9.90"),false);
	public static final String PROD_1GB = "1gb";
	public static final String PROD_ULT_SMALL = "ult_small";
	public static final String PROD_ULT_MEDIUM = "ult_medium";
	public static final String PROD_ULT_LARGE = "ult_large";
	
	public static final String PROMO_3FOR2 = "3_for_2";
	public static final String PROMO_BULK_DISCOUNT = "bulk_discount";
	public static final String PROMO_DATA_PCK_FREE = "data_pack_free";
	public static final String PROMO_I_HEART_AMAYSIM = "I<3AMAYSIM";
	
	
	private String productCode;
	private BigDecimal price;
	private boolean isPromo = false;
	private String promoCode;
	private BigDecimal priceAdjustment; //example 50 percent 10 percent
	private Integer itemCountRequired;//example 3 sims to required for a promo
	
	public PricingRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PricingRule(String productCode, BigDecimal price, boolean isPromo) {
		this.productCode = productCode;
		this.price = price;
		this.isPromo = isPromo;
	}
	public PricingRule(String productCode, BigDecimal price, boolean isPromo, String promoCode, BigDecimal priceAdjustment,
			Integer itemCountRequired) {
		this.productCode = productCode;
		this.price = price;
		this.isPromo = isPromo;
		this.promoCode = promoCode;
		this.priceAdjustment = priceAdjustment;
		this.itemCountRequired = itemCountRequired;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public boolean isPromo() {
		return isPromo;
	}
	public void setPromo(boolean isPromo) {
		this.isPromo = isPromo;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public BigDecimal getPriceAdjustment() {
		return priceAdjustment;
	}
	public void setPriceAdjustment(BigDecimal priceAdjustment) {
		this.priceAdjustment = priceAdjustment;
	}
	public Integer getItemCountRequired() {
		return itemCountRequired;
	}
	public void setItemCountRequired(Integer itemCountRequired) {
		this.itemCountRequired = itemCountRequired;
	}
	
	
	
	
	
	
	

}
