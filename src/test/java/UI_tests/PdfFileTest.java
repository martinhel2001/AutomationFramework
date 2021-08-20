package UI_tests;

import BaseTest.BaseTest_UI;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static baseMain.PDF_ContentReader.readPdfContent;

public class PdfFileTest extends BaseTest_UI {

    @Test
    public void verifyContentInPDf() {
        //specify the url of the pdf file
        String url ="http://www.pdf995.com/samples/pdf.pdf";
        driver.get(url);
        try {
            String pdfContent = readPdfContent(url);
            Assert.assertTrue(pdfContent.contains("The Pdf995 Suite offers the following features"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
