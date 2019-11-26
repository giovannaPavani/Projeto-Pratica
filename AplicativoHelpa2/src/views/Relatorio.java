package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import bd.core.MeuResultSet;
import bd.daos.Doacoes;
import bd.daos.Entidades;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Relatorio extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorio frame = new Relatorio();
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
	public Relatorio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 401);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRelatrio = new JLabel("RELAT\u00D3RIO DE DOA\u00C7\u00D5ES");
		lblRelatrio.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblRelatrio.setBounds(156, 11, 288, 23);
		contentPane.add(lblRelatrio);
		DefaultTableModel model = null;
		try 
		{
			MeuResultSet dados = Doacoes.getDoacoes();
			model = new DefaultTableModel(new Object[][] {},
			new String[] {
				"Identificação", "Produto", "Quantidade", "Entidade", "Data", "Entregue?"
			});
			
			while(dados.next())
			{
				model.addRow(new Object[] {dados.getInt(1)+"", dados.getString(2), dados.getString(3), dados.getString(4), dados.getDate(5)+"", dados.getString(6)});
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.print(ex.getMessage());
		}	
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 554, 311);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model); 
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(10, 11, 76, 23);
		contentPane.add(btnVoltar);
	}
}
