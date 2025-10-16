import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazeApp {

	private JFrame frame;
	private Maze maze;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeApp window = new MazeApp();
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
	public MazeApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		JTextArea mazeDisplay = new JTextArea();
		mazeDisplay.setEditable(false);
		mazeDisplay.setFocusable(false);
		mazeDisplay.setCaretColor(mazeDisplay.getBackground()); 
		mazeDisplay.setFont(new Font("Courier New", Font.BOLD, 20));
		mazeDisplay.setBounds(25, 38, 700, 634);
		frame.getContentPane().add(mazeDisplay);
		
		JMenuBar fileMenu = new JMenuBar();
		fileMenu.setToolTipText("File");
		fileMenu.setBounds(10, 0, 101, 22);
		frame.getContentPane().add(fileMenu);
		
		JMenu fileJMenu = new JMenu("File");
		fileMenu.add(fileJMenu);
		
		JMenuItem openFile = new JMenuItem("Open File");
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze = new Maze(mazeDisplay);
				maze.selectMap();
				System.out.println("maze is "+ maze); //debbug
			}
		});
		
		
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				//if statement to test if victory true and if it is then disable keys
				//else statements are down below 
				if(maze==null || Maze.victory==true) {
					return;
				} else if(e.getKeyCode()==KeyEvent.VK_W) {
					//call moveUp
					maze.moveUp();
					
				} else if(e.getKeyCode()==KeyEvent.VK_A) {		
					//call moveLeft
					maze.moveLeft();
				} else if(e.getKeyCode()==KeyEvent.VK_S) {		
					//call movedown
					maze.moveDown();
				} else if(e.getKeyCode()==KeyEvent.VK_D) {		
					//call moveright
					maze.moveRight();
					
				}
				
		}
		});
		frame.setBounds(100, 100, 763, 736);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		
		
		
		
		JMenuItem resetMapItem = new JMenuItem("Reset Map");
		resetMapItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.resetMap();
			}
		});
		fileJMenu.add(resetMapItem);
		fileJMenu.add(openFile);
		
		JCheckBoxMenuItem limitVisionItem = new JCheckBoxMenuItem("Limit Vision");
		limitVisionItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(limitVisionItem.isSelected()) {
					Maze.limitvision=true;
					maze.drawMap(maze.getMap());
			} else 
				Maze.limitvision=false;
				maze.drawMap(maze.getMap());
			
			
		
		}
		
		});
		fileJMenu.add(limitVisionItem);
		
	}
		
		
	}

