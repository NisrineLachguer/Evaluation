package ma.beans.classes;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mariages")
@NamedQueries({
        @NamedQuery(name = "Mariage.findByHommeBetweenDates",
                query = "SELECT m FROM Mariage m WHERE m.homme.id = :hommeId AND m.dateDebut BETWEEN :dateDebut AND :dateFin"),
        @NamedQuery(name = "Mariage.countEnfantsByFemmeBetweenDates",
                query = "SELECT SUM(m.nombreEnfants) FROM Mariage m WHERE m.femme.id = :femmeId AND m.dateDebut BETWEEN :dateDebut AND :dateFin"),
        @NamedQuery(name = "Mariage.findFemmesMarieesAuMoinsDeuxFois",
                query = "SELECT f FROM Femme f WHERE SIZE(f.mariagesFemme) >= 2")
})
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homme_id", nullable = false)
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme_id", nullable = false)
    private Femme femme;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "nombre_enfants")
    private Integer nombreEnfants;

    @Column(name = "statut", length = 20)
    private String statut; // "EN_COURS", "ECHOUE", "DIVORCE"

    // Constructeurs
    public Mariage() {}

    public Mariage(Homme homme, Femme femme, LocalDate dateDebut, LocalDate dateFin, Integer nombreEnfants, String statut) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreEnfants = nombreEnfants;
        this.statut = statut;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }

    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Integer getNombreEnfants() { return nombreEnfants; }
    public void setNombreEnfants(Integer nombreEnfants) { this.nombreEnfants = nombreEnfants; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
