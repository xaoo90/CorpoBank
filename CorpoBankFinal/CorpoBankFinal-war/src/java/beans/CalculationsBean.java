/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.KontoFacadeLocal;
import dao.PrzelewFacadeLocal;
import dao.RachunekFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.KontoInterface;
import model.RachunekInterface;

/**
 *
 * @author Dominik
 */
@SessionScoped
@Named
public class CalculationsBean implements Serializable{
    @EJB
    private PrzelewFacadeLocal przelewFacade;
    @EJB
    private KontoFacadeLocal kontoFacade;
    @EJB
    private RachunekFacadeLocal rachunekFacade;          
    
    private Map<RachunekInterface, List<int[]>> calc;

    public int getCalc(RachunekInterface r, int year, String month, int type) {
        if (calc == null){
            System.out.println("CalculationsBean getCalc() calc == null");
            countTransfers(year);
        }
        
        List<int[]> listForRachunek = calc.get(r);
        int[] transfersInMonth = listForRachunek.get(Util.monthToInt(month));
        int incomes = transfersInMonth[0];
        int outcomes = transfersInMonth[1];
        int netto = incomes - outcomes;
        
        if (type > 0){
            return incomes;
        } else if (type < 0) {
            return outcomes;            
        } else return netto;                             
    }
    
    public List<Map.Entry> countAll(String month, int type){
        if (calc == null){
            System.out.println("CalculationsBean getCalc() calc == null");
            countTransfers(2015);
        }
        Map<String, Integer> incomeMap = new HashMap();
        Map<String, Integer> outcomeMap = new HashMap();
        Map<String, Integer> nettoMap = new HashMap();

        for( RachunekInterface r : calc.keySet()){
            System.out.println("CalculationsBean cpintAll() rachunek: " + r);
            List<int[]> listForRachunek = calc.get(r);
            int[] transfersInMonth = listForRachunek.get(Util.monthToInt(month));
            int incomes = transfersInMonth[0];
            int outcomes = transfersInMonth[1];
            int netto = incomes - outcomes;
                                    
            if(!incomeMap.containsKey(r.getWaluta())){
                incomeMap.put(r.getWaluta(), 0);
            }                                    
            incomeMap.put(r.getWaluta(), incomeMap.get(r.getWaluta()) + incomes);
            
            if(!outcomeMap.containsKey(r.getWaluta())){
                outcomeMap.put(r.getWaluta(), 0);
            }
            outcomeMap.put(r.getWaluta(), outcomeMap.get(r.getWaluta()) + outcomes);
        
            if(!nettoMap.containsKey(r.getWaluta())){
                nettoMap.put(r.getWaluta(), 0);
            }
            nettoMap.put(r.getWaluta(), nettoMap.get(r.getWaluta()) + netto);
        }
        
        List<Map.Entry> list = new ArrayList();

        if (type > 0){
            list.addAll(incomeMap.entrySet());            
        } else if (type < 0) {
            list.addAll(outcomeMap.entrySet());
        } else 
            list.addAll(nettoMap.entrySet());   
        return list;
    }
    
    public void countTransfers(int year){
        Map<RachunekInterface, List<int[]>> out = new HashMap<>();
        for(RachunekInterface r : kontoFacade.getRachunki((KontoInterface) Util.getSession().getAttribute("account"))){
            out.put(r, rachunekFacade.transactionsByYear(r, year));
        }
        this.calc = out;
    }    
    
    public List<Integer> getYears(){
        HttpSession session = Util.getSession();
        RachunekInterface r = (RachunekInterface) session.getAttribute("rachunek");
        KontoInterface k = (KontoInterface) session.getAttribute("account");
        return przelewFacade.getYears(k,r);
    }
    
    public List<String> getMonths(){
        List<String> out = new ArrayList<>();
        out.add("Styczeń");out.add("Luty");out.add("Marzec");
        out.add("Kwiecień");out.add("Maj");out.add("Czerwiec");
        out.add("Lipiec");out.add("Sierpień");out.add("Wrzesień");
        out.add("Październik");out.add("Listopad");out.add("Grudzień");
        return out;
    }
    
    public List ownCapital(){        
        HttpSession session = Util.getSession();
        KontoInterface k = (KontoInterface) session.getAttribute("account");
        List<Map.Entry> out = new ArrayList();
        out.addAll(rachunekFacade.ownCapital(k).entrySet());
        
        return out;
    }
    
//    public String getProfitability(int year, String month){
//        double out = countTransfers(null,year, month, 1) - countTransfers(null,year, month, -1);
//        out = out/ownCapital();
//        out = out * 100;
//        return new DecimalFormat("#.##").format(out);
//    }
}
