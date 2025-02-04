package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.jvm.hotspot.debugger.Address;

import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userprofile_gen")
    @SequenceGenerator(name = "userprofile_gen",sequenceName = "userprofile_seq",allocationSize = 1)
    private Long id;
    @Column(unique=true)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate registration_date;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private UserDetail userDetail;

}
