package com.ajiatech.controller;
/**
 * ������Ʒ
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
		
		//ȥserviceȡ����
		long itemId=itemService.selectItemByparams(itemParamId, color, model);
		if (itemId>=1)
		{
			//status:200
			//msg:ok
			//data:itemid
			return AjiaResult.ok(itemId);
		}
		return new AjiaResult(500, "ʧ��", null);
	}
	
	// /toItemInfo/100000021.html
	@RequestMapping("/toItemInfo/{itemId}.html")
	public ModelAndView toItemInfo(@PathVariable("itemId") long itemId) throws Exception {
		// ת������Ʒ����ҳ /web-inf/jsp/product-details.jsp
		ModelAndView modelAndView = new ModelAndView();
		
		//��service��ȡ����
		AjiaItemParamItem itemInfo=itemService.selectByItemId(itemId);
		modelAndView.addObject("itemId", itemInfo.getItemId());
		//ȡparamdata�е�����
		String paramData =itemInfo.getParamData();
		
		//��jsonת��list
		List<AjiaItemParamData> list=JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
		
		//�Ѷ������modelandview��
		modelAndView.addObject("list",list);
		
		//��serviceȡtitle
		AjiaItem ajiaItem=itemService.selectItemById(itemId);
		modelAndView.addObject("ajiaItem", ajiaItem);
		
		//��serviceȡ���͵Ĳ�������
		AjiaItemParam ajiaItemParam=itemService.selectParamById(itemInfo.getItemParamId());
		String json=ajiaItemParam.getParamData();
		List<AjiaItemParamData> list2=JsonUtils.jsonToList(json, AjiaItemParamData.class);
		//��list2��һ������ŵ�modelAndView��
		if (list2!=null && list2.size()>=1)
		{
			AjiaItemParamData typeParamData=list2.get(0);
			modelAndView.addObject("typeParamData",typeParamData);
		}
		
		
		
		//ȡ��Ʒ����ͼƬ
		AjiaItemDesc ajiaItemDesc=itemService.selectDescById(itemId);
		modelAndView.addObject("ajiaItemDesc",ajiaItemDesc);
		
		modelAndView.setViewName("product_details");
		//�Ӹ����Դ��룬����Ҫɾ��
		//long itemId2=itemService.selectItemByparams(111, "��ɫ", "i7 250G��̬Ӳ��");
		
		//����Ʒ���ڵ����Ͳ���id���浽jsp��
		modelAndView.addObject("itemParamId", itemInfo.getItemParamId());
		return modelAndView;
	}
}
