package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.workoutsession.WorkoutSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
    @Table(name = "Training")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class Training {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Nullable
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
        private User user;

        @OneToOne(mappedBy = "training",cascade = CascadeType.ALL)
        private WorkoutSession workoutSession;

        @Column(name = "startTime", nullable = false)
        private Date startTime;

        @Column(name = "endTime", nullable = true)
        private Date endTime;

        @Column(name = "distance", nullable = false)
        private double distance;

        @Column(name = "averageSpeed" ,nullable = false)
        private double averageSpeed;

        public Training(
                final User user,
                final Date startTime,
                final double distance,
                final double averageSpeed) {

            this.user = user;
            this.startTime = startTime;
            this.distance = distance;
            this.averageSpeed = averageSpeed;
        }
        public Training(
                final User user,
                final Date startTime,
                final Date endTime,
                final ActivityType activityType,
                final double distance,
                final double averageSpeed) {

            this.user = user;
            this.startTime = startTime;
            this.endTime = endTime;
            this.distance = distance;
            this.averageSpeed = averageSpeed;
        }
    }




