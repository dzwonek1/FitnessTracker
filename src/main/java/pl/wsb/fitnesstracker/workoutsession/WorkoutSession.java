package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;
import java.time.LocalDateTime;

@Entity
@Table(name = "workout_sessions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id",referencedColumnName = "id",nullable = false)
    private Training training;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "start_latitude", nullable = false)
    private double  startLatitude;

    @Column(name = "start_longitude", nullable = false)
    private double  startLongitude;

    @Column(name = "end_latitude" ,nullable = true)
    private double endLatitude;

    @Column(name = "end_longitude" ,nullable = true)
    private double endLongitude;

    @Column(name = "altitude" ,nullable = true)
    private double altitude;

    public WorkoutSession(
            final Training training,
            final LocalDateTime timestamp,
            final double startLatitude,
            final double startLongitude)
    {
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
    }
    public WorkoutSession(
            final Training training,
            final LocalDateTime timestamp,
            final double startLatitude,
            final double startLongitude,
            final double endLatitude,
            final double endLongitude,
            final double altitude)
    {
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}





