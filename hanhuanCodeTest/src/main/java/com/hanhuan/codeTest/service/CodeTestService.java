package com.hanhuan.codeTest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hanhuan.codeTest.constant.UnitEnum;
import com.hanhuan.codeTest.dto.Ingredient;
import com.hanhuan.codeTest.dto.Item;
import com.hanhuan.codeTest.dto.Recipe;
/**
 * @author hanhuan@cn.ibm.com
 * @version 1.0
 * @date 2015/3/29
 * 
 */
@Service
public class CodeTestService {
	/* 
	 *    analysis of recipes
	 */
	public String analysis(Map<String, Item> itemMap, List<Recipe> recipes)
			throws ParseException {
		String result = "Order Takeout";
		Map<Long, Object> resultMap = new HashMap<Long, Object>();
		for (Recipe recipe : recipes) {
			Map<String, Object> recipeMap = analysisRecipes(itemMap, recipe);
			if ("1".equals(recipeMap.get("status"))) {
				Long minTimes = (Long) recipeMap.get("minTimes");
				resultMap.put(minTimes, recipe.getName());
			}

		}
		if (0 != resultMap.size()) {
			Set<Long> set = resultMap.keySet();
			Iterator<Long> it = set.iterator();
			while (it.hasNext()) {
				Long ky = it.next();
				result = resultMap.get(ky).toString();
				break;

			}

		}

		return result;
	}

	/*
	 * Judge can be used recipe, getting the closest use-by item
	 */
	public Map<String, Object> analysisRecipes(Map<String, Item> itemMap,
			Recipe recipe) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Ingredient> ingredients = recipe.getIngredients();
		String name = recipe.getName();
		boolean re = true;
		long minTimes = Long.MAX_VALUE;
		for (Ingredient ingredient : ingredients) {
			Item item = itemMap.get(ingredient.getItem());
			Map<String, Object> ingredientMap = analysis(item, ingredient);
			if ("0".equals(ingredientMap.get("status"))) {
				re = false;
				break;
			} else {
				long mint = (Long) ingredientMap.get("minTimes");
				if (mint <= minTimes) {
					minTimes = mint;
				}
			}

		}
		if (re) {
			resultMap.put("status", "1");
			resultMap.put("minTimes", minTimes);
		} else {
			resultMap.put("status", "0");
		}

		return resultMap;
	}
	
	
	/*
	 *  in case there is existing ingredient, obtain the Date 
	 */
	public Map<String, Object> analysis(Item item, Ingredient ingredient)
			throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String todayString = sdf.format(today);
		Date toDay = sdf.parse(todayString);
		int amount = item.getAmount();
		UnitEnum unit = item.getUnit();
		Date expireDate = item.getExpireDate();

		String amountIngredient = ingredient.getAmount();
		String unitIngredient = ingredient.getUnit();

		long minTimes = expireDate.getTime() - toDay.getTime();
		if (null != item && amount >= Integer.parseInt(amountIngredient)
				&& unit.toString().equals(unitIngredient) && minTimes >= 0) {
			resultMap.put("status", "1");
			resultMap.put("minTimes", minTimes);
		} else {
			resultMap.put("status", "0");
		}

		return resultMap;
	}
	
}
