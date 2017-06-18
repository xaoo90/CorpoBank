/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import model.KontoInterface;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

/**
 *
 * @author Xaoo
 */
@Entity
@Table(name = "konto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konto.findAll", query = "SELECT k FROM Konto k"),
    @NamedQuery(name = "Konto.findByIdKonto", query = "SELECT k FROM Konto k WHERE k.idKonto = :idKonto"),
    @NamedQuery(name = "Konto.findByNazwa", query = "SELECT k FROM Konto k WHERE k.nazwa = :nazwa"),
    @NamedQuery(name = "Konto.findByFirma", query = "SELECT k FROM Konto k WHERE k.firma = :firma"),
    @NamedQuery(name = "Konto.findByDataZalozenia", query = "SELECT k FROM Konto k WHERE k.dataZalozenia = :dataZalozenia")})
public class Konto implements Serializable, KontoInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKonto")
    private Integer idKonto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nazwa")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "firma")
    private String firma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataZalozenia")
    @Temporal(TemporalType.DATE)
    private Date dataZalozenia;
    @ManyToMany(mappedBy = "kontoCollection")
    private Collection<Uzytkownik> uzytkownikCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKonto")
    private Collection<Lokata> lokataCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKonto")
    private Collection<Rachunek> rachunekCollection;

    public Konto() {
    }

    public Konto(Integer idKonto) {
        this.idKonto = idKonto;
    }

    public Konto(Integer idKonto, String nazwa, String firma, Date dataZalozenia) {
        this.idKonto = idKonto;
        this.nazwa = nazwa;
        this.firma = firma;
        this.dataZalozenia = dataZalozenia;
    }

    @Override
    public Integer getIdKonto() {
        return idKonto;
    }

//    @Override
    public void setIdKonto(Integer idKonto) {
        this.idKonto = idKonto;
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
    public String getFirma() {
        return firma;
    }

//    @Override
    public void setFirma(String firma) {
        this.firma = firma;
    }

    @Override
    public Date getDataZalozenia() {
        return dataZalozenia;
    }

//    @Override
    public void setDataZalozenia(Date dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    @XmlTransient
    public Collection<Uzytkownik> getUzytkownikCollection() {
        return uzytkownikCollection;
    }

    public void setUzytkownikCollection(Collection<Uzytkownik> uzytkownikCollection) {
        this.uzytkownikCollection = uzytkownikCollection;
    }

    @XmlTransient
    public Collection<Lokata> getLokataCollection() {
        return lokataCollection;
    }

    public void setLokataCollection(Collection<Lokata> lokataCollection) {
        this.lokataCollection = lokataCollection;
    }

    @XmlTransient
    public Collection<Rachunek> getRachunekCollection() {
        return rachunekCollection;
    }

    public void setRachunekCollection(Collection<Rachunek> rachunekCollection) {
        this.rachunekCollection = rachunekCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKonto != null ? idKonto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Konto)) {
            return false;
        }
        Konto other = (Konto) object;
        if ((this.idKonto == null && other.idKonto != null) || (this.idKonto != null && !this.idKonto.equals(other.idKonto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Konto[ idKonto=" + idKonto + " ]";
    }
    
}
