package de.opitz.consulting.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@Entity
public class Countries {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
 
    @NotBlank
    @Length(min=2, max=100)
    @Column( length = 100, nullable = false, unique=true)
    private String name;

    private String alpha2code;
    private String alpha3code;
    private String capital;
    private String region;
    private String subregion;
    private Long population;
    
    private String flag;
    private String continent;
    

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

    public String getAlpha2code() {
        return alpha2code;
    }

    public void setAlpha2code(String alpha2code) {
        this.alpha2code = alpha2code;
    }

    public String getAlpha3code() {
        return alpha3code;
    }

    public void setAlpha3code(String alpha3code) {
        this.alpha3code = alpha3code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "[" + alpha2code + ", " + name + "]";
    }

    public Countries() {
        this.name = "none";
    }

    public Countries(String name, String alpha2code, String capital) {
        this.name = name;
        this.alpha2code = alpha2code;
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Countries(String name, String alpha2code, String alpha3code, String capital, String region, String subregion,
            Long population, String flag, String continent) {
        this.name = name;
        this.alpha2code = alpha2code;
        this.alpha3code = alpha3code;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.flag = flag;
        this.continent = continent;
    }

}