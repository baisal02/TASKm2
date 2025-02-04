package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.jvm.hotspot.debugger.Address;

import java.time.LocalDate;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userdetail_gen")
    @SequenceGenerator(name = "userdetail_gen",sequenceName = "userdetail_seq",allocationSize = 1)
    private Long id;

    private String full_Name;

    private LocalDate date_of_birth;

    private String address;

    @OneToOne(mappedBy = "userDetail",cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private UserProfile userProfile;
}
