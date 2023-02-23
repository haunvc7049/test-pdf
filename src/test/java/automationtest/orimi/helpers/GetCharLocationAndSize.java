package automationtest.orimi.helpers;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * This is an example on how to get the x/y coordinates and size of each character in PDF
 */
public class GetCharLocationAndSize extends PDFTextStripper {

    public GetCharLocationAndSize() throws IOException {
    }

    /**
     * @throws IOException If there is an error parsing the document.
     */
    public static void main( String[] args ) throws IOException {
        PDDocument document = null;
        String fileName = "apache.pdf";
        try {
            URL urlOfPdf = new URL("https://www.orimi.com/pdf-test.pdf");
            BufferedInputStream fileToParse = new BufferedInputStream(urlOfPdf.openStream());
            document = Loader.loadPDF(fileToParse);

            PDFTextStripper stripper = new GetCharLocationAndSize();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 0 );
            stripper.setEndPage( document.getNumberOfPages() );

            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
        }
        finally {
            if( document != null ) {
                document.close();
            }
        }
    }

    /**
     * Override the default functionality of PDFTextStripper.writeString()
     */
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        for (TextPosition text : textPositions) {
            System.out.println(text.getUnicode()+ " [(X=" + text.getXDirAdj() + ",Y=" +
                    text.getYDirAdj() + ") height=" + text.getHeightDir() + " width=" +
                    text.getWidthDirAdj() + "]");
        }
    }
}