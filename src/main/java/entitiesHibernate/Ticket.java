//  Завдання №1 - додай сутність Ticket
//  Додай нову сутність - Ticket. Напиши maping для Hibernate для роботи з цією сутністю.
//     Зверни увагу, що в цій сутності є мапінги one-to-many.
//      Наприклад, у одного клієнта може бути багато квитків.
//      Відповідно, в сутності Ticket має бути не ідентифікатор клієнта, а
//          саме поле типу Client з коректно прописаним мапінгом. Це ж стосується початкової та кінцевої планет.
// Ticket (квиток). Має наступні властивості:
//      id - ідентифікатор квитка, первинний сурогатний ключ, автоінкрементне число.
//      created_at - TIMESTAMP в UTC, коли був створений цей квиток
//      client_id - ідентифікатор клієнта, якому належить цей квиток.
//      from_planet_id - ідентифікатор планети, звідки відправляється пасажир
//      to_planet_id - ідентифікатор планети, куди летить пасажир

package entitiesHibernate;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;
}
