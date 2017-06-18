/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.UzytkownikFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import mailer.MailSender;
import model.EntityFactory;
import model.KontoInterface;
import model.UzytkownikInterface;



/**
 *
 * @author Xaoo
 */

@Named(value = "rejestracjaBean")
@RequestScoped
public class RejestracjaBean implements Serializable{
    @EJB
    private UzytkownikFacadeLocal uzytkownikFacade;
    @EJB
    private EntityFactory entityFactory;
    
    private UzytkownikInterface uzytkownik;
    private String potwierdzenieHasla;

    public String getPotwierdzenieHasla() {
        return potwierdzenieHasla;
    }

    public void setPotwierdzenieHasla(String potwierdzenieHasla) {
        this.potwierdzenieHasla = potwierdzenieHasla;
    }
    
    public UzytkownikInterface getUzytkownik() {
        System.out.println("PrzelewyBean getTransfer();");
        HttpSession session = Util.getSession();
        uzytkownik = (UzytkownikInterface) session.getAttribute("newUser");
        if(uzytkownik == null){
            session.setAttribute("newUser",entityFactory.newUzytkownik());
            uzytkownik = (UzytkownikInterface) session.getAttribute("newUser");
        }
        return uzytkownik;
    }

    
//    public String rejestrujUzytkownika(){
//        Random generator = new Random();
//          
//            String haslo = String.valueOf(generator.nextInt(89999)+1000);
//            uzytkownik.setIdUzytkownik(null);
//            uzytkownik.setHaslo(haslo);
//            uzytkownik.setUprawnienia("ksiegowy");
//            uzytkownikFacade.create(uzytkownik);
//            MailSender email = new MailSender();
//            email.setMessage("Witaj " + uzytkownik.getImie() + " " + uzytkownik.getNazwisko() + " Twoje konto zostało zarejestrowane " 
//                    + "\nLogin: " + uzytkownik.getLogin() + "\nHaslo: " + uzytkownik.getHaslo() + "\nPozdrawiam " + Util.getUserName());
//            email.setSubject("CorpoBank - Rejestracja");
//            email.setTo(uzytkownik.getMail());
//            System.out.println(email.sendMail());
//            return "ADMINISTRACJA";
//    }
    
    /**
     * Creates a new instance of rejestracjaBean
     */
    public RejestracjaBean(){
        //uzytkownik = entityFactory.newUzytkownik();
    }
    
//    @PostConstruct
//    public void init(){
//        uzytkownik = new Uzytkownik();
//    }
    
    
    public String rejestracja(){
        HttpSession session = Util.getSession();
        KontoInterface k = (KontoInterface) session.getAttribute("account");
        
        if(uzytkownikFacade.rejestracjaUzytkownika(uzytkownik, k).equals("SUCCESS")){
            MailSender email = new MailSender();
            email.setMessage("Witaj " + uzytkownik.getImie() + " " + uzytkownik.getNazwisko() + " Twoje konto zostało zarejestrowane " 
                    + "\nLogin: " + uzytkownik.getLogin() + "\nHaslo: " + uzytkownik.getHaslo() + "\nPozdrawiam " + Util.getUserName());
            email.setSubject("CorpoBank - Rejestracja");
            email.setTo(uzytkownik.getMail());
            email.sendMail();
            return "ManageAccess";
        }
        else
            return "FALSE";
    }
    
}
