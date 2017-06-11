package beans;

import java.util.List;

import entities.KategorijaTim4;
import entities.KorisnikTim4;
import entities.OglasTim4;

public interface OdecaTim4I {

	public boolean registracija(String ime, String prezime, String email,String username, String password, byte[] avatar);
	public KorisnikTim4 login(String username, String password); 
	public void logout();
	public void postaviOglas(KorisnikTim4 korisnik, KategorijaTim4 kategorija, String naslov, String velicina, String boja, int cena, byte[]slika);
	public boolean ponudiCenu(int idOglasa, int novaCena);
	public void zatvoriPonude(int idOglasa);
	public void prokomentarisiOglas(int idOglas, String komentar);
	public void odgovoriNaKomentar(int idOglasa, String komentar,int idKorisnika);
	public List<OglasTim4> getSviPredmeti();
	public List<OglasTim4> getByKategorija(KategorijaTim4 kategorija);
	public List<OglasTim4> getByVelicina(String velicina,KategorijaTim4 kategorija);	
	public List<OglasTim4> getByBoja(String boja,KategorijaTim4 kategorija);
	public List<OglasTim4> getByMaterijal(String matrijal,KategorijaTim4 kategorija);
	public List<OglasTim4> getByMarka(String marka,KategorijaTim4 kategorija);
	public List<OglasTim4> getByCena(int cena,KategorijaTim4 kategorija);
	public List<KategorijaTim4> getKategorije();
	public List<String> getSveUniqueVelicineByKategorija(KategorijaTim4 kategorija);
	public List<Integer> getSveUniqueCeneByKategorija(KategorijaTim4 kategorija);
	public List<String> getSveUniqueBojeByKategorija(KategorijaTim4 kategorija);
	public List<String> getSveUniqueMaterijalByKategorija(KategorijaTim4 kategorija);
	public List<String> getSveUniqueMarkalByKategorija(KategorijaTim4 kategorija);
}
