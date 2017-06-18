/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utils.Data;
import entity.AbstractFacade;
import entity.Konto;
import entity.Przelew;
import entity.Rachunek;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.KontoInterface;
import model.RachunekInterface;

/**
 *
 * @author Xaoo
 */
@Stateless
public class RachunekFacade extends AbstractFacade<RachunekInterface> 
implements RachunekFacadeLocal, Serializable {
    @PersistenceContext(unitName = "CorpoBankFinal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public RachunekFacade() {
        super(RachunekInterface.class);
    }
    
    @Override
    public Rachunek find(Object id) {
        return getEntityManager().find(Rachunek.class, id);
    }

    @Override
    public RachunekInterface findRachunekByNumber(String number) {
        
        try{
            Rachunek rachunek = getEntityManager().createNamedQuery("Rachunek.findByNumer",Rachunek.class).setParameter("numer", number).getSingleResult();
            return rachunek;
        
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Map ownCapital(KontoInterface konto) {
        Map<String, Integer> map = new HashMap<>();
        Konto k = (Konto) konto;
        for(Rachunek r : k.getRachunekCollection()){
            if(!map.containsKey(r.getWaluta())){
                map.put(r.getWaluta(), 0);
            }
            map.put(r.getWaluta(), map.get(r.getWaluta()) + r.getSaldo());
        }
        return map;
    }

    @Override
    public List<int[]> transactionsByYear(RachunekInterface rachunek, int year){
        ArrayList<int[]> out = new ArrayList<>();
        int i;
        for(i=0;i<12; i++){
            out.add(i, new int[3]);
        }
        Rachunek r = find(rachunek.getIdRachunek());
        for(Przelew p : r.getPrzelewCollection()){
            if (p.getDataRealizacji() != null){
                if( p.getDataRealizacji().getYear() +1900 == year){
                    int index = p.getDataRealizacji().getMonth();
                    switch (p.getStatus()) {
                        case "Odebrano":
                            out.get(index)[0] += p.getKwota();
                            break;
                        case "Wyslano":
                            out.get(index)[1] += p.getKwota();
                            break;
                    }
                                        
                }
            }
        }            
        return out;
    }            
    
    
    @Override
    public void createRachunek(KontoInterface konto, RachunekInterface rachunek) {
        Rachunek r = new Rachunek();
        
        r.setNazwa(rachunek.getNazwa());
        r.setWaluta(rachunek.getWaluta());
        r.setDataZalozenia(Data.toSqlDate(Data.dzisiejszaData()));
        r.setIdKonto(konto);
        r.setIdRachunek(null);
        r.setSaldo(0);
        r.setNumer(generateNumber());     
             
        create(r);        
        
        Konto k = (Konto) konto;
        k.getRachunekCollection().add(r);
    }
    
    private String generateNumber(){
        Random r = new Random();
        String nr = "542490104400004200";        
        int i;
        for(i=0; i<2; i++){
            nr = nr + String.valueOf((int)(Math.random()*9000)+1000);
        
        }
        return nr;
    }
}
