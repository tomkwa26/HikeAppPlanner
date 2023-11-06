package pl.coderslab.hikeappplanner.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trails")
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    private double length;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TrailCategory category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "trails_spots",
            joinColumns = {@JoinColumn(name = "trail_id")},
            inverseJoinColumns = {@JoinColumn(name = "spot_id")})
    private List<Spot> spots = new ArrayList<>();

    private double startLat;

    private double startLon;

    private double endLat;

    private double endLon;

    public Trail() {
    }

    public Trail(String name, String color, double length, String description, TrailCategory category, double startLat, double startLon, double endLat, double endLon) {
        this.name = name;
        this.color = color;
        this.length = length;
        this.description = description;
        this.category = category;
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public TrailCategory getCategory() {
        return category;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public double getStartLat() {
        return startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public double getEndLat() {
        return endLat;
    }

    public double getEndLon() {
        return endLon;
    }

    @Override
    public String toString() {
        return "Trail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", length=" + length +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", startLat=" + startLat +
                ", startLon=" + startLon +
                ", endLat=" + endLat +
                ", endLon=" + endLon +
                '}';
    }
}
