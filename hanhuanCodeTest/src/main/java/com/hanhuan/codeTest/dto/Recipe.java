package com.hanhuan.codeTest.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * @author hanhuan@cn.ibm.com
 * @version 1.0
 * @date 2015/3/29
 * 
 */
public class Recipe {
	private String name;
	private List<Ingredient> ingredients = new ArrayList<Ingredient>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
