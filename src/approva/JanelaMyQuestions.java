package approva;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JanelaMyQuestions extends JFrame {
	ArrayList <Question> myQuestions;// = new ArrayList <Question> ();
	ArrayList <String> identificadores;// = new ArrayList <String> ();
	ArrayList <String> categorias;// = new ArrayList <String> ();
	private JPanel contentPane;
	JComboBox comboBoxCategorias;
	JTextArea textAreaQuestion;
	JList list;
	DefaultListModel dm = new DefaultListModel();
	DefaultComboBoxModel cb_categorias = new DefaultComboBoxModel();
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaNewQuestion frame = new JanelaNewQuestion();
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
	public JanelaMyQuestions() {
		carregarArquivos();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 714, 598);
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
				
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBackground(new Color(255, 204, 153));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = identificadores.indexOf(list.getSelectedValue().toString());
				for (Question aux: myQuestions){ 
					if(aux.getIdentificador().equals(identificadores.get(index))==true){
						JanelaNewQuestion editQuestion = new JanelaNewQuestion();
						editQuestion.myQuestions = myQuestions;
						editQuestion.identificadores = identificadores;
						editQuestion.categorias = categorias;
						editQuestion.textAreaA.setText(aux.getAlternativa(0));
						editQuestion.radioButton_A.setSelected(aux.alternativas.get(0).ehCorreta);
						editQuestion.textAreaB.setText(aux.getAlternativa(1));
						editQuestion.radioButton_B.setSelected(aux.alternativas.get(1).ehCorreta);
						editQuestion.textAreaC.setText(aux.getAlternativa(2));
						editQuestion.radioButton_C.setSelected(aux.alternativas.get(2).ehCorreta);
						editQuestion.textAreaD.setText(aux.getAlternativa(3));
						editQuestion.radioButton_D.setSelected(aux.alternativas.get(3).ehCorreta);
						editQuestion.textAreaE.setText(aux.getAlternativa(4));
						editQuestion.radioButton_E.setSelected(aux.alternativas.get(4).ehCorreta);
						editQuestion.textFieldCategoria.setText(aux.getGroup());
						editQuestion.textFieldIdentificador.setText(aux.identificador + "(edited)");
						editQuestion.textAreaComando.setText(aux.getComando());
						editQuestion.myQuestions = myQuestions;
						editQuestion.identificadores = identificadores;
						editQuestion.categorias = categorias;
						editQuestion.setVisible(true);
						dispose();
					}
				}				
			}
		});
		btnEdit.setBounds(566, 439, 122, 37);
		contentPane.add(btnEdit);
		
		JButton btnErase = new JButton("Delete");
		btnErase.setBackground(new Color(255, 102, 102));
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = identificadores.indexOf(list.getSelectedValue().toString());
				for(Question aux: myQuestions){
					if(aux.getIdentificador().equals(identificadores.get(index))==true){
						myQuestions.remove(aux);
						break;
					}
				}
				criarSaida();
				identificadores.remove(index);
				criarSaidaIdentificadores();
				
				index = list.getSelectedIndex();
				dm.removeElementAt(index);
			}
		});
		btnErase.setBounds(225, 439, 122, 37);
		contentPane.add(btnErase);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 197, 464);
		contentPane.add(scrollPane);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = identificadores.indexOf(list.getSelectedValue().toString());
				char letra = 'A';
				for (Question aux: myQuestions){ 
					if(aux.getIdentificador().equals(identificadores.get(index))==true){
						textAreaQuestion.setText(aux.getComando() + "\n");	
						for(Alternativa alternativa: aux.alternativas){
							/*textAreaQuestion.append(" " + letra);
							textAreaQuestion.append(") " + alternativa.texto);*/
							textAreaQuestion.setText(textAreaQuestion.getText()+ "\n" + letra + ") " + alternativa.texto + "\n");
							letra = (char) (letra + 1);
						}
						break;
					}
				}
			}
		});
		scrollPane.setViewportView(list);
		
		for (String name : identificadores){
			atualizarLista(name);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(225, 48, 463, 380);
		contentPane.add(scrollPane_1);
		
		atualizarCategorias("");
		for (String name : categorias){
			atualizarCategorias(name);
		}
		
		textAreaQuestion = new JTextArea();
		scrollPane_1.setViewportView(textAreaQuestion);
		textAreaQuestion.setLineWrap(true);
		textAreaQuestion.setWrapStyleWord(true);
		textAreaQuestion.setEditable(false);
		
		JButton btnNewQuestion = new JButton("New Question");
		btnNewQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaNewQuestion NovaQuestao = new JanelaNewQuestion();
				NovaQuestao.myQuestions = myQuestions;
				NovaQuestao.identificadores = identificadores;
				NovaQuestao.categorias = categorias;
				NovaQuestao.setVisible(true);
				dispose();
			}
		});
		btnNewQuestion.setBounds(566, 10, 122, 26);
		contentPane.add(btnNewQuestion);
		
		JButton btnVoltar = new JButton("<<");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(10, 11, 49, 25);
		contentPane.add(btnVoltar);
	}
	
	void atualizarCategorias(String arg){
		comboBoxCategorias.setModel(cb_categorias);
		cb_categorias.addElement(arg);
	}
	
	void atualizarLista(String arg){	
		list.setModel(dm);
		dm.addElement(arg);
	}
	
	void criarSaida(){
		 try{
		    	//Gera o arquivo para armazenar o objeto
		    	FileOutputStream arquivoGrav = new FileOutputStream("myQuestions.dat");
		    	//Classe responsavel por inserir os objetos
		    	ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
		    	//Grava o objeto cliente no arquivo
		    	objGravar.writeObject(myQuestions);
		    	//objGravar.flush();
		    	objGravar.close();
		    	//arquivoGrav.flush();
		    	arquivoGrav.close();
		    	//System.out.println("Objeto gravado com sucesso!");
		    }catch( Exception e ){
		    	e.printStackTrace( );
		    }
	}
	void criarSaidaIdentificadores(){
		 try{
		    	//Gera o arquivo para armazenar o objeto
		    	FileOutputStream arquivoGrav = new FileOutputStream("identificadores.dat");
		    	//Classe responsavel por inserir os objetos
		    	ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
		    	//Grava o objeto cliente no arquivo
		    	objGravar.writeObject(identificadores);
		    	//objGravar.flush();
		    	objGravar.close();
		    	//arquivoGrav.flush();
		    	arquivoGrav.close();
		    	//System.out.println("Objeto gravado com sucesso!");
		    }catch( Exception e ){
		    	e.printStackTrace( );
		    }
	}
	void criarSaidaCategorias(){
		 try{
		    	//Gera o arquivo para armazenar o objeto
		    	FileOutputStream arquivoGrav = new FileOutputStream("categorias.dat");
		    	//Classe responsavel por inserir os objetos
		    	ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
		    	//Grava o objeto cliente no arquivo
		    	objGravar.writeObject(categorias);
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
}

