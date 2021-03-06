package com.ajiatech.pojo;

import java.io.Serializable;
import java.util.Date;

public class AjiaCollectItem implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.user_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.item_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Long itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.title
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.price
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.pic_path
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String picPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.item_param_data
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String itemParamData;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.status
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.created
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_collect_item.updated
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ajia_collect_item
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.id
     *
     * @return the value of ajia_collect_item.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.id
     *
     * @param id the value for ajia_collect_item.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.user_id
     *
     * @return the value of ajia_collect_item.user_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.user_id
     *
     * @param userId the value for ajia_collect_item.user_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.item_id
     *
     * @return the value of ajia_collect_item.item_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.item_id
     *
     * @param itemId the value for ajia_collect_item.item_id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.title
     *
     * @return the value of ajia_collect_item.title
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.title
     *
     * @param title the value for ajia_collect_item.title
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.price
     *
     * @return the value of ajia_collect_item.price
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.price
     *
     * @param price the value for ajia_collect_item.price
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.pic_path
     *
     * @return the value of ajia_collect_item.pic_path
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.pic_path
     *
     * @param picPath the value for ajia_collect_item.pic_path
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.item_param_data
     *
     * @return the value of ajia_collect_item.item_param_data
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getItemParamData() {
        return itemParamData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.item_param_data
     *
     * @param itemParamData the value for ajia_collect_item.item_param_data
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setItemParamData(String itemParamData) {
        this.itemParamData = itemParamData == null ? null : itemParamData.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.status
     *
     * @return the value of ajia_collect_item.status
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.status
     *
     * @param status the value for ajia_collect_item.status
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.created
     *
     * @return the value of ajia_collect_item.created
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.created
     *
     * @param created the value for ajia_collect_item.created
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_collect_item.updated
     *
     * @return the value of ajia_collect_item.updated
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_collect_item.updated
     *
     * @param updated the value for ajia_collect_item.updated
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_collect_item
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
        sb.append(", userId=").append(userId);
        sb.append(", itemId=").append(itemId);
        sb.append(", title=").append(title);
        sb.append(", price=").append(price);
        sb.append(", picPath=").append(picPath);
        sb.append(", itemParamData=").append(itemParamData);
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}