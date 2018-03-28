package com.ajiatech.controller;
/**
 * 处理商品
 * @author java
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ajiatech.common.utils.JsonUtils;
import com.ajiatech.pojo.AjiaItem;
import com.ajiatech.pojo.AjiaItemDesc;
import com.ajiatech.pojo.AjiaItemParam;
import com.ajiatech.pojo.AjiaItemParamItem;
import com.ajiatech.pojo.AjiaResult;
import com.ajiatech.pojo.itemParam.AjiaItemParamData;
import com.ajiatech.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/getItemInfo.html")
	@ResponseBody
	public AjiaResult getItemInfo(long itemParamId,String color,String model) throws Exception{
		
		//去service取参数
		long itemId=itemService.selectItemByparams(itemParamId, color, model);
		if (itemId>=1)
		{
			//status:200
			//msg:ok
			//data:itemid
			return AjiaResult.ok(itemId);
		}
		return new AjiaResult(500, "失败", null);
	}
	
	// /toItemInfo/100000021.html
	@RequestMapping("/toItemInfo/{itemId}.html")
	public ModelAndView toItemInfo(@PathVariable("itemId") long itemId) throws Exception {
		// 转发到商品详情页 /web-inf/jsp/product-details.jsp
		ModelAndView modelAndView = new ModelAndView();
		
		//从service中取数据
		AjiaItemParamItem itemInfo=itemService.selectByItemId(itemId);
		modelAndView.addObject("itemId", itemInfo.getItemId());
		//取paramdata列的数据
		String paramData =itemInfo.getParamData();
		
		//把json转成list
		List<AjiaItemParamData> list=JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
		
		//把对象放在modelandview中
		modelAndView.addObject("list",list);
		
		//调service取title
		AjiaItem ajiaItem=itemService.selectItemById(itemId);
		modelAndView.addObject("ajiaItem", ajiaItem);
		
		//调service取类型的参数数据
		AjiaItemParam ajiaItemParam=itemService.selectParamById(itemInfo.getItemParamId());
		String json=ajiaItemParam.getParamData();
		List<AjiaItemParamData> list2=JsonUtils.jsonToList(json, AjiaItemParamData.class);
		//把list2中一个对象放到modelAndView中
		if (list2!=null && list2.size()>=1)
		{
			AjiaItemParamData typeParamData=list2.get(0);
			modelAndView.addObject("typeParamData",typeParamData);
		}
		
		
		
		//取商品描述图片
		AjiaItemDesc ajiaItemDesc=itemService.selectDescById(itemId);
		modelAndView.addObject("ajiaItemDesc",ajiaItemDesc);
		
		modelAndView.setViewName("product_details");
		//加个测试代码，后面要删除
		//long itemId2=itemService.selectItemByparams(111, "银色", "i7 250G固态硬盘");
		
		//把商品所在的类型参数id保存到jsp中
		modelAndView.addObject("itemParamId", itemInfo.getItemParamId());
		return modelAndView;
	}
}
