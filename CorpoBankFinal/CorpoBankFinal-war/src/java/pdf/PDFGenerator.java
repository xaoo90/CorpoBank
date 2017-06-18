package pdf;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

public class PDFGenerator {

    PDDocument document;
    
    private void putText(String text, int x, int y, int fontSize, PDPageContentStream contentStream, PDFont font) throws IOException{
        
        contentStream.beginText();
        contentStream.setFont( font, fontSize );
        contentStream.moveTextPositionByAmount( x, y );
        contentStream.drawString( text );
        contentStream.endText();    
    
    }
    
    private void putImage(String path, float x, float y, PDPageContentStream contentStream) throws IOException{
    
        InputStream in = new FileInputStream(new File(path));
        PDJpeg img = new PDJpeg(document, in);
        contentStream.drawImage(img, x, y);
    
    }

public static void putTable(PDPage page, PDPageContentStream contentStream, float y, float margin, String[][] content) throws IOException {
    
    final int rows = content.length;
    final int cols = content[0].length;
    final float rowHeight = 20f;
    final float tableWidth = 590;
    final float tableHeight = rowHeight * rows;
    final float colWidth[] = {100,125,90,60,50,65,50,50};
    final float cellMargin=5f;

    
    contentStream.setNonStrokingColor(180,180,180);
    contentStream.fillRect(margin, y-rowHeight, tableWidth, rowHeight);
    contentStream.setNonStrokingColor(0,0,0);
    
    float nexty = y;
    for (int i = 0; i <= rows; i++) {
        contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
        nexty-= rowHeight;
    }

    float nextx = margin;
    for (int i = 0; i <= cols; i++) {
        contentStream.drawLine(nextx, y, nextx, y-tableHeight);
        if (i<cols) nextx += colWidth[i];
    }
      
    contentStream.setFont( PDType1Font.HELVETICA_BOLD , 8 );        

    float textx = margin+cellMargin;
    float texty = y-15;        
    for(int i = 0; i < rows; i++){
        for(int j = 0 ; j < content[i].length; j++){
            String text = content[i][j];
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(textx,texty);
            contentStream.drawString(text);
            contentStream.endText();
            textx += colWidth[j];
        }
        texty-=rowHeight;
        textx = margin+cellMargin;
    }
}
    
public void generate(String path, String filename, String name, String [] tablica) throws IOException, COSVisitorException{
    System.out.println("generate");
    document = new PDDocument();
    System.out.println("new document");

    PDPage page = new PDPage();
    
    document.addPage( page );
    System.out.println("page added");

    PDFont font = PDType1Font.HELVETICA_BOLD;
    PDPageContentStream contentStream = new PDPageContentStream(document, page);
    //putImage(path+"\\resources\\css\\img\\logo-padding.jpg",140,700,contentStream);
    int width = (int) page.getMediaBox().getWidth();
    //rozmiar srony 600-770
    System.out.println("logo added");

    int fontSize = 10; 
    
    Date d = new Date();
    int day = d.getDate();
    int month = d.getMonth()+1;
    int year = d.getYear()+1900;
    int hour = d.getHours();
    int minute = d.getMinutes();
    
    String sDay;
    String sMonth;
    String sHour;
    String sMinute;
    if(day <10)
        sDay ="0" + String.valueOf(day);
    else
        sDay = String.valueOf(day);
    
    if(month <10)
        sMonth ="0" + String.valueOf(month);
    else
        sMonth = String.valueOf(month);
    
    if(hour <10)
        sHour ="0" + String.valueOf(hour);
    else
        sHour = String.valueOf(hour);
    
    if(minute <10)
        sMinute ="0" + String.valueOf(minute);
    else
        sMinute = String.valueOf(minute);

    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.drawLine(10, 680, width-10, 680);    
    System.out.println("wystawiono");

    putText("Wystawiono przez:", 40, 650, fontSize, contentStream, font);
    putText(name, 150, 650, 10, contentStream, font);
    putText("Data wydruku:", 63, 630, fontSize, contentStream, font);
    putText(sDay+"-"+sMonth+"-"+year+"  g."+sHour+":"+sMinute, 150, 630, fontSize, contentStream, font);
    contentStream.drawLine(10, 620, width-10, 620);
    
    if (tablica[8].equals("Wyslano"))
        putText("Przelew z rachunku: ", 33, 600, fontSize, contentStream, font);
    else 
        putText("Przelew na rachunek: ", 33, 600, fontSize, contentStream, font);
    
    putText(tablica[0], 150, 600, fontSize, contentStream, font);
    contentStream.drawLine(10, 590, width-10, 590);
    
    if (tablica[8].equals("Wyslano"))
        putText("Numer rachunku odbiorcy:  ", 25, 500, fontSize, contentStream, font);
    else
        putText("Numer rachunku nadawcy:  ", 25, 500, fontSize, contentStream, font);
    
    putText(tablica[1], 160, 500, fontSize, contentStream, font);
    
    if (tablica[8].equals("Odebrano")){
        putText("Data realizacji: ", 85, 470, fontSize, contentStream, font);
        putText(tablica[3], 160, 470, fontSize, contentStream, font);
    } 
    else
        {
        putText("Data wystawienia: ", 65, 470, fontSize, contentStream, font);
            putText(tablica[2], 160, 470, fontSize, contentStream, font);
        putText("Data realizacji: ", 84 , 450, fontSize, contentStream, font);
            putText(tablica[3], 160, 450, fontSize, contentStream, font);
    }
    
        
    putText("Nazwa: ", 120, 430, fontSize, contentStream, font);
    putText(tablica[4], 160, 430, fontSize, contentStream, font);
    
    putText("Adres: ", 121, 410, fontSize, contentStream, font);
    putText(tablica[5], 160, 410, fontSize, contentStream, font);
    
    putText("Tytul: ", 123, 390, fontSize, contentStream, font);
    putText(tablica[6], 160, 390, fontSize, contentStream, font);
    
    putText("Kwota: ", 121, 370, fontSize, contentStream, font);
    if(tablica[8].equals("Wyslano"))
        putText("-"+tablica[7], 160, 370, fontSize, contentStream, font);
    else
        putText("+"+tablica[7], 160, 370, fontSize, contentStream, font);
        
    putText("CorpoBank.", 440, 15, 8, contentStream, font);
    System.out.println("stream close");

    contentStream.close();
    System.out.println("save");

    document.save(path+"\\transfers\\"+filename+".pdf");
    document.close();
    
    }
}
