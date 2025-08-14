package dao;

import entitiesHibernate.Ticket;
import java.util.List;

public interface TicketDao {
    void save(Ticket ticket);
    
    Ticket findById(Long id);
    
    List<Ticket> findAll();
    
    void update(Ticket ticket);
    
    void delete(Ticket ticket);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}