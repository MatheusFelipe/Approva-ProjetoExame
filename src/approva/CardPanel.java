package approva;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

/** @see http://stackoverflow.com/questions/5654926 */
public class CardPanel extends JPanel {

    private final String name;
    JTextPane textPane;
	JRadioButton radioButton_A;
	JRadioButton radioButton_C;
	JRadioButton radioButton_B;
	JRadioButton radioButton_D;
	JRadioButton radioButton_E;
	Question questao;
	boolean isCorrect = false; 
	
    public CardPanel(String name) {
        this.name = name;
        this.setPreferredSize(new Dimension(500, 570));
        this.setBackground(new Color(175, 238, 238));
        setLayout(null);
        
        this.radioButton_D = new JRadioButton("");
        radioButton_D.setFont(new Font("Arial", Font.PLAIN, 16));
        radioButton_D.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(radioButton_D.isSelected()==true){
        			isCorrect = questao.alternativas.get(3).ehCorreta;
        			//System.out.println("Resposta: " + isCorrect);
        			radioButton_B.setSelected(false);
        			radioButton_C.setSelected(false);
        			radioButton_A.setSelected(false);
        			radioButton_E.setSelected(false);
        		}
        	}
        });
        this.radioButton_D.setBounds(70, 370, 356, 59);
        this.radioButton_D.setBackground(new Color(175, 238, 238));
        this.add(this.radioButton_D);
        
        this.radioButton_A = new JRadioButton("");
        radioButton_A.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(radioButton_A.isSelected()==true){
        			isCorrect = questao.alternativas.get(0).ehCorreta;
        			//System.out.println("Resposta: " + isCorrect);
        			radioButton_B.setSelected(false);
        			radioButton_C.setSelected(false);
        			radioButton_D.setSelected(false);
        			radioButton_E.setSelected(false);
        			
        		}
        	}
        });
        this.radioButton_A.setBounds(70, 169, 356, 59);
        this.radioButton_A.setFont(new Font("Arial", Font.PLAIN, 16));
        this.radioButton_A.setBackground(new Color(175, 238, 238));
        add(this.radioButton_A);
        
        this.radioButton_B = new JRadioButton("");
        radioButton_B.setFont(new Font("Arial", Font.PLAIN, 16));
        radioButton_B.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(radioButton_B.isSelected()==true){
        			isCorrect = questao.alternativas.get(1).ehCorreta;
        			//System.out.println("Resposta: " + isCorrect);
        			radioButton_A.setSelected(false);
        			radioButton_C.setSelected(false);
        			radioButton_D.setSelected(false);
        			radioButton_E.setSelected(false);
        		}
        	}
        });
        this.radioButton_B.setBounds(70, 236, 356, 59);
        this.radioButton_B.setBackground(new Color(175, 238, 238));
        add(this.radioButton_B);
        
        this.radioButton_C = new JRadioButton("");
        radioButton_C.setFont(new Font("Arial", Font.PLAIN, 16));
        radioButton_C.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(radioButton_C.isSelected()==true){
        			isCorrect = questao.alternativas.get(2).ehCorreta;
        			//System.out.println("Resposta: " + isCorrect);
        			radioButton_B.setSelected(false);
        			radioButton_A.setSelected(false);
        			radioButton_D.setSelected(false);
        			radioButton_E.setSelected(false);
        		}
        	}
        });
        this.radioButton_C.setBounds(70, 303, 356, 59);
        this.radioButton_C.setBackground(new Color(175, 238, 238));
        add(this.radioButton_C);
        
        this.radioButton_E = new JRadioButton("");
        radioButton_E.setFont(new Font("Arial", Font.PLAIN, 16));
        radioButton_E.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(radioButton_E.isSelected()==true){
        			isCorrect = questao.alternativas.get(4).ehCorreta;
        			System.out.println("Resposta: " + isCorrect);
        			radioButton_B.setSelected(false);
        			radioButton_C.setSelected(false);
        			radioButton_D.setSelected(false);
        			radioButton_A.setSelected(false);
        		}
        	}
        });
        this.radioButton_E.setBounds(70, 440, 356, 59);
        this.radioButton_E.setBackground(new Color(175, 238, 238));
        add(this.radioButton_E);
        
        this.textPane = new JTextPane();
        this.textPane.setBounds(70, 45, 356, 112);
        this.textPane.setFont(new Font("Arial", Font.BOLD, 18));
        this.textPane.setBackground(new Color(175, 238, 238));
        add(this.textPane);
        StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    @Override
    public String toString() {
        return name;
    }
}