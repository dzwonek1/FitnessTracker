package pl.wsb.fitnesstracker.achievement;

import jakarta.persistence.*;
import pl.wsb.fitnesstracker.user.api.User;
import java.time.LocalDateTime;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "achievement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "earned_at")
    private LocalDateTime earnedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public Achievement(
            String name,
            LocalDateTime earnedAt,
            User user) {

        this.name = name;
        this.earnedAt = earnedAt;
        this.user = user;
    }
}