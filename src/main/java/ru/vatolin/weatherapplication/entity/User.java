package ru.vatolin.weatherapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(
            name = "user_locations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    Set<Location> locations = new HashSet<>();

    public void addLocation(Location location) {
        locations.add(location);
    }
}
