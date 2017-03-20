import java.util.List;

/**
 * Created by lveeckha on 20/03/2017.
 */
public class DataProcessor {
    public List<DataBreach> dataBreaches;

    public boolean isNationalSecurityOrLawEnforcement() {
        return true;
    }

    public Person hasDataProtectionOfficer() {
        return null;
    }

    public boolean canAskForErasure() {
        return false;
    }
}
