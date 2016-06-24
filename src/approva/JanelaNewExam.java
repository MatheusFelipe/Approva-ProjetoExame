package approva;

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
import javax.swing.JTextField;

public class JanelaNewExam extends JFrame {

	ArrayList <Question> myQuestions;
	ArrayList <String> identificadores;
	ArrayList <String> categorias;
	JComboBox comboBoxCategorias;
	JTextArea textAreaDescription;
	JList list;
	JList listQuestions;
	DefaultListModel dm = new DefaultListModel();
	DefaultListModel dm1 = new DefaultListModel();
	DefaultComboBoxModel cb_categorias = new DefaultComboBoxModel();
	int index;
	private JPanel contentPane;
	ArrayList <Exam> myExams = new ArrayList <Exam> ();
	ArrayList <String> titlesExams = new ArrayList <String> ();
	ArrayList <Question> newExam = new ArrayList <Question> ();
	Exam novoExam = new Exam();
	JTextField textFieldTitle;
	
	public JanelaNewExam() {
		carregarArquivos();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 717, 548);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxCategorias = new JComboBox();
		comboBoxCategorias.setBounds(10, 48, 197, 25);
		comboBoxCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBoxCategorias.getSelectedIndex();
				if(index!=0){
					dm.clear();
					for(Question aux: myQuestions){
						if(aux.getGroup().equals(comboBoxCategorias.getItemAt(index))==true){
							atualizarLista(aux.getIdentificador());
						}	
					}
				}
				else{
					dm.clear();
					for (String aux: identificadores){
						atualizarLista(aux);
					}
				}			
			}
		});
		contentPane.add(comboBoxCategorias);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 197, 384);
		contentPane.add(scrollPane);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = identificadores.indexOf(list.getSelectedValue().toString());
				for (Question aux: myQuestions){ 
					if(aux.getIdentificador().equals(identificadores.get(index))==true){
						dm1.clear();
						newExam.add(aux);
						for(Question aux1: newExam){
							atualizarLista2(aux1.getIdentificador());
						}
					}
				}
			}
		});
		scrollPane.setViewportView(list);
		
		for (String name : identificadores){
			atualizarLista(name);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(424, 220, 264, 205);
		contentPane.add(scrollPane_1);
		
		atualizarCategorias("");
		for (String name : categorias){
			atualizarCategorias(name);
		}
		
		textAreaDescription = new JTextArea();
		scrollPane_1.setViewportView(textAreaDescription);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setEditable(true);
		
		JButton btnVoltar = new JButton("<<");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(10, 11, 49, 25);
		contentPane.add(btnVoltar);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(217, 117, 188, 351);
		contentPane.add(scrollPane_2);
		
		listQuestions = new JList();
		listQuestions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane_2.setViewportView(listQuestions);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(424, 181, 103, 38);
		contentPane.add(lblDescription);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index2 = identificadores.indexOf(listQuestions.getSelectedValue().toString());
				for(Question aux: newExam){
					if(aux.getIdentificador().equals(identificadores.get(index2))==true){
						newExam.remove(aux);
						break;
					}
				}				
				index2 = listQuestions.getSelectedIndex();
				dm1.removeElementAt(index2);
			}
		});
		btnDelete.setBounds(316, 478, 89, 23);
		contentPane.add(btnDelete);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(424, 162, 264, 25);
		contentPane.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblExamTitle = new JLabel("Exam Title:");
		lblExamTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblExamTitle.setBounds(424, 120, 103, 38);
		contentPane.add(lblExamTitle);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoExam.setTitle(textFieldTitle.getText());
				novoExam.setDescription(textAreaDescription.getText());
				novoExam.exam = newExam;
				myExams.add(novoExam);
				titlesExams.add(textFieldTitle.getText());
				criarSaidaTitles();
				criarSaida();
				dispose();
			}
		});
		btnSave.setBackground(new Color(144, 238, 144));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(576, 446, 112, 38);
		contentPane.add(btnSave);
		
		JLabel lblAddedQuestions = new JLabel("Added Questions:");
		lblAddedQuestions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddedQuestions.setBounds(224, 84, 128, 38);
		contentPane.add(lblAddedQuestions);
	}
	
	void atualizarLista(String arg){	
		list.setModel(dm);
		dm.addElement(arg);
	}
	
	void atualizarLista2(String arg){	
		listQuestions.setModel(dm1);
		dm1.addElement(arg);
	}

	void atualizarCategorias(String arg){
		comboBoxCategorias.setModel(cb_categorias);
		cb_categorias.addElement(arg);
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
	    	FileInputStream arquivoLeitura = new FileInputStream("categorias.dat");
	    	//Classe responsavel por recuperar os objetos do arquivo
	    	ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
	    	
	    	categorias = (ArrayList)objLeitura.readObject();
	    	//System.out.println(obj_recuperado.getNome());
	    	objLeitura.close();
	    	arquivoLeitura.close();
	    	//atualizarLista(titulos);
	    }catch( Exception e ){
	    	categorias = new ArrayList <String> ();
	    	//e.printStackTrace( );
	    }
		try{
	    	//Carrega o arquivo
	    	FileInputStream arquivoLeitura = new FileInputStream("identificadores.dat");
	    	//Classe responsavel por recuperar os objetos do arquivo
	    	ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
	    	
	    	identificadores = (ArrayList)objLeitura.readObject();
	    	//System.out.println(obj_recuperado.getNome());
	    	objLeitura.close();
	    	arquivoLeitura.close();
	    	//atualizarLista(titulos);
	    }catch( Exception e ){
	    	identificadores = new ArrayList <String> ();
	    	//e.printStackTrace( );
	    }
		try{
	    	//Carrega o arquivo
	    	FileInputStream arquivoLeitura = new FileInputStream("myQuestions.dat");
	    	//Classe responsavel por recuperar os objetos do arquivo
	    	ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
	    	
	    	myQuestions = (ArrayList)objLeitura.readObject();
	    	//System.out.println(obj_recuperado.getNome());
	    	objLeitura.close();
	    	arquivoLeitura.close();
	    }catch( Exception e ){
	    	myQuestions = new ArrayList <Question> ();
	    	//e.printStackTrace( );
	    }
	}
	void disporQuestoes(){
		dm1.clear();
		for(Question aux1: newExam){
			atualizarLista2(aux1.getIdentificador());
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaNewExam frame = new JanelaNewExam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
