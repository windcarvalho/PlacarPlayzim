/*package com.playzim.placarbt;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Placar extends JFrame {
	private JTextField games_TeamA;
	private JTextField sets_TeamA;
	private JTextField games_TeamB;
	private JTextField sets_TeamB;
	private GameBT game;
	private JButton pontos_TeamB;
	private JButton pontos_TeamA;
	private JLabel saque;
	private JButton undo;

	Placar(){
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("games");
		lblNewLabel.setBounds(29, 125, 46, 14);
		getContentPane().add(lblNewLabel);
		
		games_TeamA = new JTextField();
		games_TeamA.setBounds(24, 150, 86, 20);
		getContentPane().add(games_TeamA);
		games_TeamA.setColumns(10);
		
		JLabel lblSets = new JLabel("sets");
		lblSets.setBounds(29, 197, 46, 14);
		getContentPane().add(lblSets);
		
		sets_TeamA = new JTextField();
		sets_TeamA.setColumns(10);
		sets_TeamA.setBounds(24, 222, 86, 20);
		getContentPane().add(sets_TeamA);
		
		pontos_TeamA = new JButton("New button");
		pontos_TeamA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.score(TeamSide.TEAM_A);
				update_placar();
			}
		});
		pontos_TeamA.setBounds(133, 149, 89, 94);
		getContentPane().add(pontos_TeamA);
		
		JLabel lblNewLabel_1 = new JLabel("games");
		lblNewLabel_1.setBounds(432, 125, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		games_TeamB = new JTextField();
		games_TeamB.setColumns(10);
		games_TeamB.setBounds(427, 150, 86, 20);
		getContentPane().add(games_TeamB);
		
		JLabel lblSets_1 = new JLabel("sets");
		lblSets_1.setBounds(432, 197, 46, 14);
		getContentPane().add(lblSets_1);
		
		sets_TeamB = new JTextField();
		sets_TeamB.setColumns(10);
		sets_TeamB.setBounds(427, 222, 86, 20);
		getContentPane().add(sets_TeamB);
		
		pontos_TeamB = new JButton("New button");
		pontos_TeamB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.score(TeamSide.TEAM_B);
				update_placar();
			}
		});
		pontos_TeamB.setBounds(309, 149, 89, 94);
		getContentPane().add(pontos_TeamB);
		
		saque = new JLabel("New label");
		saque.setBounds(242, 58, 136, 14);
		getContentPane().add(saque);
		
		undo = new JButton("undo");
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.undo();
				update_placar();
			}
		});
		undo.setBounds(432, 38, 89, 23);
		getContentPane().add(undo);

		game = new GameBT("dupla1", "dupla2",3,4,7,true,10,TeamSide.TEAM_A);
				
	}

	protected void update_placar() {
		this.games_TeamA.setText(game.getGames()[0]+"");
		this.games_TeamB.setText(game.getGames()[1]+"");
		this.sets_TeamA.setText(game.getSets()[0]+"");
		this.sets_TeamB.setText(game.getSets()[1]+"");	
		this.pontos_TeamA.setText(game.getPoints()[0]+"");
		this.pontos_TeamB.setText(game.getPoints()[1]+"");
		this.saque.setText(game.getCurrentServer()==TeamSide.TEAM_A?"A":"B");		
	}
	
	public static void main(String[] args) {
		Placar p = new Placar();
		p.setSize(new Dimension(700,500));
		p.setVisible(true);
	}
}
*/