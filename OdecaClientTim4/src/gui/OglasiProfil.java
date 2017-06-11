package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListCellRenderer;

import javax.swing.SwingUtilities;

import beans.OdecaTim4I;
import entities.KategorijaTim4;
import entities.KorisnikTim4;
import entities.OglasTim4;
import entities.PredmetTim4;
import main.LoginScreen;

public class OglasiProfil {

	private JFrame frame;
	private ImageIcon avatar;
	private OdecaTim4I remoteEjb;
	private JComboBox<String> comboBoxKategorije;
	private List<OglasTim4> oglasi;
	private JTextField textFieldKomentar;
	private JTextField txtUnesiteIznos;
	private JList<OglasTim4> list;

	public OglasiProfil(KorisnikTim4 k) {
		initialize(k);
	}

	@SuppressWarnings("serial")
	private void initialize(KorisnikTim4 k) {
		comboBoxKategorije = new JComboBox<>();
		remoteEjb = Remote.getRemote();
		avatar = null;
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel right = new JPanel();
		frame.getContentPane().add(right, BorderLayout.EAST);
		GridBagLayout gbl_right = new GridBagLayout();
		gbl_right.columnWidths = new int[] { 0, 0 };
		gbl_right.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_right.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_right.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		right.setLayout(gbl_right);

		JLabel lblAvatar = new JLabel();
		try {
			avatar = new ImageIcon(k.getAvatar());
		} catch (NullPointerException e) {
			avatar = new ImageIcon("avatar-default.png");
		}
		Image img = avatar.getImage();
		lblAvatar.setBounds(0, 0, 100, 100);
		img = img.getScaledInstance(lblAvatar.getWidth(), lblAvatar.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);
		lblAvatar.setIcon(icon);
		GridBagConstraints gbc_lblAvatar = new GridBagConstraints();
		gbc_lblAvatar.insets = new Insets(0, 0, 5, 0);
		gbc_lblAvatar.gridx = 0;
		gbc_lblAvatar.gridy = 0;
		right.add(lblAvatar, gbc_lblAvatar);

		JLabel lblFirstAndLastName = new JLabel(k.getIme() + " " + k.getPrezime());
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		right.add(lblFirstAndLastName, gbc_lblUsername);

		JComboBox comboBoxVelicine = new JComboBox();
		comboBoxVelicine.setVisible(true);
		GridBagConstraints gbc_comboBoxVelicine = new GridBagConstraints();
		gbc_comboBoxVelicine.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxVelicine.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVelicine.gridx = 0;
		gbc_comboBoxVelicine.gridy = 4;
		right.add(comboBoxVelicine, gbc_comboBoxVelicine);

		JComboBox<String> comboBoxFilter = new JComboBox<>();
		comboBoxFilter.addItem("Filtriraj po velicini");
		comboBoxFilter.addItem("Filtriraj po boji");
		comboBoxFilter.addItem("Filtriraj po ceni");
		comboBoxFilter.addItem("Filtriraj po materijalu");
		comboBoxFilter.addItem("Filtriraj po marci");
		comboBoxFilter.setVisible(true);
		comboBoxFilter.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				comboBoxVelicine.setVisible(true);
				int tmp = comboBoxFilter.getSelectedIndex();
				comboBoxVelicine.removeAllItems();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switch (tmp) {
					case 0:
						List<String> velicine = remoteEjb.getSveUniqueVelicineByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : velicine) {
							comboBoxVelicine.addItem(s);
						}
						break;
					case 1:
						List<String> boje = remoteEjb.getSveUniqueBojeByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : boje) {
							comboBoxVelicine.addItem(s);
						}
						break;
					case 2:
						List<Integer> cene = remoteEjb.getSveUniqueCeneByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (Integer i : cene) {
							comboBoxVelicine.addItem(i.toString());
						}
						break;
					case 3:
						List<String> materijal = remoteEjb.getSveUniqueMaterijalByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : materijal) {
							comboBoxVelicine.addItem(s);
						}
						break;
					case 4:
						List<String> marka = remoteEjb.getSveUniqueMarkalByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : marka) {
							comboBoxVelicine.addItem(s);
						}
						break;
					}
				}
			}
		});
		List<String> velicine1 = remoteEjb.getSveUniqueVelicineByKategorija(
				KategorijaTim4.valueOf("PANTALONE"));
		for (String s : velicine1) {
			comboBoxVelicine.addItem(s);
		}
		GridBagConstraints gbc_comboBoxFilter = new GridBagConstraints();
		gbc_comboBoxFilter.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFilter.gridx = 0;
		gbc_comboBoxFilter.gridy = 3;
		right.add(comboBoxFilter, gbc_comboBoxFilter);

		List<KategorijaTim4> kategorije = remoteEjb.getKategorije();
		for (KategorijaTim4 kategorija : kategorije) {
			comboBoxKategorije.addItem(kategorija.name());
		}

		GridBagConstraints gbc_comboBoxKategorije = new GridBagConstraints();
		gbc_comboBoxKategorije.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxKategorije.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxKategorije.gridx = 0;
		gbc_comboBoxKategorije.gridy = 2;
		right.add(comboBoxKategorije, gbc_comboBoxKategorije);

		JPanel mid = new JPanel();
		frame.getContentPane().add(mid, BorderLayout.CENTER);
		mid.setLayout(new BorderLayout(0, 0));

		JPanel top = new JPanel();
		frame.getContentPane().add(top, BorderLayout.NORTH);

		JButton btnFIltriraj = new JButton("Filtriraj");
		btnFIltriraj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int tmp = comboBoxFilter.getSelectedIndex();
				mid.removeAll();
				JPanel panelOglasi = new JPanel();
				
				GridBagLayout gbl_panelOglasi = new GridBagLayout();
				gbl_panelOglasi.columnWidths = new int[] { 790, 0 };
				gbl_panelOglasi.rowHeights = new int[] { 145, 0 };
				gbl_panelOglasi.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
				gbl_panelOglasi.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
				panelOglasi.setLayout(gbl_panelOglasi);

				int i = 0;
				GridBagConstraints gbc_panelOglas = new GridBagConstraints();
				switch (tmp) {
				case 0:
					oglasi = remoteEjb.getByVelicina(comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					i = 0;
					for (OglasTim4 o : oglasi) {
						gbc_panelOglas.anchor = GridBagConstraints.PAGE_START;
						gbc_panelOglas.weightx =1.0;
						gbc_panelOglas.gridx = 0;
						gbc_panelOglas.gridy = i;
						panelOglasi.add(napraviOglas(o), gbc_panelOglas);
						i++;
					}
					break;
				case 1:
					oglasi = remoteEjb.getByBoja(comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					i = 0;
					for (OglasTim4 o : oglasi) {
						gbc_panelOglas.anchor = GridBagConstraints.PAGE_START;
						gbc_panelOglas.weightx =1.0;
						gbc_panelOglas.gridx = 0;
						gbc_panelOglas.gridy = i;
						panelOglasi.add(napraviOglas(o), gbc_panelOglas);
						i++;
					}
					break;
				case 2:
					oglasi = remoteEjb.getByCena(new Integer(comboBoxVelicine.getSelectedItem().toString()),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					i = 0;
					for (OglasTim4 o : oglasi) {
						gbc_panelOglas.anchor = GridBagConstraints.PAGE_START;
						gbc_panelOglas.weightx =1.0;
						gbc_panelOglas.gridx = 0;
						gbc_panelOglas.gridy = i;
						panelOglasi.add(napraviOglas(o), gbc_panelOglas);
						i++;
					}
					break;
				case 3:
					oglasi = remoteEjb.getByMaterijal(comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					i = 0;
					for (OglasTim4 o : oglasi) {
						gbc_panelOglas.anchor = GridBagConstraints.PAGE_START;
						gbc_panelOglas.weightx =1.0;
						gbc_panelOglas.gridx = 0;
						gbc_panelOglas.gridy = i;
						panelOglasi.add(napraviOglas(o), gbc_panelOglas);
						i++;
					}
					break;

				case 4:
					oglasi = remoteEjb.getByMarka(comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					i = 0;
					for (OglasTim4 o : oglasi) {
						gbc_panelOglas.anchor = GridBagConstraints.PAGE_START;
						gbc_panelOglas.weightx =1.0;
						gbc_panelOglas.gridx = 0;
						gbc_panelOglas.gridy = i;
						panelOglasi.add(napraviOglas(o), gbc_panelOglas);
						i++;
					}
					break;
				}
				JScrollPane scrollPaneOglasi = new JScrollPane(panelOglasi);
				mid.add(scrollPaneOglasi, BorderLayout.CENTER);
				mid.revalidate();
				mid.repaint();
			}
		});
		GridBagConstraints gbc_btnFIltriraj = new GridBagConstraints();
		gbc_btnFIltriraj.insets = new Insets(0, 0, 5, 0);
		gbc_btnFIltriraj.gridx = 0;
		gbc_btnFIltriraj.gridy = 5;
		right.add(btnFIltriraj, gbc_btnFIltriraj);
			
		
		 list = new JList<OglasTim4>(new Vector<OglasTim4>(k.getMojiOglasi()));		
		 list.setCellRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                if (renderer instanceof JLabel && value instanceof OglasTim4) {	                   
	                    ((JLabel) renderer).setText(((OglasTim4) value).getPredmet().getNaslov());
	                    ((JLabel) renderer).setText(((OglasTim4) value).getPredmet().getNaslov());
	                }
	                return renderer;
	            }
	        });
		 list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JPanel prikazOglasa = napraviOglas(list.getSelectedValue());
				mid.removeAll();
				mid.add(prikazOglasa);
				frame.validate();
				frame.repaint();
				
			}
		});
		JScrollPane listScroller = new JScrollPane(list);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 6;
		right.add(list, gbc_list);

		JButton btnOdjaviSe = new JButton("Odjavi se");
		GridBagConstraints gbc_btnOdjaviSe = new GridBagConstraints();
		gbc_btnOdjaviSe.gridx = 0;
		gbc_btnOdjaviSe.gridy = 8;
		btnOdjaviSe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginScreen();
				frame.setVisible(false);

				
			}
		});
		
		JButton btnNoviOglas = new JButton("Novi oglas");
		btnNoviOglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NoviOglas(k);
			}
		});
		GridBagConstraints gbc_btnNoviOglas = new GridBagConstraints();
		gbc_btnNoviOglas.insets = new Insets(0, 0, 5, 0);
		gbc_btnNoviOglas.gridx = 0;
		gbc_btnNoviOglas.gridy = 7;
		right.add(btnNoviOglas, gbc_btnNoviOglas);
		
		right.add(btnOdjaviSe, gbc_btnOdjaviSe);

		JLabel lblOglasi = new JLabel("Oglasi");
		top.add(lblOglasi);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	private JPanel napraviOglas(OglasTim4 o) {
		JPanel panelOglas = new JPanel();
		panelOglas.setPreferredSize(new Dimension(700,200));
		ImageIcon avatar1 = new ImageIcon();
		PredmetTim4 p = o.getPredmet();
		 //GridBagConstraints gbc_panelOglas = new GridBagConstraints();
		 //gbc_panelOglas.anchor = GridBagConstraints.CENTER;
		 //gbc_panelOglas.gridx = 0;
		// gbc_panelOglas.gridy = 0;
		 //panelOglasi.add(panelOglas, gbc_panelOglas);
		panelOglas.setLayout(new BorderLayout(0, 0));

		JPanel midTopPanel = new JPanel();
		panelOglas.add(midTopPanel, BorderLayout.NORTH);
		midTopPanel.setLayout(new BorderLayout(0, 0));
		JLabel lblNaslovOglasa = new JLabel(p.getNaslov());
		lblNaslovOglasa.setHorizontalAlignment(SwingConstants.CENTER);
		midTopPanel.add(lblNaslovOglasa, BorderLayout.CENTER);

		JLabel lblIdOglas = new JLabel("ID: " + p.getId());
		midTopPanel.add(lblIdOglas, BorderLayout.WEST);

		JLabel lblCenaOglasa = new JLabel("Cena: " + o.getPonudjenaCena());
		midTopPanel.add(lblCenaOglasa, BorderLayout.EAST);

		JPanel panelUnutrasnjostOglasa = new JPanel();
		panelOglas.add(panelUnutrasnjostOglasa, BorderLayout.CENTER);
		panelUnutrasnjostOglasa.setLayout(new BorderLayout(0, 0));

		JLabel lblSlika = new JLabel();
		try {
			avatar1 = new ImageIcon(p.getSlika());
		} catch (NullPointerException e) {
			avatar1 = new ImageIcon("avatar-default.png");
		}
		Image img = avatar1.getImage();
		lblSlika.setBounds(0, 0, 100, 100);
		img = img.getScaledInstance(lblSlika.getWidth(), lblSlika.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);
		lblSlika.setIcon(icon);
		lblSlika.setHorizontalAlignment(SwingConstants.CENTER);
		panelUnutrasnjostOglasa.add(lblSlika, BorderLayout.CENTER);

		JPanel panelInformacije = new JPanel();
		panelUnutrasnjostOglasa.add(panelInformacije, BorderLayout.EAST);
		GridBagLayout gbl_panelInformacije = new GridBagLayout();
		gbl_panelInformacije.columnWidths = new int[] { 0, 0 };
		gbl_panelInformacije.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelInformacije.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelInformacije.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInformacije.setLayout(gbl_panelInformacije);

		JLabel lblImeiprezimekojeokaciooglas = new JLabel(
				"Oglas postavio: "+o.getKorisnik().getIme() + " " + o.getKorisnik().getPrezime());
		GridBagConstraints gbc_lblImeiprezimekojeokaciooglas = new GridBagConstraints();
		gbc_lblImeiprezimekojeokaciooglas.gridx = 0;
		gbc_lblImeiprezimekojeokaciooglas.gridy = 5;
		panelInformacije.add(lblImeiprezimekojeokaciooglas, gbc_lblImeiprezimekojeokaciooglas);

		JLabel lblMarka = new JLabel("Proizvodjac: "+p.getMarka());
		GridBagConstraints gbc_lblMarka = new GridBagConstraints();
		gbc_lblMarka.gridx = 0;
		gbc_lblMarka.gridy = 6;
		panelInformacije.add(lblMarka, gbc_lblMarka);

		JLabel lblMaterijal = new JLabel("Materijal: "+p.getMaterijal());
		GridBagConstraints gbc_lblMaterijal = new GridBagConstraints();
		gbc_lblMaterijal.gridx = 0;
		gbc_lblMaterijal.gridy = 7;
		panelInformacije.add(lblMaterijal, gbc_lblMaterijal);

		JLabel lblVelicina = new JLabel("Velicina: "+p.getVelicina());
		GridBagConstraints gbc_lblVelicina = new GridBagConstraints();
		gbc_lblVelicina.gridx = 0;
		gbc_lblVelicina.gridy = 8;
		panelInformacije.add(lblVelicina, gbc_lblVelicina);

		JLabel lblBoja = new JLabel("Boja: "+p.getBoja());
		GridBagConstraints gbc_lblBoja = new GridBagConstraints();
		gbc_lblBoja.gridx = 0;
		gbc_lblBoja.gridy = 9;
		panelInformacije.add(lblBoja, gbc_lblBoja);

		JLabel lblOpis = new JLabel("Opis: "+p.getOpis());
		GridBagConstraints gbc_lblOpis = new GridBagConstraints();
		gbc_lblOpis.gridx = 0;
		gbc_lblOpis.gridy = 10;
		panelInformacije.add(lblOpis,gbc_lblOpis);

//		lblOpis.setHorizontalAlignment(SwingConstants.RIGHT);
//		panelUnutrasnjostOglasa.add(lblOpis, BorderLayout.SOUTH);

		JPanel panelKomentar = new JPanel();
		panelOglas.add(panelKomentar, BorderLayout.WEST);
		GridBagLayout gbl_panelKomentar = new GridBagLayout();
		gbl_panelKomentar.columnWidths = new int[] { 73, 1, 0 };
		gbl_panelKomentar.rowHeights = new int[] { 15, 0, 0, 0 };
		gbl_panelKomentar.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelKomentar.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelKomentar.setLayout(gbl_panelKomentar);

		JLabel lblKomentari = new JLabel("Komentari");
		GridBagConstraints gbc_lblKomentari = new GridBagConstraints();
		gbc_lblKomentari.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblKomentari.insets = new Insets(0, 0, 5, 5);
		gbc_lblKomentari.gridx = 0;
		gbc_lblKomentari.gridy = 0;
		panelKomentar.add(lblKomentari, gbc_lblKomentari);

		JList listKomentari = new JList();
		GridBagConstraints gbc_listKomentari = new GridBagConstraints();
		gbc_listKomentari.gridheight = 2;
		gbc_listKomentari.insets = new Insets(0, 0, 5, 0);
		gbc_listKomentari.anchor = GridBagConstraints.WEST;
		gbc_listKomentari.gridx = 1;
		gbc_listKomentari.gridy = 0;
		panelKomentar.add(listKomentari, gbc_listKomentari);

		textFieldKomentar = new JTextField();
		GridBagConstraints gbc_textFieldKomentar = new GridBagConstraints();
		gbc_textFieldKomentar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldKomentar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldKomentar.gridx = 0;
		gbc_textFieldKomentar.gridy = 1;
		textFieldKomentar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Komentarisi", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelKomentar.add(textFieldKomentar, gbc_textFieldKomentar);
		textFieldKomentar.setColumns(10);

		JButton btnKomentarisi = new JButton("Komentarisi");
		GridBagConstraints gbc_btnKomentarisi = new GridBagConstraints();
		gbc_btnKomentarisi.insets = new Insets(0, 0, 0, 5);
		gbc_btnKomentarisi.gridx = 0;
		gbc_btnKomentarisi.gridy = 2;
		panelKomentar.add(btnKomentarisi, gbc_btnKomentarisi);

		JPanel panelLicitiraj = new JPanel();
		panelOglas.add(panelLicitiraj, BorderLayout.SOUTH);

		txtUnesiteIznos = new JTextField();
		txtUnesiteIznos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Licitiraj ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtUnesiteIznos.setBounds(200, 100, 100, 100);
		panelLicitiraj.add(txtUnesiteIznos, BorderLayout.EAST);
		txtUnesiteIznos.setColumns(10);

		JButton btnLicitiraj = new JButton("Licitiraj");
		btnLicitiraj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int licitacija = Integer.parseInt(txtUnesiteIznos.getText());
					if(Remote.getRemote().ponudiCenu(o.getId(), licitacija))
						
						JOptionPane.showMessageDialog(null, "Uspesno ste licitirali!");						
					else
						JOptionPane.showMessageDialog(null, "Cena je manja od ponudjene!");
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuæi iznos!");
				}
			}
			
		});
		panelLicitiraj.add(btnLicitiraj, BorderLayout.EAST);

		JSeparator separatorNaslov = new JSeparator(SwingConstants.HORIZONTAL);
		separatorNaslov.setPreferredSize(new Dimension(500, 1));
		separatorNaslov.setVisible(true);
		separatorNaslov.setBackground(Color.BLACK);
		midTopPanel.add(separatorNaslov, BorderLayout.SOUTH);
		
		

		return panelOglas;
	}

}
