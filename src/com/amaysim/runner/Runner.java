package com.amaysim.runner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.amaysim.entity.Item;
import com.amaysim.entity.PricingRule;
import com.amaysim.entity.ShoppingCart;

public class Runner {

	public static void main(String[] args) {
		List<PricingRule> pricingRules = new ArrayList<PricingRule>();
		
		//regular
		PricingRule ultSmallPrice = new PricingRule(PricingRule.PROD_ULT_SMALL, new BigDecimal("24.90"),false); 
		PricingRule ultMediumPrice = new PricingRule(PricingRule.PROD_ULT_MEDIUM, new BigDecimal("29.90"),false);
		PricingRule ultLargePrice = new PricingRule(PricingRule.PROD_ULT_LARGE, new BigDecimal("44.90"),false);
		PricingRule oneGBPrice = new PricingRule(PricingRule.PROD_1GB, new BigDecimal("9.90"),false);
		pricingRules.add(ultSmallPrice);
		pricingRules.add(ultMediumPrice);
		pricingRules.add(ultLargePrice);
		pricingRules.add(oneGBPrice);
		//promos
		PricingRule threeForTwo = new PricingRule(PricingRule.PROD_ULT_SMALL, new BigDecimal("24.90"), true, PricingRule.PROMO_3FOR2, new BigDecimal("0.666667") ,3);
		pricingRules.add(threeForTwo);
		PricingRule bulkDiscount = new PricingRule(PricingRule.PROD_ULT_LARGE, new BigDecimal("44.90"), true, PricingRule.PROMO_BULK_DISCOUNT, new BigDecimal("0.888641") ,3);
		pricingRules.add(bulkDiscount);
		PricingRule dataPackFree = new PricingRule(PricingRule.PROD_ULT_MEDIUM, new BigDecimal("29.90"), true, PricingRule.PROMO_DATA_PCK_FREE, BigDecimal.ZERO ,1);
		pricingRules.add(dataPackFree);
		PricingRule iHeartAmaysim = new PricingRule("", BigDecimal.ZERO, true, PricingRule.PROMO_I_HEART_AMAYSIM, new BigDecimal("0.10")  ,0);
		pricingRules.add(iHeartAmaysim);
		
		
		
		
		//scenario 1 [
		System.out.println("----------- scenario 1--------------");
		ShoppingCart cart1 = new ShoppingCart(pricingRules);
		Item scn1Small1 = new Item(PricingRule.PROD_ULT_SMALL);
		cart1.add(scn1Small1,PricingRule.PROMO_3FOR2);
		Item scn1Small2 = new Item(PricingRule.PROD_ULT_SMALL);
		cart1.add(scn1Small2,PricingRule.PROMO_3FOR2);
		Item scn1Small3 = new Item(PricingRule.PROD_ULT_SMALL);
		cart1.add(scn1Small3,PricingRule.PROMO_3FOR2);
		Item scn1Large1 = new Item(PricingRule.PROD_ULT_LARGE);
		cart1.add(scn1Large1);
		cart1.count();
		cart1.total();
		
		//]
		
		
		//scenario 2 [
		System.out.println("----------- scenario 2--------------");
		ShoppingCart cart2 = new ShoppingCart(pricingRules);
		Item scn2Small1 = new Item(PricingRule.PROD_ULT_SMALL);
		cart2.add(scn2Small1);
		Item scn2Small2 = new Item(PricingRule.PROD_ULT_SMALL);
		cart2.add(scn2Small2);
		Item scn2Large1 = new Item(PricingRule.PROD_ULT_LARGE);
		cart2.add(scn2Large1,PricingRule.PROMO_BULK_DISCOUNT);
		Item scn2Large2 = new Item(PricingRule.PROD_ULT_LARGE);
		cart2.add(scn2Large2,PricingRule.PROMO_BULK_DISCOUNT);
		Item scn2Large3 = new Item(PricingRule.PROD_ULT_LARGE);
		cart2.add(scn2Large3,PricingRule.PROMO_BULK_DISCOUNT);
		Item scn2Large4 = new Item(PricingRule.PROD_ULT_LARGE);
		cart2.add(scn2Large4,PricingRule.PROMO_BULK_DISCOUNT);
		cart2.count();
		cart2.total();
		
		//]
		
		//scenario 3 [
		System.out.println("----------- scenario 3--------------");
		ShoppingCart cart3 = new ShoppingCart(pricingRules);
		Item scn3Small1 = new Item(PricingRule.PROD_ULT_SMALL);
		cart3.add(scn3Small1);
		Item scn3Medium1 = new Item(PricingRule.PROD_ULT_MEDIUM);
		cart3.add(scn3Medium1,PricingRule.PROMO_DATA_PCK_FREE);
		Item scn3Medium2 = new Item(PricingRule.PROD_ULT_MEDIUM);
		cart3.add(scn3Medium2,PricingRule.PROMO_DATA_PCK_FREE);
		cart3.count();
		cart3.total();
		//]
		
		//scenario 3 [
		System.out.println("----------- scenario 4--------------");
		ShoppingCart cart4 = new ShoppingCart(pricingRules);
		Item scn4Small1 = new Item(PricingRule.PROD_ULT_SMALL);
		cart4.add(scn4Small1);
		Item scn4OneGB1 = new Item(PricingRule.PROD_1GB);
		cart4.add(scn4OneGB1,PricingRule.PROMO_I_HEART_AMAYSIM);
		cart4.count();
		cart4.total();
		//]

	}

}
