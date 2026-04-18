package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;


@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = true)
    private double weight;

    @Column(name = "height", nullable = true)
    private float height;

    @Column(name = "heart_rate" ,nullable = true)
    private int heartRate;

    public HealthMetric(
            final User user,
            final LocalDate date) {

        this.user = user;
        this.date = date;
    }

    public HealthMetric(
            final User user,
            final double weight,
            final float height,
            final int heartRate,
            final LocalDate date) {

        this.user = user;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
        this.date = date;
    }

}



