package com.bookmyhotel.service;

import com.bookmyhotel.dto.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PDFService {
    private static final String PDF_DIRECTORY= "C:\\Users\\arnab\\OneDrive\\Documents\\pdfBookmyhotel";

    public boolean generatePdf(String fileName, BookingDto dto){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk bookingConfirmation = new Chunk("Booking Confirmation", font);
            Chunk guestName = new Chunk("Guest Name: "+dto.getGuestName(), font);
            Chunk checkInDate = new Chunk("Check in date: : "+dto.getCheckInDate(), font);
            Chunk checkOutDate = new Chunk("Check out date: "+dto.getCheckOutDate(), font);
            //Chunk propertyName = new Chunk("Property Name: "+dto.getProperty(), font);
            Chunk totalNights = new Chunk("Total nights: "+dto.getTotalNights(), font);
            Chunk totalPrice = new Chunk("Total Price: "+dto.getTotalPrice(), font);

            document.add(bookingConfirmation);
            document.add(new Paragraph("\n"));
            document.add(guestName);
            document.add(new Paragraph("\n"));
            document.add(checkInDate);
            document.add(new Paragraph("\n"));
            document.add(checkOutDate);
            document.add(new Paragraph("\n"));
            //document.add(propertyName);
           // document.add(new Paragraph("\n"));
            document.add(totalNights);
            document.add(new Paragraph("\n"));
            document.add(totalPrice);
            document.add(new Paragraph("\n"));
            document.close();

            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
