import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

class SudokuPanel extends javax.swing.JPanel{
	SudokuGameAks game;
	private Timer timer;
	private JButton nbtn = new JButton("New Game");
	private static JTextField[][] boxes;
	private JLabel label = new JLabel("   Timer - 00:00:00");
	private JPanel[][] paneles;
	private JPanel center, bPanel, levelPanel;
	private JButton nBtn, cBtn, eBtn, hardBtn, midBtn, easyBtn, solve, about;
	private int[][] temp = new int[9][9];
	private int[][] grid = new int[9][9];
	private int counter = 0;
	
	 public JTextField newtextfield() {
	        JTextField j = new JTextField("");
	        j.setBorder(BorderFactory.createLineBorder(Color.lightGray));
	        j.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
	        j.setHorizontalAlignment(JTextField.CENTER);
	        /*-------------------mouse lisner----------------*/
	        j.addMouseListener(new MouseAdapter() {

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                if (j.isEditable()) {
	                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.decode("#f6ea80")));
	                    ((JTextField) e.getSource()).setBackground(Color.decode("#f6ea80"));
	                }
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                if (j.isEditable()) {
	                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.lightGray));
	                    ((JTextField) e.getSource()).setBackground(Color.white);
	                }
	            }
	        });
	        /*------------------------------------------------*/

	        j.addKeyListener(new KeyListener() {

	            @Override
	            public void keyTyped(KeyEvent e) {
	            }

	            @Override
	            public void keyPressed(KeyEvent e) {
	            }

	            @Override
	            public void keyReleased(KeyEvent e) {
	                if (j.isEditable()) {
	                    ((JTextField) e.getSource()).setForeground(Color.decode("#0c4"));
	                } else {
	                    ((JTextField) e.getSource()).setForeground(Color.black);
	                }
	            }
	        });
	        return j;
	    }
	    
	    public SudokuPanel() {
	        initComponents();
	        /*------------------------main panal  -------------------------------------*/
	        center = new JPanel();  //main panel
	        center.setLayout(new GridLayout(3, 3));     //grid for 3*3 
	        center.setBackground(Color.BLACK);
	        setLayout(new BorderLayout());
	        add(center);  //add main panel to frame 

	        boxes = new JTextField[9][9];
	        paneles = new JPanel[3][3];
	        label.setForeground(Color.black);
	        label.setBorder(BorderFactory.createLineBorder(Color.red, 4));
	        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
	        
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                paneles[i][j] = new JPanel();
	                paneles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
	                paneles[i][j].setLayout(new GridLayout(3, 3));
	                center.add(paneles[i][j]);
	            }
	        }
	        /*------------------------text fildes in boxes-------------------------------------*/

	        for (int n = 0; n < 9; n++) {
	            for (int i = 0; i < 9; i++) {
	                boxes[n][i] = newtextfield();
	                int fm = (n + 1) / 3;
	                if ((n + 1) % 3 > 0) {
	                    fm++;
	                }
	                int cm = (i + 1) / 3;
	                if ((i + 1) % 3 > 0) {
	                    cm++;
	                }
	                paneles[fm - 1][cm - 1].add(boxes[n][i]);   //add box to panel 
	            }
	        }
	        /*------------------------panal for buttons -------------------------------------*/

	        bPanel = new JPanel();
	        bPanel.setBackground(Color.decode("#AABFFF"));
	        bPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
	        bPanel.setLayout(new GridLayout(4, 3, 0, 20));

	        /*------------------------panal for new game button -------------------------------------*/
	        ActionListener action = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                label.setText(TimeFormat(counter));
	                counter++;

	            }
	        };

	        /*------------------------panal for new game button -------------------------------------*/
	        nBtn = new JButton("New Game");
	        nbtn.setSize(20, 50);
	        timer = new Timer(1000, action);
	        nBtn.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                counter = 0;
	                timer.start();
	                restgame();
	                SudokuGameAks.newGame();

	            }
	        });

	        /*------------------------panal for check game button -------------------------------------*/
	        cBtn = new JButton("Check Game +30s");

	        cBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                for (int i = 0; i < 9; i++) {
	                    for (int j = 0; j < 9; j++) {
	                        if (!boxes[i][j].isEditable()) {
	                            continue;
	                        } else if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) {
	                            boxes[i][j].setBackground(Color.decode("#C0DCD9"));
	                        } else if (boxes[i][j].getText().isEmpty()) {
	                            boxes[i][j].setBackground(Color.WHITE);
	                            continue;
	                        } else {
	                            boxes[i][j].setBackground(Color.red);
	                        }
	                    }
	                }
	                 counter += 30;
	            }
	        });
	        /*------------------------panal for new Exit button -------------------------------------*/
	        eBtn = new JButton("Exit");

	        eBtn.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });

	        /*------------------------panal for new Hard button -------------------------------------*/
	        easyBtn = new JButton("Hard");

	        easyBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                restgame();
	                counter = 0;
	                timer.start();
	                SudokuGameAks.setlevel(4);
	                SudokuGameAks.newGame();
	            }
	        });
	        /*------------------------panal for new Hard button -------------------------------------*/
	        midBtn = new JButton("Mideum");

	        midBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                restgame();
	                counter = 0;
	                timer.start();
	                SudokuGameAks.setlevel(3);
	                SudokuGameAks.newGame();

	            }
	        });
	        /*------------------------panal for new Hard button -------------------------------------*/
	        hardBtn = new JButton("Easy");

	        hardBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                restgame();
	                counter = 0;
	                timer.start();
	                SudokuGameAks.setlevel(2);
	                SudokuGameAks.newGame();
	            }
	        });
	        /*------------------------panal for new Hard button -------------------------------------*/
	        solve = new JButton("Solution");

	        solve.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                    timer.stop();
	                    counter = 0;
	                    label.setText(TimeFormat(counter));
	                    for (int i = 0; i < 9; i++) {
	                        for (int j = 0; j < 9; j++) {
	                            boxes[i][j].setText(String.valueOf(grid[i][j]));
	                        }
	                    }
	            }
	        });
	        /*------------------------panal for new about button -------------------------------------*/
	        about = new JButton("About");

	        about.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(center, "Created By Atish Kumar Sahu");
	            }
	        });
	        bPanel.add(hardBtn);   //add new game button to 
	        bPanel.add(midBtn);
	        bPanel.add(easyBtn);
	        bPanel.add(nBtn);   //add new game button to 
	        bPanel.add(cBtn);
	        bPanel.add(eBtn);
	        bPanel.add(solve);
	        bPanel.add(label);
	        bPanel.add(about);

	        add(bPanel, "South");   //add button panel to frame 
	    }

	    public void setarray(int[][] grid, int[][] temp) {
	        for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {
	                this.temp[i][j] = temp[i][j];
	                this.grid[i][j] = grid[i][j];
	            }
	        }
	    }

	    public void setTextLable() {
	        for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {
	                if (this.temp[i][j] != 0) {
	                    boxes[i][j].setText(String.valueOf(this.temp[i][j]));
	                    boxes[i][j].setEditable(false);
	                    boxes[i][j].setBackground(Color.decode("#C0DCC0"));
	                } else {
	                    boxes[i][j].setText("");
	                }
	            }
	        }
	    }

	    public static void restgame() {
	        for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {
	                boxes[i][j].setForeground(Color.black);
	                boxes[i][j].setEditable(true);
	                boxes[i][j].setBackground(Color.WHITE);
	            }
	        }
	    }

	    private String TimeFormat(int count) {

	        int hours = count / 3600;
	        int minutes = (count - hours * 3600) / 60;
	        int seconds = count - minutes * 60;

	        return String.format("      Timer :" + "%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
	    }

	    
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {

	        setLayout(null);
	    }// </editor-fold>//GEN-END:initComponents


	    // Variables declaration - do not modify//GEN-BEGIN:variables
	    // End of variables declaration//GEN-END:variables
}

public class SudokuGameAks {
	static JFrame frame;
	static SudokuPanel SP;
	private static int[][] grid;
	private static int[][] temp;
	private static Random ran = new Random();
	private static int level = 2;
	
	public static boolean isAvailable(int i, int j, int[][] grid) {
		for(int column = 0; column < 9; column++) {
			if(column != j && grid[i][column] == grid[i][j]) {
				return false;
			}
		}
		
		for(int row = 0; row < 9; row++) {
			if(row != i && grid[row][j] == grid[i][j]) {
				return false;
			}
		}
		
		for(int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
			for(int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
				if(row != i && col != j && grid[row][col] == grid[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int[][] getFreeCellList(int[][] grid){
		int numberOfFreeCells = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(grid[i][j] == 0) {
					numberOfFreeCells++;
				}
			}
		}
		int[][] freeCellList = new int[numberOfFreeCells][2];
		int count = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(grid[i][j] == 0) {
					freeCellList[count][0] = i;
					freeCellList[count][1] = j;
					count++;
				}
			}
		}
		return freeCellList;
	}
	
	public static boolean search(int[][] grid) {
		int[][] freeCellList = getFreeCellList(grid);
		int k = 0;
		boolean found = false;
		while(!found) {
			int i = freeCellList[k][0];
			int j = freeCellList[k][1];
			
			if(grid[i][j] == 0) {
				grid[i][j] = 1;
			}
			if(isAvailable(i, j, grid)) {
				if(k + 1 == freeCellList.length) {
					found = true;
				}else {
					k++;
				}
			}
			else if(grid[i][j] < 9) {
				grid[i][j] = grid[i][j] + 1;
			}
			else {
				while(grid[i][j] == 9) {
					grid[i][j] = 0;
					if(k == 0) {
						return false;
					}
					k--;
					i = freeCellList[k][0];
					j = freeCellList[k][1];
				}
				grid[i][j] = grid[i][j] + 1;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> getRandomNum(){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(Integer i = 1; i < 10; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		return numbers;
	}
	public static void setlevel(int lev) {
		level = lev;
	}
	
	public static void newGame() {
		int k = 0;
		ArrayList<Integer> randomnumber = getRandomNum();
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				grid[i][j] = 0;
				if(((j + 2)% 2) == 0 && ((i + 2) % 2) == 0)
				{
					grid[i][j] = randomnumber.get(k);
					k++;
					if(k == 9) {
						k = 0;
					}
				}
			}
		}
		if(search(grid)) {
			System.out.println("Ok!!!");
		}
		int rann = ran.nextInt(level);
		int c = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9 ; j++) {
				temp[i][j] = 0;
				if(c < rann) {
					c++;
					continue;
				}
				else {
					rann = ran.nextInt(level);
					c = 0;
					temp[i][j] = grid[i][j];
				}
			}
		}
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				System.err.print(grid[i][j]+" ");
			}
			System.out.println("111111111111111111111111111");
		}
		SP.setarray(grid, temp);
		SP.setTextLable();
	}
	
	public static void main(String args[]) {
		grid = new int[9][9];
		temp = new int[9][9];
		frame = new JFrame();
		frame.setSize(1000,1000);
		frame.setTitle("Sudoku Game - Atish Kumar Sahu");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Atish kumar sahu\\eclipse-workspace\\Resume_Sudoku_Game_Project_In_Java\\src\\sudokugame.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(50,50,50));
		SP = new SudokuPanel();
		frame.setContentPane(SP);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
