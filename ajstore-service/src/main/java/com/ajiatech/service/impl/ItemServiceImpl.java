package com.ajiatech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajiatech.common.utils.JsonUtils;
import com.ajiatech.mapper.AjiaItemDescMapper;
import com.ajiatech.mapper.AjiaItemMapper;
import com.ajiatech.mapper.AjiaItemParamItemMapper;
import com.ajiatech.mapper.AjiaItemParamMapper;
import com.ajiatech.pojo.AjiaItem;
import com.ajiatech.pojo.AjiaItemDesc;
import com.ajiatech.pojo.AjiaItemParam;
import com.ajiatech.pojo.AjiaItemParamItem;
import com.ajiatech.pojo.AjiaItemParamItemExample;
import com.ajiatech.pojo.AjiaItemParamItemExample.Criteria;
import com.ajiatech.pojo.itemParam.AjiaItemParamData;
import com.ajiatech.pojo.itemParam.Params;
import com.ajiatech.service.ItemService;

//加上@service 在controller 中可以用自动注入@autowired得到对象，不用写new
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	AjiaItemParamItemMapper mapper;
	
	@Autowired
	AjiaItemMapper ajiaItemMapper;
	
	@Autowired
	AjiaItemDescMapper ajiaItemDescMapper;

	@Autowired
	AjiaItemParamMapper ajiaItemParamMapper;
	@Override
	public AjiaItemParamItem selectByItemId(long itemId) throws Exception {
		// 得example
		AjiaItemParamItemExample example = new AjiaItemParamItemExample();
		// 设置查询条件
		Criteria criteria = example.or();
		//where item_id=10000028;
		//.and列名关系
		criteria.andItemIdEqualTo(itemId);
		// 如果列的类型是text，必须用WithBLOBs
		List<AjiaItemParamItem> list = mapper.selectByExampleWithBLOBs(example);
		// 把list中数据返回
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}

		return null;
	}



	@Override
	public AjiaItem selectItemById(long itemId) throws Exception {
		
		return ajiaItemMapper.selectByPrimaryKey(itemId);
	}



	@Override
	public AjiaItemDesc selectDescById(long itemId) throws Exception {
		// TODO Auto-generated method stub
		return ajiaItemDescMapper.selectByPrimaryKey(itemId);
	}



	@Override
	public AjiaItemParam selectParamById(long itemParamId) throws Exception {
		
		return ajiaItemParamMapper.selectByPrimaryKey(itemParamId);
	}



	@Override
	public long selectItemByparams
	(long itemParamsId, String color, String model) throws Exception {
		
		//得到exmaple
		AjiaItemParamItemExample example=new AjiaItemParamItemExample();
		//设置查询条件itemParamaId=111
		
		Criteria criteria=example.or();
		criteria.andItemParamIdEqualTo(itemParamsId);
		//查询同类类型的商品
		List<AjiaItemParamItem> list=mapper.selectByExampleWithBLOBs(example);
		if (list!=null && list.size()>=1)
		{		
		  for(AjiaItemParamItem ajiaItemParamItem:list){
			  
			  //判断每个商品的参数与color,model是一样
			  String paramData=ajiaItemParamItem.getParamData();
			  //把paramData转成list
			  List<AjiaItemParamData> paramList=JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
			 //isEqual[0]=true 颜色一样
			  //isEqual[1]=true 型号一样
			  boolean[] isEqual=new boolean[2];
			  //int count=0;
			  for (Params params:paramList.get(0).getParams()){
				  
				  //判断颜色是否一样
				  if ("颜色".equals(params.getKey())){
					  if (params.getValues().get(0).equals(color))
					  {
						  //颜色是一样
						  isEqual[0]=true;
					  }
				  }
				  //判断型号是否一样
				  if ("型号".equals(params.getKey()))
				  {
					  if (params.getValues().get(0).equals(model))
					  {
						 // 型号是一样
						  isEqual[1]=true;
					  }
				  }
				  
			  }
			  //判断颜色和型号是否都一样
			  if (isEqual[0]&& isEqual[1]){
				  return ajiaItemParamItem.getItemId();
			  }
		  }
			
		}
		
		return 0;
	}

}
