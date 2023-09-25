import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
class TicTacToe implements ActionListener {
	Random rand = new Random();
	JFrame frame = new JFrame();
	JPanel title = new JPanel();
	JPanel button = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player1turn;
	int count = 0;
	TicTacToe() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setTitle("Tic-Tac-Toe By Atish Kumar Sahu");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Atish kumar sahu\\eclipse-workspace\\Resume_Tic_Tac_Toe_Game_In_Java\\src\\tictactoe.png"));
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 40));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setOpaque(true);

		title.setLayout(new BorderLayout());
		title.setBounds(0, 0, 800, 100);

		button.setLayout(new GridLayout(3, 3));
		button.setBackground(new Color(150, 30, 90));

		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 100));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		JButton restartButton = new JButton("Restart");
		restartButton.setFocusable(false);
		restartButton.setFont(new Font("MV Boli", Font.PLAIN, 30));
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartGame();
			}
		});
		title.add(restartButton, BorderLayout.EAST);
		title.add(textfield);
		frame.add(title);
		frame.add(title, BorderLayout.NORTH);
		frame.add(button);

		firstturn();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i]) {
				if (player1turn) {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setText("X");
						player1turn = false;
						textfield.setText("O Turn");
						count++;
						check();
					}
				} else {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setText("O");
						player1turn = true;
						textfield.setText("X Turn");
						count++;
						check();
					}
				}
			}
		}
	}
	public void firstturn() {
		textfield.setText("Tic-Tac-Toe Game!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rand.nextInt(2) == 0) {
			player1turn = true;
			textfield.setText("X Turn!");
		} else {
			player1turn = false;
			textfield.setText("O Turn!");
		}
	}
	public void check() {
		// check X win
		if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
			xwins(0, 1, 2);
		}
		if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
			xwins(3, 4, 5);
		}
		if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
			xwins(6, 7, 8);
		}
		if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
			xwins(0, 3, 6);
		}
		if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
			xwins(1, 4, 7);
		}
		if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
			xwins(2, 5, 8);
		}
		if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
			xwins(0, 4, 8);
		}
		if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
			xwins(2, 4, 6);
		}
		// check O win
		if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
			owins(0, 1, 2);
		}
		if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
			owins(3, 4, 5);
		}
		if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
			owins(6, 7, 8);
		}
		if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
			owins(0, 3, 6);
		}
		if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
			owins(1, 4, 7);
		}
		if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
			owins(2, 5, 8);
		}
		if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
			owins(0, 4, 8);
		}
		if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
			owins(2, 4, 6);
		}
		//for draw match
		if (buttons[0].getText() == "X" || buttons[0].getText() == "O") {
			draw();
		}
		if (buttons[1].getText() == "X" || buttons[1].getText() == "O") {
			draw();
		}
		if (buttons[2].getText() == "X" || buttons[2].getText() == "O") {
			draw();
		}
		if (buttons[3].getText() == "X" || buttons[3].getText() == "O") {
			draw();
		}
		if (buttons[4].getText() == "X" || buttons[4].getText() == "O") {
			draw();
		}
		if (buttons[5].getText() == "X" || buttons[5].getText() == "O") {
			draw();
		}
		if (buttons[6].getText() == "X" || buttons[6].getText() == "O") {
			draw();
		}
		if (buttons[7].getText() == "X" || buttons[7].getText() == "O") {
			draw();
		}
		if (buttons[8].getText() == "X" || buttons[8].getText() == "O") {
			draw();
		}
	}
	public void draw() {

		if (count == 9) {
			textfield.setText("Draw Match!!");
		}
	}
	public void xwins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X Wins");
	}
	public void owins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O Wins");

	}
	public void restartGame() {
		for (int i = 0; i < 9; i++) {
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 100));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setBackground(UIManager.getColor("Button.background"));
			buttons[i].setForeground(Color.BLACK); // Reset button text color
		}

		player1turn = rand.nextBoolean();
		if (player1turn) {
			textfield.setText("X Turn!");
			count = 0;
		} else {
			textfield.setText("O Turn!");
			count = 0;
		}
	}
}
public class TicTacToeMain {
	public static void main(String[] args) {
		TicTacToe TTT = new TicTacToe();
	}
}