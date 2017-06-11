package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@NamedQueries({ 
		@NamedQuery(name = "OglasTim4.getById", query = "SELECT o FROM OglasTim4 o where o.id like :id"),
		@NamedQuery(name = "OglasTim4.findByKategorija", query = "SELECT o FROM OglasTim4 o WHERE o.predmet.kategorija LIKE :kategorija"),
		@NamedQuery(name = "OglasTim4.findByVelicina", query = "SELECT o FROM OglasTim4 o WHERE o.predmet.velicina LIKE :velicina AND o.predmet.kategorija LIKE :kategorija"),
		@NamedQuery(name = "OglasTim4.findByBoja", query = "SELECT o FROM OglasTim4 o WHERE o.predmet.boja LIKE :boja AND o.predmet.kategorija LIKE :kategorija"),
		@NamedQuery(name = "OglasTim4.findByMarka", query = "SELECT o FROM OglasTim4 o WHERE o.predmet.marka LIKE :marka AND o.predmet.kategorija LIKE :kategorija"),
		@NamedQuery(name = "OglasTim4.findByMaterijal", query = "SELECT o FROM OglasTim4 o WHERE o.predmet.materijal LIKE :materijal AND o.predmet.kategorija LIKE :kategorija"),
		@NamedQuery(name = "OglasTim4.findSviPredmeti", query = "SELECT o FROM OglasTim4 o "),
		@NamedQuery(name = "OglasTim4.findByCena", query = "SELECT o FROM OglasTim4 o WHERE o.predmet.pocetnaCena LIKE :cena AND o.predmet.kategorija LIKE :kategorija"), 
		})

public class OglasTim4 implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private int ponudjenaCena;
	private boolean istekao;
	@ManyToOne
	private KorisnikTim4 korisnik;
	@OneToOne
	private PredmetTim4 predmet;
	@OneToMany(mappedBy = "oglas", fetch = FetchType.EAGER)
	private List<KomentarTim4> komentari;

	public OglasTim4() {
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OglasTim4 other = (OglasTim4) obj;
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPonudjenaCena() {
		return ponudjenaCena;
	}

	public void setPonudjenaCena(int ponudjenaCena) {
		this.ponudjenaCena = ponudjenaCena;
	}

	public boolean isIstekao() {
		return istekao;
	}

	public void setIstekao(boolean istekao) {
		this.istekao = istekao;
	}

	public KorisnikTim4 getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikTim4 korisnik) {
		this.korisnik = korisnik;
	}

	public PredmetTim4 getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetTim4 predmet) {
		this.predmet = predmet;
	}

	public List<KomentarTim4> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<KomentarTim4> komentari) {
		this.komentari = komentari;
	}

}
