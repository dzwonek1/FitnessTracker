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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "training")
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

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    private List<WorkoutSession> workoutSessions = new ArrayList<>();

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = true)
    private Date endTime;

    @Enumerated(EnumType.STRING) // Ważne, żeby w bazie był tekst, a nie cyfry
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(name = "distance", nullable = false)
    private double distance;

    @Column(name = "average_speed" ,nullable = false)
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
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}



