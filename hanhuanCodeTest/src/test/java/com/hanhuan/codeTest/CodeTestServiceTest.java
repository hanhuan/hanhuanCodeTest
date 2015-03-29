package com.hanhuan.codeTest;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.hanhuan.codeTest.dto.Fridge;
import com.hanhuan.codeTest.dto.Item;
import com.hanhuan.codeTest.dto.Recipe;
import com.hanhuan.codeTest.service.CodeTestService;
import com.hanhuan.codeTest.utils.GsonUtil;
import com.hanhuan.codeTest.utils.ServiceLocalUtil;

public class CodeTestServiceTest extends TestCase   {
	private CodeTestService codeTestService = null;
	private Fridge fridge = null;
	public static final String[] SERVICE_FACTORY_PATH = new String[] { "/META-INF/spring/servlet-context.xml" };

	@Before
	public void setUp() {
		ApplicationContext ctx = ServiceLocalUtil
				.getContext(SERVICE_FACTORY_PATH);
		codeTestService = (CodeTestService) ctx.getBean("codeTestService");
		fridge = (Fridge) ctx.getBean("fridge");
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testExist() {
		String recipe = "[{'name':'grilledcheeseontoast','ingredients':[{'item':'bread','amount':'2','unit':'slices'},{'item':'cheese','amount':'2','unit':'slices'}]},{'name':'saladsandwich','ingredients':[{'item':'bread','amount':'2','unit':'slices'},{'item':'mixedsalad','amount':'100','unit':'grams'}]}]";
		String fridgeString = "[{'name':'bread','amount':10,'unit':'slices','expireDate':'25/12/2015'},{'name':'cheese','amount':10,'unit':'slices','expireDate':'25/12/2014'},{'name':'butter','amount':250,'unit':'grams','expireDate':'25/12/2015'},{'name':'peanutbutter','amount':250,'unit':'grams','expireDate':'02/12/2015'},{'name':'mixedsalad','amount':150,'unit':'grams','expireDate':'26/12/2015'}]";
		List<Recipe> recipes = GsonUtil.toList(recipe);

		fridge.setItemList(GsonUtil.toFridge(fridgeString));
		Map<String, Item> itemMap = Fridge2Map(fridge);
		try {
			String result = codeTestService.analysis(itemMap, recipes);
			assertEquals(result, "saladsandwich");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testNOExist() {
		String recipe = "[{'name':'grilledcheeseontoast','ingredients':[{'item':'bread','amount':'2','unit':'slices'},{'item':'cheese','amount':'2','unit':'slices'}]},{'name':'saladsandwich','ingredients':[{'item':'bread','amount':'2','unit':'slices'},{'item':'mixedsalad','amount':'100','unit':'grams'}]}]";
		String fridgeString = "[{'name':'bread','amount':10,'unit':'slices','expireDate':'25/12/2015'},{'name':'cheese','amount':10,'unit':'slices','expireDate':'25/12/2014'},{'name':'butter','amount':250,'unit':'grams','expireDate':'25/12/2015'},{'name':'peanutbutter','amount':250,'unit':'grams','expireDate':'02/12/2013'},{'name':'mixedsalad','amount':150,'unit':'grams','expireDate':'26/12/2013'}]";
		List<Recipe> recipes = GsonUtil.toList(recipe);

		fridge.setItemList(GsonUtil.toFridge(fridgeString));
		Map<String, Item> itemMap = Fridge2Map(fridge);
		try {
			String result = codeTestService.analysis(itemMap, recipes);
			assertEquals(result, "Order TakeOut");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Item> Fridge2Map(Fridge fridge) {
		Map<String, Item> map = new HashMap<String, Item>();
		for (Item item : fridge.getItemList()) {
			map.put(item.getName(), item);
		}
		return map;
	}
}
