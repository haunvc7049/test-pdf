package automationtest.orimi.tests;

import automationtest.orimi.helpers.GetTextPDF;
import automationtest.orimi.tasks.Navigate;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.containsString;

@ExtendWith(SerenityJUnit5Extension.class)
public class PDFTest {
    @CastMember(name = "tester")
    Actor tester;

    @BeforeEach
    public void accessOrimi() {
        givenThat(tester).attemptsTo(Navigate.toTheOrimiPage());
    }

    @Test
    public void demo1() {
        then(tester).should(
                seeThat(GetTextPDF.ofCurrentPage(), containsString("Yukon Department of Education"))
        );
    }
}
