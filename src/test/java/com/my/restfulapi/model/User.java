package com.my.restfulapi.model;

import javax.persistence.*;

@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 工号
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取工号
     *
     * @return user_code - 工号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置工号
     *
     * @param userCode 工号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}