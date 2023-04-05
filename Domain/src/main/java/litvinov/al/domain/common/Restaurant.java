package litvinov.al.domain.common;

import litvinov.al.domain.owners.Owner;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Restaurant {

    @Id
    private String id;
    private String name;
    @ManyToOne
    private Owner owner;
    @OneToMany
    private List<Cuisine> cuisines;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
