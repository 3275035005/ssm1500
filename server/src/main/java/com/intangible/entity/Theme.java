package com.intangible.entity;

import java.io.Serializable;

/**
 * theme
 * @author 
 */
public class Theme implements Serializable {
    private Integer id;

    /**
     * 主题名称
     */
    private String title;

    /**
     * 主题封面
     */
    private String image;

    /**
     * 是否为推荐主题 0否 1是
     */
    private String recommendState;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 简介
     */
    private String intro;

    private static final long serialVersionUID = 1L;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRecommendState() {
        return recommendState;
    }

    public void setRecommendState(String recommendState) {
        this.recommendState = recommendState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Theme other = (Theme) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getRecommendState() == null ? other.getRecommendState() == null : this.getRecommendState().equals(other.getRecommendState()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getRecommendState() == null) ? 0 : getRecommendState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", image=").append(image);
        sb.append(", recommendState=").append(recommendState);
        sb.append(", createTime=").append(createTime);
        sb.append(", intro=").append(intro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}