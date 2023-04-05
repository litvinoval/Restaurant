package litvinov.al.domain.users;

import litvinov.al.domain.common.Booking;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "usr")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr_id")
    private Long id;
    private String email;
    private String name;

    private LocalDateTime lastVisit;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "usr_roles")
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookings;

}