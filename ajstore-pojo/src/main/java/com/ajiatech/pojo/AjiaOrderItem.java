package com.ajiatech.pojo;

import java.io.Serializable;
import java.util.List;

import com.ajiatech.pojo.itemParam.Params;

public class AjiaOrderItem implements Serializable {
    
	/**
	 * paramDate={{gruop:,params:[{��ɫ},{�ͺ�}]},{}}
	 */
	List<Params> paramList;
	public List<Params> getParamList() {
		return paramList;
	}

	public void setParamList(List<Params> paramList) {
		this.paramList = paramList;
	}

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.item_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.order_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.num
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.title
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.price
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.total_fee
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Double totalFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_order_item.pic_path
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String picPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.id
     *
     * @return the value of ajia_order_item.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.id
     *
     * @param id the value for ajia_order_item.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.item_id
     *
     * @return the value of ajia_order_item.item_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.item_id
     *
     * @param itemId the value for ajia_order_item.item_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.order_id
     *
     * @return the value of ajia_order_item.order_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.order_id
     *
     * @param orderId the value for ajia_order_item.order_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.num
     *
     * @return the value of ajia_order_item.num
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.num
     *
     * @param num the value for ajia_order_item.num
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.title
     *
     * @return the value of ajia_order_item.title
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.title
     *
     * @param title the value for ajia_order_item.title
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.price
     *
     * @return the value of ajia_order_item.price
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.price
     *
     * @param price the value for ajia_order_item.price
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.total_fee
     *
     * @return the value of ajia_order_item.total_fee
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Double getTotalFee() {
        return totalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.total_fee
     *
     * @param totalFee the value for ajia_order_item.total_fee
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_order_item.pic_path
     *
     * @return the value of ajia_order_item.pic_path
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_order_item.pic_path
     *
     * @param picPath the value for ajia_order_item.pic_path
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", orderId=").append(orderId);
        sb.append(", num=").append(num);
        sb.append(", title=").append(title);
        sb.append(", price=").append(price);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", picPath=").append(picPath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}