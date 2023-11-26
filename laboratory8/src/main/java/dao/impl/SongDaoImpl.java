package dao.impl;

import dao.Dao;
import model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SongDaoImpl implements Dao<Song> {

    private final SessionFactory sessionFactory;

    public SongDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Song findById(int id) {
        return sessionFactory.openSession().get(Song.class, id);
    }

    @Override
    public void save(Song song) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(song);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Song song) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(song);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Song song) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(song);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Song> findAll() {
        return (List<Song>) sessionFactory.openSession().createQuery("From Song").list();
    }
}
