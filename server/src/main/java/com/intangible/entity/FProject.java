package com.intangible.entity;

import java.io.Serializable;

/**
 * f_project
 * @author
 */
public class FProject implements Serializable {
    private Integer id;

    /**
     * 项目名称
     */
    private String title;

    /**
     * 项目传承人id
     */
    private String inheritorId;

    /**
     * 项目分类id
     */
    private Integer classifyId;


    /**
     * 封面
     */
    private String image;

    /**
     * 发源地区
     */
    private String area;

    /**
     * 起源时间
     */
    private String originTime;

    /**
     * 是否为推荐项目 0否 1是
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

    /**
     * 传承人姓名
     */
    private String inheritorName;

    private String classifyName;

    private Integer collectId;


    /**
     * 视频地址
     */
    private String videoPath;


    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }
    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getInheritorName() {
        return inheritorName;
    }

    public void setInheritorName(String inheritorName) {
        this.inheritorName = inheritorName;
    }

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

    public String getInheritorId() {
        return inheritorId;
    }

    public void setInheritorId(String inheritorId) {
        this.inheritorId = inheritorId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOriginTime() {
        return originTime;
    }

    public void setOriginTime(String originTime) {
        this.originTime = originTime;
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

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
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
        FProject other = (FProject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getInheritorId() == null ? other.getInheritorId() == null : this.getInheritorId().equals(other.getInheritorId()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getOriginTime() == null ? other.getOriginTime() == null : this.getOriginTime().equals(other.getOriginTime()))
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
        result = prime * result + ((getInheritorId() == null) ? 0 : getInheritorId().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getOriginTime() == null) ? 0 : getOriginTime().hashCode());
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
        sb.append(", inheritorId=").append(inheritorId);
        sb.append(", image=").append(image);
        sb.append(", area=").append(area);
        sb.append(", originTime=").append(originTime);
        sb.append(", recommendState=").append(recommendState);
        sb.append(", createTime=").append(createTime);
        sb.append(", intro=").append(intro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
