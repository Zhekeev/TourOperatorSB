package kz.ktu.touroperator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "number_of_people")
    private String numberOfPeople;

    @Column(name = "number_of_days")
    private String numberOfDays;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="country_id")
    private Country country;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "tour")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "tour")
    private List<Image> images;

    public String getCountry() {
        return country != null ? country.getName() : "<none>";
    }
}
