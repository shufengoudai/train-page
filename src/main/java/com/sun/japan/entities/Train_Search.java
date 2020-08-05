package com.sun.japan.entities;

import java.io.Serializable;

public class Train_Search implements Serializable {
    private Integer trainId;//培训No.
    private String trainTypeName;//培训名称
    private String period;//实施日
    private String directorName;//授课者
    private String status;//培训状态
    private Integer plannedJoinNum;//计划参加人数
    private Integer confirmedJoinNum;//确认参加人数

    public Train_Search(Train_Info trainInfo,Integer plannedJoinNum,Integer confirmedJoinNum,String directorName,String trainTypeName) {
        this.trainId = trainInfo.getTrainId();
        this.trainTypeName = trainTypeName;
        this.period = trainInfo.getPeriod();
        this.directorName = directorName;
        this.status = trainInfo.getStatus();
        this.plannedJoinNum = plannedJoinNum;
        this.confirmedJoinNum = confirmedJoinNum;
    }

    public Train_Search(Integer trainId, String trainTypeName, String period, String directorName, String status) {
        this.trainId = trainId;
        this.trainTypeName = trainTypeName;
        this.period = period;
        this.directorName = directorName;
        this.status = status;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainType) {
        this.trainTypeName = trainType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String director) {
        this.directorName = director;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPlannedJoinNum() {
        return plannedJoinNum;
    }

    public void setPlannedJoinNum(Integer plannedJoinNum) {
        this.plannedJoinNum = plannedJoinNum;
    }

    public Integer getConfirmedJoinNum() {
        return confirmedJoinNum;
    }

    public void setConfirmedJoinNum(Integer confirmedJoinNum) {
        this.confirmedJoinNum = confirmedJoinNum;
    }

    @Override
    public String toString() {
        return "Train_Search{" +
                "trainId=" + trainId +
                ", trainTypeName='" + trainTypeName + '\'' +
                ", period='" + period + '\'' +
                ", director='" + directorName + '\'' +
                ", status='" + status + '\'' +
                ", plannedJoinNum=" + plannedJoinNum +
                ", confirmedJoinNum=" + confirmedJoinNum +
                '}';
    }
}
