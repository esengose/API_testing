package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ZippopotamBodyPojo {
    private String postCode;
    private String country;
    private String countryAbbreviation;
    private ZippopotamPlacesPojo places;

    public ZippopotamBodyPojo() {
    }

    public ZippopotamBodyPojo(String postCode, String country, String countryAbbreviation, ZippopotamPlacesPojo places) {
        this.postCode = postCode;
        this.country = country;
        this.countryAbbreviation = countryAbbreviation;
        this.places = places;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public ZippopotamPlacesPojo getPlaces() {
        return places;
    }

    public void setPlaces(ZippopotamPlacesPojo places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "ZippopotamBodyPojo{" +
                "postCode='" + postCode + '\'' +
                ", country='" + country + '\'' +
                ", countryAbbreviation='" + countryAbbreviation + '\'' +
                ", places=" + places +
                '}';
    }
}
