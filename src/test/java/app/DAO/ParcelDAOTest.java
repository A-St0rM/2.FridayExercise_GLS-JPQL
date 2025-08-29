package app.DAO;

import app.DAO.populators.ParcelPopulator;
import app.config.HibernateConfig;
import app.entities.Parcel;
import app.enums.DeliveryStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ParcelDAOTest {

    private static EntityManagerFactory emf;
    private static ParcelDAO parcelDAO;
    private static Parcel p1;
    private static Parcel p2;

    @BeforeAll
    static void setup() {
        HibernateConfig.setTest(true);  // Aktivér testcontainers
        emf = HibernateConfig.getEntityManagerFactory();
        parcelDAO = new ParcelDAO(emf);
    }

    @AfterAll
    static void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @BeforeEach
    void cleanDatabase() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Parcel").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE parcel_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();

            Parcel[] testParcels = ParcelPopulator.populate(parcelDAO);
            p1 = testParcels[0];
            p2 = testParcels[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void createParcel() {
        Parcel parcel = Parcel.builder()
                .trackingNumber("123D")
                .senderName("Alissa")
                .receiverName("Morten")
                .deliveryStatus(DeliveryStatus.PENDING)
                .build();

        Parcel createdParcel = parcelDAO.createParcel(parcel);
        assertNotNull(createdParcel.getId());
        assertEquals("123D", createdParcel.getTrackingNumber());
    }

    @Test
    void retrieveParcelByTrackingNumber() {
        Parcel retrievedParcel = parcelDAO.retrieveParcelByTrackingNumber(p1.getTrackingNumber());

        assertEquals(retrievedParcel.getTrackingNumber(), p1.getTrackingNumber());
        assertEquals(retrievedParcel.getSenderName(), p1.getSenderName());
    }

    @Test
    void retrieveAllParcels() {
        List<Parcel> allParcels = parcelDAO.retrieveAllParcels();
        assertEquals(2, allParcels.size());
    }

    @Test
    void updateDeliveryStatus() {
        Parcel updated = parcelDAO.UpdateDeliveryStatus(p1.getTrackingNumber(), DeliveryStatus.DELIVERED);

        assertNotEquals(p1.getDeliveryStatus(), updated.getDeliveryStatus());
        assertEquals(DeliveryStatus.DELIVERED, updated.getDeliveryStatus());
    }

    @Test
    void removeParcel() {
        parcelDAO.removeParcel(p1.getId());

        Parcel deleted = parcelDAO.retrieveParcelByTrackingNumber(p1.getTrackingNumber());
        assertNull(deleted);
    }
}
