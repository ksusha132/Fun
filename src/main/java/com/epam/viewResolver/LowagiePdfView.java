package com.epam.viewResolver;

import com.epam.dto.BookTicketDto;
import com.epam.dto.TicketDTO;
import com.epam.dto.UserDto;
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
        List<UserDto> users = (List<UserDto>) model.get("usersPdf");

        TicketDTO ticketDTO = (TicketDTO) model.get("ticketPdf");

        PdfPTable table;

        if (users != null) {
            table = getPdfPTableForUsers(users);
        } else {
            table = getPdfTableForTickets(ticketDTO);
        }

        document.add(table);
    }

    private PdfPTable getPdfPTableForUsers(List<UserDto> users) {
        PdfPTable table = new PdfPTable(3);
        setViewSettings(table);

        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Birthday");

        users.forEach(userDTO -> {
            table.addCell(userDTO.getName());
            table.addCell(userDTO.getEmail());
            table.addCell(DATE_FORMAT.format(userDTO.getBirthday()));
        });
        return table;
    }

    private void setViewSettings(PdfPTable table) {
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBackgroundColor(Color.lightGray);
    }

    private PdfPTable getPdfTableForTickets(TicketDTO ticketDTO) {
        PdfPTable table = new PdfPTable(6);

        setViewSettings(table);

        table.addCell("Event");
        table.addCell("Price");
        table.addCell("Date and time");
        table.addCell("Seat");
        table.addCell("User");
        table.addCell("Auditorium");

        String userName = ticketDTO.getUserName();
        String auditorium = ticketDTO.getAuditorium();

        List<BookTicketDto> bookTicketDtos = ticketDTO.getBookTicketDtos();
        bookTicketDtos.forEach(bookTicket -> {
            table.addCell(bookTicket.getEventName());
            table.addCell(String.valueOf(bookTicket.getPrice()));
            table.addCell(bookTicket.getDateTime().toString());
            table.addCell(String.valueOf(bookTicket.getSeat()));
            table.addCell(userName);
            table.addCell(auditorium);
        });
        return table;
    }
}
