package ma.beans.service;

import ma.beans.classes.Homme;
import ma.beans.classes.Mariage;
import ma.beans.dao.IDao;
import ma.beans.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HommeService implements IDao<Homme> {

    public HommeService() {}

    @Override
    public Homme save(Homme homme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(homme);
            transaction.commit();
            return homme;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Homme> findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Homme homme = session.get(Homme.class, id);
            return Optional.ofNullable(homme);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Homme> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Homme> query = session.createQuery("FROM Homme", Homme.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Homme update(Homme homme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Homme updated = (Homme) session.merge(homme);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Homme homme = session.get(Homme.class, id);
            if (homme != null) {
                session.delete(homme);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // Méthode pour afficher les épouses d'un homme entre deux dates
    public List<Mariage> getEpousesEntreDates(Long hommeId, LocalDate dateDebut, LocalDate dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Mariage> query = session.createQuery(
                    "SELECT m FROM Mariage m WHERE m.homme.id = :hommeId AND m.dateDebut BETWEEN :dateDebut AND :dateFin",
                    Mariage.class);
            query.setParameter("hommeId", hommeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Méthode pour afficher les mariages d'un homme avec détails
    public void afficherMariagesHomme(Long hommeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Homme homme = session.get(Homme.class, hommeId);
            if (homme == null) {
                System.out.println("Homme non trouvé");
                return;
            }

            System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());
            System.out.println("Mariages en cours :");

            // Charger les mariages explicitement
            Query<Mariage> query = session.createQuery(
                    "SELECT m FROM Mariage m WHERE m.homme.id = :hommeId", Mariage.class);
            query.setParameter("hommeId", hommeId);
            List<Mariage> mariages = query.list();

            int countEnCours = 1;
            int countEchoues = 1;

            for (Mariage mariage : mariages) {
                if ("EN_COURS".equals(mariage.getStatut())) {
                    System.out.println(countEnCours + ". Femme : " +
                            mariage.getFemme().getNom() + " " + mariage.getFemme().getPrenom() +
                            " | Date Début : " + mariage.getDateDebut() +
                            " | Nbr Enfants : " + mariage.getNombreEnfants());
                    countEnCours++;
                }
            }

            System.out.println("Mariages échoués :");
            for (Mariage mariage : mariages) {
                if ("ECHOUE".equals(mariage.getStatut())) {
                    System.out.println(countEchoues + ". Femme : " +
                            mariage.getFemme().getNom() + " " + mariage.getFemme().getPrenom() +
                            " | Date Début : " + mariage.getDateDebut() +
                            " | Date Fin : " + mariage.getDateFin() +
                            " | Nbr Enfants : " + mariage.getNombreEnfants());
                    countEchoues++;
                }
            }
        } finally {
            session.close();
        }
    }
}