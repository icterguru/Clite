/**
 * The Clite Programming Language
 * 
 * A Lexeme is a pair that contains a word (value) and its part of speech (type).
 * 
 * A Lexeme may hold left and right pointers to other Lexemes. In this way, 
 * we may build parse trees with Lexemes. Depending on its type, a Lexeme may 
 * also have other attributes. For example, a Lexeme of type "FUNCTION" 
 * holds a reference to its Defining Environment, so the values of variables
 * may be retrieved during a function call.
 * 
 * 
 */

import java.util.ArrayList;

public class Lexeme {

	private String type;
	private int intValue;
	private String strValue;
	private Lexeme left, right;
	private Environment definingEnv;
	private ArrayList<Lexeme> lexList;

	/**
	 * Create an empty lexeme with a type.
	 */
	public Lexeme(String strType) {
		type = strType;
	}

	/**
	 * Create a new single-character Lexeme.
	 */
	public Lexeme(String strType, int intChar) {
		type = strType;
		intValue = intChar;
	}

	/**
	 * Create a new multi-character Lexeme.
	 */
	public Lexeme(String strType, String svalue) {
		type = strType;
		strValue = svalue;
	}
	
	public void setLeft(Lexeme tree) { left = tree; }
	public void setRight(Lexeme tree) { right = tree; }
	public Lexeme right() { return right; }
	public Lexeme left() { return left; }
	public String getType() { return type; }
	public void setType(String t) { type = t; }
	
	/**
	 * Retrieve the Defining Environment of Closures
	 */
	public Environment getDefiningEnv() {
		return definingEnv;
	}

	/**
	 * Set the Defining Environment of a Closure.
	 */
	public void setDefiningEnv(Environment definingEnv) {
		this.definingEnv = definingEnv;
	}
	
	public String getStrValue() {
		if (type.equals("NUMBER")) return Integer.toString(intValue);
		return strValue; 
	}
	
	public int getIntValue() { return intValue; }
	
	// Only applies to Lexemes of Type "NUMBER"
	public int getNumberValue() {
		if (strValue != null) return Integer.parseInt(strValue);
		return intValue;
	}
	
	// Only applies to Lexemes of Type "ARRAY"
	public ArrayList<Lexeme> getArrayList() { return lexList;}
	public void setArrayList(ArrayList<Lexeme> aList) { this.lexList = aList; }
	
	@Override
	public String toString() {
		if (type.equals("NUMBER")) {
 			return (type + " " + intValue);
		}
		
		else if (type.equals("VARIABLE")) {
			return (type + " " + strValue);
		}
		
		else if (type.equals("FUNCTION")) {
			return (type);
		}
		
		else if (type.equals("ARRAY")) {
			String str = "[";
			if (lexList != null) {
				for (int i = 0; i < lexList.size(); i++) {
					if (i != lexList.size() - 1)
						str = str + lexList.get(i) + ", ";
					else
						str = str + lexList.get(i);
				}
			}
			str = str + "]"; 
			return str;
		}
	
		else if (intValue != 0)  {
			return (type + " " + "(char " + intValue + ") " + (char) intValue);
		}
		
		else {
			return (type + " " + strValue);
		}
		
		
	}

	/**
	 * Display the single lexeme in the terminal.
	 */
	public void display() {
		System.out.println(this.toString());
	}
	
	/**
	 * Print the single lexeme.
	 */
	public void print() {
		System.out.print(this.toString());
	}
	
	/**
	 * Display the single lexeme in the terminal. Using this method
	 * within a traversal allows us to quickly visualize the 
	 * structure of parse trees.
	 */
	public void displayNode() {
		System.out.print(this.toString());
		if (left != null) { 
			System.out.print(" | LEFT: " + left.toString());
		} 
		if (right != null) {
			System.out.print(" | RIGHT: " + right.toString());
		}
		System.out.println();
	}
	
	/**
	 * Preorder traversal and printing of the tree of lexemes.
	 */
	public void preorderTraversal(Lexeme lexeme) {
		if (lexeme == null) return;
		lexeme.displayNode();
		preorderTraversal(lexeme.left);
		preorderTraversal(lexeme.right);
	}
	
	/**
	 * Two lexemes are equal if they have the same strValue.
	 */
	@Override
	public boolean equals(Object obj) {
		Lexeme lexeme = (Lexeme) obj;
		if (strValue.equals(lexeme.getStrValue())) {
			return true;
		}
		return false;
    }
	

	@Override
    public int hashCode() {
        return strValue.hashCode();
    }
}
