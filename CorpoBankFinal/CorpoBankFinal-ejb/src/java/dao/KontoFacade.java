/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Konto;
import entity.Rachunek;
import entity.Uzytkownik;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.KontoInterface;
import model.RachunekInterface;
import model.UzytkownikInterface;

/**
 *
 * @author Xaoo
 */
@Stateless
public class KontoFacade extends AbstractFacade<KontoInterface> implements KontoFacadeLocal {
    @PersistenceContext(unitName = "CorpoBankFinal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KontoFacade() {
        super(KontoInterface.class);
    }
       
    @Override
    public List<KontoInterface> findUserAccounts(UzytkownikInterface user){
        Uzytkownik u = (Uzytkownik) user;
        ArrayList<KontoInterface> list = new ArrayList<>();
        for( Konto k : u.getKontoCollection()){
            list.add(k);
        }    
        return list;
    }
    
    @Override
    public Konto find(Object id) {
        return getEntityManager().find(Konto.class, id);
    }
    
    @Override
    public List<RachunekInterface> getRachunki(KontoInterface account) {
        Konto k = (Konto) account;
        ArrayList<RachunekInterface> list = new ArrayList<>();
        list.addAll(k.getRachunekCollection());
        return list;    
    }
    
    @Override
    public List<UzytkownikInterface> getUsers(KontoInterface konto) {
        Konto k = (Konto) konto;
        List<UzytkownikInterface> out = new ArrayList();
        out.addAll(k.getUzytkownikCollection());
        return out;
    }
}
