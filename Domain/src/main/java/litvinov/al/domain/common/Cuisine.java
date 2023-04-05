package litvinov.al.domain.common;


import litvinov.al.domain.common.Restaurant;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Type type;

    public enum Type{
        ITALIAN,
        CHINESE,
        JAPANESE,
        FRENCH
    }
}
