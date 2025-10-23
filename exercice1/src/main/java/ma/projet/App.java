package ma.projet;

import ma.projet.service.*;
import ma.projet.classes.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) throws ParseException {

            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("=== TEST DE L'APPLICATION DE GESTION DE STOCK ===\n");

            System.out.println("1. Création des catégories:");
            Categorie cat1 = new Categorie("Cat1", "Ordinateurs");
            Categorie cat2 = new Categorie("Cat2", "Périphériques");
            Categorie cat3 = new Categorie("Cat3", "Composants");

            categorieService.create(cat1);
            categorieService.create(cat2);
            categorieService.create(cat3);
            System.out.println("Catégories créées avec succès!\n");

            System.out.println("2. Création des produits:");
            Produit p1 = new Produit("PR1", 120.0, cat1);
            Produit p2 = new Produit("PR2", 100.0, cat1);
            Produit p3 = new Produit("PR3", 200.0, cat2);


            produitService.create(p1);
            produitService.create(p2);
            produitService.create(p3);
            System.out.println("Produits créés avec succès!\n");

            System.out.println("3. Création des commandes:");
            Commande cmd1 = new Commande(sdf.parse("14/03/2013"));
            Commande cmd2 = new Commande(sdf.parse("15/03/2013"));
            Commande cmd3 = new Commande(sdf.parse("20/03/2013"));

            commandeService.create(cmd1);
            commandeService.create(cmd2);
            commandeService.create(cmd3);
            System.out.println("Commandes créées avec succès!\n");

            System.out.println("4. Création des lignes de commande:");
            LigneCommandeProduit lcp1 = new LigneCommandeProduit(7, p1, cmd1);
            LigneCommandeProduit lcp2 = new LigneCommandeProduit(14, p2, cmd1);
            LigneCommandeProduit lcp3 = new LigneCommandeProduit(5, p3, cmd1);

            ligneCommandeService.create(lcp1);
            ligneCommandeService.create(lcp2);
            ligneCommandeService.create(lcp3);

            System.out.println("Lignes de commande créées avec succès!\n");


            System.out.println("=== TEST 1: Produits par catégorie ===");
            List<Produit> produitsCat1 = produitService.getProduitsByCategorie(cat1);
            System.out.println("Produits de la catégorie " + cat1.getLibelle() + ":");
            for (Produit p : produitsCat1) {
                System.out.println(" - " + p.getReference() + " : " + p.getPrix() + " DH");
            }
            System.out.println();

            System.out.println("=== TEST 2: Produits commandés entre deux dates ===");
            Date dateDebut = sdf.parse("10/03/2013");
            Date dateFin = sdf.parse("20/03/2013");
            List<Object[]> produitsCommandes = produitService.getProduitsCommandesBetweenDates(dateDebut, dateFin);
            System.out.println("Produits commandés entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + ":");
            for (Object[] result : produitsCommandes) {
                System.out.println(" - Réf: " + result[0] + ", Prix: " + result[1] +
                        " DH, Quantité: " + result[2] + ", Date: " + sdf.format(result[3]));
            }
            System.out.println();

            System.out.println("=== TEST 3: Produits de la commande " + cmd1.getId() + " ===");
            produitService.afficherProduitsParCommande(cmd1);
            System.out.println();

            System.out.println("=== TEST 4: Produits avec prix > 100 DH ===");
            List<Produit> produitsChers = produitService.getProduitsPrixSuperieurA100();
            System.out.println("Produits avec prix supérieur à 100 DH:");
            for (Produit p : produitsChers) {
                System.out.println(" - " + p.getReference() + " : " + p.getPrix() + " DH");
            }
            System.out.println();

            System.out.println("=== TEST 5: Liste de tous les produits ===");
            List<Produit> tousProduits = produitService.findAll();
            System.out.println("Tous les produits:");
            for (Produit p : tousProduits) {
                System.out.println(" - " + p.getReference() + " : " + p.getPrix() +
                        " DH (Catégorie: " + p.getCategorie().getLibelle() + ")");
            }
    }
}