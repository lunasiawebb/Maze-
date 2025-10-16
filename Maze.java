import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Maze {
	
	private char [][] map=new char[21][52];
	private int [][] bitmap = new int [21][52];
	private int charXpos;
	private int charYpos;
	private int endXpos;
	private int endYpos;
	private JTextArea textArea;
	public static boolean victory;
	public static boolean limitvision=false;
	private File current;
	
	
	public Maze(JTextArea txt){
		textArea=txt;
	}
	
	public void openMaze(File file) {
		current=file;
		victory=false;
			try (Scanner in=new Scanner(file)){
				// Read in the data one line at a time
				int row = 0;
				while(in.hasNextLine()) {
					// Do something with this String
					String charline= in.nextLine();
	
					for (int col = 0; col < charline.length() && col < map[row].length; col++) {
		                char character = charline.charAt(col);
		                map[row][col] = character;
		                if (character == 'S') { //set start
		                    charXpos = col;
		                    charYpos = row;
		                    map[charYpos][charXpos]='֍';
		                } else if (character == 'E') { // set end
		                    endXpos = col;
		                    endYpos = row;
		                    map[endYpos][endXpos]='◌';
		                }
		                if (character!='|' && character!='=' && character!='.' && character!='\'' ) { //if not wall set 0
		                	bitmap[row][col]=0;
		                }
		                else
		                	bitmap[row][col]=1; //if wall set 1
					}
					row++;
			// Fix anything that broke and clean up
				}
				drawMap(map);
			
		} catch (FileNotFoundException e1) {
			System.err.println("File Unavailable: " + e1.getMessage());
		}
	}
		
		
	public void selectMap(){
		JFileChooser picker = new JFileChooser();
	    if (picker.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        File file = picker.getSelectedFile();
	        openMaze(file);
	    } else {
	        System.out.println("No file was picked....Exiting");
	        System.exit(0);
	    }
	}
	
	public void resetMap() {
		victory=false;
		openMaze(current);
	}
	
	public void drawMap(char [][] map) {
		//will use info from OpenMap to put the correct chars onto the text Area
		String display = "";
	    for (int row = 0; row < map.length; row++) {
	        for (int col = 0; col < map[row].length; col++) {
	        	if(limitvision) {
	        		//only display characters within the 2x6 everything else is the character
	        		boolean range = Math.abs(charYpos - row) <= 2 && Math.abs(charXpos - col) <= 6;
	        		if (range) {
	        		    display += map[row][col];
	        		} else 
	        		    display += '░';
	        	} else 
	            display += map[row][col];
	        }
	        display += "\n";
	    }
	    textArea.setText(display);
	}
	
	public void checkVictory(){
		if(endXpos==charXpos && endYpos==charYpos) {
			//turn off movement show victory msg 
			//pop up message makes character unmoveable until new game is opened
			victory=true;
			JOptionPane.showMessageDialog(null,"You Win!", null, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void moveUp() {
		//check if anticipated spot is moveable, if so, shifts character to that spot
		//call drawmap every move to update the text

		//cant move into negative space or wall
		int nextX=charXpos;
		int nextY=charYpos-1;
		 if (nextY >= 0 && nextY < map.length && bitmap[nextY][nextX] == 0) {
		        map[charYpos][charXpos] = ' ';
		        charYpos = nextY;
		        map[charYpos][charXpos] = '֍';
		        drawMap(map);
		        checkVictory();
		    }
		 System.out.println(charYpos); //check if position update
		}
		
	
	public void moveDown() {
		//check if anticipated spot is moveable, if so, shifts character to that spot
		//call drawmap every move to update the text

		int nextX=charXpos;
		int nextY=charYpos+1;
		 if (nextY >= 0 && nextY < map.length && nextX >= 0 && nextX < map[0].length && bitmap[nextY][nextX] == 0) {
		        map[charYpos][charXpos] = ' ';
		        charYpos = nextY;
		        map[charYpos][charXpos] = '֍';
		        drawMap(map);
		        checkVictory();
		    }
	}
	
	public void moveLeft() {
		//check if anticipated spot is moveable, if so, shifts character to that spot
		//call drawmap every move to update the text
		int nextX=charXpos-1;
		int nextY=charYpos;
		 if (nextY >= 0 && nextY < map.length && nextX >= 0 && nextX < map[0].length && bitmap[nextY][nextX] == 0) {
		        map[charYpos][charXpos] = ' ';
		        charXpos = nextX;
		        map[charYpos][charXpos] = '֍';
		        drawMap(map);
		        checkVictory();
		    }

	}
	
	public void moveRight(){
		//check if anticipated spot is moveable, if so, shifts character to that spot
		//call drawmap every move to update the text
		int nextX=charXpos+1;
		int nextY=charYpos;
		 if (nextY >= 0 && nextY < map.length && nextX >= 0 && nextX < map[0].length && bitmap[nextY][nextX] == 0) {
		        map[charYpos][charXpos] = ' ';
		        charXpos = nextX;
		        map[charYpos][charXpos] = '֍';
		        drawMap(map);
		        checkVictory();
		    }

	}
	
	public char[][] getMap() {
	    return map;
	}
	

}
