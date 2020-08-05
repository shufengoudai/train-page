package com.sun.japan.service;

import com.sun.japan.repository.TrainLessonMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainLessonMasterServiceImpl implements TrainLessonMasterService{
    private TrainLessonMasterRepository trainLessonMasterRepository;

    @Autowired
    public void setTrainLessonMasterRepository(TrainLessonMasterRepository trainLessonMasterRepository) {
        this.trainLessonMasterRepository = trainLessonMasterRepository;
    }

    @Override
    public String getTrainTypeName(String trainType) {
        return trainLessonMasterRepository.getTrainTypeNameByTrainType(trainType);
    }

    @Override
    public String getTrainType(String trainTypeName) {
        return trainLessonMasterRepository.getTrainTypeByTrainTypeName(trainTypeName);
    }
}
