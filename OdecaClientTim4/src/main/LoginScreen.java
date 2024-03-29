package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.OdecaTim4I;
import entities.KorisnikTim4;
import gui.OglasiProfil;
import gui.Registracija;
import gui.Remote;

public class LoginScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	private JTextField passwordVisibleField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public LoginScreen() {		
		
		setLocationRelativeTo(null);
		
		
		setResizable(false);
		setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(444, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//posto povezivanje sa udaljnim serverom moze da potraje, izvrsavamo u posebnoj niti
				new Thread( () -> Remote.getRemote() ).start();
				
		JLabel lblDobrodosliUnesitePodatke = new JLabel("Dobrodo�li, unesite podatke:");
		lblDobrodosliUnesitePodatke.setBounds(24, 12, 398, 15);
		contentPane.add(lblDobrodosliUnesitePodatke);

		JLabel lblMolimVasPopunite = new JLabel("Pogrešno ste uneli korisnièko ime ili lozinku.");
		lblMolimVasPopunite.setVisible(false);
		lblMolimVasPopunite.setForeground(Color.RED);
		lblMolimVasPopunite.setBounds(24, 82, 398, 15);
		contentPane.add(lblMolimVasPopunite);
		
		JCheckBox passwordCheckBox = new JCheckBox("");
		passwordCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==1) {
					passwordVisibleField.setText(new String(passwordField.getPassword()));
				}
				else {
					passwordField.setText(passwordVisibleField.getText());
				}
				boolean state = arg0.getStateChange()==1 ? true : false;
				passwordVisibleField.setVisible(state);
				passwordField.setVisible(!state);
			}
		});
		passwordCheckBox.setBounds(288, 46, 21, 23);
		contentPane.add(passwordCheckBox);
		
		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.setEnabled(false);
		btnPrijava.setBounds(319, 46, 98, 25);
		contentPane.add(btnPrijava);
		btnPrijava.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				lblMolimVasPopunite.setVisible(false);
				String username = usernameField.getText();
				String password = passwordCheckBox.isSelected() ? passwordVisibleField.getText() : new String(passwordField.getPassword());
				KorisnikTim4 korisnik = Remote.getRemote().login(username, password);
				if (korisnik != null) {
					new OglasiProfil(korisnik);
					setVisible(false);
				} else {
					lblMolimVasPopunite.setVisible(true);
				}
			}

		});

		passwordVisibleField = new JTextField();
		passwordVisibleField.setVisible(false);
		passwordVisibleField.setBounds(162, 49, 118, 19);
		contentPane.add(passwordVisibleField);
		passwordVisibleField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(24, 49, 114, 19);
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (usernameField.getText().length() == 0 || new String(passwordField.getPassword()).length() == 0)
					btnPrijava.setEnabled(false);
				else {
					btnPrijava.setEnabled(true);
				}
			}
		});
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(162, 49, 118, 19);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (usernameField.getText().length() == 0 || new String(passwordField.getPassword()).length() == 0)
					btnPrijava.setEnabled(false);
				else {
					btnPrijava.setEnabled(true);
				}
			}

		});
		contentPane.add(passwordField);

		JLabel lblNapraviNoviNalog = new JLabel("Napravi novi nalog.");
		lblNapraviNoviNalog.addMouseListener(new MouseAdapter() {

			Font original;

			@Override
			public void mouseClicked(MouseEvent e) {
				new Registracija();
				setVisible(false);
			}

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void mouseEntered(MouseEvent e) {
				original = e.getComponent().getFont();
				Map attributes = original.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				e.getComponent().setFont(original.deriveFont(attributes));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.getComponent().setFont(original);
			}

		});
		lblNapraviNoviNalog.setForeground(Color.BLUE);
		lblNapraviNoviNalog.setBounds(24, 100, 165, 15);
		contentPane.add(lblNapraviNoviNalog);
		setVisible(true);
	}

}