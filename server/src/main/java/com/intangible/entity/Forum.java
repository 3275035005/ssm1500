package com.intangible.entity;

import java.beans.Transient;
import java.io.Serializable;

/**
 * forum
 * @author 
 */
public class Forum implements Serializable {
    private Integer id;

    /**
     * 论坛标题
     */
    private String title;

    /**
     * 论坛内容
     */
    private String content;


    /**
     * 用户id
     */
    private String uid;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 发布时间
     */
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}