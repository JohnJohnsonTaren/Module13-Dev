package serviceCRUD;

import dao.TicketDao;
import dao.TicketDaoImpl;
import entitiesHibernate.Ticket;
import java.util.List;

public class TicketCrudService implements TicketService {
    private TicketDao ticketDao;

    public TicketCrudService() {
        this.ticketDao = new TicketDaoImpl();
    }

    public TicketCrudService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void create(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException();
        }
        ticketDao.save(ticket);
    }

    @Override
    public Ticket read(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return ticketDao.findById(id);
    }

    @Override
    public void update(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException();
        }
        if (ticket.getId() == null) {
            throw new IllegalArgumentException();
        }
        ticketDao.update(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException();
        }
        ticketDao.delete(ticket);
    }

    @Override
    public List<Ticket> listAll() {
        return ticketDao.findAll();
    }

    @Override
    public boolean exists(Long id) {
        if (id == null) {
            return false;
        }
        return ticketDao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        ticketDao.deleteById(id);
    }
}