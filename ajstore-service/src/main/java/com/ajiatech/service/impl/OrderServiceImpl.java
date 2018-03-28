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
		// 取每个商品的数量和价格,计算总价
		//try {
			List<AjiaCartItemVo> voList = cartService.selectByItemIds(itemIdList, userId);
			double payment = 0;
			for (AjiaCartItemVo vo : voList) {
				payment = payment + vo.getAjiaCartItem().getNum() * vo.getAjiaItem().getPrice();
			}
			// 保存订单
			AjiaOrder ajiaOrder = new AjiaOrder();
			String orderId = generateId();
			ajiaOrder.setOrderId(orderId);
			ajiaOrder.setUserId(userId);
			ajiaOrder.setStatus(1);// 1刚下订单 求付款

			ajiaOrder.setAddId(addId);
			ajiaOrder.setPayment(payment);
			ajiaOrder.setCreateTime(new Date());
			// 存放地址信息
			AjiaShipping ajiaShipping = ajiaShippingMapper.selectByPrimaryKey(addId);
			ajiaOrder.setShippingName(ajiaShipping.getReceiverName());

			ajiaOrderMapper.insert(ajiaOrder);
			for (AjiaCartItemVo vo : voList) {
				// 保存订单详情
				AjiaOrderItem orderItem = new AjiaOrderItem();
				orderItem.setId(generateId());
				//为了测试事务，让添加出错
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
		// 查所有订单
		AjiaOrderExample orderExample = new AjiaOrderExample();
		AjiaOrderExample.Criteria orderCriteria = orderExample.or();
		orderExample.setOrderByClause("create_time desc");
		orderCriteria.andUserIdEqualTo(userId);
		orderCriteria.andStatusNotEqualTo(9);// 9是已经删除的订单
		// 0是所有订单
		if (status != 0) {
			// 查询某个状态的订单
			orderCriteria.andStatusEqualTo(status);
		}
		List<AjiaOrder> orderList = ajiaOrderMapper.selectByExample(orderExample);
		for (AjiaOrder ajiaOrder : orderList) {
			AjiaOrderVo vo = new AjiaOrderVo();
			voList.add(vo);
			vo.setAjiaOrder(ajiaOrder);
			// 查一个订单的订单详情

			AjiaOrderItemExample orderItemExample = new AjiaOrderItemExample();
			AjiaOrderItemExample.Criteria orderItemCriteria = orderItemExample.or();
			orderItemCriteria.andOrderIdEqualTo(ajiaOrder.getOrderId());
			List<AjiaOrderItem> ajiaOrderItemList = ajiaOrderItemMapper.selectByExample(orderItemExample);
			vo.setAjiaOrderItemList(ajiaOrderItemList);
			// 查每个订单详情商品的参数
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
		// 前面6位是日期+随机数7

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
		// 分页第一步：设定页数
		// currentPage:第几页
		// pagesize:每页显示几行
		PageHelper.startPage(currentPage, pageSize);
		PageHelper.orderBy("create_time desc");
		ArrayList<AjiaOrderVo> voList = new ArrayList<>();
		// 查所有订单
		AjiaOrderExample orderExample = new AjiaOrderExample();
		AjiaOrderExample.Criteria orderCriteria = orderExample.or();
		// orderExample.setOrderByClause("create_time desc");
		orderCriteria.andUserIdEqualTo(userId);
		orderCriteria.andStatusNotEqualTo(9);// 9是已经删除的订单
		// 0是所有订单
		if (status != 0) {
			// 查询某个状态的订单
			orderCriteria.andStatusEqualTo(status);
		}
		List<AjiaOrder> orderList = ajiaOrderMapper.selectByExample(orderExample);
		// 分页第二步：设置总行数，总页数=总行数/pageSize,orderList.size是总行数
		PageInfo<AjiaOrderVo> pageInfoList = new PageInfo(orderList);
		for (AjiaOrder ajiaOrder : orderList) {
			AjiaOrderVo vo = new AjiaOrderVo();
			voList.add(vo);
			vo.setAjiaOrder(ajiaOrder);
			// 查一个订单的订单详情

			AjiaOrderItemExample orderItemExample = new AjiaOrderItemExample();
			AjiaOrderItemExample.Criteria orderItemCriteria = orderItemExample.or();
			orderItemCriteria.andOrderIdEqualTo(ajiaOrder.getOrderId());
			List<AjiaOrderItem> ajiaOrderItemList = ajiaOrderItemMapper.selectByExample(orderItemExample);
			vo.setAjiaOrderItemList(ajiaOrderItemList);
			// 查每个订单详情商品的参数
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
		// 查所有订单
		AjiaOrderExample orderExample = new AjiaOrderExample();
		AjiaOrderExample.Criteria orderCriteria = orderExample.or();
		//orderExample.setOrderByClause("create_time desc");
		//orderCriteria.andUserIdEqualTo(userId);
		//orderCriteria.andStatusNotEqualTo(9);// 9是已经删除的订单
		orderCriteria.andOrderIdEqualTo(orderId);
		// 0是所有订单
		//if (status != 0) {
			// 查询某个状态的订单
			//orderCriteria.andStatusEqualTo(status);
		//}
		List<AjiaOrder> orderList = ajiaOrderMapper.selectByExample(orderExample);
		AjiaOrderVo vo = new AjiaOrderVo();
		for (AjiaOrder ajiaOrder : orderList) {
			//voList.add(vo);
			vo.setAjiaOrder(ajiaOrder);
			// 查一个订单的订单详情

			AjiaOrderItemExample orderItemExample = new AjiaOrderItemExample();
			AjiaOrderItemExample.Criteria orderItemCriteria = orderItemExample.or();
			orderItemCriteria.andOrderIdEqualTo(ajiaOrder.getOrderId());
			List<AjiaOrderItem> ajiaOrderItemList = ajiaOrderItemMapper.selectByExample(orderItemExample);
			vo.setAjiaOrderItemList(ajiaOrderItemList);
			// 查每个订单详情商品的参数
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
