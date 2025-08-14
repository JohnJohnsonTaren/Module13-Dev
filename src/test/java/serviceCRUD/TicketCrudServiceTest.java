package serviceCRUD;

import dao.ClientDao;
import dao.ClientDaoImpl;
import dao.PlanetDao;
import dao.PlanetDaoImpl;
import dao.TicketDao;
import dao.TicketDaoImpl;
import entitiesHibernate.Client;
import entitiesHibernate.Planet;
import entitiesHibernate.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilPackage.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketCrudServiceTest {
    private TicketCrudService ticketService;
    private ClientCrudService clientService;
    private PlanetCrudService planetService;
    
    @BeforeEach
    void setUp() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        TicketDao ticketDao = new TicketDaoImpl();
        ClientDao clientDao = new ClientDaoImpl();
        PlanetDao planetDao = new PlanetDaoImpl();
        
        ticketService = new TicketCrudService(ticketDao);
        clientService = new ClientCrudService(clientDao);
        planetService = new PlanetCrudService(planetDao);
    }

    /**
     * Тестує створення нового квитка з усіма необхідними залежностями.
     * Спочатку створює клієнта та планети, потім створює квиток із зв'язками між ними.
     * Перевіряє, що операція створення проходить без винятків.
     */
    @Test
    void create() {
        // Спочатку створюємо та зберігаємо клієнта
        Client client = new Client();
        client.setName("Test Client");
        clientService.create(client);
        
        // Створюємо та зберігаємо планети
        Planet fromPlanet = new Planet();
        fromPlanet.setId("EARTH");
        fromPlanet.setName("Earth");
        planetService.create(fromPlanet);
        
        Planet toPlanet = new Planet();
        toPlanet.setId("MARS");
        toPlanet.setName("Mars");
        planetService.create(toPlanet);
        
        // Тепер створюємо квиток із збереженими об'єктами
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        assertDoesNotThrow(() -> {
            ticketService.create(ticket);
        });
    }

    /**
     * Тестує валідацію при спробі створення null квитка.
     * Перевіряє, що сервіс кидає IllegalArgumentException при передачі null.
     */
    @Test
    void create_nullTicket() {
        assertThrows(IllegalArgumentException.class, () -> {
            ticketService.create(null);
        });
    }

    /**
     * Тестує отримання списку всіх квитків з бази даних.
     * Перевіряє, що операція виконується без винятків та повертає не-null список.
     */
    @Test
    void listAll() {
        assertDoesNotThrow(() -> {
            List<Ticket> tickets = ticketService.listAll();
            assertNotNull(tickets);
        });
    }

    /**
     * Тестує валідацію при спробі оновлення null квитка.
     * Перевіряє, що сервіс кидає IllegalArgumentException при передачі null.
     */
    @Test
    void update() {
        assertThrows(IllegalArgumentException.class, () -> {
            ticketService.update(null);
        });
    }

    /**
     * Тестує валідацію при спробі видалення null квитка.
     * Перевіряє, що сервіс кидає IllegalArgumentException при передачі null.
     */
    @Test
    void delete() {
        assertThrows(IllegalArgumentException.class, () -> {
            ticketService.delete(null);
        });
    }

    /**
     * Тестує валідацію при спробі видалення квитка за null ID.
     * Перевіряє, що сервіс кидає IllegalArgumentException при передачі null ID.
     */
    @Test
    void deleteById() {
        assertThrows(IllegalArgumentException.class, () -> {
            ticketService.deleteById(null);
        });
    }
}
