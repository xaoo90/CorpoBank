/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PrzelewFacadeLocal;
import dao.RachunekFacadeLocal;
import dao.UzytkownikFacadeLocal;
import data.Data;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.PrzelewInterface;
import model.RachunekInterface;

/**
 *
 * @author Xaoo
 */
@Named(value = "autoryzacjaBeans")
@SessionScoped
public class AutoryzacjaBean implements Serializable{

    /**
     * Creates a new instance of AutoryzacjaBeans
     */
    public AutoryzacjaBean() {
    }
    
    @EJB
    private PrzelewFacadeLocal przelewFacade;
    
    @EJB
    private RachunekFacadeLocal rachunekFacade;
    
    @EJB
    private UzytkownikFacadeLocal uzytkownikFacade;
    
    
    
    private int kod = 0;
    private int wprowadzonyKod;

    public int getWprowadzonyKod() {
        return wprowadzonyKod;
    }

    public void setWprowadzonyKod(int wprowadzonyKod) {
        this.wprowadzonyKod = wprowadzonyKod;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }    
    
//    public String autoryzacjaMail(int id){
//        Random generator = new Random();
//        
//        PrzelewInterface przel = przelewFacade.find(id);
//        
//        RachunekInterface rach = rachunekFacade.find(przel.getRachunek().getIdRachunek());
//        int roznica = rach.getSaldo() - przel.getKwota();
//        
//        if (roznica < 0 ){
//            return "AUTORYZACJA";
//        }
//        
//        setKod(generator.nextInt(1000)+1);
//                
//        MailSender email = new MailSender();
//        email.setMessage("Przelew na rachunek "+ przel.getNrRachunku() + " do " + przel.getNazwa() 
//                + " " + przel.getAdres() + " w kwocie " + przel.getKwota()+przel.getRachunek().getWaluta() + "\n" + "KOD " + getKod());
//        email.setSubject("CorpoBank - Autoryzacja przelewu");
//        email.setTo(uzytkownikFacade.find(Util.getUserId()).getMail());
//        email.sendMail();
//        System.out.println(email.sendMail());
//        
//        return "AUTORYZACJA";
//    }  
    
    
    
//    public String autoryzacjaPrzelewu(int id){
//        
//        if (getKod() != getWprowadzonyKod() ){
//            return "AUTORYZACJA";
//        }
//        
//        PrzelewInterface przel = przelewFacade.find(id);
//        przel.setStatus("Wyslano");
//        przel.setDataRealizacji(Data.toSqlDate(Data.dzisiejszaData()));
//        
//        RachunekInterface rach = rachunekFacade.find(przel.getRachunek().getIdRachunek());
//        int roznica = rach.getSaldo() - przel.getKwota();
//        
//        
//        rach.setSaldo(roznica);
//               
//        rachunekFacade.edit(rach);
//        przelewFacade.edit(przel);
//        
//        setKod(0);
//        return "AUTORYZACJA";
//    } 
    
}
