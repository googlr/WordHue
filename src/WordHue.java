/**
 * 
 */

/**
 * @author dc
 *
 */

import java.util.Scanner;
/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
*/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class WordHue {
	
	static Set<String> wordsSet;


	/**
	 * @param args
	 * check_for_word(String word) is copied from:
	 * http://stackoverflow.com/questions/11607270/how-to-check-whether-given-string-is-a-word
	 */

/*    public static boolean check_for_word(String word) {
        // System.out.println(word);
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    "/usr/share/dict/web2"));
            String str;
            while ((str = in.readLine()) != null) {
                if ( str.contains(word) ) {
                    return true;
                }
                else{
                	return false;
                }
            }
            in.close();
        } catch (IOException e) {
        }

        return false;
    }*/
    
    public static void search_for_word(int length, int width, 
    		char[][] board, char[][] visited,
    		int startX, int startY, String wordStr){
    	//get a copy of the current visited
    	char[][] visited_XY = new char[width][length];
    	int i,j;
    	for(i=0; i<width; i++){
    		for(j=0; j<length; j++){
    			visited_XY[i][j] = visited[i][j];
    		}
    	}
    	//then revise it
    	String wordStr_addXY = wordStr + board[startX][startY];
    	visited_XY[startX][startY] = 1;
    	//check if it is a word
    	if( wordsSet.contains(wordStr_addXY) ){
			System.out.println(wordStr_addXY);
		}
    	
    	//continue recursive search
    	//left_above
		if(startX-1>=0 && startX-1<width && startY-1>=0 && startY-1<length && 
				visited_XY[startX-1][startY-1] == 0){
			search_for_word(length,width,board, visited_XY, startX-1, startY-1, wordStr_addXY);
		}
    	//above
		if(startX-1>=0 && startX-1<width && startY>=0 && startY<length && 
				visited_XY[startX-1][startY] == 0){
			search_for_word(length,width,board, visited_XY, startX-1, startY, wordStr_addXY);
		}
    	//right_above
		if(startX-1>=0 && startX-1<width && startY+1>=0 && startY+1<length && 
				visited_XY[startX-1][startY+1] == 0){
			search_for_word(length,width,board, visited_XY, startX-1, startY+1, wordStr_addXY);
		}
    	//left
		if(startX>=0 && startX<width && startY-1>=0 && startY-1<length && 
				visited_XY[startX][startY-1] == 0){
			search_for_word(length,width,board, visited_XY, startX, startY-1, wordStr_addXY);
		}
    	//right
		if(startX>=0 && startX<width && startY+1>=0 && startY+1<length && 
				visited_XY[startX][startY+1] == 0){
			search_for_word(length,width,board, visited_XY, startX, startY+1, wordStr_addXY);
		}
    	//left_beneath
		if(startX+1>=0 && startX+1<width && startY-1>=0 && startY-1<length && 
				visited_XY[startX+1][startY-1] == 0){
			search_for_word(length,width,board, visited_XY, startX+1, startY-1, wordStr_addXY);
		}
    	//beneath
		if(startX+1>=0 && startX+1<width && startY>=0 && startY<length && 
				visited_XY[startX+1][startY] == 0){
			search_for_word(length,width,board, visited_XY, startX+1, startY, wordStr_addXY);
		}
    	//right_beneath
		if(startX+1>=0 && startX+1<width && startY+1>=0 && startY+1<length && 
				visited_XY[startX+1][startY+1] == 0){
			search_for_word(length,width,board, visited_XY, startX+1, startY+1, wordStr_addXY);
		}

		
    }

    public static void main(String[] args) {
    	//get the local dictionary
    	
        Path path = Paths.get("words3.txt");
        try{
        	byte[] readBytes = Files.readAllBytes(path);
            String wordListContents = new String(readBytes, "UTF-8");
            String[] words = wordListContents.split("\n");
            wordsSet = new HashSet<>();
            Collections.addAll(wordsSet, words);
        } catch(IOException e){
        	System.out.println("IOException Occured!");
        }
    	
        //get the size of the board
    	System.out.println("Please give the length and width of the WordHue board");
    	Scanner in = new Scanner(System.in);
    	int length = in.nextInt();
    	int width = in.nextInt();
    	
    	//read the WordHue board
    	System.out.println("Please give the WordHue board");
    	char[][] board = new char[width][length];
    	int i=0, j=0;
    	for(i=0; i<width; i++){
    		for(j=0; j<length; j++){
    			board[i][j] = in.next().charAt(0);
    		}
    	}
    	
    	//search the board for the top 10 longest word.
    	char[][] visited = new char[width][length];
    	for(i=0; i<width; i++){
    		for(j=0; j<length; j++){
    			visited[i][j]=0;
    		}
    	}
    	//
    	for(i=0; i<width; i++){
    		for(j=0; j<length; j++){
    			search_for_word(length, width, board, visited,
    					i, j, "");
    		}
    	}
        System.out.println(wordsSet.contains("world"));
    }

}
