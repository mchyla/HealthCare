package com.example.marcin.healthcare.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.marcin.healthcare.R;
import com.example.marcin.healthcare.model.Kid;
import com.example.marcin.healthcare.model.SchoolCoordinator;
import com.example.marcin.healthcare.repository.OrmLiteKidRepository;
import com.example.marcin.healthcare.repository.SchoolCoordinatorRepository;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by mchyl on 02/05/2017.
 */

public class GenerateDocument {

    public static void genrateDocument(Context context, String building, String city, String route,
                                       String hours, String eventDate, String eventStart,
                                       String eventEnd, String collegeNumber,
                                       String telephone) throws IOException, DocumentException, SQLException {
        String path = Environment.getExternalStorageDirectory().toString() + "/liderap/zgodatemplate.pdf";
        //String path = Environment.DIRECTORY_DOCUMENTS + "/zgodatemplate.pdf";
        Document document = new Document();

        try{
            OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(path),"ISO-8859-1");
            out.write("");
            PdfWriter.getInstance(document, new FileOutputStream(path));
            Log.e("PATH: ", path);
        } catch (FileNotFoundException e){
            Log.e("PATH: ", path);

            final File dir = new File(Environment.getExternalStorageDirectory().toString() + "/liderap");
            dir.mkdirs(); //create folders where write files
            final File file = new File(dir, "zgodatemplate.pdf");
            OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(file),"ISO-8859-1");
            out.write("");
            FileOutputStream f = new FileOutputStream(file);

            PdfWriter.getInstance(document, new FileOutputStream(file));

        }


        document.open();
        String pathF = "/system/fonts/DroidSans.ttf";
        File file = new File(pathF);
        File ff[] = file.listFiles();

        BaseFont timesRoman = BaseFont.createFont(pathF, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font times12 = new com.itextpdf.text.Font(timesRoman, 12);
        Font times12Bold = new Font(timesRoman, 12, Font.BOLD);
        com.itextpdf.text.Font times10 = new com.itextpdf.text.Font(timesRoman, 10);
        Font times10Bold = new Font(timesRoman, 10, Font.BOLD);

        //Chunk glue = new Chunk(new VerticalPositionMark());

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = sdf.format(date);
        List<Kid> listOfKids = OrmLiteKidRepository.findAll(context);

        for (int i = 0; i < listOfKids.size(); i++) {

            PdfPTable head = new PdfPTable(3);
            head.setWidthPercentage(100);

            PdfPCell nullCell = new PdfPCell();
            nullCell.setBorder(Rectangle.NO_BORDER);
            head.addCell(nullCell);
            Phrase oswtext = new Phrase("Oświadczenie", times12);
            //oswtext.setFont(times12);
            PdfPCell oswCell = new PdfPCell(oswtext);
            oswCell.setBorder(Rectangle.NO_BORDER);
            oswCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            head.addCell(oswCell);

            PdfPCell dateCell = new PdfPCell(new Phrase(strDate, times10));
            dateCell.setBorder(Rectangle.NO_BORDER);
            dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            head.addCell(dateCell);

            document.add(head);

            PdfPTable title = new PdfPTable(1);
            title.setWidthPercentage(100);
            Phrase titleText = new Phrase("ZGODA  RODZICA, (OPIEKUNA PRAWNEGO)\n" +
                    "NA UDZIAŁ DZIECKA W WYDARZENIU ZORGANIZOWANYM PRZEZ AKADEMIĘ PRZYSZŁOŚCI\n", times10);
            PdfPCell titleCell = new PdfPCell(titleText);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            title.addCell(titleCell);
            document.add(title);

            Phrase kidText = new Phrase("Wyrażam zgodę na udział mojego dziecka "
                    + listOfKids.get(i).getNameChanged() + " " + listOfKids.get(i).getLastNameChanged()
                    + " w wydarzeniu, które odbędzie się \nw budynku "
                    + building + " na ulicy " + route + " w " + city + " w dniu "
                    + eventDate + " roku w godzinach " + hours + ".", times10);

            PdfPTable body = new PdfPTable(1);
            PdfPCell bodyCell = new PdfPCell(kidText);
            bodyCell.setBorder(Rectangle.NO_BORDER);
            bodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            body.addCell(bodyCell);
            document.add(body);

            if (eventStart != "" && eventEnd != "") {
                PdfPTable eventStartEnd = new PdfPTable(1);
                Phrase eventStartEndText = new Phrase("Wyjście sprzed szkoły nastąpi o godzinie " + eventStart
                        + ". Powrót planowany jest na godzinę " + eventEnd + ".\n"
                        + "Dziecko zostanie zabrane na spotkanie i odwiezione po nim pod Szkołę Podstawową nr "
                        + collegeNumber + ".", times10);
                PdfPCell eventStartEndCell = new PdfPCell(eventStartEndText);
                eventStartEndCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                eventStartEndCell.setBorder(Rectangle.NO_BORDER);
                eventStartEnd.addCell(eventStartEndCell);
                document.add(eventStartEnd);
            }

            PdfPTable permission = new PdfPTable(1);
            Phrase permissionText = new Phrase("Wyrażam zgodę na samodzielny powrót " +
                    "dziecka do domu  / odebranie go przez osobę dorosłą*.\n"
                    + "Za szkody wynikające  z nieprzestrzegania regulaminu wyjść, " +
                    "AKADEMIA PRZYSZŁOŚCI odpowiada rodzic.", times10);
            PdfPCell permissionCell = new PdfPCell(permissionText);
            permissionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            permissionCell.setBorder(Rectangle.NO_BORDER);
            permission.addCell(permissionCell);
            document.add(permission);

            PdfPTable end = new PdfPTable(1);
            Phrase endText = new Phrase("…………………………………………………………" +
                    "\n/ Podpis rodzica, (prawnego opiekuna)/\n" +
                    "Tel. kontaktowy : " + telephone, times10);
            PdfPCell endCell = new PdfPCell(endText);
            endCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            endCell.setBorder(Rectangle.NO_BORDER);
            end.addCell(endCell);
            document.add(end);

            PdfPTable star = new PdfPTable(1);
            Phrase starText = new Phrase("*niepotrzebne proszę skreślić\n------------------------" +
                    "----------------------------------------------------------------------------" +
                    "--------------------------------------------------", times10);
            PdfPCell starCell = new PdfPCell(starText);
            starCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            starCell.setBorder(Rectangle.NO_BORDER);
            star.addCell(starCell);
            document.add(star);
/*


            Paragraph line = new Paragraph("-------------------------------------------------------" +
                    "------------------------------------------------------------------------------" +
                    "------------------------");
            document.add(line);*/
            if(((i % 2) == 0) && i != 0) {
                document.newPage();
            }
        }

        document.close();

        String email = SchoolCoordinatorRepository.findAll(context).get(0).getMail();
        Log.e("TEST WYSYLANIA ->",email);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Wydarzenie - "+ eventDate);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Dzień dobry\nW załączniku wysyłam PDF z " +
                "oświadczeniami dla rodziców w związku z wydarzeniem dla dzieci, plik jest gotowy do druku." +
                "\n Pozdrawiam\nLider kolegium nr: "+collegeNumber + " w "+city);
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+Environment.getExternalStorageDirectory().toString() + "/liderap/zgodatemplate.pdf"));

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public static PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
}
