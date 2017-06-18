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

/**
 *
 * @author Xaoo
 */
@Entity
@Table(name = "lokata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lokata.findAll", query = "SELECT l FROM Lokata l"),
    @NamedQuery(name = "Lokata.findByIdLokata", query = "SELECT l FROM Lokata l WHERE l.idLokata = :idLokata"),
    @NamedQuery(name = "Lokata.findByNazwa", query = "SELECT l FROM Lokata l WHERE l.nazwa = :nazwa"),
    @NamedQuery(name = "Lokata.findBySaldo", query = "SELECT l FROM Lokata l WHERE l.saldo = :saldo"),
    @NamedQuery(name = "Lokata.findByWaluta", query = "SELECT l FROM Lokata l WHERE l.waluta = :waluta"),
    @NamedQuery(name = "Lokata.findByDataZalozenia", query = "SELECT l FROM Lokata l WHERE l.dataZalozenia = :dataZalozenia"),
    @NamedQuery(name = "Lokata.findByDataZakonczenia", query = "SELECT l FROM Lokata l WHERE l.dataZakonczenia = :dataZakonczenia")})
public class Lokata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLokata")
    private Integer idLokata;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataZakonczenia")
    @Temporal(TemporalType.DATE)
    private Date dataZakonczenia;
    @JoinColumn(name = "idKonto", referencedColumnName = "idKonto")
    @ManyToOne(optional = false)
    private Konto idKonto;
    @JoinColumn(name = "idRodzaj", referencedColumnName = "idRodzaj")
    @ManyToOne(optional = false)
    private Rodzaj idRodzaj;

    public Lokata() {
    }

    public Lokata(Integer idLokata) {
        this.idLokata = idLokata;
    }

    public Lokata(Integer idLokata, String nazwa, int saldo, String waluta, Date dataZalozenia, Date dataZakonczenia) {
        this.idLokata = idLokata;
        this.nazwa = nazwa;
        this.saldo = saldo;
        this.waluta = waluta;
        this.dataZalozenia = dataZalozenia;
        this.dataZakonczenia = dataZakonczenia;
    }

    public Integer getIdLokata() {
        return idLokata;
    }

    public void setIdLokata(Integer idLokata) {
        this.idLokata = idLokata;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public Date getDataZalozenia() {
        return dataZalozenia;
    }

    public void setDataZalozenia(Date dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public Konto getIdKonto() {
        return idKonto;
    }

    public void setIdKonto(Konto idKonto) {
        this.idKonto = idKonto;
    }

    public Rodzaj getIdRodzaj() {
        return idRodzaj;
    }

    public void setIdRodzaj(Rodzaj idRodzaj) {
        this.idRodzaj = idRodzaj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLokata != null ? idLokata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lokata)) {
            return false;
        }
        Lokata other = (Lokata) object;
        if ((this.idLokata == null && other.idLokata != null) || (this.idLokata != null && !this.idLokata.equals(other.idLokata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Lokata[ idLokata=" + idLokata + " ]";
    }
    
}
