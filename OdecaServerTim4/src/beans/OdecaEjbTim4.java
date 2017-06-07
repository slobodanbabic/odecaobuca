package beans;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.KategorijaTim4;
import entities.KomentarTim4;
import entities.KorisnikTim4;
import entities.OglasTim4;
import entities.PredmetTim4;

@Stateful
@Remote(OdecaTim4I.class)
public class OdecaEjbTim4 implements OdecaTim4I{
	    // da li je klijent ulogovan
		private KorisnikTim4 korisnik=null;
		
		@PersistenceContext(name = "OdecaServerTim4")
		private EntityManager em;

	@Override
	public String registracija(String ime, String prezime, String email, String username, String password,
			byte[] avatar) {
		TypedQuery<String> q = em.createNamedQuery("KorisnikTim4.getByUsername", String.class);
		q.setParameter("username", username);		
		List<String> usernamel=q.getResultList();
		if(usernamel.size() != 0)
			return String.format("Korisnicko ime %s vec postoji u bazi! \nPokusajte sa drugim korisnickim imenom.", username);
		else{
			KorisnikTim4 korisnik = new KorisnikTim4();
			korisnik.setIme(ime);
			korisnik.setPrezime(prezime);
			korisnik.setEmail(email);
			korisnik.setUsername(username);
			korisnik.setPassword(password);
			korisnik.setAvatar(avatar);
			em.persist(korisnik);
			return "Uspesno ste se registrovali!";
		}
	}

	@Override
	public boolean login(String username, String password) {
		TypedQuery<KorisnikTim4>q=em.createNamedQuery("KorisnikTim4.getByUserAndPass",KorisnikTim4.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		korisnik=q.getSingleResult(); 
		if(korisnik != null)			
			return true;
		else			
			return false;
	}

	@Override
	@Remove
	public void logout() {
		korisnik = null;
	}

	@Override
	public void postaviOglas(KategorijaTim4 kategorija, String velicina, String boja, int cena, byte[] slika) {
		if (korisnik != null){
			PredmetTim4 predmet = new PredmetTim4();
			predmet.setKategorija(kategorija);
			predmet.setVelicina(velicina);
			predmet.setBoja(boja);
			predmet.setPocetnaCena(cena);
			predmet.setSlika(slika);
			em.persist(predmet);
			
			OglasTim4 oglas = new OglasTim4();
			oglas.setKorisnik(korisnik);
			oglas.setPredmet(predmet);
			em.persist(oglas);			
		}
		
	}

	@Override
	public boolean ponudiCenu(int idOglasa, int novaCena) {
		if(korisnik != null){
			OglasTim4 oglas = em.find(OglasTim4.class, idOglasa);
			if(oglas.getKorisnik().getUsername() != korisnik.getUsername()){
				if(oglas.getPonudjenaCena() < novaCena){
					oglas.setPonudjenaCena(novaCena);
					return true;
				}
			}				
		}
		return false;
	}

	@Override
	public void zatvoriPonude(int idOglasa) {
		if(korisnik !=null){
			OglasTim4 oglas = em.find(OglasTim4.class, idOglasa);
			if(oglas.getKorisnik().getUsername() == korisnik.getUsername())
				oglas.setIstekao(true);
		}
		
	}
	//postavlja komentar na oglas
	@Override
	public void prokomentarisiOglas(int idOglas, String strKomentar) {		
		if(korisnik !=null){
			TypedQuery<OglasTim4> q = em.createQuery("OglasTim4.getById", OglasTim4.class);
			q.setParameter("id", idOglas);
			OglasTim4 oglas = q.getSingleResult();		
			if(oglas.getKomentari() == null)
			oglas.setKomentari(new ArrayList<>());
		
			KomentarTim4 komentar = new KomentarTim4();		
			komentar.setDatum(new Date());
			komentar.setKomentar(strKomentar);
			komentar.setKorisnik(korisnik);
			em.persist(komentar);
		
			oglas.getKomentari().add(komentar);
			em.merge(oglas);
		}
		
	}
	// idKorisnika je id onog korisnika na kog se komentar odnosi
	@Override	
	public void odgovoriNaKomentar(int idOglasa, String strKomentar, int idKorisnika) {
		if(korisnik != null){
			TypedQuery<OglasTim4> q = em.createQuery("OglasTim4.getById", OglasTim4.class);		
			q.setParameter("id", idOglasa);
			OglasTim4 oglas = q.getSingleResult();	
		
			KomentarTim4 komentar = new KomentarTim4();		
			komentar.setDatum(new Date());
			komentar.setKomentar(strKomentar);
			komentar.setKorisnik(korisnik);
			em.persist(komentar);
		
			oglas.getKomentari().add(komentar);
			em.merge(oglas);
		
			//dodajemo komentar onom korisniku kome je upucen
			TypedQuery<KorisnikTim4> query = em.createQuery("KorisnikTim4.getById", KorisnikTim4.class);
			query.setParameter("id", idKorisnika);
			KorisnikTim4 primalacKomentara = query.getSingleResult();
			if(primalacKomentara.getKomentari() != null)
			primalacKomentara.setKomentari(new ArrayList<>());
			primalacKomentara.getKomentari().add(komentar);
			em.persist(primalacKomentara);
		}
	}

	@Override
	public List<PredmetTim4> getSviPredmeti() {
		TypedQuery<PredmetTim4> q = em.createNamedQuery("PredmetTim4.findSviPredmeti", PredmetTim4.class);		
		return q.getResultList();
	}

	@Override
	public List<PredmetTim4> getByKategorija(String kategorija) {
		TypedQuery<PredmetTim4> q = em.createNamedQuery("PredmetTim4.findByKategorija", PredmetTim4.class);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<PredmetTim4> getByVelicina(String velicina, String kategorija) {
		TypedQuery<PredmetTim4> q = em.createNamedQuery("PredmetTim4.findByVelicina", PredmetTim4.class);
		q.setParameter("velicina", velicina);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<PredmetTim4> getByBoja(String boja, String kategorija) {
		TypedQuery<PredmetTim4> q = em.createNamedQuery("PredmetTim4.findByBoja", PredmetTim4.class);
		q.setParameter("boja", boja);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<PredmetTim4> getByMaterijal(String materijal, String kategorija) {
		TypedQuery<PredmetTim4> q = em.createNamedQuery("PredmetTim4.findByMaterijal", PredmetTim4.class);
		q.setParameter("materijal", materijal);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<PredmetTim4> getByMarka(String marka, String kategorija) {
		TypedQuery<PredmetTim4> q = em.createNamedQuery("PredmetTim4.findByMarka", PredmetTim4.class);
		q.setParameter("marka", marka);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	
	
}