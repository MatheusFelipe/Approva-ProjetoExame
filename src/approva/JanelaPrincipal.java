package approva;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JanelaPrincipal {

	private JFrame frame;
	JanelaMyQuestions janelaMyQuestions;
	JanelaMyExams janelaMyExams;
	JanelaMakeTest janelaMakeTest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal window = new JanelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Impact", Font.PLAIN, 14));
		frame.getContentPane().setBackground(new Color(0, 206, 209));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 613, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMyQuestions = new JButton("MyQuestions");
		btnMyQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaMyQuestions = new JanelaMyQuestions();
				janelaMyQuestions.setVisible(true);
			}
		});
		btnMyQuestions.setFont(new Font("Impact", Font.PLAIN, 15));
		btnMyQuestions.setBackground(new Color(244, 164, 96));
		btnMyQuestions.setBounds(59, 361, 127, 44);
		frame.getContentPane().add(btnMyQuestions);
		
		JButton btnMyExams = new JButton("MyExams");
		btnMyExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaMyExams = new JanelaMyExams();
				janelaMyExams.setVisible(true);
			}
		});
		btnMyExams.setFont(new Font("Impact", Font.PLAIN, 15));
		btnMyExams.setBackground(new Color(244, 164, 96));
		btnMyExams.setBounds(239, 361, 127, 44);
		frame.getContentPane().add(btnMyExams);
		
		JButton btnMakeTest = new JButton("MakeTest");
		btnMakeTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaMakeTest = new JanelaMakeTest ();
				janelaMakeTest.setVisible(true);
			}
		});
		btnMakeTest.setFont(new Font("Impact", Font.PLAIN, 15));
		btnMakeTest.setBackground(new Color(244, 164, 96));
		btnMakeTest.setBounds(418, 361, 127, 44);
		frame.getContentPane().add(btnMakeTest);
	}
}

