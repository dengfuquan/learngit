package com.ajiatech.service;

import java.util.List;

import com.ajiatech.pojo.AjiaOrder;
import com.ajiatech.pojo.AjiaOrderVo;
import com.github.pagehelper.PageInfo;

/**
 * �������
 * @author java
 *
 */
public interface OrderService {
	/**
	 * ��ѯ������Ϣ
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public AjiaOrderVo selectVoByOrderId(String orderId) throws Exception;
	
	/**
	 * ɾ������
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByOrderId(String orderId) throws Exception;
	
	/**
	 * ���ݶ�����Ÿ��¶���״̬
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public boolean updateStatusByOrderid(String orderId,int status) throws Exception;
	/**
	 * ���ݶ�����Ų��Ҷ���
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public AjiaOrder selectByOrderId(String orderid) throws Exception;
	/**
	 * ��ѯ�û��Ķ���
	 * @param userId
	 * @param status
	 * @return  pageInfo������ҳ���͵�ǰҳ����
	 * @throws Exception
	 */
	public PageInfo<AjiaOrderVo> selectByUserIdStatusForPage(Long userId,int status,int currentPage,int pageSize) throws Exception;
	
	/**
	 * ��ѯ�û��Ķ���
	 * @param userId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<AjiaOrderVo> selectByUserIdStatus(Long userId,int status) throws Exception;
	
	/**
	 * ���涩��
	 * @param userId
	 * @param addId
	 * @param itemIdList ��������Ʒ��id
	 * @return
	 * @throws Exception
	 */
public AjiaOrder saveOrder(Long userId,Long addId,List<Long> itemIdList) throws Exception;
}
