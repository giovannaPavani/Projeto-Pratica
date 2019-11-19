/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praticahelpa;
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

/**
 *
 * @author u19173
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
		lblEmail.setBounds(51, 107, 57, 30);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(62, 148, 46, 28);
		frame.getContentPane().add(lblSenha);
		
		JLabel lblEntrar = new JLabel("LOGIN");
		lblEntrar.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblEntrar.setBounds(169, 11, 152, 52);
		frame.getContentPane().add(lblEntrar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(118, 110, 233, 28);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(118, 149, 233, 30);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsuario.getText().equals("") || txtSenha.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Insira os dados para se logar!");
				else
				if(!rdbEntidade.isSelected() && !rdbFuncionario.isSelected())
					JOptionPane.showMessageDialog(null,"Selecione um tipo de usuário!");
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
								JOptionPane.showMessageDialog(null,"Usuário não cadastrado!");
						}
						catch(Exception ex)
						{
							//JOptionPane.showMessageDialog(null,"Usuário não cadastrado!");
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
								JOptionPane.showMessageDialog(null,"Usuário não cadastrado!");
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
		btnEntrar.setBounds(169, 189, 89, 30);
		frame.getContentPane().add(btnEntrar);
		
		rdbEntidade = new JRadioButton("Entidade");
		rdbEntidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbEntidade.setBounds(242, 67, 109, 23);
		frame.getContentPane().add(rdbEntidade);
		
		rdbFuncionario = new JRadioButton("Funcion\u00E1rio ");
		rdbFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbFuncionario.setBounds(51, 67, 157, 23);
		frame.getContentPane().add(rdbFuncionario);
		
		btnGrupo = new javax.swing.ButtonGroup();
		btnGrupo.add(rdbEntidade);
		btnGrupo.add(rdbFuncionario);
		
		JLabel lblHelpa = new JLabel("Helpa!");
		lblHelpa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHelpa.setBounds(72, 86, 72, 23);
		frame.getContentPane().add(lblHelpa);
	}
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
