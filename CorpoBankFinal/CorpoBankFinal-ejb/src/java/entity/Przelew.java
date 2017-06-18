/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import model.PrzelewInterface;
import model.RachunekInterface;

/**
 *
 * @author Xaoo
 */
@Entity
@Table(name = "przelew")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Przelew.findAll", query = "SELECT p FROM Przelew p"),
    @NamedQuery(name = "Przelew.findByIdPrzelew", query = "SELECT p FROM Przelew p WHERE p.idPrzelew = :idPrzelew"),
    @NamedQuery(name = "Przelew.findByNrRachunku", query = "SELECT p FROM Przelew p WHERE p.nrRachunku = :nrRachunku"),
    @NamedQuery(name = "Przelew.findByNazwa", query = "SELECT p FROM Przelew p WHERE p.nazwa = :nazwa"),
    @NamedQuery(name = "Przelew.findByAdres", query = "SELECT p FROM Przelew p WHERE p.adres = :adres"),
    @NamedQuery(name = "Przelew.findByTytul", query = "SELECT p FROM Przelew p WHERE p.tytul = :tytul"),
    @NamedQuery(name = "Przelew.findByKwota", query = "SELECT p FROM Przelew p WHERE p.kwota = :kwota"),
    @NamedQuery(name = "Przelew.findByStatus", query = "SELECT p FROM Przelew p WHERE p.status = :status"),
    @NamedQuery(name = "Przelew.findByDataWystawienia", query = "SELECT p FROM Przelew p WHERE p.dataWystawienia = :dataWystawienia"),
    @NamedQuery(name = "Przelew.findByDataRealizacji", query = "SELECT p FROM Przelew p WHERE p.dataRealizacji = :dataRealizacji")})
public class Przelew implements Serializable, PrzelewInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrzelew")
    private Integer idPrzelew;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nrRachunku")
    private String nrRachunku;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nazwa")
    private String nazwa;
    @Size(max = 50)
    @Column(name = "adres")
    private String adres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tytul")
    private String tytul;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kwota")
    private int kwota;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @Column(name = "dataWystawienia")
    @Temporal(TemporalType.DATE)
    private Date dataWystawienia;
    @Column(name = "dataRealizacji")
    @Temporal(TemporalType.DATE)
    private Date dataRealizacji;
    @JoinColumn(name = "idRachunek", referencedColumnName = "idRachunek")
    @ManyToOne(optional = false)
    private Rachunek idRachunek;

    public Przelew() {
    }

    public Przelew(Integer idPrzelew) {
        this.idPrzelew = idPrzelew;
    }

    public Przelew(Integer idPrzelew, String nrRachunku, String nazwa, String tytul, int kwota) {
        this.idPrzelew = idPrzelew;
        this.nrRachunku = nrRachunku;
        this.nazwa = nazwa;
        this.tytul = tytul;
        this.kwota = kwota;
    }

//    @Override
    public Integer getIdPrzelew() {
        return idPrzelew;
    }

    @Override
    public void setIdPrzelew(Integer idPrzelew) {
        this.idPrzelew = idPrzelew;
    }

    @Override
    public String getNrRachunku() {
        return nrRachunku;
    }

    @Override
    public void setNrRachunku(String nrRachunku) {
        this.nrRachunku = nrRachunku;
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String getAdres() {
        return adres;
    }

    @Override
    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String getTytul() {
        return tytul;
    }

    @Override
    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @Override
    public int getKwota() {
        return kwota;
    }

    @Override
    public void setKwota(int kwota) {
        this.kwota = kwota;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    @Override
    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    @Override
    public Date getDataRealizacji() {
        return dataRealizacji;
    }

    @Override
    public void setDataRealizacji(Date dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
    }

    public Rachunek getIdRachunek() {
        return idRachunek;
    }

    public void setIdRachunek(Rachunek idRachunek) {
        this.idRachunek = idRachunek;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrzelew != null ? idPrzelew.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Przelew)) {
            return false;
        }
        Przelew other = (Przelew) object;
        if ((this.idPrzelew == null && other.idPrzelew != null) || (this.idPrzelew != null && !this.idPrzelew.equals(other.idPrzelew))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Przelew[ idPrzelew=" + idPrzelew + " ]";
    }

    @Override
    public void setRachunek(RachunekInterface r) {
        this.idRachunek = (Rachunek) r;
    }

    @Override
    public RachunekInterface getRachunek() {
        return idRachunek;
    }
    
}
