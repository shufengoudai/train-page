package com.sun.japan.repository;

import com.sun.japan.entities.Train_Students;
import com.sun.japan.entities.Train_Students_composite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrainStudentsRepository extends JpaRepository<Train_Students, Train_Students_composite> {
//    @Query("select count(emp_id) from trn_students where train_id = ?1 and flg = ?2")
//    public Integer countTrain_StudentsByTrainIdAndFlg(Integer trainId,String flg);

    public Integer countTrain_StudentsByTrainIdAndFlg(Integer trainId,String flg);

    public List<Train_Students> getTrain_StudentsByTrainIdAndFlg(Integer trainId,String flg);

    public Train_Students getTrain_StudentsByTrainIdAndEmpIdAndFlg(Integer trainId,Integer empId,String flg);

    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query(value = "update trn_students set flg = ?3 where train_id = ?1 and emp_id = ?2",nativeQuery = true)
    public void updateFlgByTrainIdAndAndEmpId(Integer trainId,Integer empId,String flg);

    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into trn_students(train_id,emp_id,flg) values (?1,?2,?3)",nativeQuery = true)
    public void insertTrain_Students(Integer trainId,Integer empId,String flg);

    public Train_Students getTrain_StudentsByTrainIdAndEmpId(Integer trainId,Integer empId);
}
