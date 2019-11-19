import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import bd.daos.*;
import bd.dbos.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

public class Login {
	private JFrame frame;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	JRadioButton rdbEntidade;
	JRadioButton rdbFuncionario;
	ButtonGroup btnGrupo;
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
		lblEmail.setBounds(51, 97, 57, 30);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(62, 138, 46, 28);
		frame.getContentPane().add(lblSenha);
		
		JLabel lblEntrar = new JLabel("LOGIN");
		lblEntrar.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblEntrar.setBounds(169, 11, 152, 52);
		frame.getContentPane().add(lblEntrar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(118, 100, 233, 28);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(118, 139, 233, 30);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsuario.getText() == "" || txtSenha.getText() == "")
					JOptionPane.showMessageDialog(null,"Insira os dados antes de se logar!");
				if(!rdbEntidade.isSelected() && !rdbFuncionario.isSelected())
					JOptionPane.showMessageDialog(null,"Selecione um tipo de usu�rio!");
				else
				{
					String usuario = txtUsuario.getText();
					String senha = txtSenha.getText();
					if(rdbFuncionario.isSelected()) // login usuario
					{
						try 
						{
							if(Funcionarios.cadastrado(usuario))
							{
								Funcionario func = Funcionarios.getFuncionarioByUsuario(usuario);
								if(func.getSenha().equals(senha) && func.getUsuario().equals(usuario))
								{
									System.out.print("senha");
									Principal formP = new Principal(func.getCodigo());
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
								JOptionPane.showMessageDialog(null,"Usu�rio n�o cadastrado!");
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
					else // login entidade
					{
						try 
						{
							if(Entidades.cadastrado(usuario))
							{
								Entidade ent = Entidades.getEntidadeByUsuario(usuario);
								if(ent.getSenha().equals(senha) && ent.getUsuario().equals(usuario))
								{
									PrincipalEntidade formE = new PrincipalEntidade(ent.getCodigo());
									formE.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Senha incorreta!");
									txtUsuario.setText("");
									txtSenha.setText("");
								}
							}
							else
								JOptionPane.showMessageDialog(null,"Usu�rio n�o cadastrado!");
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null,ex.getMessage());
							txtUsuario.setText("");
							txtSenha.setText("");
						}
					}
				}
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEntrar.setBounds(169, 180, 89, 30);
		frame.getContentPane().add(btnEntrar);
		
		rdbEntidade = new JRadioButton("Entidade");
		rdbEntidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbEntidade.setBounds(225, 67, 109, 23);
		frame.getContentPane().add(rdbEntidade);
		
		rdbFuncionario = new JRadioButton("Funcion\u00E1rio");
		rdbFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbFuncionario.setBounds(75, 67, 109, 23);
		frame.getContentPane().add(rdbFuncionario);
		
		btnGrupo = new javax.swing.ButtonGroup();
		btnGrupo.add(rdbEntidade);
		btnGrupo.add(rdbFuncionario);
	}
}

