import java.util.HashMap;

import lemurproject.indri.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Source {

	HashMap<Integer, ArrayList<Integer>> hashmap;
	HashMap<Integer, Integer> hashmap_docs;
	
	public Source() {
		hashmap = null;
		hashmap_docs = null;
	}
	
	public static void main(String[] args) {
		
		ArrayList<ArrayList<Integer>> docId_docNum = RetrieveDocument();
		
		Source obj = new Source();
		Source obj1 = new Source();
		
		
		String passageIndex = "/home/reen/Desktop/index/IndriIndex";
		String articleIndex = "/home/reen/Desktop/index/ArticleIndex";
		//RetrieveDocument();
		
		int id_list[] = new int[] {
			1,2	
		};
		
		try {
			QueryEnvironment env = new QueryEnvironment();
			env.addIndex(passageIndex);
			
			DocumentVector[] dv = env.documentVectors(id_list);
			System.out.println("Size:"+dv.length); //Size of DocumentVector dependent on number of document_id passed
			
			for(int i=0;i<dv.length;i++) {
				int[] positions = dv[i].positions;
				String[] words = dv[i].stems;
				DocumentVector.Field[] dvf = dv[i].fields;
				
				for(int j=0;j<positions.length;j++) {
					System.out.println(positions[i]);
				}
				
				
				System.out.println("Positions:"+positions.length+" Words:"+words.length+" dvf:"+dvf.length);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		/*obj= EntityPassage(passageIndex);
		obj1= EntityPassage(articleIndex);
		
		
		for(int i=0; i<1; i++) {
			
			int id = (((docId_docNum.get(0)).get(1)));
			float passage_prob = 0;
		    float article_prob = 0;
		    //long tot_terms1 = 3341319783;
		    //long tot_terms2 = 3182731918;
		    
		    int docnum = obj.hashmap_docs.get(id);
		    ArrayList<Integer> list = new ArrayList<Integer>();
		    list = obj.hashmap.get(docnum);
		    
		    
		    int docnum1 = obj1.hashmap_docs.get(id);
		    ArrayList<Integer> list1 = new ArrayList<Integer>();
		    list = obj1.hashmap.get(docnum1);
		    
		    for(int j=0; j<list1.size(); j++) {
		    	System.out.println(list1.get(j));
		    }
		    
		
		}*/
	
		
		/*for (Map.Entry<Integer, ArrayList<Integer>> entry : obj.hashmap.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}*/
	
		
	}
	
	public static ArrayList<ArrayList<Integer>> RetrieveDocument() {
		
		ArrayList<ArrayList<Integer>> docId_docNum = new ArrayList<ArrayList<Integer>>();
		
		try {
			
			Source object = new Source();
			QueryEnvironment env = new QueryEnvironment();
			String myIndex = "/home/reen/Desktop/index/IndriIndex";
			env.addIndex(myIndex);
			String myQuery = "#band(#syn(Stana Katic) #syn(co-star colleague) #syn(Jon Heurtas))";
			ScoredExtentResult[] query_results = env.runQuery(myQuery,34682258);
			System.out.println("yoyooyo "+ query_results.length);
			//ParsedDocument[] results1 = env.documents(query_results);
			//System.out.println("yoyooyo "+ results1.length);
//			/for(int i=0;i<results1.length;i++) {
//				ArrayList<Integer> list = new ArrayList<Integer>();
//				list.add(query_results[i].document);
//				String document_content = results1[i].content;
//				String regex = "<DOCID>(.*)</DOCID>";
//				
//				//System.out.println(document_content);
//				Pattern r = Pattern.compile(regex);
//				Matcher m = r.matcher(document_content);
//				m.find();
//				String docid = (m.group(1));
//				String[] arrOfStr = docid.split("\\.",2);
//				/*for (String a : arrOfStr)
//			            System.out.println(a);*/
//				int doc_id = Integer.valueOf(arrOfStr[0]);
//				list.add((doc_id));
//				docId_docNum.add(list);
//				
//			}
		 
	
			}catch(Exception e){
			e.printStackTrace();
		}

		return docId_docNum;
	}
	
	
	public static Source EntityPassage(String myIndex){
		
		HashMap<Integer,ArrayList<Integer>> hashmap =new HashMap<Integer,ArrayList<Integer>>();  
		HashMap<Integer,Integer> hashmap_docs =new HashMap<Integer,Integer>();  
		Source obj = new Source();
		
		try {
			
			QueryEnvironment env = new QueryEnvironment();
			//String myIndex = "/home/reen/Desktop/index/ArticleIndex";
			env.addIndex(myIndex);
			//ScoredExtentResult[] results = env.runQuery(myQuery,200);
			ScoredExtentResult[] results = env.expressionList("#band(Stana)");
			ParsedDocument[] results1 = env.documents(results);
			
			
			
			for(int i=0;i<results.length;i++) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				
				if(hashmap.containsKey(results[i].document)) {
					list = hashmap.get(results[i].document);
					list.set(0, (list.get(0) + 1));
					//System.out.println(list);
					//System.out.println(results[i].begin);
					//System.out.println(results[i].document);
					//System.out.println(env.documentLength(results[i].document));
				}
				else {
					int count = 0;
					if(results[i].begin>=0) {
						count = 1;
					}
					ArrayList<Integer> newList = new ArrayList<Integer>();
					int docLength = env.documentLength(results[i].document);
					newList.add(count);
					newList.add(docLength);
					hashmap.put(results[i].document,newList);
					
					ArrayList<Integer> list1 = new ArrayList<Integer>();
					list1.add(results[i].document);
					String document_content = results1[i].content;
					String regex = "<DOCID>(.*)</DOCID>";
					
					//System.out.println(document_content);
					Pattern r = Pattern.compile(regex);
					Matcher m = r.matcher(document_content);
					m.find();
					String docid = (m.group(1));
					String[] arrOfStr = docid.split("\\.",2);
					int doc_id = Integer.valueOf(arrOfStr[0]);
					list1.add((doc_id));
					hashmap_docs.put(doc_id,results[i].document);
					
				}
				
				obj.hashmap = hashmap;
				obj.hashmap_docs = hashmap_docs;
			
			}
			env.close();
			
			System.out.println(hashmap.size());
			/*for (Map.Entry<Integer, ArrayList<Integer>> entry : hashmap.entrySet()) {
			    System.out.println(entry.getKey()+" : "+entry.getValue());
			}*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return obj;
		
	}
	
	
	
}
