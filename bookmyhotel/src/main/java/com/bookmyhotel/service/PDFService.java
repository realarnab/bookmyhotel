package com.bookmyhotel.service;

import com.bookmyhotel.dto.BookingConfirmationDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class PDFService {

    public boolean generatePdf(String fileName, BookingConfirmationDto dto){
        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(fileName));
//
//            document.open();
//            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//            Chunk bookingConfirmation = new Chunk("Booking Confirmation", font);
//            Chunk guestName = new Chunk("Guest Name: "+dto.getGuestName(), font);
//            Chunk checkInDate = new Chunk("Check in date: : "+dto.getCheckInDate(), font);
//            Chunk checkOutDate = new Chunk("Check out date: "+dto.getCheckOutDate(), font);
//            //Chunk propertyName = new Chunk("Property Name: "+dto.getProperty(), font);
//            Chunk totalNights = new Chunk("Total nights: "+dto.getTotalNights(), font);
//            Chunk propertyName = new Chunk("Property Name: "+dto.getPropertyName(), font);
//            Chunk email = new Chunk("Total nights: "+dto.getGuestEmail(), font);
//            Chunk mobile = new Chunk("Total nights: "+dto.getGuestMobile(), font);
//            Chunk totalPrice = new Chunk("Total Price: "+dto.getTotalPrice(), font);
//            //Chunk property= new Chunk("Property: "+dto.getProperty());
//
//            document.add(bookingConfirmation);
//            document.add(new Paragraph("\n"));
//            document.add(guestName);
//            document.add(new Paragraph("\n"));
//            document.add(mobile);
//            document.add(new Paragraph("\n"));
//            document.add(email);
//            document.add(new Paragraph("\n"));
//            document.add(checkInDate);
//            document.add(new Paragraph("\n"));
//            document.add(checkOutDate);
//            document.add(new Paragraph("\n"));
//            //document.add(propertyName);
//           // document.add(new Paragraph("\n"));
//            document.add(totalNights);
//            document.add(new Paragraph("\n"));
//            document.add(propertyName);
//            document.add(new Paragraph("\n"));
//            document.add(totalPrice);
//            document.add(new Paragraph("\n"));
//            document.close();
//
//            return true;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            // Create a table with 2 columns
            PdfPTable table = new PdfPTable(2);

            // Define fonts for headers and data
            Font boldFont = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
            Font titleFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLUE);
            Font subTitleFont = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            // Add document title as a separate paragraph
            Paragraph title = new Paragraph("BookMyHotel - Book Your Property for Your Holiday Destination", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            //add space
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph subTitle = new Paragraph("Booking Confirmation", subTitleFont);
            title.setAlignment(Element.ALIGN_TOP);
            document.add(subTitle);

            // Add space before the table
            document.add(new Paragraph(" "));

            // Add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Detail", boldFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Value", boldFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // Add data rows
            table.addCell(new Phrase("Booking id", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(String.valueOf(dto.getId()), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Guest Name", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(dto.getGuestName(), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Email", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(dto.getGuestEmail(), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Mobile", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(dto.getGuestMobile(), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Check In Date", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(String.valueOf(dto.getCheckInDate()), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Check Out Date", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(String.valueOf(dto.getCheckOutDate()), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Property Name", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(dto.getPropertyName(), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Total Nights", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(dto.getTotalNights().toString(), FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase("Total Price", FontFactory.getFont(FontFactory.COURIER, 10)));
            table.addCell(new Phrase(dto.getTotalPrice().toString(), FontFactory.getFont(FontFactory.COURIER, 10)));

            // Add table to document
            document.add(table);

            document.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
