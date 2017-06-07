package entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class KomentarTim4 implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(length=100)
	private String komentar;
	//private List<KomentarTim4>podKomentari;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;
	@ManyToOne
	private OglasTim4 oglas;
	@ManyToOne
	private KorisnikTim4 korisnik;	
	private boolean komentarOglasa;
	
	public KomentarTim4() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKomentar() {
		return komentar;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public OglasTim4 getOglas() {
		return oglas;
	}
	public void setOglas(OglasTim4 oglas) {
		this.oglas = oglas;
	}
	public KorisnikTim4 getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(KorisnikTim4 korisnik) {
		this.korisnik = korisnik;
	}
	/*public List<KomentarTim4> getPodKomentari() {
		return podKomentari;
	}
	public void setPodKomentari(List<KomentarTim4> podKomentari) {
		this.podKomentari = podKomentari;
	}*/
	public boolean isKomentarOglasa() {
		return komentarOglasa;
	}
	public void setKomentarOglasa(boolean komentarOglasa) {
		this.komentarOglasa = komentarOglasa;
	}
	
	
}
