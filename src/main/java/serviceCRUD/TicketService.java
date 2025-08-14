package serviceCRUD;

import entitiesHibernate.Ticket;
import java.util.List;

public interface TicketService {
    void create(Ticket ticket);

    Ticket read(Long id);

    void update(Ticket ticket);

    void delete(Ticket ticket);

    List<Ticket> listAll();
    
    boolean exists(Long id);
    
    void deleteById(Long id);
}