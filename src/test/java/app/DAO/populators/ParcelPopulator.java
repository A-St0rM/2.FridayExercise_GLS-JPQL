package app.DAO.populators;

import app.DAO.ParcelDAO;
import app.entities.Parcel;
import app.enums.DeliveryStatus;

public class ParcelPopulator {

    public static Parcel[] populate(ParcelDAO dao) {
        Parcel p1 = Parcel.builder()
                .trackingNumber("TRACK001")
                .senderName("Alice")
                .receiverName("Bob")
                .deliveryStatus(DeliveryStatus.PENDING)
                .build();

        Parcel p2 = Parcel.builder()
                .trackingNumber("TRACK002")
                .senderName("Charlie")
                .receiverName("Diana")
                .deliveryStatus(DeliveryStatus.IN_TRANSIT)
                .build();

        dao.createParcel(p1);
        dao.createParcel(p2);

        return new Parcel[]{p1, p2};
    }
}
