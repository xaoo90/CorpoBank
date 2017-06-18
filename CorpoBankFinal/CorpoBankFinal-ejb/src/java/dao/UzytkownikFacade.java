/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utils.Data;
import entity.Konto;
import entity.Uzytkownik;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.KontoInterface;
import model.UzytkownikInterface;

/**
 *
 * @author Xaoo
 */
@Stateless
public class UzytkownikFacade extends AbstractFacade<UzytkownikInterface> implements UzytkownikFacadeLocal {
    @PersistenceContext(unitName = "CorpoBankFinal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UzytkownikFacade() {
        super(UzytkownikInterface.class);
    }

    @Override
    public UzytkownikInterface login(String uname, String password) {
        try{
            Uzytkownik uzytkownik = getEntityManager().createNamedQuery("Uzytkownik.findByLogin", Uzytkownik.class).setParameter("login", uname).getSingleResult();
            if(uzytkownik.getHaslo().equals(password)){
                uzytkownik.setLogowanieUdane(Data.toSqlDate(Data.dzisiejszaData()));
                return uzytkownik;
            }                
            else {
                uzytkownik.setLogowanieNieUdane(Data.toSqlDate(Data.dzisiejszaData()));
                return null;
            }
        } catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public String rejestracjaUzytkownika(UzytkownikInterface uzytkownik, KontoInterface konto){
        try {
            getEntityManager().createNamedQuery("Uzytkownik.findByLogin", Uzytkownik.class).setParameter("login", uzytkownik.getLogin()).getSingleResult();
        } catch (NoResultException e) {
            Random generator = new Random();
            String haslo = String.valueOf(generator.nextInt(89999)+10000);
            Uzytkownik u = (Uzytkownik) uzytkownik;
            Konto k = (Konto) konto;
            Collection<Konto> kontoCollection = new ArrayList<>();
            kontoCollection.add(k);
            u.setKontoCollection(kontoCollection);
            u.setIdUzytkownik(null);
            u.setUprawnienia("ksiegowy");
            u.setHaslo(haslo);
            create(u);           
            k.getUzytkownikCollection().add(u);
            return "SUCCESS";
        }
        return "FALSE"; 
    }
}
