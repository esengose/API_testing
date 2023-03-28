package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CovidApiPojo {
    private Integer totalConfirmed;
    private Integer totalDeaths;
    private Integer totalRecovered;

    public CovidApiPojo() {
    }

    public CovidApiPojo(Integer totalConfirmed, Integer totalDeaths, Integer totalRecovered) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
    }

    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(Integer totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    @Override
    public String toString() {
        return "CovidApiPojo{" +
                "totalConfirmed=" + totalConfirmed +
                ", totalDeaths=" + totalDeaths +
                ", totalRecovered=" + totalRecovered +
                '}';
    }
}


