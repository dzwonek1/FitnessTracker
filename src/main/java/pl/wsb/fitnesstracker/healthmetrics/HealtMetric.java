package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

public class HealtMetric {
    @Entity
    @Table(name = "healt_metric")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class Healt_Metrics {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Nullable
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id",referencedColumnName = "id")
        private User user;
        @Column(name = "date", nullable = false)
        private LocalDate date;

        @Column(name = "weight", nullable = false)
        private float weight;

        @Column(name = "height", nullable = false)
        private float height;

        @Column(name = "heart_rate" ,nullable = false)
        private int heart_rate;

        public Healt_Metrics(
                final User user,
                final float weight,
                final float height,
                final int heart_rate,
                final LocalDate date) {

            this.user = user;
            this.weight = weight;
            this.height = height;
            this.heart_rate = heart_rate;
            this.date = date;
        }

    }


}
