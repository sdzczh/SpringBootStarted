package com.zh.module.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 4087349148385508529L;

    private Integer id;

    private String phone;

    private String userpassword;

    private String username;

    private Integer uuid;

    private Integer referenceid;

    private Integer referenceStatus;

    private String idcard;

    private Integer idstatus;

    private String headpath;

    private String secretkey;

    private String token;

    private Date tokencreatetime;

    private String orderpwd;

    private Date logintime;

    private String devicenum;

    private Integer state;

    private Integer role;

    private String nickname;

    private Date createtime;

    private Date updatetime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", userpassword=").append(userpassword);
        sb.append(", username=").append(username);
        sb.append(", uuid=").append(uuid);
        sb.append(", referenceid=").append(referenceid);
        sb.append(", referenceStatus=").append(referenceStatus);
        sb.append(", idcard=").append(idcard);
        sb.append(", idstatus=").append(idstatus);
        sb.append(", headpath=").append(headpath);
        sb.append(", secretkey=").append(secretkey);
        sb.append(", token=").append(token);
        sb.append(", tokencreatetime=").append(tokencreatetime);
        sb.append(", orderpwd=").append(orderpwd);
        sb.append(", logintime=").append(logintime);
        sb.append(", devicenum=").append(devicenum);
        sb.append(", state=").append(state);
        sb.append(", role=").append(role);
        sb.append(", nickname=").append(nickname);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}