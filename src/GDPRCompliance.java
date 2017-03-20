import java.util.Date;

/**
 * Created by lveeckha on 20/03/2017.
 */
public class GDPRCompliance
{
    public Compliance isCompliant(DataProcessor processor, ProcessedData data)
    {
        Country processorcountry = DataProcessorLocationDetector.detect(data);
        Country datacountry = DataProcessorLocationDetector.detect(processor);
        Date current = new Date();
        if (current.compareTo(new Date(2018, 5, 25)) < 0)//25 May 2018
        {
            return Compliance.NOTAPPLICABLE;
        }
        if (!processorcountry.isEuropean() && !datacountry.isEuropean())
        {
            return Compliance.NOTAPPLICABLE;
        }
        if (!PersonalDetector.isPersonal(data) || processor.isNationalSecurityOrLawEnforcement())
        {
            return Compliance.COMPLIANCE;
        }
        if (SupervisoryAuthority.hasReceivedComplaint())
        {
            return Compliance.NONCOMPLIANCE;
        }
        if (!DataCollection.hasDataDefined(data.consent) || !DataCollection.hasPurposeDefined(data.consent) )
        {
            return Compliance.NONCOMPLIANCE;
        }
        Person person = processor.hasDataProtectionOfficer();
        DataProtectionOfficer.isOfficer(person);

        if (DataBreach.isDetected(processor) && !DataBreach.isReportedWithin72Hours(processor.dataBreaches))
        {
            return Compliance.NONCOMPLIANCE;
        }
        if (processor.canAskForErasure() && CustomerRightForErasure.hasAskedErasure())
        {
            return Compliance.NONCOMPLIANCE;
        }
        if (!data.isPortable())
        {
            return Compliance.NONCOMPLIANCE;
        }
        return Compliance.COMPLIANCE;
    }
}
