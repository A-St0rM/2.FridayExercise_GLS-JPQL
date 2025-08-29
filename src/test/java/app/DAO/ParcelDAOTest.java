package app.DAO;

import app.config.HibernateConfig;
import app.entities.Parcel;
import app.enums.DeliveryStatus;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelDAOTest {

    private static EntityManagerFactory emf;
    private static ParcelDAO parcelDAO;

    @BeforeAll
    static void setup() {
        HibernateConfig.setTest(true);  // Her bruger vi test containers automatisk
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
        // To reset database if needed.
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
    }

    @Test
    void retrieveAllParcels() {
    }

    @Test
    void updateDeliveryStatus() {
    }

    @Test
    void removeParcel() {
    }
}