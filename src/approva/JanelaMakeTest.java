package approva;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class JanelaMakeTest extends JFrame {
	int pontuacao = 0;
	JPanel cards = new JPanel(new CardLayout());
	private JPanel contentPane;
	ArrayList <Exam> myExams;
	ArrayList <String> titlesExams;
	JTextArea textAreaDescription;
	JList list;
	DefaultListModel dm = new DefaultListModel();
	int index;
	ArrayList <Question> actualTestQuestions;
	ArrayList <CardPanel> painel = new ArrayList <CardPanel> ();
	JButton end;
	public JanelaMakeTest() {
		
		carregarArquivos();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 691, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 205, 347);
		contentPane.add(scrollPane);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = titlesExams.indexOf(list.getSelectedValue().toString());
				for (Exam aux1: myExams ){ 
					if(aux1.getTitle().equals(titlesExams.get(index))==true){
						textAreaDescription.setText(aux1.getTitle() + "\n");	
						textAreaDescription.setText(textAreaDescription.getText()+ "\n" + aux1.getDescription());
					}
				}
			}
		});
		scrollPane.setViewportView(list);
		
		for (String name : titlesExams){
			atualizarLista(name);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(225, 125, 435, 228);
		contentPane.add(scrollPane_1);
		
		textAreaDescription = new JTextArea();
		scrollPane_1.setViewportView(textAreaDescription);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setEditable(false);
		
		JButton btnVoltar = new JButton("<<");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(10, 11, 66, 27);
		contentPane.add(btnVoltar);
		
		JLabel lblMyExams = new JLabel("My Exams");
		lblMyExams.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyExams.setBounds(10, 54, 83, 19);
		contentPane.add(lblMyExams);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(225, 95, 83, 19);
		contentPane.add(lblDescription);
		
		JButton btnStart = new JButton("Start!");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Exam aux1: myExams ){ 
					if(aux1.getTitle().equals(titlesExams.get(index))==true){
						actualTestQuestions = aux1.exam;
						JFrame f = new JFrame();
					        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					        for (Question q : actualTestQuestions) {
					            CardPanel p = new CardPanel("Panel " + String.valueOf(actualTestQuestions.indexOf(q)));
					            p.radioButton_A.setText(q.alternativas.get(0).texto);
								p.radioButton_B.setText(q.alternativas.get(1).texto);
								p.radioButton_C.setText(q.alternativas.get(2).texto);
								p.radioButton_D.setText(q.alternativas.get(3).texto);
								p.radioButton_E.setText(q.alternativas.get(4).texto);
								p.textPane.setText(q.comandoQuestao);
								p.questao = q;
					            cards.add(p, p.toString());
					            painel.add(p);
					       						
					        }
					        JPanel control = new JPanel();
					        control.setBackground(new Color(175, 238, 238));
					        
					        control.add(new JButton(new AbstractAction("\u22b2Prev") {

					            @Override
					            public void actionPerformed(ActionEvent e) {
					                CardLayout cl = (CardLayout) cards.getLayout();
					                cl.previous(cards);
					            }
					        }));
					        control.add(new JButton(new AbstractAction("Next\u22b3") {

					            @Override
					            public void actionPerformed(ActionEvent e) {
					                CardLayout cl = (CardLayout) cards.getLayout();
					                cl.next(cards);
					            }
					        }));
					        JPanel Finish  = new JPanel();
					        Finish.setBounds(0, 0, 570 , 80);
					        Finish.setBackground(new Color(175, 238, 238));
					        end = new JButton();
					        end.setText("Concluir");
					        Finish.add(end);
					        end.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									for(CardPanel p : painel){
										if(p.isCorrect==true){
											pontuacao = pontuacao + 1;
										}
									}
									FimdeProva aviso = new FimdeProva ();
									aviso.textPane.setText("Você acertou " + pontuacao + " questões de "+ actualTestQuestions.size()+"!");
									aviso.setVisible(true);
									aviso.okButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										aviso.dispose();
										dispose();	
										f.dispose();
										}
									});
								}
							});
					        
					        f.getContentPane().add(Finish,BorderLayout.SOUTH);
					        f.getContentPane().add(cards, BorderLayout.NORTH);
					        f.getContentPane().add(control, BorderLayout.CENTER);
					        f.pack();
					        f.setLocationRelativeTo(null);
					        f.setVisible(true);
					}
				}
			}
		});
		btnStart.setBackground(new Color(50, 205, 50));
		btnStart.setBounds(525, 369, 135, 45);
		contentPane.add(btnStart);
	}
	void atualizarLista(String arg){	
		list.setModel(dm);
		dm.addElement(arg);
	}
	
	void criarSaida(){
		 try{
		    	//Gera o arquivo para armazenar o objeto
		    	FileOutputStream arquivoGrav = new FileOutputStream("myExams.dat");
		    	//Classe responsavel por inserir os objetos
		    	ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
		    	//Grava o objeto cliente no arquivo
		    	objGravar.writeObject(myExams);
		    	//objGravar.flush();
		    	objGravar.close();
		    	//arquivoGrav.flush();
		    	arquivoGrav.close();
		    	//System.out.println("Objeto gravado com sucesso!");
		    }catch( Exception e ){
		    	e.printStackTrace( );
		    }
	}
	void criarSaidaTitles(){
		 try{
		    	//Gera o arquivo para armazenar o objeto
		    	FileOutputStream arquivoGrav = new FileOutputStream("titlesExams.dat");
		    	//Classe responsavel por inserir os objetos
		    	ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
		    	//Grava o objeto cliente no arquivo
		    	objGravar.writeObject(titlesExams);
		    	//objGravar.flush();
		    	objGravar.close();
		    	//arquivoGrav.flush();
		    	arquivoGrav.close();
		    	//System.out.println("Objeto gravado com sucesso!");
		    }catch( Exception e ){
		    	e.printStackTrace( );
		    }
	}
	void carregarArquivos(){
		try{
	    	//Carrega o arquivo
	    	FileInputStream arquivoLeitura = new FileInputStream("myExams.dat");
	    	//Classe responsavel por recuperar os objetos do arquivo
	    	ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
	    	
	    	myExams = (ArrayList)objLeitura.readObject();
	    	//System.out.println(obj_recuperado.getNome());
	    	objLeitura.close();
	    	arquivoLeitura.close();
	    	//atualizarLista(titulos);
	    }catch( Exception e ){
	    	myExams = new ArrayList <Exam> ();
	    	//e.printStackTrace( );
	    }
		try{
	    	//Carrega o arquivo
	    	FileInputStream arquivoLeitura = new FileInputStream("titlesExams.dat");
	    	//Classe responsavel por recuperar os objetos do arquivo
	    	ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
	    	
	    	titlesExams = (ArrayList)objLeitura.readObject();
	    	//System.out.println(obj_recuperado.getNome());
	    	objLeitura.close();
	    	arquivoLeitura.close();
	    	//atualizarLista(titulos);
	    }catch( Exception e ){
	    	titlesExams = new ArrayList <String> ();
	    	//e.printStackTrace( );
	    }
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaMakeTest frame = new JanelaMakeTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
