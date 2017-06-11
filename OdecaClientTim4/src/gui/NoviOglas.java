package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import entities.KategorijaTim4;
import entities.KorisnikTim4;

public class NoviOglas extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtNaslov;
	private JTextField txtProizvodjac;
	private JTextField txtOpis;
	private JTextField txtVelicina;
	private JTextField txtBoja;
	private Icon nemaSlike=new ImageIcon("slika.png");
	String putanja = "";
	private JTextField txtCena;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new NoviOglas();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 * Create the frame.
	 */
	public NoviOglas(KorisnikTim4 korisnik) {
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblKreiranjeNovogOglasa = new JLabel("Kreiranje novog oglasa");
		lblKreiranjeNovogOglasa.setFont(new Font("SansSerif", Font.BOLD, 18));
		topPanel.add(lblKreiranjeNovogOglasa);
		
		JPanel midPanel = new JPanel();
		contentPane.add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(null);
		
		JComboBox comboBoxKategorije = new JComboBox();
		List<KategorijaTim4> kategorije = Remote.getRemote().getKategorije();
		for (KategorijaTim4 kategorija : kategorije) {
			comboBoxKategorije.addItem(kategorija.name());
		}
		comboBoxKategorije.setBounds(72, 58, 363, 24);
		midPanel.add(comboBoxKategorije);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setEnabled(false);
		
		txtNaslov = new JTextField();
		txtNaslov.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtNaslov.getText().length() == 0 || txtBoja.getText().length() == 0 || txtProizvodjac.getText().length() == 0 || txtOpis.getText().length() == 0 || txtVelicina.getText().length() == 0 )
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});
		txtNaslov.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Unesite naslov oglasa", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtNaslov.setBounds(72, 108, 360, 45);
		midPanel.add(txtNaslov);
		txtNaslov.setColumns(10);
		
		txtProizvodjac = new JTextField();
		txtProizvodjac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtNaslov.getText().length() == 0 || txtBoja.getText().length() == 0 || txtProizvodjac.getText().length() == 0 || txtOpis.getText().length() == 0 || txtVelicina.getText().length() == 0 )
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});
		txtProizvodjac.setBorder(new TitledBorder(null, "Unesite marku proizvoda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		txtProizvodjac.setBounds(72, 226, 360, 45);
		midPanel.add(txtProizvodjac);
		txtProizvodjac.setColumns(10);
		
		txtOpis = new JTextField();
		txtOpis.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtNaslov.getText().length() == 0 || txtBoja.getText().length() == 0 || txtProizvodjac.getText().length() == 0 || txtOpis.getText().length() == 0 || txtVelicina.getText().length() == 0 )
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});
		txtOpis.setBorder(new TitledBorder(null, "Unesite opis proizvoda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		txtOpis.setBounds(72, 403, 608, 75);
		midPanel.add(txtOpis);
		txtOpis.setColumns(10);
		
		txtVelicina = new JTextField();
		txtVelicina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtNaslov.getText().length() == 0 || txtBoja.getText().length() == 0 || txtProizvodjac.getText().length() == 0 || txtOpis.getText().length() == 0 || txtVelicina.getText().length() == 0 )
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}
		});
		txtVelicina.setBorder(new TitledBorder(null, "Unesite velicinu proizvoda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		txtVelicina.setBounds(72, 335, 360, 45);
		midPanel.add(txtVelicina);
		txtVelicina.setColumns(10);
		
		txtBoja = new JTextField();
		txtBoja.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtNaslov.getText().length() == 0 || txtBoja.getText().length() == 0 || txtProizvodjac.getText().length() == 0 || txtOpis.getText().length() == 0 || txtVelicina.getText().length() == 0 )
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});
		txtBoja.setBorder(new TitledBorder(null, "Unesite boju proizvoda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		txtBoja.setBounds(72, 280, 360, 45);
		midPanel.add(txtBoja);
		txtBoja.setColumns(10);
		
		JLabel lblSlika = new JLabel("");
		lblSlika.setLocation(new Point(453, 28));
		lblSlika.setMaximumSize(new Dimension(190, 270));
		lblSlika.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						final JFileChooser fc = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png");
						fc.setFileFilter(filter);
						int returnVal = fc.showOpenDialog(new JFrame());
						if(returnVal == JFileChooser.APPROVE_OPTION)
						{
							File selected = fc.getSelectedFile();
							String path = selected.getAbsolutePath();
							putanja = path;
							ImageIcon slika = new ImageIcon(path);
							Image img = slika.getImage();
							lblSlika.setBounds(453, 28, 200,280);
							Image novaSlika = img.getScaledInstance(lblSlika.getWidth(), lblSlika.getHeight(), Image.SCALE_SMOOTH);
							ImageIcon slicica = new ImageIcon(novaSlika);
							lblSlika.setText("");
							lblSlika.setIcon(slicica);
						}
					}
				});
		lblSlika.setIcon(nemaSlike);
		lblSlika.setBounds(447, 95, 219, 270);
		midPanel.add(lblSlika);
		
		txtCena = new JTextField();
		txtCena.setColumns(10);
		txtCena.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Unesite cenu proizvoda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		txtCena.setBounds(72, 169, 360, 45);
		midPanel.add(txtCena);
		
		JPanel panelBot = new JPanel();
		contentPane.add(panelBot, BorderLayout.SOUTH);
		btnPotvrdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					byte[] niz = null;
					try {
						niz = SlikaKonverter.fileToByteArray(putanja);
					} catch (FileNotFoundException e2) {
						try {
							niz = SlikaKonverter.extractBytes("slika.png");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					Remote.getRemote().postaviOglas(korisnik, KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString()), txtNaslov.getText(), txtVelicina.getText(), txtBoja.getText(), Integer.parseInt(txtCena.getText()), niz);
					
					setVisible(false);
				} catch (NumberFormatException pe) {
					JOptionPane.showMessageDialog(null, "Unesite cenu ciframa a ne slovima");
				}
			}
		});
		
		panelBot.add(btnPotvrdi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panelBot.add(btnOdustani);
	}
}