package com.itextpdf.samples.book.part1.chapter02;

import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.model.Document;
import com.itextpdf.model.element.Paragraph;
import com.itextpdf.samples.GenericTest;
import com.lowagie.database.DatabaseConnection;
import com.lowagie.database.HsqldbConnection;
import com.lowagie.filmfestival.Movie;
import com.lowagie.filmfestival.PojoFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class Listing_02_06_MovieTitles extends GenericTest {
    public static final String DEST = "./target/test/resources/book/part1/chapter02/Listing_02_06_MovieTitles.pdf";

    public static void main(String args[]) throws IOException, SQLException {
        new Listing_02_06_MovieTitles().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws FileNotFoundException, SQLException, UnsupportedEncodingException {
        // Initialize document
        FileOutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        // Make the connection to the database
        DatabaseConnection connection = new HsqldbConnection("filmfestival");
        List<Movie> movies = PojoFactory.getMovies(connection);
        for (Movie movie : movies) {
            doc.add(new Paragraph(movie.getTitle()));
        }
        doc.close();
    }
}