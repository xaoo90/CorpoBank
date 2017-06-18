/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.KontoInterface;
import model.PrzelewInterface;
import model.RachunekInterface;

/**
 *
 * @author Xaoo
 */
@Entity
@Table(name = "rachunek")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rachunek.findAll", query = "SELECT r FROM Rachunek r"),
    @NamedQuery(name = "Rachunek.findByIdRachunek", query = "SELECT r FROM Rachunek r WHERE r.idRachunek = :idRachunek"),  
    @NamedQuery(name = "Rachunek.findByNumer", query = "SELECT r FROM Rachunek r WHERE r.numer = :numer"),
    @NamedQuery(name = "Rachunek.findByNazwa", query = "SELECT r FROM Rachunek r WHERE r.nazwa = :nazwa"),
    @NamedQuery(name = "Rachunek.findBySaldo", query = "SELECT r FROM Rachunek r WHERE r.saldo = :saldo"),
    @NamedQuery(name = "Rachunek.findByWaluta", query = "SELECT r FROM Rachunek r WHERE r.waluta = :waluta"),
    @NamedQuery(name = "Rachunek.findByDataZalozenia", query = "SELECT r FROM Rachunek r WHERE r.dataZalozenia = :dataZalozenia")})
public class Rachunek implements Serializable, RachunekInterface {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRachunek")
    private Collection<Przelew> przelewCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRachunek")
    private Integer idRachunek;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "numer")
    private String numer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nazwa")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private int saldo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "waluta")
    private String waluta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataZalozenia")
    @Temporal(TemporalType.DATE)
    private Date dataZalozenia;
    @JoinColumn(name = "idKonto", referencedColumnName = "idKonto")
    @ManyToOne(optional = false)
    private Konto idKonto;
        
    public Rachunek() {
    }

    public Rachunek(Integer idRachunek) {
        this.idRachunek = idRachunek;
    }

    public Rachunek(Integer idRachunek, String numer, String nazwa, int saldo, String waluta, Date dataZalozenia) {
        this.idRachunek = idRachunek;
        this.numer = numer;
        this.nazwa = nazwa;
        this.saldo = saldo;
        this.waluta = waluta;
        this.dataZalozenia = dataZalozenia;
    }

    @Override
    public Integer getIdRachunek() {
        return idRachunek;
    }

//    @Override
    public void setIdRachunek(Integer idRachunek) {
        this.idRachunek = idRachunek;
    }

    @Override
    public String getNumer() {
        return numer;
    }

//    @Override
    public void setNumer(String numer) {
        this.numer = numer;
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

//    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public int getSaldo() {
        return saldo;
    }

//    @Override
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public String getWaluta() {
        return waluta;
    }

//    @Override
    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    @Override
    public Date getDataZalozenia() {
        return dataZalozenia;
    }

//    @Override
    public void setDataZalozenia(Date dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    @Override
    public KontoInterface getIdKonto() {
        return idKonto;
    }

//    @Override
    public void setIdKonto(KontoInterface idKonto) {
        this.idKonto = (Konto) idKonto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRachunek != null ? idRachunek.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rachunek)) {
            return false;
        }
        Rachunek other = (Rachunek) object;
        return !((this.idRachunek == null && other.idRachunek != null) || (this.idRachunek != null && !this.idRachunek.equals(other.idRachunek)));
    }

    @Override
    public String toString() {
        return "entity.Rachunek[ idRachunek=" + idRachunek + " ]";
    }

//    @XmlTransient
    public Collection<Przelew> getPrzelewCollection() {
        System.out.println("Rachunek getPrzelewCollection()");
//
//        List<PrzelewInterface> list = new ArrayList<>();
//        for(Przelew p : przelewCollection){
//            list.add(p);
//        }
//        System.out.println("Rachunek getPrzelewCollection() " + list);
//        return list;
        return przelewCollection;
    }

    public void setPrzelewCollection(Collection<Przelew> przelewCollection) {
        this.przelewCollection = przelewCollection;
    }
    
}
