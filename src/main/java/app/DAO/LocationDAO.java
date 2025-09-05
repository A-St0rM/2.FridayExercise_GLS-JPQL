package app.DAO;

import app.entities.Location;

import java.util.List;

public class LocationDAO implements IDAO<Location> {
    @Override
    public Location findById(int id) {
        return null;
    }

    @Override
    public List<Location> findAll() {
        return List.of();
    }

    @Override
    public void save(Location location) {

    }

    @Override
    public void delete(Location location) {

    }

    @Override
    public Location update(Location location) {
        return null;
    }
}
