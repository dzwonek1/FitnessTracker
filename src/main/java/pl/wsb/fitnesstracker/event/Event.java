package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.workoutsession.WorkoutSession;

import java.time.LocalDateTime;
import java.util.Date;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table(name = "Event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "startTime" ,nullable = false)
    private LocalDateTime startTime;

    @Column(name = "endTime" ,nullable = false)
    private LocalDateTime endTime;

    @Column(name = "country" ,nullable = true)
    private String country;

    @Column(name = "city" ,nullable = true)
    private String city;

    public Event(
            final String name,
            final LocalDateTime startTime,
            final LocalDateTime endTime) {

        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Event(
            final String name,
            final String description,
            final LocalDateTime startTime,
            final LocalDateTime endTime,
            final String country,
            final String city)
    {

        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.country = country;
        this.city = city;
    }
    }




