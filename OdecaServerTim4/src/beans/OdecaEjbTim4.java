package beans;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	public boolean registracija(String ime, String prezime, String email, String username, String password,
			byte[] avatar) {
		TypedQuery<String> q = em.createNamedQuery("KorisnikTim4.getByUsername", String.class);
		q.setParameter("username", username);		
		List<String> usernamel=q.getResultList();
		if(usernamel.size() != 0)
			return false;
			//return String.format("Korisnicko ime %s vec postoji u bazi! \nPokusajte sa drugim korisnickim imenom.", username);
		else{
			KorisnikTim4 korisnik = new KorisnikTim4();
			korisnik.setIme(ime);
			korisnik.setPrezime(prezime);
			korisnik.setEmail(email);
			korisnik.setUsername(username);
			korisnik.setPassword(password);
			korisnik.setAvatar(avatar);
			em.persist(korisnik);
			return true;
			//return "Uspesno ste se registrovali!";
		}
	}

	@Override
	public KorisnikTim4 login(String username, String password) {
		TypedQuery<KorisnikTim4>q=em.createNamedQuery("KorisnikTim4.getByUserAndPass",KorisnikTim4.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		try {
			return korisnik=q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	@Remove
	public void logout() {
		korisnik = null;
	}

	@Override
	public void postaviOglas(KorisnikTim4 korisnik, KategorijaTim4 kategorija, String naslov, String velicina, String boja, int cena, byte[] slika) {
		if (korisnik != null){
			PredmetTim4 predmet = new PredmetTim4();
			predmet.setKategorija(kategorija);
			predmet.setNaslov(naslov);
			predmet.setVelicina(velicina);
			predmet.setBoja(boja);
			predmet.setPocetnaCena(cena);
			predmet.setSlika(slika);
			em.persist(predmet);
			
			OglasTim4 oglas = new OglasTim4();
			oglas.setKorisnik(korisnik);
			oglas.setPredmet(predmet);
			em.persist(oglas);
			
			korisnik.getMojiOglasi().add(oglas);
			em.merge(korisnik);
		}
		
	}

	@Override
	public boolean ponudiCenu(int idOglasa, int novaCena) {		
		if(korisnik != null){
			OglasTim4 oglas = em.find(OglasTim4.class, idOglasa);
			if(oglas.getKorisnik().getUsername() != korisnik.getUsername()){
				if(oglas.getPonudjenaCena() < novaCena){
					oglas.setPonudjenaCena(novaCena);
					em.merge(oglas);
					korisnik.getAukcije().add(oglas);					
					em.merge(korisnik);
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
	public List<OglasTim4> getSviPredmeti() {
		TypedQuery<OglasTim4> q = em.createNamedQuery("OglasTim4.findSviPredmeti", OglasTim4.class);		
		return q.getResultList();
	}

	@Override
	public List<OglasTim4> getByKategorija(KategorijaTim4 kategorija) {
		TypedQuery<OglasTim4> q = em.createNamedQuery("OglasTim4.findByKategorija", OglasTim4.class);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<OglasTim4> getByVelicina(String velicina, KategorijaTim4 kategorija) {
		TypedQuery<OglasTim4> q = em.createNamedQuery("OglasTim4.findByVelicina", OglasTim4.class);
		q.setParameter("velicina", velicina);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<OglasTim4> getByBoja(String boja, KategorijaTim4 kategorija) {
		TypedQuery<OglasTim4> q = em.createNamedQuery("OglasTim4.findByBoja", OglasTim4.class);
		q.setParameter("boja", boja);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<OglasTim4> getByMaterijal(String materijal, KategorijaTim4 kategorija) {
		TypedQuery<OglasTim4> q = em.createNamedQuery("OglasTim4.findByMaterijal", OglasTim4.class);
		q.setParameter("materijal", materijal);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<OglasTim4> getByMarka(String marka, KategorijaTim4 kategorija) {
		TypedQuery<OglasTim4> q = em.createNamedQuery("OglasTim4.findByMarka", OglasTim4.class);
		q.setParameter("marka", marka);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<KategorijaTim4> getKategorije() {
		return Arrays.asList(KategorijaTim4.values());
	}

	@Override
	public List<String> getSveUniqueVelicineByKategorija(KategorijaTim4 kategorija) {
		TypedQuery<String> q =em.createQuery("SELECT DISTINCT(p.velicina) FROM PredmetTim4 p where p.kategorija = :kategorija",String.class);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<Integer> getSveUniqueCeneByKategorija(KategorijaTim4 kategorija) {
		TypedQuery<Integer> q =em.createQuery("SELECT DISTINCT(o.ponudjenaCena) FROM OglasTim4 o where o.predmet.kategorija = :kategorija",Integer.class);
		//TypedQuery<Integer> q =em.createQuery("SELECT DISTINCT(p.pocetnaCena) FROM PredmetTim4 p where p.kategorija = :kategorija",Integer.class);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<String> getSveUniqueBojeByKategorija(KategorijaTim4 kategorija) {
		TypedQuery<String> q =em.createQuery("SELECT DISTINCT(p.boja) FROM PredmetTim4 p where p.kategorija = :kategorija",String.class);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<String> getSveUniqueMaterijalByKategorija(KategorijaTim4 kategorija) {
		TypedQuery<String> q =em.createQuery("SELECT DISTINCT(p.materijal) FROM PredmetTim4 p where p.kategorija = :kategorija",String.class);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<OglasTim4> getByCena(int cena, KategorijaTim4 kategorija) {
		TypedQuery<OglasTim4> q = em.createNamedQuery("OglasTim4.findByCena", OglasTim4.class);
		q.setParameter("cena", cena);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}

	@Override
	public List<String> getSveUniqueMarkalByKategorija(KategorijaTim4 kategorija) {
		TypedQuery<String> q =em.createQuery("SELECT DISTINCT(p.marka) FROM PredmetTim4 p where p.kategorija = :kategorija",String.class);
		q.setParameter("kategorija", kategorija);
		return q.getResultList();
	}
	

	
	
}
