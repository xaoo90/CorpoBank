/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import model.EntityFactory;
import model.PrzelewInterface;

/**
 *
 * @author Dominik
 */
@Named(value = "newTransferBean")
@RequestScoped
public class NewTransferBean {

    @EJB
    private EntityFactory entityFactory;

    private PrzelewInterface transfer = entityFactory.newPrzelew(); 
    
    public PrzelewInterface getTransfer() {
        return transfer;
    }

    public void setTransfer(PrzelewInterface transfer) {
        this.transfer = transfer;
    }
    
    
}
