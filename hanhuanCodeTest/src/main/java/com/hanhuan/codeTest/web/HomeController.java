package com.hanhuan.codeTest.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanhuan.codeTest.dto.Fridge;
import com.hanhuan.codeTest.dto.Item;
import com.hanhuan.codeTest.dto.Recipe;
import com.hanhuan.codeTest.service.CodeTestService;
import com.hanhuan.codeTest.utils.GsonUtil;
import com.hanhuan.codeTest.utils.JsonMapper;

/**
 * @author hanhuan@cn.ibm.com
 * @version 1.0
 * @date 2015/3/29
 * 
 */
@Controller
public class HomeController {

	@Autowired
	private CodeTestService codeTestService;

	@Autowired
	private Fridge fridge;

	private static final String DEFAULT_JQUERY_JSONP_CALLBACK_PARM_NAME = "callback";
	private JsonMapper mapper = new JsonMapper();
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws ParseException {
		return "home";
	}

	@RequestMapping(value = "/findRecipes", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findRecipes(
			@RequestParam(DEFAULT_JQUERY_JSONP_CALLBACK_PARM_NAME) String callbackName,
			HttpServletRequest request) throws UnsupportedEncodingException,
			ParseException {
		
		// Matching the parameters of front end
		String recipe = request.getParameter("recipe");
		String fridgeString = request.getParameter("fridge");
		List<Recipe> recipes = GsonUtil.toList(recipe);
		
		// invoke the service
		fridge.setItemList(GsonUtil.toFridge(fridgeString));
		Map<String, Item> itemMap = Fridge2Map(fridge);
		String result = codeTestService.analysis(itemMap, recipes);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("result", result);
		
		return mapper.toJsonP(callbackName, jsonMap);
	}

	public Map<String, Item> Fridge2Map(Fridge fridge) {
		Map<String, Item> map = new HashMap<String, Item>();
		for (Item item : fridge.getItemList()) {
			map.put(item.getName(), item);
		}
		return map;
	}

}
