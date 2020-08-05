package com.sun.japan.service;

import com.sun.japan.entities.Train_Info;
import com.sun.japan.entities.Train_Search;

import java.util.List;

public interface TrainInfoService {
    public List<Train_Info> getTrainInfos(String status, String startTime,String endTime, String trainType);

    public void saveTrainInfo(String subjects,Integer director,String period,String trainType);

    public Train_Info getTrain_InfoById(Integer trainId);

    public void trainLessonStatusUpdate(String status,Integer trainId);

    public List<Integer> getTrainIds(String subjects);

    public List<Train_Info> getTrainInfos(Integer trainId,String subjects,String trainType,String status);

    public List<Integer> getTrainIdsByType(String trainType);
}
