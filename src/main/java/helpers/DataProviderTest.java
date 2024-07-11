package helpers;

import data.inReqres.Authorization;
import org.testng.annotations.DataProvider;

public class DataProviderTest {

    @DataProvider(name = "pageNumber")
    public Object[] pageNumbers() {
        return new Object[]{2};
    }

    @DataProvider(name = "successfulAuthorization")
    public Object[] correctLogIns() {
        return new Object[]{new Authorization("eve.holt@reqres.in", "cityslicka")};
    }

    @DataProvider(name = "unSuccessfulAuthorization")
    public Object[] incorrect_log_ins() {
        return new Object[]{new Authorization("peter@klaven", "")};
    }

    @DataProvider(name = "tagCountProvider")
    public Object[][] tagCountProvider() {
        return new Object[][] {
                { "https://gateway.autodns.com/", 14 }
        };
    }
}
