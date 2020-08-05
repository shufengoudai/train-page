package com.sun.japan.service;

import com.sun.japan.entities.Train_Students;
import com.sun.japan.repository.TrainStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainStudentsServiceImpl implements TrainStudentsService{
    private TrainStudentsRepository trainStudentsRepository;

    @Autowired
    public void setTrainStudentsRepository(TrainStudentsRepository trainStudentsRepository) {
        this.trainStudentsRepository = trainStudentsRepository;
    }

    @Override
    public Integer countStudentsNum(Integer trainId, String flg) {
        return trainStudentsRepository.countTrain_StudentsByTrainIdAndFlg(trainId,flg);
    }

    @Override
    public List<Train_Students> getStudentsList(Integer trainId, String flg) {
        return trainStudentsRepository.getTrain_StudentsByTrainIdAndFlg(trainId, flg);
    }

    @Override
    public Train_Students getTrainStudents(Integer trainId, Integer empId, String flg) {
        return trainStudentsRepository.getTrain_StudentsByTrainIdAndEmpIdAndFlg(trainId, empId, flg);
    }

    @Override
    public void updateFlg(Integer trainId, Integer empId, String flg) {
        trainStudentsRepository.updateFlgByTrainIdAndAndEmpId(trainId, empId, flg);
    }

    @Override
    public void signUp(Integer trainId, Integer empId, String flg) {
        trainStudentsRepository.insertTrain_Students(trainId, empId, flg);
    }

    @Override
    public Train_Students getTrainStudents(Integer trainId, Integer empId) {
        return trainStudentsRepository.getTrain_StudentsByTrainIdAndEmpId(trainId, empId);
    }
}
