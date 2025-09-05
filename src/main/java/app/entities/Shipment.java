package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate shipmentDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Parcel parcel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Location sourceLocation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Location destinationLocation;


}
