package pl.coderslab.hikeappplanner.model;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    public DailySelection() {
    }

    public DailySelection(Hike hike, TrailCategory category, Trail trail, Spot spot) {
        this.hike = hike;
        this.category = category;
        this.trail = trail;
        this.spot = spot;
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

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    @Override
    public String toString() {
        return "DailySelection{" +
                "id=" + id +
                ", hike=" + hike +
                ", category=" + category +
                ", trail=" + trail +
                ", spot=" + spot +
                '}';
    }
}
