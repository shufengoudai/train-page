package com.sun.japan.service;

import com.sun.japan.entities.Train_Students;

import java.util.List;

public interface TrainStudentsService {
    public Integer countStudentsNum(Integer trainId,String flg);

    public List<Train_Students> getStudentsList(Integer trainId,String flg);

    public Train_Students getTrainStudents(Integer trainId,Integer empId,String flg);

    public void updateFlg(Integer trainId,Integer empId,String flg);

    public void signUp(Integer trainId,Integer empId,String flg);

    public Train_Students getTrainStudents(Integer trainId,Integer empId);
}
