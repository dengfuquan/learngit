package com.ajiatech.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajiatech.common.utils.JsonUtils;
import com.ajiatech.exception.AjstoreException;
import com.ajiatech.mapper.AjiaItemParamItemMapper;
import com.ajiatech.mapper.AjiaItemParamMapper;
import com.ajiatech.mapper.AjiaOrderItemMapper;
import com.ajiatech.mapper.AjiaOrderMapper;
import com.ajiatech.mapper.AjiaShippingMapper;
import com.ajiatech.pojo.AjiaCartItemVo;
import com.ajiatech.pojo.AjiaItemParam;
import com.ajiatech.pojo.AjiaItemParamItem;
import com.ajiatech.pojo.AjiaItemParamItemExample;
import com.ajiatech.pojo.AjiaOrder;
import com.ajiatech.pojo.AjiaOrderExample;
import com.ajiatech.pojo.AjiaOrderItem;
import com.ajiatech.pojo.AjiaOrderItemExample;
import com.ajiatech.pojo.AjiaOrderVo;
import com.ajiatech.pojo.AjiaShipping;
import com.ajiatech.pojo.itemParam.AjiaItemParamData;
import com.ajiatech.pojo.itemParam.Params;
import com.ajiatech.service.CartService;
import com.ajiatech.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	CartService cartService;

	@Autowired
	AjiaOrderMapper ajiaOrderMapper;
	@Autowired
	AjiaOrderItemMapper ajiaOrderItemMapper;

	@Autowired
	AjiaShippingMapper ajiaShippingMapper;

	@Autowired
	AjiaItemParamItemMapper ajiaItemParamItemMapper;

	@Override
	public AjiaOrder saveOrder(Long userId, Long addId, List<Long> itemIdList) throws Exception  {
		// ȡÿ����Ʒ�������ͼ۸�,�����ܼ�
		//try {
			List<AjiaCartItemVo> voList = cartService.selectByItemIds(itemIdList, userId);
			double payment = 0;
			for (AjiaCartItemVo vo : voList) {
				payment = payment + vo.getAjiaCartItem().getNum() * vo.getAjiaItem().getPrice();
			}
			// ���涩��
			AjiaOrder ajiaOrder = new AjiaOrder();
			String orderId = generateId();
			ajiaOrder.setOrderId(orderId);
			ajiaOrder.setUserId(userId);
			ajiaOrder.setStatus(1);// 1���¶��� �󸶿�

			ajiaOrder.setAddId(addId);
			ajiaOrder.setPayment(payment);
			ajiaOrder.setCreateTime(new Date());
			// ��ŵ�ַ��Ϣ
			AjiaShipping ajiaShipping = ajiaShippingMapper.selectByPrimaryKey(addId);
			ajiaOrder.setShippingName(ajiaShipping.getReceiverName());

			ajiaOrderMapper.insert(ajiaOrder);
			for (AjiaCartItemVo vo : voList) {
				// ���涩������
				AjiaOrderItem orderItem = new AjiaOrderItem();
				orderItem.setId(generateId());
				//Ϊ�˲�����������ӳ���
				//orderItem.setId("55");
				orderItem.setItemId(String.valueOf(vo.getAjiaItem().getId()));
				orderItem.setOrderId(orderId);

				orderItem.setNum(vo.getAjiaCartItem().getNum());
				orderItem.setPrice(vo.getAjiaItem().getPrice());
				orderItem.setTotalFee(vo.getAjiaCartItem().getNum() * vo.getAjiaItem().getPrice());
				orderItem.setTitle(vo.getAjiaItem().getTitle());
				orderItem.setPicPath(vo.getAjiaItem().getImage());

				ajiaOrderItemMapper.insert(orderItem);

			}
			return ajiaOrder;
		//} catch (Exception e) {
		//}

		//return null;
	}

	@Override
	public List<AjiaOrderVo> selectByUserIdStatus(Long userId, int status) throws Exception {
		ArrayList<AjiaOrderVo> voList = new ArrayList<>();
		// �����ж���
		AjiaOrderExample orderExample = new AjiaOrderExample();
		AjiaOrderExample.Criteria orderCriteria = orderExample.or();
		orderExample.setOrderByClause("create_time desc");
		orderCriteria.andUserIdEqualTo(userId);
		orderCriteria.andStatusNotEqualTo(9);// 9���Ѿ�ɾ���Ķ���
		// 0�����ж���
		if (status != 0) {
			// ��ѯĳ��״̬�Ķ���
			orderCriteria.andStatusEqualTo(status);
		}
		List<AjiaOrder> orderList = ajiaOrderMapper.selectByExample(orderExample);
		for (AjiaOrder ajiaOrder : orderList) {
			AjiaOrderVo vo = new AjiaOrderVo();
			voList.add(vo);
			vo.setAjiaOrder(ajiaOrder);
			// ��һ�������Ķ�������

			AjiaOrderItemExample orderItemExample = new AjiaOrderItemExample();
			AjiaOrderItemExample.Criteria orderItemCriteria = orderItemExample.or();
			orderItemCriteria.andOrderIdEqualTo(ajiaOrder.getOrderId());
			List<AjiaOrderItem> ajiaOrderItemList = ajiaOrderItemMapper.selectByExample(orderItemExample);
			vo.setAjiaOrderItemList(ajiaOrderItemList);
			// ��ÿ������������Ʒ�Ĳ���
			for (AjiaOrderItem ajiaOrderItem : ajiaOrderItemList) {

				AjiaItemParamItemExample paramItemExample = new AjiaItemParamItemExample();
				AjiaItemParamItemExample.Criteria pCriteria = paramItemExample.or();
				pCriteria.andItemIdEqualTo(Long.parseLong(ajiaOrderItem.getItemId()));

				List<AjiaItemParamItem> pList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(paramItemExample);
				try {
					if (pList != null && pList.size() >= 1) {
						AjiaItemParamItem ajiaItemParamItem = pList.get(0);
						String paramData = ajiaItemParamItem.getParamData();
						List<AjiaItemParamData> list = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
						List<Params> paramsList = list.get(0).getParams();
						ajiaOrderItem.setParamList(paramsList);
					}
				} catch (Exception e) {
					ajiaOrderItem.setParamList(new ArrayList<>());
				}

			}

		}

		return voList;
	}

	public String generateId() {
		// ǰ��6λ������+�����7

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String newDate = simpleDateFormat.format(new Date());
		Random random = new Random();
		int num = random.nextInt(999999) + 1000000;
		String id = newDate + num;
		return id;
	}

	@Override
	public PageInfo<AjiaOrderVo> selectByUserIdStatusForPage(Long userId, int status, int currentPage, int pageSize)
			throws Exception {
		// ��ҳ��һ�����趨ҳ��
		// currentPage:�ڼ�ҳ
		// pagesize:ÿҳ��ʾ����
		PageHelper.startPage(currentPage, pageSize);
		PageHelper.orderBy("create_time desc");
		ArrayList<AjiaOrderVo> voList = new ArrayList<>();
		// �����ж���
		AjiaOrderExample orderExample = new AjiaOrderExample();
		AjiaOrderExample.Criteria orderCriteria = orderExample.or();
		// orderExample.setOrderByClause("create_time desc");
		orderCriteria.andUserIdEqualTo(userId);
		orderCriteria.andStatusNotEqualTo(9);// 9���Ѿ�ɾ���Ķ���
		// 0�����ж���
		if (status != 0) {
			// ��ѯĳ��״̬�Ķ���
			orderCriteria.andStatusEqualTo(status);
		}
		List<AjiaOrder> orderList = ajiaOrderMapper.selectByExample(orderExample);
		// ��ҳ�ڶ�������������������ҳ��=������/pageSize,orderList.size��������
		PageInfo<AjiaOrderVo> pageInfoList = new PageInfo(orderList);
		for (AjiaOrder ajiaOrder : orderList) {
			AjiaOrderVo vo = new AjiaOrderVo();
			voList.add(vo);
			vo.setAjiaOrder(ajiaOrder);
			// ��һ�������Ķ�������

			AjiaOrderItemExample orderItemExample = new AjiaOrderItemExample();
			AjiaOrderItemExample.Criteria orderItemCriteria = orderItemExample.or();
			orderItemCriteria.andOrderIdEqualTo(ajiaOrder.getOrderId());
			List<AjiaOrderItem> ajiaOrderItemList = ajiaOrderItemMapper.selectByExample(orderItemExample);
			vo.setAjiaOrderItemList(ajiaOrderItemList);
			// ��ÿ������������Ʒ�Ĳ���
			for (AjiaOrderItem ajiaOrderItem : ajiaOrderItemList) {

				AjiaItemParamItemExample paramItemExample = new AjiaItemParamItemExample();
				AjiaItemParamItemExample.Criteria pCriteria = paramItemExample.or();
				pCriteria.andItemIdEqualTo(Long.parseLong(ajiaOrderItem.getItemId()));

				List<AjiaItemParamItem> pList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(paramItemExample);
				try {
					if (pList != null && pList.size() >= 1) {
						AjiaItemParamItem ajiaItemParamItem = pList.get(0);
						String paramData = ajiaItemParamItem.getParamData();
						List<AjiaItemParamData> list = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
						List<Params> paramsList = list.get(0).getParams();
						ajiaOrderItem.setParamList(paramsList);
					}
				} catch (Exception e) {
					ajiaOrderItem.setParamList(new ArrayList<>());
				}

			}

		}
		pageInfoList.setList(voList);

		return pageInfoList;
	}

	@Override
	public boolean updateStatusByOrderid(String orderId, int status) throws Exception {

		AjiaOrder ajiaOrder = new AjiaOrder();
		ajiaOrder.setOrderId(orderId);
		ajiaOrder.setStatus(status);
		// update set status=8 where orderId=1
		int row = ajiaOrderMapper.updateByPrimaryKeySelective(ajiaOrder);
		return row >= 1 ? true : false;
	}

	@Override
	public AjiaOrder selectByOrderId(String orderId) throws Exception {

		return ajiaOrderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public boolean deleteByOrderId(String orderId) throws Exception {
		AjiaOrder ajiaOrder = new AjiaOrder();
		ajiaOrder.setOrderId(orderId);
		ajiaOrder.setStatus(9);
		int row = ajiaOrderMapper.updateByPrimaryKeySelective(ajiaOrder);
		return row >= 1 ? true : false;
	}

	@Override
	public AjiaOrderVo selectVoByOrderId(String orderId) throws Exception {
		//ArrayList<AjiaOrderVo> voList = new ArrayList<>();
		// �����ж���
		AjiaOrderExample orderExample = new AjiaOrderExample();
		AjiaOrderExample.Criteria orderCriteria = orderExample.or();
		//orderExample.setOrderByClause("create_time desc");
		//orderCriteria.andUserIdEqualTo(userId);
		//orderCriteria.andStatusNotEqualTo(9);// 9���Ѿ�ɾ���Ķ���
		orderCriteria.andOrderIdEqualTo(orderId);
		// 0�����ж���
		//if (status != 0) {
			// ��ѯĳ��״̬�Ķ���
			//orderCriteria.andStatusEqualTo(status);
		//}
		List<AjiaOrder> orderList = ajiaOrderMapper.selectByExample(orderExample);
		AjiaOrderVo vo = new AjiaOrderVo();
		for (AjiaOrder ajiaOrder : orderList) {
			//voList.add(vo);
			vo.setAjiaOrder(ajiaOrder);
			// ��һ�������Ķ�������

			AjiaOrderItemExample orderItemExample = new AjiaOrderItemExample();
			AjiaOrderItemExample.Criteria orderItemCriteria = orderItemExample.or();
			orderItemCriteria.andOrderIdEqualTo(ajiaOrder.getOrderId());
			List<AjiaOrderItem> ajiaOrderItemList = ajiaOrderItemMapper.selectByExample(orderItemExample);
			vo.setAjiaOrderItemList(ajiaOrderItemList);
			// ��ÿ������������Ʒ�Ĳ���
			for (AjiaOrderItem ajiaOrderItem : ajiaOrderItemList) {

				AjiaItemParamItemExample paramItemExample = new AjiaItemParamItemExample();
				AjiaItemParamItemExample.Criteria pCriteria = paramItemExample.or();
				pCriteria.andItemIdEqualTo(Long.parseLong(ajiaOrderItem.getItemId()));

				List<AjiaItemParamItem> pList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(paramItemExample);
				try {
					if (pList != null && pList.size() >= 1) {
						AjiaItemParamItem ajiaItemParamItem = pList.get(0);
						String paramData = ajiaItemParamItem.getParamData();
						List<AjiaItemParamData> list = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
						List<Params> paramsList = list.get(0).getParams();
						ajiaOrderItem.setParamList(paramsList);
					}
				} catch (Exception e) {
					ajiaOrderItem.setParamList(new ArrayList<>());
				}

			}

		}

		return vo;
	}

}
