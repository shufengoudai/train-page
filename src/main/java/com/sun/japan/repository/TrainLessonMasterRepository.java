package com.sun.japan.repository;

import com.sun.japan.entities.Train_Lesson_Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainLessonMasterRepository extends JpaRepository<Train_Lesson_Master,String> {

    @Query(value = "select train_type_name from trn_lesson_master where train_type = ?1",nativeQuery = true)
    public String getTrainTypeNameByTrainType(String trainType);

    @Query(value = "select train_type from trn_lesson_master where train_type_name = ?1",nativeQuery = true)
    public String getTrainTypeByTrainTypeName(String trainTypeName);
}
