package app.DAO;

import app.entities.Shipment;

import java.util.List;

public class ShipmentDAO implements IDAO<Shipment> {
    @Override
    public Shipment findById(int id) {
        return null;
    }

    @Override
    public List<Shipment> findAll() {
        return List.of();
    }

    @Override
    public void save(Shipment shipment) {

    }

    @Override
    public void delete(Shipment shipment) {

    }

    @Override
    public Shipment update(Shipment shipment) {
        return null;
    }
}
