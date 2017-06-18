/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import dao.PrzelewFacadeLocal;
import dao.RachunekFacadeLocal;
import data.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.EntityFactory;
import model.KontoInterface;
import model.PrzelewInterface;
import model.RachunekInterface;
import org.apache.pdfbox.exceptions.COSVisitorException;
import pdf.PDFGenerator;

/**
 *
 * @author Xaoo
 */

@Named(value = "przelewyBean")
@RequestScoped
@ManagedBean
public class PrzelewyBean implements Serializable{

    @EJB
    private EntityFactory entityFactory;
    
    @EJB 
    private PrzelewFacadeLocal przelewFacade;
    
    @EJB
    private RachunekFacadeLocal rachunekFacade;
    
    private PrzelewInterface transfer;

    public PrzelewInterface getTransfer() {
        System.out.println("PrzelewyBean getTransfer();");
        HttpSession session = Util.getSession();
        transfer = (PrzelewInterface) session.getAttribute("newTransfer");
        if(transfer == null){
            session.setAttribute("newTransfer",entityFactory.newPrzelew());
            transfer = (PrzelewInterface) session.getAttribute("newTransfer");
        }
        return transfer;
    }

    public void setTransfer(PrzelewInterface transfer) {
        this.transfer = transfer;
    }
    
    public List<PrzelewInterface> getTransfers(int status){
        System.out.println("PrzelewyBean getTransfers();");
        HttpSession session = Util.getSession();
        KontoInterface account = (KontoInterface) session.getAttribute("account");
        RachunekInterface rachunek = (RachunekInterface) session.getAttribute("rachunek");
        System.out.println("PrzelewyBean getTransfers(); konto: " + account);
        
        if(status == 1){//"Historia".equals(status)){
            ArrayList<PrzelewInterface> list = new ArrayList<>();
            list.addAll(przelewFacade.getTransfers(account, rachunek, "Odebrano"));
            list.addAll(przelewFacade.getTransfers(account, rachunek, "Wyslano"));
            Collections.sort(list, new TransferDateComparator());
            return list;
        } else {
            List<PrzelewInterface> list = przelewFacade.getTransfers(account, rachunek, "Oczekuje");
            //Collections.sort(list, new TransferDateComparator());
            return list;
        }           
       
    }
    
    public List<PrzelewInterface> getTransfersHistory(){
        System.out.println("PrzelewyBean getTransfersHistory();");
        HttpSession session = Util.getSession();
        KontoInterface account = (KontoInterface) session.getAttribute("account");
        RachunekInterface rachunek = (RachunekInterface) session.getAttribute("rachunek");
        System.out.println("PrzelewyBean getTransfers(); konto: " + account);
        
        List<PrzelewInterface> list = przelewFacade.getTransfersHistory(account, rachunek);
        return list;
    }
    
    public String getAccountNameByTransfer(PrzelewInterface przelew){
        return przelewFacade.getAccountByTransfer(przelew).getNazwa();
    }
    
    public String getAccountNumberByTransfer(PrzelewInterface przelew){
        return przelewFacade.getAccountByTransfer(przelew).getNumer();
    }
    
    public String getAccountCurrencyByTransfer(PrzelewInterface przelew){
        return przelewFacade.getAccountByTransfer(przelew).getWaluta();
    }
    
    public String authorizeTransfer(PrzelewInterface p){
        przelewFacade.authorizeTransfer(p);
        return "awaitingTransfers";
    }
    
    public String denyTransfer(PrzelewInterface p){
        przelewFacade.denyTransfer(p);
        return "awaitingTransfers";
    }
    
    public void authorizeAll(){
        HttpSession session = Util.getSession();
        KontoInterface account = (KontoInterface) session.getAttribute("account");
        RachunekInterface rachunek = (RachunekInterface) session.getAttribute("rachunek");
        przelewFacade.authorizeAll(account, rachunek);
    }
    
    public void setRachunek(RachunekInterface r){
        HttpSession session = Util.getSession();
        session.setAttribute("rachunek", r);
    }

    private Part file;
    private String fileContent;

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    
    public void upload() {

        if (null != file) {
            try {
                InputStream is = file.getInputStream();
                fileContent = new Scanner(is).useDelimiter("\\A").next();
            } catch (IOException ex) {
            }
        }
        loadTransfersFromFile();
    }

    public Part getFile() {
      return file;
    }

    public void setFile(Part file) {
      this.file = file;
    }
    
    public List<PrzelewInterface> getImportedTransfers() {
        HttpSession session = Util.getSession();
        List<PrzelewInterface> out = (ArrayList<PrzelewInterface>) session.getAttribute("importedTransfers");       
        return out;
    }
    
    public String denyImportedTransfer(PrzelewInterface p) {
        HttpSession session = Util.getSession();
        List<PrzelewInterface> out = (ArrayList<PrzelewInterface>) session.getAttribute("importedTransfers");       
        out.remove(p);
        session.setAttribute("importedTransfers", out);
        return "checkTransfers";                
    }
    
    public int getImportedTransfersCount() {
        HttpSession session = Util.getSession();
        List<PrzelewInterface> out = (ArrayList<PrzelewInterface>) session.getAttribute("importedTransfers");
        if(out == null){
            return 0;
        }
        return out.size();
    }
    
    public String rejectImportedTransfers() {
        HttpSession session = Util.getSession();
        session.setAttribute("importedTransfers", null);
        return "awaitingTransfers";
    }
    
    public String confirmImportedTransfers() {
        HttpSession session = Util.getSession();
        List<PrzelewInterface> importedTransfers = (ArrayList<PrzelewInterface>) session.getAttribute("importedTransfers");       

        for(PrzelewInterface p : importedTransfers){
            p.setDataWystawienia(Data.toSqlDate(Data.dzisiejszaData()));
            przelewFacade.create(p);
        }
        
        session.setAttribute("importedTransfers", null);
        return "awaitingTransfers";
    }
    
    public void loadTransfersFromFile(){
    String line;
    String csvSplitBy = "\",\"";

    InputStream is;
    try {
        is = file.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<PrzelewInterface> importedTransfers = new ArrayList<>();
        RachunekInterface r;
        while ((line = br.readLine()) != null) {
            String[] transferCSV = line.split(csvSplitBy);
            System.out.println(Arrays.toString(transferCSV));
            r = rachunekFacade.findRachunekByNumber(transferCSV[0].replaceAll("\"", ""));
            if (r == null){
                System.out.println("PrzelewyBean loadTransfersFromFile() " + r);
                continue;
            }
                
            PrzelewInterface newTransfer = entityFactory.newPrzelew();

            newTransfer.setRachunek(r);
            newTransfer.setNrRachunku(transferCSV[1]);
            newTransfer.setNazwa(transferCSV[2]);
            newTransfer.setAdres(transferCSV[3]);
            newTransfer.setTytul(transferCSV[4]);
            newTransfer.setKwota(Integer.valueOf(transferCSV[5].replaceAll("\"", "")));
            newTransfer.setIdPrzelew(null);
            newTransfer.setStatus("Oczekuje");

            importedTransfers.add(newTransfer);
            
            System.out.println(newTransfer);
            System.out.println("adding newTransfer to importedTransfers");
            System.out.println(newTransfer.getNrRachunku());
            System.out.println(newTransfer.getNazwa());
            System.out.println(newTransfer.getAdres());
            System.out.println(newTransfer.getTytul());
            System.out.println(newTransfer.getKwota());
                
            }
        
        HttpSession session = Util.getSession();
        session.setAttribute("importedTransfers", importedTransfers);
        
        } catch (IOException ex) {
        }
        
    }
    
    public int getAwaitingTransfersCount(RachunekInterface r) {
        HttpSession session = Util.getSession();
        KontoInterface account = (KontoInterface) session.getAttribute("account");
        List<PrzelewInterface> list = przelewFacade.getTransfers(account, r, "Oczekuje");
        return list.size();
    }
    
    public String sendTransfer(){
        System.out.println("PrzelewyBean sendTransfer(); Z: " + transfer.getRachunek().getNazwa());
        System.out.println("PrzelewyBean sendTransfer(); do: " + transfer.getNrRachunku());
        System.out.println("PrzelewyBean sendTransfer(); do: " + transfer.getNazwa());
        System.out.println("PrzelewyBean sendTransfer(); do: " + transfer.getAdres());
        System.out.println("PrzelewyBean sendTransfer(); do: " + transfer.getTytul());
        System.out.println("PrzelewyBean sendTransfer(); do: " + transfer.getKwota());

        
        System.out.println("PrzelewyBean sendTransfer(); IdPrzelew = null");
        transfer.setIdPrzelew(null);
        System.out.println("PrzelewyBean sendTransfer(); DataWystawienia = today");
        transfer.setDataWystawienia(Data.toSqlDate(Data.dzisiejszaData()));
        System.out.println("PrzelewyBean sendTransfer(); Status = Oczekuje");
        transfer.setStatus("Oczekuje");
        
        przelewFacade.create(transfer);
        System.out.println("PrzelewyBean sendTransfer(); Created");

        transfer = null;
        
        return "awaitingTransfers";
    }
    
    public String print(PrzelewInterface przel){
        System.out.println("PrzelewyBean print();");
        PDFGenerator gen = new PDFGenerator();
        String nazwa = "potwierdzenie";
        String absoluteWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        String[] tablica = new String[9];
        
        tablica[0]=przel.getRachunek().getNazwa() + " (" + przel.getRachunek().getNumer() + ")";
        tablica[1]=przel.getNrRachunku();
        
        if( przel.getStatus().equals("Odebrano")){
                tablica[2]="";
            }
            else{           
                int dzienW = przel.getDataWystawienia().getDate(); 
                int miesiacW =przel.getDataWystawienia().getMonth()+1;
                String rokW = String.valueOf(przel.getDataWystawienia().getYear()+1900);
                String sDzienW;
                String sMiesiacW;
                if(dzienW <10)
                    sDzienW ="0" + String.valueOf(dzienW);            
                else
                    sDzienW = String.valueOf(dzienW);
                if(miesiacW <10)
                    sMiesiacW ="0" + String.valueOf(miesiacW);            
                else
                    sMiesiacW = String.valueOf(miesiacW);         
                tablica[2]=sDzienW + "-" + sMiesiacW + "-" + rokW;
            }
            
            if(przel.getStatus().equals("Oczekuje")){
                tablica[3]="";
            }    
            else{
                int dzienR = przel.getDataRealizacji().getDate();
                int miesiacR = przel.getDataRealizacji().getMonth()+1;
                String rokR = String.valueOf(przel.getDataRealizacji().getYear()+1900);
                String sDzienR;
                String sMiesiacR;
                if(dzienR <10)
                     sDzienR ="0" + String.valueOf(dzienR);            
                else
                    sDzienR = String.valueOf(dzienR);
                if(miesiacR <10)
                    sMiesiacR ="0" + String.valueOf(miesiacR);            
                else
                    sMiesiacR = String.valueOf(miesiacR);
                tablica[3]=sDzienR + "-" + sMiesiacR + "-" + rokR;
            }
        
        tablica[4]=przel.getNazwa();
        tablica[5]=przel.getAdres();
        tablica[6]=przel.getTytul();
        tablica[7]=przel.getKwota() + " " + przel.getRachunek().getWaluta();
        tablica[8]=przel.getStatus();
        

        try {
            System.out.println("PrzelewyBean print(); try");
            gen.generate(absoluteWebPath, nazwa, Util.getUserName(), tablica);
        } catch (IOException | COSVisitorException ex) {
            System.out.println("PrzelewyBean print(); catch");
        }
        return nazwa+".pdf";
    }
    
    public void setRach(String nazwa, RachunekInterface rachunek){
        Util.getSession().setAttribute(nazwa, rachunek);
    }
        
    int kwota;
    RachunekInterface rach1;
    RachunekInterface rach2;
    
    public int getKwota() {
        return kwota;
    }

    public void setKwota(int kwota) {
        this.kwota = kwota;
    }

    public RachunekInterface getRach1() {
        return rach1;
    }

    public void setRach1(RachunekInterface rach1) {
        this.rach1 = rach1;
    }

    public RachunekInterface getRach2() {
        return rach2;
    }

    public void setRach2(RachunekInterface rach2) {
        this.rach2 = rach2;
    }
    
    
    public String transferOwn(){
        if(rach1.getWaluta().equals(rach2.getWaluta())){
            System.out.println("PrzelewyBean");
            przelewFacade.transferOwn(rach1, rach2, kwota);
        }

        return "rachunki";
    }
    
}