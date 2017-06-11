package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.naming.NamingException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import beans.OdecaTim4I;
import main.LoginScreen;

public class Registracija {

	private JFrame frame;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldEMail;
	private JTextField textFieldUsername;
	private final JPasswordField passwordField;
	private JTextField passwordVisibleField;
	private final JPasswordField passwordField_1;
	private String putanja;
	private Icon avatar1Icon;

	private static OdecaTim4I remoteEjb;
	private JTextField passwordVisibleField_1;

	public Registracija() {
		putanja = "";
		passwordField = new JPasswordField();
		passwordField_1 = new JPasswordField();
		avatar1Icon = new ImageIcon("avatar-default.png");
		remoteEjb = Remote.getRemote();
		frame = new JFrame();
		frame.setBounds(100, 100, 489, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setEnabled(false);

		JPanel top = new JPanel();
		frame.getContentPane().add(top, BorderLayout.NORTH);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblRegistracija = new JLabel("Registracija");
		top.add(lblRegistracija);

		JPanel mid = new JPanel();
		frame.getContentPane().add(mid, BorderLayout.CENTER);
		mid.setLayout(null);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(48, 12, 120, 15);
		mid.add(lblIme);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(48, 39, 120, 15);
		mid.add(lblPrezime);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(48, 66, 120, 15);
		mid.add(lblEmail);

		JLabel lblKorisnickoIme = new JLabel("Korisničko ime:");
		lblKorisnickoIme.setBounds(48, 92, 120, 14);
		mid.add(lblKorisnickoIme);

		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setBounds(48, 117, 120, 14);
		mid.add(lblLozinka);

		JLabel lblPotvrdaLozinke = new JLabel("Potvrdi lozinku:");
		lblPotvrdaLozinke.setBounds(48, 142, 120, 14);
		mid.add(lblPotvrdaLozinke);

		textFieldIme = new JTextField();
		textFieldIme.setBounds(190, 10, 223, 20);
		mid.add(textFieldIme);
		textFieldIme.setColumns(10);
		textFieldIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldIme.getText().length() == 0 || textFieldPrezime.getText().length() == 0
						|| textFieldEMail.getText().length() == 0 || textFieldUsername.getText().length() == 0
						|| new String(passwordField.getPassword()).length() == 0
						|| new String(passwordField_1.getPassword()).length() == 0)
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});

		textFieldPrezime = new JTextField();
		textFieldPrezime.setBounds(190, 37, 223, 20);
		mid.add(textFieldPrezime);
		textFieldPrezime.setColumns(10);
		textFieldPrezime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldIme.getText().length() == 0 || textFieldPrezime.getText().length() == 0
						|| textFieldEMail.getText().length() == 0 || textFieldUsername.getText().length() == 0
						|| new String(passwordField.getPassword()).length() == 0
						|| new String(passwordField_1.getPassword()).length() == 0)
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});

		textFieldEMail = new JTextField();
		textFieldEMail.setBounds(190, 64, 223, 20);
		mid.add(textFieldEMail);
		textFieldEMail.setColumns(10);
		textFieldEMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldIme.getText().length() == 0 || textFieldPrezime.getText().length() == 0
						|| textFieldEMail.getText().length() == 0 || textFieldUsername.getText().length() == 0
						|| new String(passwordField.getPassword()).length() == 0
						|| new String(passwordField_1.getPassword()).length() == 0)
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(190, 90, 223, 20);
		mid.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldIme.getText().length() == 0 || textFieldPrezime.getText().length() == 0
						|| textFieldEMail.getText().length() == 0 || textFieldUsername.getText().length() == 0
						|| new String(passwordField.getPassword()).length() == 0
						|| new String(passwordField_1.getPassword()).length() == 0)
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});

		passwordField.setBounds(190, 115, 223, 20);
		mid.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldIme.getText().length() == 0 || textFieldPrezime.getText().length() == 0
						|| textFieldEMail.getText().length() == 0 || textFieldUsername.getText().length() == 0
						|| new String(passwordField.getPassword()).length() == 0
						|| new String(passwordField_1.getPassword()).length() == 0)
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});

		passwordVisibleField = new JTextField();
		passwordVisibleField.setVisible(false);
		passwordVisibleField.setBounds(190, 115, 223, 20);
		mid.add(passwordVisibleField);

		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(421, 112, 21, 23);
		mid.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == 1) {
					passwordVisibleField.setText(new String(passwordField.getPassword()));
				} else {
					passwordField.setText(passwordVisibleField.getText());
				}
				boolean state = arg0.getStateChange() == 1 ? true : false;
				passwordVisibleField.setVisible(state);
				passwordField.setVisible(!state);
			}
		});

		passwordField_1.setBounds(190, 140, 223, 20);
		mid.add(passwordField_1);
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldIme.getText().length() == 0 || textFieldPrezime.getText().length() == 0
						|| textFieldEMail.getText().length() == 0 || textFieldUsername.getText().length() == 0
						|| new String(passwordField.getPassword()).length() == 0
						|| new String(passwordField_1.getPassword()).length() == 0)
					btnPotvrdi.setEnabled(false);
				else {
					btnPotvrdi.setEnabled(true);
				}
			}

		});

		JPanel bot = new JPanel();
		frame.getContentPane().add(bot, BorderLayout.SOUTH);

		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen();
				frame.setVisible(false);
			}
		});
		btnOdustani.setHorizontalAlignment(SwingConstants.RIGHT);
		bot.add(btnOdustani);

		final JLabel avatar1 = new JLabel();
		avatar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selected = fc.getSelectedFile();
					String path = selected.getAbsolutePath();
					putanja = path;
					ImageIcon slika = new ImageIcon(path);
					Image img = slika.getImage();
					avatar1.setBounds(48, 205, 100, 100);
					img = img.getScaledInstance(avatar1.getWidth(), avatar1.getHeight(),
							Image.SCALE_SMOOTH);
					ImageIcon slicica = new ImageIcon(img);
					avatar1.setText("");
					avatar1.setIcon(slicica);
				}
			}
		});

		avatar1.setBounds(48, 205, 100, 100);
		avatar1.setIcon(avatar1Icon);
		mid.add(avatar1);

		JLabel lblKlikniNaAvatar = new JLabel("Kliknite na avatara da bi ste postavili sliku:");
		lblKlikniNaAvatar.setBounds(48, 181, 420, 14);
		mid.add(lblKlikniNaAvatar);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == 1) {
					passwordVisibleField_1.setText(new String(passwordField_1.getPassword()));
				} else {
					passwordField_1.setText(passwordVisibleField_1.getText());
				}
				boolean state = arg0.getStateChange() == 1 ? true : false;
				passwordVisibleField_1.setVisible(state);
				passwordField_1.setVisible(!state);
			}
		});

		checkBox.setBounds(421, 138, 21, 23);
		mid.add(checkBox);

		passwordVisibleField_1 = new JTextField();
		passwordVisibleField_1.setBounds(190, 140, 223, 20);
		mid.add(passwordVisibleField_1);
		passwordVisibleField_1.setColumns(10);
		

		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ime = textFieldIme.getText();
				String prezime = textFieldPrezime.getText();
				String eMail = textFieldEMail.getText();
				String username = textFieldUsername.getText();
				String pass = new String(passwordField.getPassword());
				String potvrda = new String(passwordField_1.getPassword());

				if (!pass.equals(potvrda)) {
					JOptionPane.showMessageDialog(null, "Sifra i potvrda sifre se ne poklapaju!");
				}

				else {

					byte[] niz = null;
					try {
						niz = SlikaKonverter.fileToByteArray(putanja);
					} catch (FileNotFoundException e) {
						try {
							niz = SlikaKonverter.extractBytes("avatar-default.png");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

					boolean test = remoteEjb.registracija(ime, prezime, eMail, username, pass, niz);
					if (test) {
						new LoginScreen();
						frame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Username vec postoji");
					}
				}
			}
		});
		btnPotvrdi.setHorizontalAlignment(SwingConstants.RIGHT);
		bot.add(btnPotvrdi);
		frame.setVisible(true);
	}
}
