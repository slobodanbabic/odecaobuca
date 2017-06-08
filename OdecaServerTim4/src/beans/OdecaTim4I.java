package beans;

import java.util.List;

import entities.KategorijaTim4;
import entities.KorisnikTim4;
import entities.PredmetTim4;

public interface OdecaTim4I {

	public boolean registracija(String ime, String prezime, String email,String username, String password, byte[] avatar);
	public KorisnikTim4 login(String username, String password); 
	public void logout();
	public void postaviOglas(KategorijaTim4 kategorija, String velicina, String boja, int cena,byte[]slika );
	public boolean ponudiCenu(int idOglasa, int novaCena);
	public void zatvoriPonude(int idOglasa);
	public void prokomentarisiOglas(int idOglas, String komentar);
	public void odgovoriNaKomentar(int idOglasa, String komentar,int idKorisnika);
	public List<PredmetTim4> getSviPredmeti();
	public List<PredmetTim4> getByKategorija(String kategorija);
	public List<PredmetTim4> getByVelicina(String velicina,String kategorija);	
	public List<PredmetTim4> getByBoja(String boja,String kategorija);
	public List<PredmetTim4> getByMaterijal(String matrijal,String kategorija);
	public List<PredmetTim4> getByMarka(String marka,String kategorija);
	public List<KategorijaTim4> getKategorije();
	public List<String> getSveUniqueVelicineByKategorija(KategorijaTim4 kategorija);
}
