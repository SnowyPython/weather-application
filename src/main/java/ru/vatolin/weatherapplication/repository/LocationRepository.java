package ru.vatolin.weatherapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vatolin.weatherapplication.entity.Location;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByName(String name);
}
