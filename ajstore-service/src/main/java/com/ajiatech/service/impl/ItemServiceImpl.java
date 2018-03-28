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

//����@service ��controller �п������Զ�ע��@autowired�õ����󣬲���дnew
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
		// ��example
		AjiaItemParamItemExample example = new AjiaItemParamItemExample();
		// ���ò�ѯ����
		Criteria criteria = example.or();
		//where item_id=10000028;
		//.and������ϵ
		criteria.andItemIdEqualTo(itemId);
		// ����е�������text��������WithBLOBs
		List<AjiaItemParamItem> list = mapper.selectByExampleWithBLOBs(example);
		// ��list�����ݷ���
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
		
		//�õ�exmaple
		AjiaItemParamItemExample example=new AjiaItemParamItemExample();
		//���ò�ѯ����itemParamaId=111
		
		Criteria criteria=example.or();
		criteria.andItemParamIdEqualTo(itemParamsId);
		//��ѯͬ�����͵���Ʒ
		List<AjiaItemParamItem> list=mapper.selectByExampleWithBLOBs(example);
		if (list!=null && list.size()>=1)
		{		
		  for(AjiaItemParamItem ajiaItemParamItem:list){
			  
			  //�ж�ÿ����Ʒ�Ĳ�����color,model��һ��
			  String paramData=ajiaItemParamItem.getParamData();
			  //��paramDataת��list
			  List<AjiaItemParamData> paramList=JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
			 //isEqual[0]=true ��ɫһ��
			  //isEqual[1]=true �ͺ�һ��
			  boolean[] isEqual=new boolean[2];
			  //int count=0;
			  for (Params params:paramList.get(0).getParams()){
				  
				  //�ж���ɫ�Ƿ�һ��
				  if ("��ɫ".equals(params.getKey())){
					  if (params.getValues().get(0).equals(color))
					  {
						  //��ɫ��һ��
						  isEqual[0]=true;
					  }
				  }
				  //�ж��ͺ��Ƿ�һ��
				  if ("�ͺ�".equals(params.getKey()))
				  {
					  if (params.getValues().get(0).equals(model))
					  {
						 // �ͺ���һ��
						  isEqual[1]=true;
					  }
				  }
				  
			  }
			  //�ж���ɫ���ͺ��Ƿ�һ��
			  if (isEqual[0]&& isEqual[1]){
				  return ajiaItemParamItem.getItemId();
			  }
		  }
			
		}
		
		return 0;
	}

}
