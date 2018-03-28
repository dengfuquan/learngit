package com.ajiatech.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajiatech.common.utils.JsonUtils;
import com.ajiatech.mapper.AjiaCartItemMapper;
import com.ajiatech.mapper.AjiaItemMapper;
import com.ajiatech.mapper.AjiaItemParamItemMapper;
import com.ajiatech.mapper.AjiaShippingMapper;
import com.ajiatech.pojo.AjiaCartItem;
import com.ajiatech.pojo.AjiaCartItemExample;
import com.ajiatech.pojo.AjiaCartItemExample.Criteria;
import com.ajiatech.pojo.AjiaCartItemVo;
import com.ajiatech.pojo.AjiaItem;
import com.ajiatech.pojo.AjiaItemParamItem;
import com.ajiatech.pojo.AjiaItemParamItemExample;
import com.ajiatech.pojo.AjiaShipping;
import com.ajiatech.pojo.AjiaShippingExample;
import com.ajiatech.pojo.itemParam.AjiaItemParamData;
import com.ajiatech.pojo.itemParam.Params;
import com.ajiatech.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	AjiaCartItemMapper ajiaCartItemMapper;

	@Autowired
	AjiaItemMapper ajiaItemMapper;

	@Autowired
	AjiaItemParamItemMapper ajiaItemParamItemMapper;

	@Autowired
	AjiaShippingMapper ajiaShippingMapper;

	@Override
	public boolean insert(AjiaCartItem ajiaCartItem) throws Exception {

		// where item_id=10000028 and user_id=14 and status=1
		// ����example
		AjiaCartItemExample example = new AjiaCartItemExample();
		// ������������
		Criteria criteria = example.or();
		criteria.andItemIdEqualTo(ajiaCartItem.getItemId());
		criteria.andUserIdEqualTo(ajiaCartItem.getUserId());
		criteria.andStatusEqualTo(1);
		// �Ȳ�ѯ����û����ӹ�
		List<AjiaCartItem> list = ajiaCartItemMapper.selectByExample(example);
		int row = 0;
		if (list.size() == 0) {
			// û��ӹ���ִ��insert
			row = ajiaCartItemMapper.insert(ajiaCartItem);
		} else if (list.size() >= 1) {

			// ��ӹ���ִ��update
			// update ajia_cart_item set num=num+1 where user_id=14 and
			// item_id=100028 and status=1
			AjiaCartItem ajiaCartItem2 = list.get(0);
			ajiaCartItem2.setNum(ajiaCartItem2.getNum() + ajiaCartItem.getNum());

			row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem2, example);
		}
		return row >= 1 ? true : false;
	}

	@Override
	public List<AjiaCartItemVo> selectByUserId(long userId) throws Exception {

		List<AjiaCartItemVo> voList = new ArrayList<>();
		// ��ajia_cart_item��ѯ�û����ﳵ��������Ʒ
		// ��ѯ������where user_id=14 and status=1 order by created desc
		AjiaCartItemExample cartItemExample = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = cartItemExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);
		cartItemExample.setOrderByClause("created desc");
		List<AjiaCartItem> cartItemList = ajiaCartItemMapper.selectByExample(cartItemExample);

		for (AjiaCartItem ajiaCartItem : cartItemList) {
			AjiaCartItemVo vo = new AjiaCartItemVo();
			voList.add(vo);
			vo.setAjiaCartItem(ajiaCartItem);
			// ��ajia_item��ѯ��Ʒ��title,price
			long itemId = ajiaCartItem.getItemId();
			AjiaItem ajiaItem = ajiaItemMapper.selectByPrimaryKey(itemId);
			vo.setAjiaItem(ajiaItem);
			// ��ajia_item_param_item ��ѯparamData
			// List<Params> paramList=new ArrayList<>();
			// vo.setParamsList(paramList);
			try {
				AjiaItemParamItemExample paramItemExample = new AjiaItemParamItemExample();
				AjiaItemParamItemExample.Criteria criteria2 = paramItemExample.or();
				criteria2.andItemIdEqualTo(itemId);
				List<AjiaItemParamItem> paramItemList = ajiaItemParamItemMapper
						.selectByExampleWithBLOBs(paramItemExample);
				if (paramItemList != null && paramItemList.size() >= 1) {
					AjiaItemParamItem ajiaItemParamItem = paramItemList.get(0);
					String paramData = ajiaItemParamItem.getParamData();
					List<AjiaItemParamData> paramDataList = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
					AjiaItemParamData ajiaItemParamData = paramDataList.get(0);
					vo.setParamsList(ajiaItemParamData.getParams());
				}
			} catch (Exception e) {
				e.printStackTrace();
				vo.setParamsList(new ArrayList<>());
			}
		}

		return voList;
	}

	@Override
	public List<AjiaCartItemVo> selectByItemIds(List<Long> idList, long userId) throws Exception {

		List<AjiaCartItemVo> voList = new ArrayList<>();
		// ��ajia_cart_item��ѯ�û����ﳵ��������Ʒ
		// ��ѯ������where user_id=14 and status=1 order by created desc
		AjiaCartItemExample cartItemExample = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = cartItemExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);
		criteria.andItemIdIn(idList);
		cartItemExample.setOrderByClause("created desc");
		List<AjiaCartItem> cartItemList = ajiaCartItemMapper.selectByExample(cartItemExample);

		for (AjiaCartItem ajiaCartItem : cartItemList) {
			AjiaCartItemVo vo = new AjiaCartItemVo();
			voList.add(vo);
			vo.setAjiaCartItem(ajiaCartItem);
			// ��ajia_item��ѯ��Ʒ��title,price
			long itemId = ajiaCartItem.getItemId();
			AjiaItem ajiaItem = ajiaItemMapper.selectByPrimaryKey(itemId);
			vo.setAjiaItem(ajiaItem);
			// ��ajia_item_param_item ��ѯparamData
			// List<Params> paramList=new ArrayList<>();
			// vo.setParamsList(paramList);
			try {
				AjiaItemParamItemExample paramItemExample = new AjiaItemParamItemExample();
				AjiaItemParamItemExample.Criteria criteria2 = paramItemExample.or();
				criteria2.andItemIdEqualTo(itemId);
				List<AjiaItemParamItem> paramItemList = ajiaItemParamItemMapper
						.selectByExampleWithBLOBs(paramItemExample);
				if (paramItemList != null && paramItemList.size() >= 1) {
					AjiaItemParamItem ajiaItemParamItem = paramItemList.get(0);
					String paramData = ajiaItemParamItem.getParamData();
					List<AjiaItemParamData> paramDataList = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
					AjiaItemParamData ajiaItemParamData = paramDataList.get(0);
					vo.setParamsList(ajiaItemParamData.getParams());
				}
			} catch (Exception e) {
				e.printStackTrace();
				vo.setParamsList(new ArrayList<>());
			}
		}

		return voList;
	}

	@Override
	public boolean changeCartNum(AjiaCartItem ajiaCartItem) throws Exception {

		// where user_id=14 and item_id=10000028 and status=1
		AjiaCartItemExample example = new AjiaCartItemExample();
		// Criteria��AjiaCartItemExample�еľ�̬�ڲ���
		AjiaCartItemExample.Criteria criteria = example.or();
		criteria.andItemIdEqualTo(ajiaCartItem.getItemId());
		criteria.andUserIdEqualTo(ajiaCartItem.getUserId());
		criteria.andStatusEqualTo(1);
		int row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem, example);
		if (row >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteById(AjiaCartItem ajiaCartItem) throws Exception {
		// where userId=14 and item_id=28 and status=1
		AjiaCartItemExample example = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = example.or();
		criteria.andItemIdEqualTo(ajiaCartItem.getItemId());
		criteria.andUserIdEqualTo(ajiaCartItem.getUserId());
		criteria.andStatusEqualTo(1);

		// example.or().andItemIdEqualTo(1L);
		// example.or().andStatusEqualTo(1);
		int row = ajiaCartItemMapper.deleteByExample(example);
		if (row >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteByIds(List<Long> idList, long userId) throws Exception {
		// TODO Auto-generated method stub
		// where itemid in(id,id,id)
		AjiaCartItemExample example = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = example.or();
		criteria.andItemIdIn(idList);
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);

		// �� status���ó�2
		// set status=2
		AjiaCartItem ajiaCartItem = new AjiaCartItem();
		ajiaCartItem.setStatus(2);
		int row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem, example);
		if (row >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public AjiaShipping getDefaultAddress(long userId) throws Exception {

		// where userid=14L and is_deault=true and status=1

		AjiaShippingExample example = new AjiaShippingExample();
		AjiaShippingExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDefaultEqualTo(true);
		criteria.andStatusEqualTo((byte) 1);

		List<AjiaShipping> list = ajiaShippingMapper.selectByExample(example);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;

	}

}
