package com.sun.japan.repository;

import com.sun.japan.entities.Train_Lesson_Schedule;
import com.sun.japan.entities.Train_Lesson_Schedule_Composite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrainLessonScheduleRepository extends JpaRepository<Train_Lesson_Schedule, Train_Lesson_Schedule_Composite> {
    public List<Train_Lesson_Schedule> getTrain_Lesson_SchedulesByTrainId(Integer trainId);

    @Transactional()
    @Modifying(clearAutomatically = true)
    public void deleteTrain_Lesson_ScheduleByLessonId(String lessonId);

    public Train_Lesson_Schedule getTrain_Lesson_ScheduleByDateNumAndStartTimeAndEndTimeAndDescription(String dateNum,String startTime,String endTime,String description);

    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into trn_lesson_schedule(train_id,lesson_id,date_num,description,start_time,end_time) values (?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    public void createTrain_Lesson_Schedule(Integer trainId,String lessonId,String dateNum,String description,String startTime,String endTime);
}
