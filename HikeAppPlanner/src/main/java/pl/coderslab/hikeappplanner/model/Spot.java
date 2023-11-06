package pl.coderslab.hikeappplanner.model;

import javax.persistence.*;

@Entity
@Table(name = "spots")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double height;

    private String description;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private double latitude;

    private double longitude;

    public Spot() {
    }

    public Spot(String name, double height, String description, Area area, double latitude, double longitude) {
        this.name = name;
        this.height = height;
        this.description = description;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }

    public Area getArea() {
        return area;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", description='" + description + '\'' +
                ", area=" + area +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
