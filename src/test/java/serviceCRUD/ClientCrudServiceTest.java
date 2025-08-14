package serviceCRUD;

import dao.ClientDao;
import dao.ClientDaoImpl;
import entitiesHibernate.Client;
import utilPackage.HibernateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {
    private ClientCrudService clientService;
    private HibernateUtil hibernateUtil;
    private Client testClient;

    @BeforeEach
    void setUp() {
        hibernateUtil = new HibernateUtil();
        ClientDao ClientDao = new ClientDaoImpl();
        clientService = new ClientCrudService(ClientDao);
        
        // Створюємо тестового клієнта для використання в тестах
        testClient = new Client();
        testClient.setName("Test Client");
        clientService.create(testClient);
    }

    /**
     * Тестує створення нового клієнта в базі даних.
     * Перевіряє, що після створення клієнт отримує валідний ID.
     */
    @Test
    void create() {
        // Перевіряємо створення нового клієнта
        Client newClient = new Client();
        newClient.setName("New Test Client");
        clientService.create(newClient);
        
        assertNotNull(newClient.getId());
        assertTrue(newClient.getId() > 0);
    }

    /**
     * Тестує зчитування існуючого клієнта з бази даних за його ID.
     * Перевіряє, що дані клієнта коректно повертаються.
     */
    @Test
    void read() {
        // Перевіряємо читання існуючого клієнта
        Client readClient = clientService.read(testClient.getId());
        
        assertNotNull(readClient);
        assertEquals("Test Client", readClient.getName());
        assertEquals(testClient.getId(), readClient.getId());
    }

    /**
     * Тестує оновлення даних існуючого клієнта.
     * Перевіряє, що зміни зберігаються в базі даних.
     */
    @Test
    void update() {
        // Перевіряємо оновлення клієнта
        testClient.setName("Updated Client");
        clientService.update(testClient);
        
        Client updatedClient = clientService.read(testClient.getId());
        assertEquals("Updated Client", updatedClient.getName());
    }

    /**
     * Тестує видалення клієнта з бази даних.
     * Перевіряє, що після видалення клієнт не може бути знайдений.
     */
    @Test
    void delete() {
        // Перевіряємо видалення клієнта
        clientService.delete(testClient);
        
        Client deletedClient = clientService.read(testClient.getId());
        assertNull(deletedClient);
    }
}
