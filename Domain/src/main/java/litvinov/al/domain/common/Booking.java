package litvinov.al.domain.common;

import litvinov.al.domain.common.BookingHelper;
import litvinov.al.domain.common.Restaurant;
import litvinov.al.domain.owners.OwnerRole;
import litvinov.al.domain.users.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@IdClass(BookingHelper.class)
public class Booking {
    @Id
    @ManyToOne
    private Restaurant restaurant;

    @Id
    private int number;

    @ManyToOne
    private User user;


}
