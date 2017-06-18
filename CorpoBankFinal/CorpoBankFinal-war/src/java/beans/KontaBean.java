/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.KontoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.KontoInterface;
import model.UzytkownikInterface;

@Named(value = "kontaBean")
@RequestScoped
public class KontaBean implements Serializable{
    
    @EJB
    private KontoFacadeLocal kontoFacade;
    
    public List<KontoInterface> showAccounts() {
        System.out.println("kontaBean pokazKonta()");
        HttpSession session = Util.getSession();
        UzytkownikInterface user = (UzytkownikInterface) session.getAttribute("user");
        List<KontoInterface> list = kontoFacade.findUserAccounts(user);
        return list;  
    }
    
    public String setAccount(KontoInterface account){
        Util.getSession().setAttribute("account", account);
        return "rachunki";
    }
    
    public String changeAccount(){
        return "konta";
    }
    
    public List<UzytkownikInterface> getUsers(){
        HttpSession session = Util.getSession();
        KontoInterface account = (KontoInterface) session.getAttribute("account");
        return kontoFacade.getUsers(account);
        
    }
//    @EJB
//    private KontoFacadeLocal kontoFacade;
//    
//    private int idUzytkownik;
//
//    public int getIdUzytkownik() {
//        return idUzytkownik;
//    }
//
//    public void setIdUzytkownik(int idUzytkownik) {
//        this.idUzytkownik = idUzytkownik;
//    }
//        
//    /**
//     * Creates a new instance of kontaBean
//     */  
//    public KontaBean(){ 
//    }
//        
//    public List<Konto> getKonta(){
//        return kontoFacade.findAll();
//    }          
//    

//
//    public String changeAccount(){
//        return "konta";
//    }
//    

}
