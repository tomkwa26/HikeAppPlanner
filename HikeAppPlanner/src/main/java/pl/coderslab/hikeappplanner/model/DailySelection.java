package pl.coderslab.hikeappplanner.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "selections")
public class DailySelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hike_id")
    private Hike hike;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TrailCategory category;

    @ManyToOne
    @JoinColumn(name = "trail_id")
    private Trail trail;

    private LocalDate date;

    public DailySelection() {
    }

    public DailySelection(Hike hike, TrailCategory category, Trail trail, LocalDate date) {
        this.hike = hike;
        this.category = category;
        this.trail = trail;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hike getHike() {
        return hike;
    }

    public void setHike(Hike hike) {
        this.hike = hike;
    }

    public TrailCategory getCategory() {
        return category;
    }

    public void setCategory(TrailCategory category) {
        this.category = category;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        this.trail = trail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DailySelection{" +
                "id=" + id +
                ", hike=" + hike +
                ", category=" + category +
                ", trail=" + trail +
                ", date=" + date +
                '}';
    }
}
