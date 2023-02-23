package automationtest.orimi.helpers;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class GetTextPDF implements Question<String> {
    public static GetTextPDF ofCurrentPage() {
        return new GetTextPDF();
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            URL urlOfPdf = new URL(BrowseTheWeb.as(actor).getDriver().getCurrentUrl());
            BufferedInputStream fileToParse = new BufferedInputStream(urlOfPdf.openStream());
            PDDocument document = Loader.loadPDF(fileToParse);

            return new PDFTextStripper().getText(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
