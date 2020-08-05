package com.sun.japan.repository;

import com.sun.japan.entities.Train_Info;
import com.sun.japan.entities.Train_Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrainInfoRepository extends JpaRepository<Train_Info,Integer> {

    public List<Train_Info> getTrain_InfosByStatusAndPeriodAndSubjects(String status,String period,String subjects);

    @Query(value = "select * from trn_info where status = ?1 and train_type = ?4 and period between ?2 and ?3",nativeQuery = true)
    public List<Train_Info> getTrain_InfosByStatusAndPeriodBetweenStartTimeAndEndTimeAndTrainType(String status,String startTime,String endTime,String trainType);

    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into trn_info(train_type,subjects,director,period) values (?4,?1,?2,?3)",nativeQuery = true)
    public void insertTrainInfo(String subjects,Integer director,String period,String trainType);

    public Train_Info getTrain_InfoByTrainId(Integer trainId);

    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query(value = "update trn_info set status = ?1 where train_id = ?2",nativeQuery = true)
    public void updateStatusOfTrain_InfoByTrainId(String status,Integer trainId);

    @Query(value = "select train_id from trn_info where subjects = ?1",nativeQuery = true)
    public List<Integer> getTrainIdsBySubjects(String subjects);

    public List<Train_Info> getTrain_InfosByTrainIdAndSubjectsAndTrainTypeAndStatus(Integer trainId,String subjects,String trainType,String status);

    @Query(value = "select train_id from trn_info where train_type = ?1",nativeQuery = true)
    public List<Integer> getTrainIdsByTrainType(String trainType);
}
