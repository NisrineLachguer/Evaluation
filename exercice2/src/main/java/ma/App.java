package ma;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            // Initialisation des services
            EmployeService employeService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeTacheService employeTacheService = new EmployeTacheService();

            // Format de date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Création d'employés
            Employe emp1 = new Employe("Dupont", "Jean", "jean.dupont@email.com");
            Employe emp2 = new Employe("Martin", "Marie", "marie.martin@email.com");
            employeService.create(emp1);
            employeService.create(emp2);

            // Création de projets
            Projet projet1 = new Projet("Gestion de stock", sdf.parse("14/01/2013"), emp1);
            Projet projet2 = new Projet("Site E-commerce", sdf.parse("01/02/2013"), emp2);
            projetService.create(projet1);
            projetService.create(projet2);

            // Création de tâches
            Tache tache1 = new Tache("Analyse", 1500, sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), projet1);
            Tache tache2 = new Tache("Conception", 1200, sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), projet1);
            Tache tache3 = new Tache("Développement", 2000, sdf.parse("10/04/2013"), sdf.parse("25/04/2013"), projet1);
            Tache tache4 = new Tache("Test", 800, sdf.parse("01/05/2013"), sdf.parse("10/05/2013"), projet1);

            // Définir les dates réelles pour certaines tâches
            tache1.setDateDebutReelle(sdf.parse("10/02/2013"));
            tache1.setDateFinReelle(sdf.parse("20/02/2013"));
            tache2.setDateDebutReelle(sdf.parse("10/03/2013"));
            tache2.setDateFinReelle(sdf.parse("15/03/2013"));
            tache3.setDateDebutReelle(sdf.parse("10/04/2013"));
            tache3.setDateFinReelle(sdf.parse("25/04/2013"));

            tacheService.create(tache1);
            tacheService.create(tache2);
            tacheService.create(tache3);
            tacheService.create(tache4);

            // Création des relations employé-tâche
            EmployeTache et1 = new EmployeTache(sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), emp1, tache1);
            EmployeTache et2 = new EmployeTache(sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), emp1, tache2);
            EmployeTache et3 = new EmployeTache(sdf.parse("10/04/2013"), sdf.parse("25/04/2013"), emp2, tache3);
            employeTacheService.create(et1);
            employeTacheService.create(et2);
            employeTacheService.create(et3);

            // Tests des fonctionnalités

            System.out.println("=== Tâches réalisées par l'employé 1 ===");
            List<Tache> tachesEmp1 = employeService.getTachesRealiseesParEmploye(emp1.getId());
            for (Tache t : tachesEmp1) {
                System.out.println("Tâche: " + t.getNom() + " - Prix: " + t.getPrix() + " DH");
            }

            System.out.println("\n=== Projets gérés par l'employé 1 ===");
            List<Projet> projetsEmp1 = employeService.getProjetsGeresParEmploye(emp1.getId());
            for (Projet p : projetsEmp1) {
                System.out.println("Projet: " + p.getNom() + " - Date début: " + sdf.format(p.getDateDebut()));
            }

            System.out.println("\n=== Affichage formaté des tâches réalisées pour le projet 1 ===");
            projetService.afficherTachesRealisees(projet1.getId());

            System.out.println("\n=== Tâches avec prix > 1000 DH ===");
            List<Tache> tachesChere = tacheService.getTachesPrixSuperieurA1000();
            for (Tache t : tachesChere) {
                System.out.println("Tâche: " + t.getNom() + " - Prix: " + t.getPrix() + " DH");
            }

            System.out.println("\n=== Tâches réalisées entre 01/03/2013 et 30/04/2013 ===");
            List<Tache> tachesEntreDates = tacheService.getTachesRealiseesEntreDates(
                    sdf.parse("01/03/2013"), sdf.parse("30/04/2013"));
            for (Tache t : tachesEntreDates) {
                System.out.println("Tâche: " + t.getNom() + " - Début: " + sdf.format(t.getDateDebutReelle()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}