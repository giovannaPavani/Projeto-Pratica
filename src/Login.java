import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import bd.daos.*;
import bd.dbos.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {
	private JFrame frame;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 431, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Usuario:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(48, 75, 57, 30);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(59, 116, 46, 28);
		frame.getContentPane().add(lblSenha);
		
		JLabel lblEntrar = new JLabel("LOGIN");
		lblEntrar.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblEntrar.setBounds(169, 11, 152, 52);
		frame.getContentPane().add(lblEntrar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(115, 78, 233, 28);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(115, 117, 233, 30);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsuario.getText() == "" || txtSenha.getText() == "")
					JOptionPane.showMessageDialog(null,"Insira os dados antes de se logar!");
				else
				{
					String usuario = txtUsuario.getText();
					String senha = txtSenha.getText();
					try 
					{
						if(Funcionarios.cadastrado(usuario))
						{
							//int cod = Funcionarios.getCodigo(usuario);
							//System.out.print(cod+"");
							Funcionario func = Funcionarios.getFuncionario(usuario);
							System.out.print(func.getAgencia()+"");
							if(func.getSenha() == senha && func.getUsuario() == usuario)
							{
								Principal formP = new Principal(12);
								formP.frame.setVisible(true);
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Senha incorreta!");
								txtUsuario.setText("");
								txtSenha.setText("");
							}
						}
						else
							JOptionPane.showMessageDialog(null,"11Usu�rio n�o cadastrado!");
						
					}
					catch(Exception ex)
					{
						//JOptionPane.showMessageDialog(null,"Usu�rio n�o cadastrado!");
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,ex.getMessage());
						txtUsuario.setText("");
						txtSenha.setText("");
					}
				}
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEntrar.setBounds(169, 180, 89, 30);
		frame.getContentPane().add(btnEntrar);
	}
}

