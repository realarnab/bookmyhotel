package com.bookmyhotel.service;

import com.bookmyhotel.dto.BookingConfirmationDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class PDFService {

    public boolean generatePdf(String fileName, BookingConfirmationDto dto){
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
            Chunk propertyName = new Chunk("Property Name: "+dto.getPropertyName(), font);
            Chunk email = new Chunk("Total nights: "+dto.getGuestEmail(), font);
            Chunk mobile = new Chunk("Total nights: "+dto.getGuestMobile(), font);
            Chunk totalPrice = new Chunk("Total Price: "+dto.getTotalPrice(), font);
            //Chunk property= new Chunk("Property: "+dto.getProperty());

            document.add(bookingConfirmation);
            document.add(new Paragraph("\n"));
            document.add(guestName);
            document.add(new Paragraph("\n"));
            document.add(mobile);
            document.add(new Paragraph("\n"));
            document.add(email);
            document.add(new Paragraph("\n"));
            document.add(checkInDate);
            document.add(new Paragraph("\n"));
            document.add(checkOutDate);
            document.add(new Paragraph("\n"));
            //document.add(propertyName);
           // document.add(new Paragraph("\n"));
            document.add(totalNights);
            document.add(new Paragraph("\n"));
            document.add(propertyName);
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
