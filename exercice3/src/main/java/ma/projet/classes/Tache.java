package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(
        name = "Tache.findByPrixGreaterThan",
        query = "SELECT t FROM Tache t WHERE t.prix > :prix"
)
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private double prix;

    @Temporal(TemporalType.DATE)
    private Date dateDebutPlanifie;

    @Temporal(TemporalType.DATE)
    private Date dateFinPlanifie;

    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;

    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "tache")
    private List<EmployeTache> employeTaches;

    // Constructeurs
    public Tache() {}

    public Tache(String nom, double prix, Date dateDebutPlanifie, Date dateFinPlanifie, Projet projet) {
        this.nom = nom;
        this.prix = prix;
        this.dateDebutPlanifie = dateDebutPlanifie;
        this.dateFinPlanifie = dateFinPlanifie;
        this.projet = projet;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public Date getDateDebutPlanifie() { return dateDebutPlanifie; }
    public void setDateDebutPlanifie(Date dateDebutPlanifie) { this.dateDebutPlanifie = dateDebutPlanifie; }

    public Date getDateFinPlanifie() { return dateFinPlanifie; }
    public void setDateFinPlanifie(Date dateFinPlanifie) { this.dateFinPlanifie = dateFinPlanifie; }

    public Date getDateDebutReelle() { return dateDebutReelle; }
    public void setDateDebutReelle(Date dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }

    public Date getDateFinReelle() { return dateFinReelle; }
    public void setDateFinReelle(Date dateFinReelle) { this.dateFinReelle = dateFinReelle; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public List<EmployeTache> getEmployeTaches() { return employeTaches; }
    public void setEmployeTaches(List<EmployeTache> employeTaches) { this.employeTaches = employeTaches; }
}