package ma;

import ma.beans.classes.Femme;
import ma.beans.classes.Homme;
import ma.beans.classes.Mariage;
import ma.beans.service.FemmeService;
import ma.beans.service.HommeService;
import ma.beans.service.MariageService;
import ma.beans.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 *
 */

public class App {
    public static void main(String[] args) {
        try {
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            System.out.println("=== Création des personnes ===");
            creerDonneesTest(hommeService, femmeService, mariageService);

            System.out.println("\n=== Liste des femmes ===");
            List<Femme> femmes = femmeService.findAll();
            femmes.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

            System.out.println("\n=== Femme la plus âgée ===");
            Femme femmeAgee = femmeService.getFemmeLaPlusAgee();
            System.out.println("Femme la plus âgée : " + femmeAgee.getNom() + " " +
                    femmeAgee.getPrenom() + " - " + femmeAgee.getDateNaissance());

            System.out.println("\n=== Épouses d'un homme donné ===");
            List<Mariage> epouses = hommeService.getEpousesEntreDates(1L,
                    LocalDate.of(1990, 1, 1), LocalDate.of(2005, 12, 31));
            epouses.forEach(e -> System.out.println(e.getFemme().getNom() + " " + e.getFemme().getPrenom()));

            System.out.println("\n=== Nombre d'enfants d'une femme ===");
            Integer nbEnfants = femmeService.compterEnfantsEntreDatesNative(1L,
                    LocalDate.of(1990, 1, 1), LocalDate.of(2005, 12, 31));
            System.out.println("Nombre d'enfants : " + nbEnfants);

            System.out.println("\n=== Femmes mariées au moins deux fois ===");
            List<Femme> femmesMultiMaries = femmeService.getFemmesMarieesAuMoinsDeuxFois();
            femmesMultiMaries.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

            System.out.println("\n=== Hommes mariés à 4 femmes ===");
            Long nbHommes = mariageService.compterHommesMariesQuatreFemmes(
                    LocalDate.of(1990, 1, 1), LocalDate.of(2005, 12, 31));
            System.out.println("Nombre d'hommes mariés à 4 femmes : " + nbHommes);

            System.out.println("\n=== Détails des mariages d'un homme ===");
            hommeService.afficherMariagesHomme(1L);

        } finally {
            HibernateUtil.shutdown();        }
    }

    private static void creerDonneesTest(HommeService hommeService, FemmeService femmeService,
                                         MariageService mariageService) {
        Homme h1 = new Homme("STIOUI", "Mohamed", LocalDate.of(1970, 5, 15), "Ville1");
        Homme h2 = new Homme("LAGHOUITI", "Ayman", LocalDate.of(1965, 3, 20), "Ville2");
        Homme h3 = new Homme("LACHGUER", "Alami", LocalDate.of(1975, 8, 10), "Ville3");
        Homme h4 = new Homme("KHALDOUNE", "Adam", LocalDate.of(1980, 12, 5), "Ville4");
        Homme h5 = new Homme("BOUHADDA", "Akram", LocalDate.of(1972, 7, 25), "Ville5");

        hommeService.save(h1);
        hommeService.save(h2);
        hommeService.save(h3);
        hommeService.save(h4);
        hommeService.save(h5);

        Femme f1 = new Femme("KHALDOUN", "fati", LocalDate.of(1972, 6, 10), "VilleA");
        Femme f2 = new Femme("LACHGUER", "Loubna", LocalDate.of(1975, 9, 15), "VilleB");
        Femme f3 = new Femme("IRHARISSA", "Imane", LocalDate.of(1978, 11, 20), "VilleC");
        Femme f4 = new Femme("NJARI", "ROKAYA", LocalDate.of(1980, 2, 25), "VilleD");
        Femme f5 = new Femme("RZAKI", "Nawal", LocalDate.of(1970, 1, 1), "VilleE");
        Femme f6 = new Femme("LACHGUER", "Nisrine", LocalDate.of(1973, 4, 12), "VilleF");
        Femme f7 = new Femme("NADI", "Hajar", LocalDate.of(1976, 7, 18), "VilleG");
        Femme f8 = new Femme("SKITINA", "Karima", LocalDate.of(1979, 10, 22), "VilleH");
        Femme f9 = new Femme("BOUHADDA", "Asma", LocalDate.of(1982, 12, 30), "VilleI");
        Femme f10 = new Femme("CHAIKE", "Asiya", LocalDate.of(1968, 3, 8), "VilleJ");

        femmeService.save(f1);
        femmeService.save(f2);
        femmeService.save(f3);
        femmeService.save(f4);
        femmeService.save(f5);
        femmeService.save(f6);
        femmeService.save(f7);
        femmeService.save(f8);
        femmeService.save(f9);
        femmeService.save(f10);

        Mariage m1 = new Mariage(h1, f1, LocalDate.of(1998, 9, 3), null, 4, "EN_COURS");
        Mariage m2 = new Mariage(h1, f2, LocalDate.of(1998, 8, 3), null, 2, "EN_COURS");
        Mariage m3 = new Mariage(h1, f3, LocalDate.of(1998, 11, 4), null, 3, "EN_COURS");
        Mariage m4 = new Mariage(h1, f4, LocalDate.of(1998, 9, 3), LocalDate.of(1998, 9, 3), 0, "ECHOUE");

        mariageService.save(m1);
        mariageService.save(m2);
        mariageService.save(m3);
        mariageService.save(m4);
    }
}
