package app.DAO;

import app.entities.Parcel;
import app.enums.DeliveryStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ParcelDAO {

    private EntityManagerFactory emf;

    public ParcelDAO(EntityManagerFactory emf){
        this.emf = emf;
    }

    //Persist a new parcel
    //Vi bruger en try catch for at sørge for at vores em altid lukker hvis der går noget galt i processen
    public Parcel createParcel(Parcel p){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        }
        finally {
            em.close();
        }
    }

    //Retrieve a parcel by its tracking number

    public Parcel retrieveParcelByTrackingNumber(String trackingNumber){
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Parcel> query = em.createQuery("SELECT p FROM Parcel p WHERE p.trackingNumber = :trackingNumber", Parcel.class);
            query.setParameter("trackingNumber", trackingNumber);
            return query.getSingleResult();
        }
        catch(NoResultException nre){
            return null;
        }
        finally{
            em.close();
        }
    }

    //Retrieve all parcels in the system
    public List<Parcel> retrieveAllParcels(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Parcel> query = em.createQuery("SELECT p FROM Parcel p", Parcel.class);
            return query.getResultList();
        }
        catch (NoResultException e){
            return null;
        }
        finally {
            em.close();
        }
    }

    //Update the delivery status of a parcel
    public Parcel UpdateDeliveryStatus(String trackingNumber, DeliveryStatus newDeliveryStatus){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            TypedQuery<Parcel> query = em.createQuery("SELECT P FROM Parcel p WHERE p.trackingNumber = :trackingNumber", Parcel.class);
            query.setParameter("trackingNumber", trackingNumber);

            Parcel parcel = query.getSingleResult();

            parcel.setDeliveryStatus(newDeliveryStatus);

            Parcel updatedParcel = em.merge(parcel);
            em.getTransaction().commit();
            return updatedParcel;
        }
        catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Remove a parcel from the system
    public void removeParcel(int id){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Parcel parcel = em.find(Parcel.class, id);

            if(parcel != null){
                em.remove(parcel);
            }
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
