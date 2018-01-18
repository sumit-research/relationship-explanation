import lemurproject.indri.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Entity {

	String name = "";
	HashMap<Integer, ArrayList<Integer>> docNum_count;
	//HashMap<Integer, Integer> docId_docNum;
	//ArrayList<Integer> docNumlist;
	Set<Integer> docNumset ;
	ScoredExtentResult[] results;
	
	public Entity() {
		name = "";
		docNum_count = new HashMap<Integer, ArrayList<Integer>>();
		//docId_docNum = new HashMap<Integer, Integer>();
		docNumset = new HashSet<Integer>();
		results = new ScoredExtentResult[]{};
	}
	
	public void set_name(String entity_name) {
		name = entity_name;
	}
	
	public void set_docNum_count(HashMap<Integer, ArrayList<Integer>> DocNum_count) {
	     docNum_count = DocNum_count;
	}
	
	/*public void set_docId_docNum(HashMap<Integer, Integer> DocId_DocNum) {
	      docId_docNum  = DocId_DocNum;
	}*/
	
	/*public void  set_docIdList(ArrayList<Integer> docNumList) {
	      docNumlist = docNumList;
	}*/

	public void  set_docNumset(Set<Integer> docNumSet) {
    	docNumset = docNumSet;
	}

	public void  set_docIdList(ScoredExtentResult[] resultsList) {
	      results = resultsList;
	}
	
	public HashMap<Integer, ArrayList<Integer>> get_docNum_count() {
	      return docNum_count;
	}
	
	/*public HashMap<Integer, Integer> get_docId_docNum() {
	      return docId_docNum;
	}*/
	
	/*public ArrayList<Integer>  get_docIdList() {
	      return docNumlist;
	}*/
	
	public Set<Integer> get_docNumset() {
    	return docNumset;
	}
	
	public ScoredExtentResult[] get_results() {
	      return results;
	}
	
	public String get_Name() {
		return name;
	}
}
