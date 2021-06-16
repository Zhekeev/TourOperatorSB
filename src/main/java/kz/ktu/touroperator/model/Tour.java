package kz.ktu.touroperator.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tour")
public class Tour {
    @Id
    private Long id;

    @NotEmpty(message = "Пожалуйста заполните поле")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Пожалуйста заполните поле")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "Пожалуйста заполните поле")
    @Column(name = "date")
    private Date date;

    @NotEmpty(message = "Пожалуйста заполните поле")
    @Column(name = "price")
    private BigDecimal price;

    @NotEmpty(message = "Пожалуйста заполните поле")
    @Column(name = "number_of_people")
    private String numberOfPeople;

    @NotEmpty(message = "Пожалуйста заполните поле")
    @Column(name = "number_of_days")
    private String numberOfDays;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "image")
    private String image;

    @Column(name = "first_photo")
    private String firstPhoto;

    @Column(name = "second_photo")
    private String secondPhoto;

    @Column(name = "third_photo")
    private String thirdsPhoto;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "tour")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "tour")
    private List<Image> images;

    public String getCountry() {
        return country != null ? country.getDescription() : "<none>";
    }

    public Tour() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstPhoto() {
        return firstPhoto;
    }

    public void setFirstPhoto(String firstPhoto) {
        this.firstPhoto = firstPhoto;
    }

    public String getSecondPhoto() {
        return secondPhoto;
    }

    public void setSecondPhoto(String secondPhoto) {
        this.secondPhoto = secondPhoto;
    }

    public String getThirdsPhoto() {
        return thirdsPhoto;
    }

    public void setThirdsPhoto(String thirdsPhoto) {
        this.thirdsPhoto = thirdsPhoto;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
