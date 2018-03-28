package com.ajiatech.pojo;

import java.io.Serializable;
import java.util.Date;

public class AjiaUser implements Serializable {
    
	public AjiaUser()
	{
		//System.out.println("����AjiaUser");
	}
	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_user.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_user.username
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_user.password
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_user.phone
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_user.email
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_user.created
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ajia_user.updated
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_user.id
     *
     * @return the value of ajia_user.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_user.id
     *
     * @param id the value for ajia_user.id
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_user.username
     *
     * @return the value of ajia_user.username
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_user.username
     *
     * @param username the value for ajia_user.username
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_user.password
     *
     * @return the value of ajia_user.password
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_user.password
     *
     * @param password the value for ajia_user.password
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_user.phone
     *
     * @return the value of ajia_user.phone
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_user.phone
     *
     * @param phone the value for ajia_user.phone
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_user.email
     *
     * @return the value of ajia_user.email
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_user.email
     *
     * @param email the value for ajia_user.email
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_user.created
     *
     * @return the value of ajia_user.created
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_user.created
     *
     * @param created the value for ajia_user.created
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ajia_user.updated
     *
     * @return the value of ajia_user.updated
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ajia_user.updated
     *
     * @param updated the value for ajia_user.updated
     *
     * @mbg.generated Wed Dec 20 14:19:38 CST 2017
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
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
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}