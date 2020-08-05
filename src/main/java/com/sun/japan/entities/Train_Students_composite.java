package com.sun.japan.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class Train_Students_composite implements Serializable {
    private Integer trainId;
    private Integer empId;

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train_Students_composite that = (Train_Students_composite) o;
        return Objects.equals(trainId, that.trainId) &&
                Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, empId);
    }
}
