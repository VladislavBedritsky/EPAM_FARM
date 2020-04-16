package org.example.consumer.model;

import java.util.Objects;

public class Currency {

    private String date;
    private String abbreviation;
    private Integer scale;
    private String name;
    private Double officialRate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOfficialRate() {
        return officialRate;
    }

    public void setOfficialRate(Double officialRate) {
        this.officialRate = officialRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(date, currency.date) &&
                Objects.equals(abbreviation, currency.abbreviation) &&
                Objects.equals(scale, currency.scale) &&
                Objects.equals(name, currency.name) &&
                Objects.equals(officialRate, currency.officialRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, abbreviation, scale, name, officialRate);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "date='" + date + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", scale=" + scale +
                ", name='" + name + '\'' +
                ", officialRate=" + officialRate +
                '}';
    }
}
