package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;

import java.sql.Timestamp;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "Workout_Session")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class WorkoutSession {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Nullable
        private Long id;

        @OneToOne
        @JoinColumn(name = "training_id",referencedColumnName = "id",nullable = false)
        private Training training;

        @Column(name = "timestamp", nullable = false)
        private LocalDateTime timestamp;

        @Column(name = "startLatitude", nullable = false)
        private double  startLatitude;

        @Column(name = "startLongitude", nullable = false)
        private double  startLongitude;

        @Column(name = "endLatitude" ,nullable = true)
        private double endLatitude;

        @Column(name = "endLongitude" ,nullable = true)
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



