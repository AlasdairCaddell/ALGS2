package SuffixTreePackage;
import java.util.*;
/**
 * Class with methods for carrying out applications of suffix trees
 * @author David Manlove
 */

public class SuffixTreeAppl {

	/** The suffix tree */
	private SuffixTree t;

	/**
	 * Default constructor.
	 */
	public SuffixTreeAppl () {
		t = null;
	}
	
	/**
	 * Constructor with parameter.
	 * 
	 * @param tree the suffix tree
	 */
	public SuffixTreeAppl (SuffixTree tree) {
		t = tree;
	}
	
	/**
	 * Search the suffix tree t representing string s for a target x.
	 * Stores -1 in Task1Info.pos if x is not a substring of s,
	 * otherwise stores p in Task1Info.pos such that x occurs in s
	 * starting at s[p] (p counts from 0)
	 * - assumes that characters of s and x occupy positions 0 onwards
	 * 
	 * @param x the target string to search for
	 * 
	 * @return a Task1Info object
	 */
	public Task1Info searchSuffixTree(byte[] x) {
		Task1Info task1Info = new Task1Info();
		int pos, j, k;   //j:len to find
		SuffixTreeNode current, next;
		pos = 0;  // position in s
		current = t.getRoot();
		int len=x.length;
		
		while (true) {
			// search for child of current with left label x such that s[x]==s[pos]
			next = t.searchList(current.getChild(), x[pos]);

			if (next == null) {
				
				break;
			}
			else {
				// try to match s[node.getLeftLabel()+1..node.getRightLabel()] with 
				// segment of s starting at position pos+1
				j = next.getLeftLabel() + 1;
				k = pos + 1;

				while (j <= next.getRightLabel()) {
					if (t.getString()[j]==x[k]) {
						j++;
						k++;
					}
					else
						break;
				}
				if (k >= len) {
					task1Info.setMatchNode(next);
					task1Info.setPos(j-len);
					break;
				}
				if (j > next.getRightLabel()) {
					// succeeded in matching whole segment, so go further down tree
					pos = k;
					current = next;
				}
				else {
					break;
				}
			}
		}
		return task1Info;
	}

	/**
	 * Search suffix tree t representing string s for all occurrences of target x.
	 * Stores in Task2Info.positions a linked list of all such occurrences.
	 * Each occurrence is specified by a starting position index in s
	 * (as in searchSuffixTree above).  The linked list is empty if there
	 * are no occurrences of x in s.
	 * - assumes that characters of s and x occupy positions 0 onwards
	 * 
	 * @param x the target string to search for
	 * 
	 * @return a Task2Info object
	 */
	public Task2Info allOccurrences(byte[] x) {
		
		
		Task1Info task1Info = searchSuffixTree(x);
		if(task1Info.getPos()==-1) {
			return new Task2Info(); 
		}
		Task2Info task2Info = new Task2Info();
		
		SuffixTreeNode current;
		current=task1Info.getMatchNode().getChild();
		if(current!=null) {
			t2recursive(task2Info, current);
		}
		return task2Info; 

	}
	public void t2recursive(Task2Info task2Info, SuffixTreeNode current) {
		if (current!=null){
			if(current.getSuffix()==-1) {
				t2recursive(task2Info,current.getChild());
			}else {
				task2Info.addEntry(current.getSuffix());
			}
			t2recursive(task2Info,current.getSibling());
		}
		
	}

	

	/**
	 * Traverses suffix tree t representing string s and stores ln, p1 and
	 * p2 in Task3Info.len, Task3Info.pos1 and Task3Info.pos2 respectively,
	 * so that s[p1..p1+ln-1] = s[p2..p2+ln-1], with ln maximal;
	 * i.e., finds two embeddings of a longest repeated substring of s
	 * - assumes that characters of s occupy positions 0 onwards
	 * so that p1 and p2 count from 0
	 * 
	 * @return a Task3Info object
	 */
	public Task3Info traverseForLrs () {
		Task3Info task3Info = new Task3Info();
		SuffixTreeNode current=t.getRoot();
		if(current.getChild()!=null) {
			t3recursive(task3Info,current);
		}
		
		return task3Info;
	}
	
	public void t3recursive(Task3Info task3Info, SuffixTreeNode current) {
		SuffixTreeNode next=current.getChild(),sibling=current.getSibling();
		if(next!=null) {
			t3recursive(task3Info,next);
		}else {int len =current.getLeftLabel()-current.getSuffix();
				if(sibling !=null) {
					if(len>task3Info.getLen()) {
						task3Info.setLen(len);
						task3Info.setPos1(current.getSuffix());
						task3Info.setPos2(sibling.getSuffix());
					}
				}
		}
		if (sibling!=null) {
			t3recursive(task3Info,sibling);
		}	
	}

	/**
	 * Traverse generalised suffix tree t representing strings s1 (of length
	 * s1Length), and s2, and store ln, p1 and p2 in Task4Info.len,
	 * Task4Info.pos1 and Task4Info.pos2 respectively, so that
	 * s1[p1..p1+ln-1] = s2[p2..p2+ln-1], with len maximal;
	 * i.e., finds embeddings in s1 and s2 of a longest common substring 
         * of s1 and s2
	 * - assumes that characters of s1 and s2 occupy positions 0 onwards
	 * so that p1 and p2 count from 0
	 * 
	 * @param s1Length the length of s1
	 * 
	 * @return a Task4Info object
	 */
	public Task4Info traverseForLcs (int s1Length) {
		
		return null; // replace with your code!
	}
}
