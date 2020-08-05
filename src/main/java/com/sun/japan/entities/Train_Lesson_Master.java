package com.sun.japan.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trn_lesson_master")
public class Train_Lesson_Master {
    @Id
    private String trainType;

    private String trainTypeName;
    private String trainDescription;
    private Integer lessonHour;
    private String studentType;
    private String content;
    private String trainLevelLimit;

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

    public String getTrainDescription() {
        return trainDescription;
    }

    public void setTrainDescription(String trainDescription) {
        this.trainDescription = trainDescription;
    }

    public Integer getLessonHour() {
        return lessonHour;
    }

    public void setLessonHour(Integer lessonHour) {
        this.lessonHour = lessonHour;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTrainLevelLimit() {
        return trainLevelLimit;
    }

    public void setTrainLevelLimit(String trainLevelLimit) {
        this.trainLevelLimit = trainLevelLimit;
    }
}
