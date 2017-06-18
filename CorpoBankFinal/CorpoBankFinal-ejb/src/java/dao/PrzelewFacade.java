/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utils.Data;
import entity.Przelew;
import entity.Rachunek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.KontoInterface;
import model.PrzelewInterface;
import model.RachunekInterface;

/**
 *
 * @author Xaoo
 */
@Stateless
public class PrzelewFacade extends AbstractFacade<PrzelewInterface> implements PrzelewFacadeLocal {
    @EJB
    private RachunekFacadeLocal rachunekFacade;
    
    @PersistenceContext(unitName = "CorpoBankFinal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrzelewFacade() {
        super(PrzelewInterface.class);
    }

    public List<Przelew> findAll() {
        System.out.println("PrzelewFacade findAll();");
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Przelew.class));
        System.out.println("PrzelewFacade findAll(); returning");

        return getEntityManager().createQuery(cq).getResultList();

    }
    
    @Override
    public List<PrzelewInterface> getTransfers(KontoInterface konto, RachunekInterface rachunek, String status){
        System.out.println("PrzelewFacade getAwaitingTransfers("+konto + " " + rachunek + ");");
        List<PrzelewInterface> list = new ArrayList<>();     
        List<Przelew> in = findAll();        
        if(in.isEmpty())
            System.out.println("PrzelewFacade getAwaitingTransfers(); in.isEmpty");

        for(Przelew p : in){           
            if(Objects.equals(p.getRachunek().getIdKonto().getIdKonto(), konto.getIdKonto())){
                if(status == null){
                    list.add(p);
                } else {
                if(status.equals(p.getStatus()))
                    list.add(p);
                }
            }
        }
        
        if( rachunek == null ){
            return list;
        } else {
            List<PrzelewInterface> listForRachunek = new ArrayList<>();
            for( PrzelewInterface p : list ){
                Przelew przelew = (Przelew) p;
                if(Objects.equals(rachunek.getIdRachunek(), przelew.getRachunek().getIdRachunek())){
                    listForRachunek.add(przelew);
                }
            }
            return listForRachunek;
        }
    }

    @Override
    public List<PrzelewInterface> getTransfersHistory(KontoInterface konto, RachunekInterface rachunek){

        List<PrzelewInterface> list = new ArrayList<>();
        for(Przelew p : findAll()){
            if(Objects.equals(p.getRachunek().getIdKonto().getIdKonto(), konto.getIdKonto())){
                list.add(p);
            }
        }
        if( rachunek == null ){
            return list;
        } else {
            List<PrzelewInterface> listForRachunek = new ArrayList<>();
            for( PrzelewInterface p : list ){
                Przelew przelew = (Przelew) p;
                if(Objects.equals(rachunek.getIdRachunek(), przelew.getRachunek().getIdRachunek())){
                    listForRachunek.add(przelew);
                }
            }
            return listForRachunek;
        }
        
    }

    @Override
    public RachunekInterface getAccountByTransfer(PrzelewInterface przelew) {
        Przelew p = (Przelew) przelew;
        return p.getRachunek();
    }

    @Override
    public boolean authorizeTransfer(PrzelewInterface p){
        Przelew przelew = (Przelew) p;
        System.out.println("PrzelewFacade authorizeTransfer(); przelew: " + przelew);
        Rachunek rachunek = (Rachunek) przelew.getRachunek();
        System.out.println("PrzelewFacade authorizeTransfer(); rachunek: " + rachunek);

        if(rachunek.getSaldo() > przelew.getKwota()){
            rachunek.setSaldo(rachunek.getSaldo() - przelew.getKwota());
            przelew.setDataRealizacji(Data.toSqlDate(Data.dzisiejszaData()));
            przelew.setStatus("Wyslano");
            
            edit(przelew);
            rachunekFacade.edit(rachunek);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean denyTransfer(PrzelewInterface p){
        Przelew przelew = (Przelew) p;
        remove(przelew);
        return true;
    }

    
    @Override
    public void authorizeAll(KontoInterface k, RachunekInterface r) {
        List<PrzelewInterface> list = getTransfers(k, r, "Oczekuje");
        for(PrzelewInterface p : list){
            Przelew przelew = (Przelew) p;
            authorizeTransfer(przelew);
        }
    }
    
    @Override
    public void create(PrzelewInterface entity) {
        System.out.println("PrzelewFacade create(" + entity + ")");
        Przelew p = (Przelew) entity;
        getEntityManager().persist(p);
        System.out.println("PrzelewFacade create(" + entity + ") persisted");
    }
    
    @Override
    public Map getTransfersValue(RachunekInterface rachunek){
        return null;
//        Map<String, Integer> map = new HashMap<>();
//        RachunekFacade rachunekFacade = new RachunekFacade();
//        RachunekInterface r = rachunekFacade.find(rachunek.getIdRachunek());
//        Rachunek rach = (Rachunek) r;
//        for(Przelew p : rach.getPrzelewCollection()){
//            String date = String.valueOf(p.getDataRealizacji().getYear()) + String.valueOf(p.getDataRealizacji().getMonth());
//            if(!map.containsKey(date + p.getStatus())){                               
//                map.put(date, 0);
//            }
//            map.put(date + p.getStatus(), map.get(date + p.getStatus()) + p.getKwota());
//        }
//        return map;
//        System.out.println("PrzelewFacade getTransfersValue(" + k + " , " + r + " , " + year + " , " + month + " , " + type +")");
//        int out = 0;      
//        
//        String status;
//        if (type < 0)
//            status = "Wyslano";
//        else
//            status = "Odebrano";
//        
//        System.out.println("Status: " + status);
//        for(PrzelewInterface p : getTransfersHistory(k, r)){
//            
//            if(status.equals(p.getStatus())){
//                if((p.getDataRealizacji().getYear() + 1900) == year){
//                    if (p.getDataRealizacji().getMonth() == month){
//                        out += p.getKwota();
//                        System.out.println("PrzelewFacade getTransfersValue(); transfer counted");
//                    }
//                }
//            }
//        }
//        System.out.println("PrzelewFacade getTransfersValue(); " + out);
//        return out;
    }

    @Override
    public List<Integer> getYears(KontoInterface k, RachunekInterface r) {
        List<Integer> out = new ArrayList<>();
        for(PrzelewInterface p : getTransfers(k, r, null)){
            out.add(p.getDataWystawienia().getYear());            
        }
        System.out.println("PrzelewFacade getYears();" + out);
        return out;
    }

    @Override
    public void transferOwn(RachunekInterface rach1, RachunekInterface rach2, int kwota) {
        Rachunek r1 = (Rachunek) rach1;
        Rachunek r2 = (Rachunek) rach2;
        
        r1.setSaldo(r1.getSaldo() - kwota);
        r2.setSaldo(r2.getSaldo() + kwota);
                        
        rachunekFacade.edit(r1);
        rachunekFacade.edit(r2);                
    }
}
