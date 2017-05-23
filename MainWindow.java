package org.reddiumdevs.afdc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Label;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.Box;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	static MainWindow frame = new MainWindow();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("AFDC");
		setResizable(false);
		// Here is window elements creation

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TextArea descArea = new TextArea();
		descArea.setText("Type description here");
		descArea.setBounds(10, 71, 514, 330);
		contentPane.add(descArea);

		JLabel headLabel = new JLabel("AFDC - Automate Flow Description Composer");
		headLabel.setBounds(83, 11, 380, 20);
		headLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(headLabel);

		// Label to click then show up info
		Label lblAbout = new Label("About");
		lblAbout.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAbout.setBounds(10, 439, 36, 22);
		lblAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblAbout);

		// Bold button
		JButton setBold = new JButton("B");
		setBold.setToolTipText("Bold");
		setBold.setFont(new Font("Tahoma", Font.BOLD, 11));
		setBold.setBounds(10, 40, 46, 23);
		setBold.setFocusable(false);
		contentPane.add(setBold);

		JButton setItalic = new JButton("I");
		setItalic.setToolTipText("Italic");
		setItalic.setFont(new Font("Tahoma", Font.ITALIC, 11));
		setItalic.setFocusable(false);
		setItalic.setBounds(66, 40, 46, 23);
		contentPane.add(setItalic);

		JButton setColor = new JButton("C");
		setColor.setToolTipText("Colored");
		setColor.setForeground(Color.RED);
		setColor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		setColor.setFocusable(false);
		setColor.setBounds(122, 40, 46, 23);
		contentPane.add(setColor);

		JButton setHeader = new JButton("H");
		setHeader.setToolTipText("Header (larger size)");
		setHeader.setForeground(Color.BLACK);
		setHeader.setFont(new Font("Tahoma", Font.PLAIN, 11));
		setHeader.setFocusable(false);
		setHeader.setBounds(178, 40, 46, 23);
		contentPane.add(setHeader);

		JButton loadPreview = new JButton("Load preview");
		loadPreview.setBounds(10, 407, 113, 23);
		loadPreview.setFocusable(false);
		contentPane.add(loadPreview);

		JButton clearTags = new JButton("[X]");
		clearTags.setToolTipText("Clear tags");
		clearTags.setForeground(Color.RED);
		clearTags.setFont(new Font("Tahoma", Font.BOLD, 11));
		clearTags.setFocusable(false);
		clearTags.setBounds(468, 40, 56, 23);
		contentPane.add(clearTags);

		setBold.addActionListener(new ActionListener() {

			@Override // Listener for setBold
			public void actionPerformed(ActionEvent arg0) {
				if (descArea.getText().length() > 0 && descArea.getSelectedText().length() > 0) {
					descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
							"<b>" + descArea.getSelectedText() + "</b>"));
				}
			}

		});

		setItalic.addActionListener(new ActionListener() {

			@Override // Listener for setItalic
			public void actionPerformed(ActionEvent e) {
				if (descArea.getText().length() > 0 && descArea.getSelectedText().length() > 0) {
					descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
							"<i>" + descArea.getSelectedText() + "</i>"));
				}
			}

		});

		setColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (descArea.getText().length() > 0 && descArea.getSelectedText().length() > 0) {
					String color = (String) JOptionPane
							.showInputDialog("Enter color name or hex code\nUse color-hex.com to get hex color"); // Show
																													// dialog
																													// to
																													// let
																													// user
																													// enter
					if (color.length() > 0) { // color
						if (color.substring(0, 1) == "#") {
							if (color.length() < 7) {
								System.out.println(1);
								JOptionPane.showMessageDialog(frame, "Invalid hex color code.",
										"Hex code must be not shorter than 6 characters long (excluding #)\nand look like this - #FFFFFF",
										JOptionPane.ERROR_MESSAGE); // Dialog to
																	// throw on
																	// invalid
																	// color
																	// [TODO:
																	// FIX!]

							} else {
								descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
										"<font color=\"" + color + "\">" + descArea.getSelectedText() + "</font>")); // Insert
																														// tags
																														// into
																														// text
							}
						} else {
							descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
									"<font color=\"" + color + "\">" + descArea.getSelectedText() + "</font>")); // Insert
																													// tags
																													// into
																													// text
						}

					}
				}
			}
		});

		setHeader.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (descArea.getText().length() > 0 && descArea.getSelectedText().length() > 0) {
					String[] headerslevels = { "1-st level header", "2-nd level header", "3-rd level header",
							"4-th level header", "5-th level header" }; // Enumeration
																		// of
																		// choices
					String headerlevel = (String) JOptionPane.showInputDialog(frame, "Select header level",
							"Header select", JOptionPane.PLAIN_MESSAGE, null, headerslevels, "1-st level header"); // Show
																														// dialog
					if (headerlevel == "1-st level header") {
						descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
								"<h1>" + descArea.getSelectedText() + "</h1>"));
					} else if (headerlevel == "2-nd level header") {
						descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
								"<h2>" + descArea.getSelectedText() + "</h2>"));
					} else if (headerlevel == "3-rd level header") {
						descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
								"<h3>" + descArea.getSelectedText() + "</h3>"));
					} else if (headerlevel == "4-th level header") {
						descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
								"<h4>" + descArea.getSelectedText() + "</h4>"));
					} else if (headerlevel == "5-th level header") {
						descArea.setText(descArea.getText().replaceAll(descArea.getSelectedText(),
								"<h5>" + descArea.getSelectedText() + "</h5>"));
					}
				}
			}

		});

		loadPreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("preview.html");
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write("<html>" + descArea.getText() + "</html>");
					fileWriter.flush();
					fileWriter.close();
					Desktop.getDesktop().browse(file.toURI());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		clearTags.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				descArea.setText(descArea.getText().replaceAll("<[^>]*>", ""));
			}

		});

		// Listener for lblAbout
		lblAbout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frame,
						"AFDC v1.1\nBy REDDIUMDEVS\nMain dev. - Redd35 (Jast Redd)\nSpecial thanks to Grank14\n\nA GrassLab project", "About", JOptionPane.PLAIN_MESSAGE, null);
			}
		});

	}
}
