package com.sun.japan.service;

import com.sun.japan.entities.Train_Lesson_Schedule;

import java.util.List;

public interface TrainLessonScheduleService {
    public List<Train_Lesson_Schedule> getTrainLessonScheduleByTrainId(Integer trainId);

    public void deleteTrainLessonScheduleByLessonId(String lessonId);

    public Train_Lesson_Schedule getTrainLessonSchedule(String dateNum,String startTime,String endTime,String description);

    public void createTrainLessonSchedule(Integer trainId,String lessonId,String dateNum,String description,String startTime,String endTime);
}
