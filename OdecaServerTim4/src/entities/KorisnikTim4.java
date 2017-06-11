package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
	@NamedQuery(name="KorisnikTim4.getByUsername", query="SELECT k.username FROM KorisnikTim4 k WHERE k.username = :username"),
	@NamedQuery(name="KorisnikTim4.getByUserAndPass",query="SELECT k FROM KorisnikTim4 k WHERE k.username = :username"
			+ " AND k.password = :password")
	
})
public class KorisnikTim4 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	private String ime;
	private String prezime;
	private String email;
	@Lob
	private byte[] avatar;
	
	private String password;	
	
	//svi oglasi koje je korisnik kreirao
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.EAGER)	
	private List<OglasTim4> mojiOglasi;
	
	//sva nadmetanja u kojima je korisnik ucestvovao ili jos ucestvuje	
	@OneToMany(fetch = FetchType.EAGER)
	private List<OglasTim4> aukcije;
	
	//svi komentari u kojima je korisnik spomenut
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.EAGER)	
	private List<KomentarTim4> komentari;
	
	public KorisnikTim4() {
		
	}

	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<OglasTim4> getMojiOglasi() {
		return mojiOglasi;
	}

	public void setMojiiOglasi(List<OglasTim4> licitiraniOglasi) {
		this.mojiOglasi = licitiraniOglasi;
	}

	public List<OglasTim4> getAukcije() {
		return aukcije;
	}

	public void setAukcije(List<OglasTim4> aukcije) {
		this.aukcije = aukcije;
	}

	public List<KomentarTim4> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<KomentarTim4> komentari) {
		this.komentari = komentari;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public  byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar( byte[] avatar) {
		this.avatar = avatar;
	}
			
}
