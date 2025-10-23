package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public boolean create(Produit o) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Produit o) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Produit o) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Produit findById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Produit produit = session.get(Produit.class, id);
            session.getTransaction().commit();
            return produit;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Produit> findAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Produit> produits = session.createQuery("FROM Produit", Produit.class).list();
            session.getTransaction().commit();
            return produits;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Produit> getProduitsByCategorie(Categorie categorie) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query<Produit> query = session.createQuery(
                    "FROM Produit p WHERE p.categorie = :categorie", Produit.class);
            query.setParameter("categorie", categorie);
            List<Produit> produits = query.list();
            session.getTransaction().commit();
            return produits;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Object[]> getProduitsCommandesBetweenDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query<Object[]> query = session.createQuery(
                    "SELECT p.reference, p.prix, lcp.quantite, c.date " +
                            "FROM LigneCommandeProduit lcp " +
                            "JOIN lcp.produit p " +
                            "JOIN lcp.commande c " +
                            "WHERE c.date BETWEEN :dateDebut AND :dateFin", Object[].class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            List<Object[]> results = query.list();
            session.getTransaction().commit();
            return results;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public void afficherProduitsParCommande(Commande commande) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            Query<Object[]> query = session.createQuery(
                    "SELECT p.reference, p.prix, lcp.quantite " +
                            "FROM LigneCommandeProduit lcp " +
                            "JOIN lcp.produit p " +
                            "WHERE lcp.commande = :commande", Object[].class);
            query.setParameter("commande", commande);
            List<Object[]> results = query.list();

            System.out.println("| Commande : " + commande.getId() + " | Date : " + commande.getDate() + " |");
            System.out.println("| Liste des produits :    |    |");
            System.out.println("| Référence   | Prix    | Quantité    |");
            System.out.println("|-------------|---------|-------------|");

            for (Object[] result : results) {
                String reference = (String) result[0];
                Double prix = (Double) result[1];
                Integer quantite = (Integer) result[2];
                System.out.printf("| %-11s | %-6.2f DH | %-11d |\n", reference, prix, quantite);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Produit> getProduitsPrixSuperieurA100() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query<Produit> query = session.createNamedQuery(
                    "Produit.findByPrixSuperieurA", Produit.class);
            query.setParameter("prixMin", 100.0);
            List<Produit> produits = query.list();
            session.getTransaction().commit();
            return produits;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }
}