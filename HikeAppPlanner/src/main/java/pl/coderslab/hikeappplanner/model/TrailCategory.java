package pl.coderslab.hikeappplanner.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class TrailCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "areas_trail_categories",
            joinColumns = {@JoinColumn(name = "trail_category_id")},
            inverseJoinColumns = {@JoinColumn(name = "area_id")})
    private List<Area> areas = new ArrayList<>();

    public TrailCategory() {
    }

    public TrailCategory(String name, String description, List<Area> areas) {
        this.name = name;
        this.description = description;
        this.areas = areas;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Area> getAreas() {
        return areas;
    }

    @Override
    public String toString() {
        return "TrailCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", areas=" + areas +
                '}';
    }
}
