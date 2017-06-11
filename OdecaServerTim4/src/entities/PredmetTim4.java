package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="PredmetTim4.findByKategorija",query="SELECT p FROM PredmetTim4 p WHERE p.kategorija LIKE :kategorija"),
	@NamedQuery(name="PredmetTim4.findByVelicina",query="SELECT p FROM PredmetTim4 p WHERE p.velicina LIKE :velicina AND p.kategorija LIKE :kategorija"),
	@NamedQuery(name="PredmetTim4.findByBoja",query="SELECT p FROM PredmetTim4 p WHERE p.boja LIKE :boja AND p.kategorija LIKE :kategorija"),
	@NamedQuery(name="PredmetTim4.findByMarka",query="SELECT p FROM PredmetTim4 p WHERE p.marka LIKE :marka AND p.kategorija LIKE :kategorija"),
	@NamedQuery(name="PredmetTim4.findByMaterijal",query="SELECT p FROM PredmetTim4 p WHERE p.materijal LIKE :materijal AND p.kategorija LIKE :kategorija"),
	@NamedQuery(name="PredmetTim4.findSviPredmeti",query="SELECT p FROM PredmetTim4 p "),
	@NamedQuery(name="PredmetTim4.findByCena",query="SELECT p FROM PredmetTim4 p WHERE p.pocetnaCena LIKE :cena AND p.kategorija LIKE :kategorija"),
	
	
	
})
public class PredmetTim4 implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;	
	private KategorijaTim4 kategorija;
	private String naslov;
	private String velicina;
	private String boja;
	private int pocetnaCena;
	private String materijal;
	private String marka;
	@Column(length=100)
	private String opis;
	@Lob
	private byte[] slika;
	
	public PredmetTim4() {		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public KategorijaTim4 getKategorija() {
		return kategorija;
	}

	public void setKategorija(KategorijaTim4 kategorija) {
		this.kategorija = kategorija;
	}

	public String getVelicina() {
		return velicina;
	}

	public void setVelicina(String velicina) {
		this.velicina = velicina;
	}

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public byte[] getSlika() {
		return slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public int getPocetnaCena() {
		return pocetnaCena;
	}

	public void setPocetnaCena(int pocetnaCena) {
		this.pocetnaCena = pocetnaCena;
	}

	public String getMaterijal() {
		return materijal;
	}

	public void setMaterijal(String materijal) {
		this.materijal = materijal;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	
}
