package com.hanhuan.codeTest.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
/**
 * @author hanhuan@cn.ibm.com
 * @version 1.0
 * @date 2015/3/29
 * 
 */
@Component
public class Fridge {
	private List<Item> itemList = new ArrayList<Item>(0);

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

}
