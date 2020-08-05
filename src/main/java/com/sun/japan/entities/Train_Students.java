package com.sun.japan.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "trn_students")
@IdClass(Train_Students_composite.class)
public class Train_Students {
    private Integer trainId;
    private Integer empId;

    private String flg;
    private Integer updateId;
    private Date updateDate;

    @Id
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    @Id
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Train_Students{" +
                "trainId=" + trainId +
                ", empId=" + empId +
                ", flg='" + flg + '\'' +
                ", updateId=" + updateId +
                ", updateDate=" + updateDate +
                '}';
    }
}
