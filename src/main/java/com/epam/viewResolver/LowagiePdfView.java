package com.epam.viewResolver;

import com.epam.dto.UserDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;

public class LowagiePdfView extends AbstractPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        @SuppressWarnings("unchecked")
        List<UserDTO> users = (List<UserDTO>) model.get("usersPdf");

        PdfPTable table = new PdfPTable(3);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBackgroundColor(Color.lightGray);

        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Birthday");

        if (users != null) {
            users.forEach(userDTO -> {
                table.addCell(userDTO.getName());
                table.addCell(userDTO.getEmail());
                table.addCell(DATE_FORMAT.format(userDTO.getBirthday()));
            });
        }

        document.add(table);
    }
}
