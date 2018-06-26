package pe.edu.upc.RsAuth.domains;


/**
 * Created by Paolo Ortega on 23/06/2018.
 */
public class Country {
    private Integer countryId;
    private String countryName;
    private Integer state;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", state=" + state +
                '}';
    }
}
