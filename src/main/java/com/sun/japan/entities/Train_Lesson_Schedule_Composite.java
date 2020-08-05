package com.sun.japan.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class Train_Lesson_Schedule_Composite implements Serializable {
    private Integer trainId;
    private String lessonId;

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train_Lesson_Schedule_Composite that = (Train_Lesson_Schedule_Composite) o;
        return Objects.equals(trainId, that.trainId) &&
                Objects.equals(lessonId, that.lessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, lessonId);
    }
}
