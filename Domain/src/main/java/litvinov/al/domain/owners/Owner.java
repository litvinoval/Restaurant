package litvinov.al.domain.owners;

import litvinov.al.domain.common.Restaurant;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "owner_roles")
    private List<OwnerRole> ownerRoles;
}
