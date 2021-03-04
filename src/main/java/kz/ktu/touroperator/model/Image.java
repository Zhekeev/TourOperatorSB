package kz.ktu.touroperator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tour_id", referencedColumnName = "id")
    private Tour tour;
}
