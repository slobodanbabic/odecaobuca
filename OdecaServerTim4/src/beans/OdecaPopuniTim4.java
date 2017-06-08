package beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.KategorijaTim4;
import entities.KorisnikTim4;
import entities.OglasTim4;
import entities.PredmetTim4;

@Singleton
@LocalBean
@Startup
public class OdecaPopuniTim4 {

	@PersistenceContext(name = "OdecaServerTim4")
	EntityManager em;

	@PostConstruct
	public void postConstruct() throws IOException {
		KorisnikTim4 k1 = dodajKorisnika("Mika", "Mikic", "miki@gmail.com", null, "miki", "miki");
		KorisnikTim4 k2 = dodajKorisnika("Joca", "Jocic", "joca@gmail.com", null, "joca", "joca");
		KorisnikTim4 k3 = dodajKorisnika("Miki", "Mikic", "mika@gmail.com", null, "mika", "mika");

		PredmetTim4 p1 = dodajPredmet("majica", "S", "crvena", 500, "pamuk", "puma", "polovna dobro ocuvana majica",
				null);
		PredmetTim4 p2 = dodajPredmet("pantalone", "36", "teksas", 700, "teksas", "Legend", "Ko novo", null);
		OglasTim4 o1 = dodajOglas(k1, p1);
		OglasTim4 o2 = dodajOglas(k3, p2);

		em.merge(k1);
		em.merge(k2);
		em.merge(k3);
		em.merge(p1);
		em.merge(o1);
		em.merge(o2);
	}

	private KorisnikTim4 dodajKorisnika(String ime, String prezime, String email, byte[] avatar, String username,
			String password) {
		KorisnikTim4 k = em.find(KorisnikTim4.class, username);
		if (k != null)
			return k;
		k = new KorisnikTim4();
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setEmail(email);
		k.setAvatar(avatar);
		k.setUsername(username);
		k.setPassword(password);
		em.persist(k);
		return k;
	}

	private PredmetTim4 dodajPredmet(String kategorija, String velicina, String boja, int pocetnaCena, String materijal,
			String marka, String opis, byte[] slika) {
		PredmetTim4 p = new PredmetTim4();
		p.setKategorija(KategorijaTim4.valueOf(kategorija.toUpperCase()));
		p.setVelicina(velicina);
		p.setBoja(boja);
		p.setPocetnaCena(pocetnaCena);
		p.setMaterijal(materijal);
		p.setMarka(marka);
		p.setOpis(opis);
		p.setSlika(slika);
		em.persist(p);
		return p;
	}

	private OglasTim4 dodajOglas(KorisnikTim4 korisnik, PredmetTim4 predmet) {
		OglasTim4 oglas = new OglasTim4();
		oglas.setKorisnik(korisnik);
		oglas.setPredmet(predmet);
		oglas.setPonudjenaCena(predmet.getPocetnaCena());
		em.persist(oglas);
		return oglas;
	}
}
