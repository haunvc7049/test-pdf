package automationtest.orimi.tasks;

import automationtest.orimi.pages.HomePage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;

public class Navigate {
    public static Performable toTheOrimiPage() {
        return Open.browserOn(new HomePage());
    }
}
