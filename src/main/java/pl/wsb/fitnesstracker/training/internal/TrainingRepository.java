package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Query(
            value = "Select SUM(distance) From trainings where user_id = :userId", nativeQuery = true
    )
    double sumDistanceByUserId(@Param("userId") Long userId);

    @Query("Select t From Training t where t.activityType = :activityType")
    List<Training> findByActivityType(@Param("activityType")ActivityType activityType);
}
