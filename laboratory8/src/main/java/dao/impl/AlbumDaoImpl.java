package dao.impl;

import dao.Dao;
import model.Album;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class AlbumDaoImpl implements Dao<Album> {

    private final SessionFactory sessionFactory;

    public AlbumDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Album findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Album.class, id);
        }
    }

    @Override
    public void save(Album album) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Album album) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(album);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Album album) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(album);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Album> findAll() {
        Session session = sessionFactory.openSession();
        return (List<Album>) session.createQuery("From Album").list();
    }
}
