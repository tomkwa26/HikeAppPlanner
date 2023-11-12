package pl.coderslab.hikeappplanner.model;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private double startLat;

    private double startLon;

    private double endLat;

    private double endLon;

    public Trail() {
    }

    public Trail(String name, String color, double length, String description, TrailCategory category, Area area, double startLat, double startLon, double endLat, double endLon) {
        this.name = name;
        this.color = color;
        this.length = length;
        this.description = description;
        this.category = category;
        this.area = area;
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

    public Area getArea() {
        return area;
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
                ", area=" + area +
                ", startLat=" + startLat +
                ", startLon=" + startLon +
                ", endLat=" + endLat +
                ", endLon=" + endLon +
                '}';
    }
}
