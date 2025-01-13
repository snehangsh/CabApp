package com.project.cab.cabApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.geolatte.geom.Point;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name="idx_driver_vehicle_id", columnList = "vehicleId")
})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private Double rating;
    private Boolean available;

    private String vehicleId;
    //this is column definition, of type geometry, 4326 is to define that we are implementing Earth
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point currentLocation;


}
