package beans;
 
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class Util implements Serializable{

      public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("username").toString();
      }
       
      public static Integer getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (Integer) session.getAttribute("userid");
        else
            return null;
      }
      
      public static int monthToInt(String month){
          switch(month){
            case "Styczeń"  : return 0;
            case "Luty"     : return 1;
            case "Marzec"   : return 2;
            case "Kwiecień" : return 3;
            case "Maj"      : return 4;
            case "Czerwiec" : return 5;
            case "Lipiec"   : return 6;
            case "Sierpień" : return 7;
            case "Wrzesień" : return 8;
            case "Październik":return 9;
            case "Listopad" : return 10;
            case "Grudzień": return 11;
            default : return -1;
          }
      }
      
    public static void download() throws IOException {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext ec = fc.getExternalContext();

        File file = new File("out.pdf");
        //Faces.sendFile(file, true);
        /*String fileName = file.getName();
        String contentType = ec.getMimeType(fileName); // JSF 1.x: ((ServletContext) ec.getContext()).getMimeType(fileName);
        int contentLength = (int) file.length();
        
        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(contentType); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
        OutputStream output = ec.getResponseOutputStream();
        // Now you can write the InputStream of the file to the above OutputStream the usual way.
        // ...

        Files.copy(file.toPath(), output);

        // ...

        */

        
        //fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }
}