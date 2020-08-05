package com.sun.japan.service;

import com.sun.japan.entities.Train_Lesson_Schedule;
import com.sun.japan.repository.TrainLessonScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainLessonScheduleServiceImpl implements TrainLessonScheduleService{
    private TrainLessonScheduleRepository trainLessonScheduleRepository;

    @Autowired
    public void setTrainLessonScheduleRepository(TrainLessonScheduleRepository trainLessonScheduleRepository) {
        this.trainLessonScheduleRepository = trainLessonScheduleRepository;
    }

    @Override
    public List<Train_Lesson_Schedule> getTrainLessonScheduleByTrainId(Integer trainId) {
        return trainLessonScheduleRepository.getTrain_Lesson_SchedulesByTrainId(trainId);
    }

    @Override
    public void deleteTrainLessonScheduleByLessonId(String lessonId) {
        trainLessonScheduleRepository.deleteTrain_Lesson_ScheduleByLessonId(lessonId);
    }

    @Override
    public Train_Lesson_Schedule getTrainLessonSchedule(String dateNum, String startTime, String endTime, String description) {
        return trainLessonScheduleRepository.getTrain_Lesson_ScheduleByDateNumAndStartTimeAndEndTimeAndDescription(dateNum, startTime, endTime, description);
    }

    @Override
    public void createTrainLessonSchedule(Integer trainId, String lessonId, String dateNum, String description, String startTime, String endTime) {
        trainLessonScheduleRepository.createTrain_Lesson_Schedule(trainId, lessonId, dateNum, description, startTime, endTime);
    }
}
