package com.sun.japan.service;

import com.sun.japan.entities.Train_Info;
import com.sun.japan.entities.Train_Search;
import com.sun.japan.repository.TrainInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainInfoServiceImpl implements TrainInfoService{
    private TrainInfoRepository trainInfoRepository;

    @Autowired
    public void setTrainInfoRepository(TrainInfoRepository trainInfoRepository) {
        this.trainInfoRepository = trainInfoRepository;
    }

    //通过培训检索页面的三个字段拿到一堆培训公告信息
    @Override
    public List<Train_Info> getTrainInfos(String status, String startTime,String endTime, String trainType) {
        return trainInfoRepository.getTrain_InfosByStatusAndPeriodBetweenStartTimeAndEndTimeAndTrainType(status,startTime,endTime,trainType);
    }

    @Override
    public void saveTrainInfo(String subjects, Integer director, String period, String trainType) {
        trainInfoRepository.insertTrainInfo(subjects,director,period,trainType);
    }

    @Override
    public Train_Info getTrain_InfoById(Integer trainId) {
        return trainInfoRepository.getTrain_InfoByTrainId(trainId);
    }

    @Override
    public void trainLessonStatusUpdate(String status, Integer trainId) {
        trainInfoRepository.updateStatusOfTrain_InfoByTrainId(status,trainId);
    }

    @Override
    public List<Integer> getTrainIds(String subjects) {
        return trainInfoRepository.getTrainIdsBySubjects(subjects);
    }

    @Override
    public List<Train_Info> getTrainInfos(Integer trainId, String subjects, String trainType,String status) {
        return trainInfoRepository.getTrain_InfosByTrainIdAndSubjectsAndTrainTypeAndStatus(trainId, subjects, trainType,status);
    }

    @Override
    public List<Integer> getTrainIdsByType(String trainType) {
        return trainInfoRepository.getTrainIdsByTrainType(trainType);
    }
}
