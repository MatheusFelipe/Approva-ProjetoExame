package approva;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JanelaNewQuestion extends JFrame {

	private JPanel contentPane;
	JTextField textFieldIdentificador;
	JTextField textFieldCategoria;
	JTextArea textAreaA;
	JTextArea textAreaB;
	JTextArea textAreaC;
	JTextArea textAreaD;
	JTextArea textAreaE;
	Question newQuestion;
	JRadioButton radioButton_A;
	JRadioButton radioButton_C;
	JRadioButton radioButton_B;
	JRadioButton radioButton_D;
	JRadioButton radioButton_E;
	JTextArea textAreaComando;
	ArrayList <Question> myQuestions = new ArrayList <Question> ();
	ArrayList <String> identificadores = new ArrayList <String> ();
	ArrayList <String> categorias = new ArrayList <String> ();

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
	public JanelaNewQuestion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 565, 617);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textAreaA = new JTextArea();
		textAreaA.setBounds(80, 224, 432, 43);
		contentPane.add(textAreaA);
		
		textAreaB = new JTextArea();
		textAreaB.setBounds(80, 276, 432, 43);
		contentPane.add(textAreaB);
		
		textAreaC = new JTextArea();
		textAreaC.setBounds(80, 330, 432, 43);
		contentPane.add(textAreaC);
		
		textAreaD = new JTextArea();
		textAreaD.setBounds(80, 384, 432, 43);
		contentPane.add(textAreaD);
		
		textAreaE = new JTextArea();
		textAreaE.setBounds(80, 438, 432, 43);
		contentPane.add(textAreaE);
		
		textAreaComando = new JTextArea();
		textAreaComando.setBounds(45, 101, 467, 112);
		contentPane.add(textAreaComando);
		
		radioButton_B = new JRadioButton("");
		radioButton_B.setBackground(new Color(175, 238, 238));
		radioButton_B.setBounds(45, 296, 35, 23);
		contentPane.add(radioButton_B);
		
		radioButton_C = new JRadioButton("");
		radioButton_C.setBackground(new Color(175, 238, 238));
		radioButton_C.setBounds(45, 350, 35, 23);
		contentPane.add(radioButton_C);
		
		radioButton_D = new JRadioButton("");
		radioButton_D.setBackground(new Color(175, 238, 238));
		radioButton_D.setBounds(45, 404, 35, 23);
		contentPane.add(radioButton_D);
		
		radioButton_E = new JRadioButton("");
		radioButton_E.setBackground(new Color(175, 238, 238));
		radioButton_E.setBounds(45, 458, 35, 23);
		contentPane.add(radioButton_E);
		
		radioButton_A = new JRadioButton("");
		radioButton_A.setBackground(new Color(175, 238, 238));
		radioButton_A.setBounds(45, 245, 35, 23);
		contentPane.add(radioButton_A);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBackground(new Color(50, 205, 50));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newQuestion = new Question(textAreaComando.getText(), textFieldCategoria.getText(), textFieldIdentificador.getText());
				newQuestion.AddAlternativa(textAreaA.getText(), radioButton_A.isSelected());
				newQuestion.AddAlternativa(textAreaB.getText(), radioButton_B.isSelected());
				newQuestion.AddAlternativa(textAreaC.getText(), radioButton_C.isSelected());
				newQuestion.AddAlternativa(textAreaD.getText(), radioButton_D.isSelected());
				newQuestion.AddAlternativa(textAreaE.getText(), radioButton_E.isSelected());
				myQuestions.add(newQuestion);
				identificadores.add(textFieldIdentificador.getText());
				if(categorias.indexOf(textFieldCategoria.getText())<0){
					categorias.add(textFieldCategoria.getText());
				}
				criarSaidaIdentificadores();
				criarSaidaCategorias();
				criarSaida();
				dispose();
			}
		});
		btnSave.setBounds(417, 504, 95, 34);
		contentPane.add(btnSave);
		
		JButton btnClear = new JButton("Clear All");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaA.setText("");;
				textAreaB.setText("");
				textAreaC.setText("");
				textAreaD.setText("");
				textAreaE.setText("");
				radioButton_A.setSelected(false);;
				radioButton_B.setSelected(false);
				radioButton_C.setSelected(false);
				radioButton_D.setSelected(false);
				radioButton_E.setSelected(false);
				textAreaComando.setText("");
				textFieldCategoria.setText("");
				textFieldIdentificador.setText("");	
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnClear.setBackground(new Color(255, 51, 51));
		btnClear.setBounds(81, 510, 89, 23);
		contentPane.add(btnClear);
		
		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdentificador.setBounds(45, 62, 89, 23);
		contentPane.add(lblIdentificador);
		
		textFieldIdentificador = new JTextField();
		textFieldIdentificador.setBounds(137, 65, 102, 20);
		contentPane.add(textFieldIdentificador);
		textFieldIdentificador.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria.setBounds(305, 10, 89, 23);
		contentPane.add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setColumns(10);
		textFieldCategoria.setBounds(380, 13, 158, 21);
		contentPane.add(textFieldCategoria);
		
		JButton btnBack = new JButton("<<");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(45, 12, 70, 34);
		contentPane.add(btnBack);
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
}
