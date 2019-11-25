package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import bd.daos.Entidades;
import bd.daos.Funcionarios;
import bd.dbos.Entidade;
import bd.dbos.Funcionario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrocarSenha extends JFrame {

	private JPanel contentPane;
	private JTextField txtSenha;
	private JTextField txtConfirma;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrocarSenha frame = new TrocarSenha();
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
	public TrocarSenha(Object usu) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
			}
		});
		Object usuario = usu;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trocar Senha");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(99, 11, 153, 30);
		contentPane.add(lblNewLabel);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(91, 52, 223, 30);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(20, 55, 54, 21);
		contentPane.add(lblSenha);
		
		txtConfirma = new JTextField();
		txtConfirma.setColumns(10);
		txtConfirma.setBounds(91, 93, 223, 30);
		contentPane.add(txtConfirma);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar");
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmarSenha.setBounds(10, 86, 123, 21);
		contentPane.add(lblConfirmarSenha);
		
		JButton btnNewButton = new JButton("Trocar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSenha.getText().equals("") || txtConfirma.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Digite a nova senha!");
				else if(!txtSenha.getText().equals(txtConfirma.getText()))
				{
					JOptionPane.showMessageDialog(null,"Senhas incondizentes. Digite novamente!");
					txtConfirma.setText("");
				}
				else
				{
					String senha = txtSenha.getText();
					if(usuario instanceof Funcionario)
					{
						Funcionario func = (Funcionario)usuario;
						try 
						{
							func.setSenha(senha);
							Funcionarios.alterar(func);
							JOptionPane.showMessageDialog(null,"Senha alterada com sucesso!");
							dispose();
						} 
						catch (Exception e) 
						{
							JOptionPane.showMessageDialog(null,"Senha inválida. Tente novamente!");
						}
					}
					else
					{
						Entidade ent = (Entidade)usuario;
						try 
						{
							ent.setSenha(senha);
							Entidades.alterar(ent);
							JOptionPane.showMessageDialog(null,"Senha alterada com sucesso!");
							dispose();
						} 
						catch (Exception e) 
						{
							JOptionPane.showMessageDialog(null,"Senha inválida. Tente novamente!");
						}
					}
				}
			}
		});
		btnNewButton.setBounds(188, 134, 107, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblSenha_1 = new JLabel("senha:");
		lblSenha_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha_1.setBounds(20, 102, 123, 21);
		contentPane.add(lblSenha_1);
		
		JButton btnVoltar = new JButton("Cancelar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setBounds(41, 134, 107, 30);
		contentPane.add(btnVoltar);
	}
}
