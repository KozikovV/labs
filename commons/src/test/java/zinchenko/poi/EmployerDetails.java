package zinchenko.poi;

import java.util.List;

public class EmployerDetails {

    private String effectiveDate;

    private String employerSetupName;

    private AdditionalDetails additionalDetails;

    private List<Location> locations;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getEmployerSetupName() {
        return employerSetupName;
    }

    public void setEmployerSetupName(String employerSetupName) {
        this.employerSetupName = employerSetupName;
    }

    public AdditionalDetails getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(AdditionalDetails additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
