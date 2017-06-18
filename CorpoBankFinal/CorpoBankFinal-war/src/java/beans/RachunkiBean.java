/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.KontoFacadeLocal;
import dao.RachunekFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.KontoInterface;
import model.RachunekInterface;
import model.EntityFactory;

/**
 *
 * @author Xaoo
 */
@Named(value = "rachunkiBean")
@RequestScoped
public class RachunkiBean implements Serializable{
    
    @EJB
    KontoFacadeLocal kontoFacade;    
    @EJB
    RachunekFacadeLocal rachunekFacade;
    @EJB
    EntityFactory entityFactory;
    
    private RachunekInterface rachunek;

    public RachunekInterface getRachunek() {
        if (rachunek == null)
            rachunek = entityFactory.newRachunek();        
        return rachunek;
    }

    public void setRachunek(RachunekInterface rachunek) {
        this.rachunek = rachunek;
    }
    
     public List<RachunekInterface> pokazRachunki(){
        HttpSession session = Util.getSession();
        KontoInterface account = (KontoInterface) session.getAttribute("account");
        List<RachunekInterface> list = kontoFacade.getRachunki(account);
        return list;  
    }
     
    public String createRachunek(){              
        KontoInterface konto = (KontoInterface) Util.getSession().getAttribute("account");
        rachunekFacade.createRachunek(konto, rachunek);
        return "ADMINISTRACJA";
    }   

}