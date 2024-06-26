import java.util.*;
import SuffixTreePackage.*;

/**
 * Main class - for accessing suffix tree applications
 * @author David Manlove
 */

public class Main {

	/**
	 * The main method.
	 * @param args the arguments
	 * @return 
	 */
	
	
	public static SuffixTreeAppl helper(String file) {
		FileInput one = new FileInput(file);
		byte[] s = one.readFile();
		SuffixTree tree = new SuffixTree(s);
		SuffixTreeAppl appl = new SuffixTreeAppl(tree);
		return appl;
	}
	
	
	
	public static void main(String args[]) {
	        String errorMessage = "Required syntax:\n";
                errorMessage += "  java Main SearchOne <filename> <query string> for Task 1\n";
                errorMessage += "  java Main SearchAll <filename> <query string> for Task 2\n";
                errorMessage += "  java Main LRS <filename> for Task 3\n" ;
                errorMessage += "  java Main LCS <filename1> <filename2> for Task 4";

		if (args.length < 2)
			System.out.println(errorMessage+"trigger");
		else {
			// get the command from the first argument
			String command = args[0];

			switch (command) {
				case "SearchOne": {
					if (args.length < 3) {
				        System.out.println(errorMessage);
			        }
					SuffixTreeAppl sTree1 = helper(args[1]);
					Task1Info task1=sTree1.searchSuffixTree(args[2].getBytes());
					if(task1.getPos()<0) {
						System.out.println("Search string \""+args[2]+"\" not found in "+args[1] );
					}else {
						System.out.println("Search string \""+args[2]+"\" occurs at position "+task1.getPos()+" of "+args[1] );
					}
					break;
				}
				
				
				
				
				case "SearchAll": {
			        if (args.length < 3) {
				        System.out.println(errorMessage);
			        }
				    SuffixTreeAppl sTree2 = helper(args[1]);
					Task2Info task2=sTree2.allOccurrences(args[2].getBytes());
					if(task2.getPositions().isEmpty()) {
						System.out.println("Search string \""+args[2]+"\" not found in "+args[1] );
					}else {
						System.out.println("Search string \""+args[2]+"\" occurs in "+args[1]+" at positions:" );
						int i=0;
						while(!task2.getPositions().isEmpty()) {
							System.out.println(task2.getPositions().pop());
							i++;
						}
						System.out.println("The total number of occurrences is "+i);
						
					}
					break;
				}
				
				
				
				
				
				case "LRS": {	
					FileInput three = new FileInput(args[1]);
					byte[] s3 = three.readFile();
					SuffixTree tree3 = new SuffixTree(s3);
					SuffixTreeAppl appl3 = new SuffixTreeAppl(tree3);
					Task3Info task3=appl3.traverseForLrs();
					
					
					System.out.println("An LRS in "+args[1]+" is \"");
					int len3 =task3.getLen();
					int pos13 = task3.getPos1();
					for(int i=0;i<len3;i++) {
						System.out.print((char)s3[pos13+i]);
					}
					System.out.print("\"\n");
					
					System.out.println("Its length is "+len3);
					System.out.println("Starting position of one occurence is "+pos13);
					System.out.println("Starting position of another occurence is "+task3.getPos2());
					
					break;
				
				}
				
				
				
				
				
				case "LCS": {
				if (args.length < 3) {
					System.out.println(errorMessage);
					break;
				}
				System.out.println("LCS results should be displayed here");
				break;
			}
			default: System.out.println(errorMessage+"trigger2");
			}
		}
	}
}