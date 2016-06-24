package approva;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class JanelaMyExams extends JFrame {

	private JPanel contentPane;
	ArrayList <Exam> myExams;
	ArrayList <String> titlesExams;
	JTextArea textAreaDescription;
	JList list;
	DefaultListModel dm = new DefaultListModel();
	int index;
	
	public JanelaMyExams() {
		carregarArquivos();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 691, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBackground(new Color(255, 204, 153));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = titlesExams.indexOf(list.getSelectedValue().toString());
				System.out.println("Print:" + list.getSelectedValue());
				for (Exam aux: myExams){ 
					if(aux.getTitle().equals(titlesExams.get(index))==true){
						JanelaNewExam editExam = new JanelaNewExam();
						editExam.myExams = myExams;
						editExam.titlesExams = titlesExams;
						editExam.textAreaDescription.setText(aux.getDescription());
						editExam.textFieldTitle.setText(aux.getTitle());
						editExam.newExam = aux.exam;
						editExam.setVisible(true);
						editExam.disporQuestoes();
						dispose();
					}
				}				
			}
		});
		btnEdit.setBounds(538, 439, 122, 37);
		contentPane.add(btnEdit);
		
		JButton btnErase = new JButton("Delete");
		btnErase.setBackground(new Color(255, 102, 102));
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = titlesExams.indexOf(list.getSelectedValue().toString());
				for(Exam aux: myExams){
					if(aux.getTitle().equals(titlesExams.get(index))==true){
						myExams.remove(aux);
						break;
					}
				}
				criarSaida();
				titlesExams.remove(index);
				criarSaidaTitles();
				
				index = list.getSelectedIndex();
				dm.removeElementAt(index);
				textAreaDescription.setText(" ");
			}
			
		});
		btnErase.setBounds(10, 439, 122, 37);
		contentPane.add(btnErase);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 205, 347);
		contentPane.add(scrollPane);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = titlesExams.indexOf(list.getSelectedValue().toString());
				System.out.println("oi");
				System.out.println(list.getSelectedValue());
				for (Exam aux1: myExams ){ 
					System.out.println("no for");
					if(aux1.getTitle().equals(titlesExams.get(index))==true){
						System.out.println("no if");
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
		scrollPane_1.setBounds(225, 125, 435, 303);
		contentPane.add(scrollPane_1);
		
		textAreaDescription = new JTextArea();
		scrollPane_1.setViewportView(textAreaDescription);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setEditable(false);
		
		JButton btnNewQuestion = new JButton("New Exam");
		btnNewQuestion.setBackground(new Color(50, 205, 50));
		btnNewQuestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaNewExam NovoExame = new JanelaNewExam();
				NovoExame.myExams = myExams;
				NovoExame.titlesExams = titlesExams;
				NovoExame.setVisible(true);
				dispose();
			}
		});
		btnNewQuestion.setBounds(525, 69, 135, 37);
		contentPane.add(btnNewQuestion);
		
		JButton btnVoltar = new JButton("<<");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(10, 11, 49, 25);
		contentPane.add(btnVoltar);
		
		JLabel lblMyExams = new JLabel("My Exams");
		lblMyExams.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyExams.setBounds(10, 54, 83, 19);
		contentPane.add(lblMyExams);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(225, 95, 83, 19);
		contentPane.add(lblDescription);
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
					JanelaMyExams frame = new JanelaMyExams();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
}
