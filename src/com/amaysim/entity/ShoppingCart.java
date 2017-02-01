package com.amaysim.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
	
	private List<Item> items = new ArrayList<Item>();
	private List<PricingRule> pricingRules;
	
	public ShoppingCart(List<PricingRule> pricingRules) {
		super();
		this.pricingRules = pricingRules;
	}
	
	public void add(Item item){
		this.getItems().add(item);
		
	}
	
	public void add(Item item,String promoCode){
		item.setPromoCode(promoCode);
		item.setPromo(true);
		this.getItems().add(item);
		
		if(PricingRule.PROMO_DATA_PCK_FREE.equals(promoCode)){
			Item oneGB = new Item("1gb");
			oneGB.setFree(true);
			getItems().add(oneGB);//additional 1gb data pack item
		}
		
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	private BigDecimal getItemPrice(Item item){
		BigDecimal price = BigDecimal.ZERO;
		for(PricingRule rule:pricingRules){
			if(item.getProductCode().equals(rule.getProductCode())){
				price = rule.getPrice();
			}
		}
		return price;
	}
	
	private PricingRule getPricingRule(String promoCode){
		for(PricingRule rule:pricingRules){
			if(rule.isPromo() && rule.getPromoCode().equals(promoCode)){
				return rule;
			}
		}
		return new PricingRule();
	}
	private BigDecimal getRegularItemTotalPrice(){
		BigDecimal total = BigDecimal.ZERO;
		for(Item item:getItems()){
				if((!item.isPromo() || item.getPromoCode().equals(PricingRule.PROMO_I_HEART_AMAYSIM))&&!item.isFree()){
					total = total.add(getItemPrice(item));
				}
		}
		return total;
	}
	
	private List<Item> getPromoItems(String promoCode) {
		List<Item> promoItemList = new ArrayList<Item>();
		for(Item item:getItems()){
			if(item.isPromo() && item.getPromoCode().equals(promoCode)){
				promoItemList.add(item);
			}
			
		}
		return promoItemList;
	}
	
	private String[] getAvailedPromos(){
		String[] promosAvailed = new String[pricingRules.size()];
		int i = 0;
		for(Item item:getItems()){
			if(item.isPromo()&& !Arrays.asList(promosAvailed).contains(item.getPromoCode())){
				promosAvailed[i] = item.getPromoCode();
				i++;
			}
		}
		return promosAvailed;
	}
	
	
	
	private BigDecimal getTotalPrice(){
		BigDecimal totalPrice = BigDecimal.ZERO;
		//get exisitng promos availed
		String[] availedPromos = getAvailedPromos();
		for(int i=0;i<availedPromos.length;i++){
			String promoCode = availedPromos[i];
			if(promoCode !=null){
				if(promoCode.equals(PricingRule.PROMO_3FOR2)){
					totalPrice = totalPrice.add(get3For2PromoTotalPrice());
				}else if(promoCode.equals(PricingRule.PROMO_BULK_DISCOUNT)){
					totalPrice = totalPrice.add(getBulkDiscountPromoTotalPrice());
				}else if(promoCode.equals(PricingRule.PROMO_DATA_PCK_FREE)){
					totalPrice = totalPrice.add(getDataPackFreePromoTotalPrice());
				}
			}	
		}
		totalPrice = totalPrice.add(getRegularItemTotalPrice());//items without promocode
		
		if(Arrays.asList(availedPromos).contains(PricingRule.PROMO_I_HEART_AMAYSIM)){
			PricingRule rule = getPricingRule(PricingRule.PROMO_I_HEART_AMAYSIM);
			BigDecimal discount = totalPrice.multiply(rule.getPriceAdjustment());
			totalPrice = totalPrice.subtract(discount);
		}
		return totalPrice;
	}
	private BigDecimal get3For2PromoTotalPrice(){
		PricingRule rule = getPricingRule(PricingRule.PROMO_3FOR2);
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal subtotal = BigDecimal.ZERO;
		List<Item> promoItems = getPromoItems(rule.getPromoCode());
		for(int i=1; i<=promoItems.size();i++){
			subtotal = subtotal.add(rule.getPrice());
			if(i%rule.getItemCountRequired() == 0){
				BigDecimal adjustedPrice = subtotal.multiply(rule.getPriceAdjustment());
				total = total.add(adjustedPrice);
				subtotal = BigDecimal.ZERO;
			}
		}
		int remainder = promoItems.size()%rule.getItemCountRequired();
		BigDecimal remainingPrice = rule.getPrice().multiply(new BigDecimal(remainder));
		total = total.add(remainingPrice);
		return total;
	} 
	
	private BigDecimal getBulkDiscountPromoTotalPrice(){
		PricingRule rule = getPricingRule(PricingRule.PROMO_BULK_DISCOUNT);
		BigDecimal newPrice = rule.getPrice(); 
		if(getPromoItems(rule.getPromoCode()).size()>rule.getItemCountRequired()){
			newPrice = rule.getPrice().multiply(rule.getPriceAdjustment());
		}
		
		BigDecimal total = BigDecimal.ZERO;
		for(int i=1; i<=getPromoItems(rule.getPromoCode()).size();i++){
			total = total.add(newPrice);
		}
		
		return total;
	} 
	
	private BigDecimal getDataPackFreePromoTotalPrice(){
		PricingRule rule = getPricingRule(PricingRule.PROMO_DATA_PCK_FREE);
		
		BigDecimal total = BigDecimal.ZERO;
		for(int i=1; i<=getPromoItems(rule.getPromoCode()).size();i++){
			total = total.add(rule.getPrice());
		}
		return total;
	}
	private List<String> getDistinctProductCodes(List<Item> items){
		List<String> productCodes = new ArrayList<String>();
		for(Item item: getItems()){
			int occurrences = Collections.frequency(productCodes, item.getProductCode());
			if(occurrences <1){
				productCodes.add(item.getProductCode());
			}
		}
		return productCodes;
	}
	
	private List<String> getItemProductCodes(List<Item> items){
		List<String> productCodes = new ArrayList<String>();
		for(Item item: getItems()){
			productCodes.add(item.getProductCode());
		}
		return productCodes;
	}
	
	public void count(){
		List<String> distinctProductCodes = getDistinctProductCodes(getItems()); 
		List<String> itemProductCodes = getItemProductCodes(getItems());
		for(String productCode : distinctProductCodes){
			int occurrences = Collections.frequency(itemProductCodes, productCode);
			System.out.println(occurrences+"  "+ProductRef.getProductName(productCode));
		}
	}
	public void total(){
		System.out.println(getTotalPrice());
	}
}
