package com.zh.module.entity;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    private static final long serialVersionUID = -3830640338420146103L;

    private Integer id;

    private String account;

    private String phone;

    private String password;

    private String nickName;

    private String orderPwd;

    private Integer contribution;

    private Byte level;

    private Byte uuid;

    private Byte state;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getOrderPwd() {
        return orderPwd;
    }

    public void setOrderPwd(String orderPwd) {
        this.orderPwd = orderPwd == null ? null : orderPwd.trim();
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getUuid() {
        return uuid;
    }

    public void setUuid(Byte uuid) {
        this.uuid = uuid;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", phone=").append(phone);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", orderPwd=").append(orderPwd);
        sb.append(", contribution=").append(contribution);
        sb.append(", level=").append(level);
        sb.append(", uuid=").append(uuid);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}