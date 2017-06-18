/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import model.KontoInterface;
import model.PrzelewInterface;
import model.RachunekInterface;

/**
 *
 * @author Dominik
 */
@Local
public interface PrzelewFacadeLocal {

    void create(PrzelewInterface przelew);

//    void edit(PrzelewInterface przelew);

//    void remove(PrzelewInterface przelew);

//    PrzelewInterface find(Object id);

//    List<PrzelewInterface> findRange(int[] range);

//    int count();
    
    public List<PrzelewInterface> getTransfers(KontoInterface konto, RachunekInterface rachunek,String status);

    public List<PrzelewInterface> getTransfersHistory(KontoInterface konto, RachunekInterface rachunek);
    
    public RachunekInterface getAccountByTransfer(PrzelewInterface przelew);

    public void authorizeAll(KontoInterface konto, RachunekInterface rachunek);

    public boolean authorizeTransfer(PrzelewInterface p);

    public boolean denyTransfer(PrzelewInterface p);
        
    public Map getTransfersValue(RachunekInterface r);

    public List<Integer> getYears(KontoInterface k, RachunekInterface r);

    public void transferOwn(RachunekInterface rach1, RachunekInterface rach2, int kwota);
}
