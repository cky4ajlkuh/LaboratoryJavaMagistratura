package dao.impl;

import dao.Dao;
import model.Artist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ArtistDaoImpl implements Dao<Artist> {

    private final SessionFactory sessionFactory;

    public ArtistDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Artist findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Artist.class, id);
        }
    }

    @Override
    public void save(Artist artist) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Artist artist) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(artist);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Artist artist) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(artist);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Artist> findAll() {
        return (List<Artist>) sessionFactory.openSession().createQuery("From Artist").list();
    }
}
