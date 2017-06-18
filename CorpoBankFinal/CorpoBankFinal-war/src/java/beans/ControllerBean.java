/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;



/**
 *
 * @author Xaoo
 */
@Named(value = "controllerBean")
@RequestScoped
public class ControllerBean implements Serializable {
   
    
    /**
     * Creates a new instance of ControllerBean
     */
    
    public ControllerBean() {
    }
    
    public boolean userAllowedAccess() {
        System.out.println("ControllerBean userAllowedAccess()");
        String rights = (String) Util.getSession().getAttribute("rights");
        System.out.println("ControllerBean userAllowedAccess() " + "prezes".equals(rights));
        return "prezes".equals(rights);
    }
    
    public String goCheckTransfers(){
        return "checkTransfers";
    }
    
    public String goCalculations(){
        return "goCalculations";
    }
    
    public String goAwaitingTransfers(){
        return "awaitingTransfers";
    }
    public String goTransfersHistory(){
        return "transfersHistory";
    }
    
    public String goNewTransfer(){
        return "newTransfer";
    }
    
    
    public String goRachunki(){
        return "rachunki";
    }
    
    public String goPrzelewy(){
        return "przelewy";
    }
    
    public String zmianaKonta(){
        return "KONTA";
    }
    
    public String administracja(){
        return "ADMINISTRACJA";
    }
    
}
