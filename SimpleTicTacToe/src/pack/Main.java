package pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	JButton[] btns;
	String[] player = { "x", "o" };
	boolean player1 = true;
	int round = 0;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		btns = new JButton[9];
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++) {
			JButton btn = new JButton();
			btn.setFont(new Font("ROBOT", Font.PLAIN, 50));
			btn.setFocusPainted(false);
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();
					if (btn.getName() == null)
						if (player1) {
							btn.setName(player[0]);
							btn.setText(player[0]);
							btn.setForeground(Color.BLUE);
							if (check())
								quitWindow(player[0] + " winns!");
							player1 = false;
						} else {
							btn.setName(player[1]);
							btn.setText(player[1]);
							btn.setForeground(Color.RED);
							if (check())
								quitWindow(player[1] + " winns!");
							player1 = true;
						}
				}
			});
			frame.add(btn);
			btns[i] = btn;
		}
		frame.setVisible(true);
	}

	private boolean check() {
		for (int i = 0; i < 3; i++)
			if (checkNames(i * 3, 1 + i * 3, 2 + i * 3) || checkNames(i, 3 + i, 6 + i))
				return true;
		if (checkNames(0, 4, 8) || checkNames(2, 4, 6))
			return true;
		round++;
		if (round == 9)
			quitWindow("No Winner!");
		return false;
	}

	private boolean checkNames(int b1, int b2, int b3) {
		if (btns[b1].getName() == btns[b2].getName() && btns[b2].getName() == btns[b3].getName()
				&& btns[b2].getName() != null)
			return true;
		return false;
	}

	private void quitWindow(String text) {
		Object[] options = { "restart", "quit" };
		int res = JOptionPane.showOptionDialog(null, text, "END", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		if (res != 1) {
			player1 = true;
			for (JButton tempbtn : btns) {
				tempbtn.setName(null);
				tempbtn.setText(null);
				tempbtn.setForeground(Color.BLACK);
				round=0;
			}
		} else if (res == 1)
			System.exit(0);
	}
}
