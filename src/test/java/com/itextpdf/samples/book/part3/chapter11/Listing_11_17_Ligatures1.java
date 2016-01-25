package com.itextpdf.samples.book.part3.chapter11;

import com.itextpdf.basics.font.PdfEncodings;
import com.itextpdf.core.font.PdfFont;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.model.Document;
import com.itextpdf.model.element.Paragraph;
import com.itextpdf.samples.GenericTest;

import java.io.FileOutputStream;

public class Listing_11_17_Ligatures1 extends GenericTest {
    public static final String DEST = "./target/test/resources/book/part3/chapter11/Listing_11_17_Ligatures1.pdf";

    public static void main(String[] args) throws Exception {
        new Listing_11_17_Ligatures1().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        //Initialize writer
        FileOutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);

        //Initialize document
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        PdfFont font = PdfFont.createFont(/*"c:/windows/fonts/arial.ttf"*/"./src/test/resources/font/FreeSans.ttf",
                PdfEncodings.CP1252, true);
        document.add(new Paragraph("Movie title: Love at First Hiccough (Denmark)").setFont(font));
        document.add(new Paragraph("directed by Tomas Villum Jensen").setFont(font));
        document.add(new Paragraph("K\u00e6rlighed ved f\u00f8rste hik").setFont(font));
        document.add(new Paragraph(ligaturize("Kaerlighed ved f/orste hik")).setFont(font));

        //Close document
        document.close();
    }

    /**
     * Method that makes the ligatures for the combinations 'a' and 'e'
     * and for '/' and 'o'.
     *
     * @param s a String that may have the combinations ae or /o
     * @return a String where the combinations are replaced by a unicode character
     */
    public String ligaturize(String s) {
        int pos;
        while ((pos = s.indexOf("ae")) > -1) {
            s = s.substring(0, pos) + '\u00e6' + s.substring(pos + 2);
        }
        while ((pos = s.indexOf("/o")) > -1) {
            s = s.substring(0, pos) + '\u00f8' + s.substring(pos + 2);
        }
        return s;
    }
}