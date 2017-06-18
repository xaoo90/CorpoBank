/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.RachunekFacadeLocal;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.RachunekInterface;

/**
 *
 * @author Dominik
 */
@FacesConverter(value = "accountsConverter")
public class AccountsConverter implements Converter {


    @EJB
    private RachunekFacadeLocal rachunekFacade;
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String out = ((RachunekInterface) o).getNazwa() + "(" + ((RachunekInterface) o).getNumer() + ")" + ((RachunekInterface) o).getSaldo() + ((RachunekInterface) o).getWaluta();
        return out;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int first = value.indexOf("(");
        int last = value.indexOf(")");
        String nr = value.substring(first+1, last);

        return (RachunekInterface) rachunekFacade.findRachunekByNumber(nr);
    }
}