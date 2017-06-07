package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@NamedQuery(name="OglasTim4.getById", query="SELECT o FROM OglasTim4 o where o.id like :id")
public class OglasTim4 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private int ponudjenaCena;
	private boolean istekao;
	@ManyToOne
	private KorisnikTim4 korisnik;
	@OneToOne
	@PrimaryKeyJoinColumn
	private PredmetTim4 predmet;
	@OneToMany(mappedBy = "oglas")
	private List<KomentarTim4>komentari;
	
    public OglasTim4() {		
	}
	
	@Override
	public int hashCode(){
		return id;
	}

	@Override
	public boolean equals(Object obj){
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
