package com.ernest.reefangel.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by ernest on 2017/04/22.
 */
@Service
public class PdfService {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

public String createPdf(String content) throws IOException, DocumentException {
    LocalDateTime now = LocalDateTime.now();

    String FILE = now +".pdf";
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(FILE));
    document.open();

    Paragraph subPara = new Paragraph(content, subFont);
    // now add all this to the document
    document.add(subPara);

    document.close();

    return FILE;

}
}
