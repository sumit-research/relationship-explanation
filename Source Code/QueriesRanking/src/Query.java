import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lemurproject.indri.ParsedDocument;
import lemurproject.indri.QueryEnvironment;
import lemurproject.indri.ScoredExtentResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Query {
	
	
	public static void main(String args[]) throws Exception {
		
		String articleIndex = "/home/reen/Desktop/index/ArticleIndex";
		String passageIndex = "/home/reen/Desktop/index/IndriIndex";
		
		QueryEnvironment passage_env = new QueryEnvironment(); //open passage index
		QueryEnvironment article_env = new QueryEnvironment(); //open article index
		
		passage_env.addIndex(passageIndex);
		article_env.addIndex(articleIndex);
		
//		String[] stopWords = {
//				"without" , "see" , "unless", "due", "also", "must", "might", "like", "]", "[", "}", "{", "<", ">", "?", "\\", "/", ")", "(", "will", "may", "can", "much", "every", "the", "in", "other", "this", "the", "many", "any", "an", "or", "for", "in", "an", "an", "is", "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren’t", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can’t", "cannot", "could",
//				"couldn’t", "did", "didn’t", "do", "does", "doesn’t", "doing", "don’t", "down", "during", "each", "few", "for", "from", "further", "had", "hadn’t", "has", "hasn’t", "have", "haven’t", "having",
//				"he", "he’d", "he’ll", "he’s", "her", "here", "here’s", "hers", "herself", "him", "himself", "his", "how", "how’s", "i", "i", "i’d", "i’ll", "i’m", "i’ve", "if", "in", "into", "is",
//				"isn’t", "it", "it’s", "its", "itself", "let’s", "me", "more", "most", "mustn’t", "my", "myself", "no", "nor","not", "of", "off", "on", "once", "only", "ought", "our", "ours", "ourselves",
//				"out", "over", "own", "same", "shan’t", "she", "she’d", "she’ll", "she’s", "should", "shouldn’t", "so", "some", "such", "than",
//				"that", "that’s", "their", "theirs", "them", "themselves", "then", "there", "there’s", "these", "they", "they’d", "they’ll", "they’re", "they’ve",
//				"this", "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasn’t", "we", "we’d", "we’ll", "we’re", "we’ve",
//				"were", "weren’t", "what", "what’s", "when", "when’s", "where", "where’s", "which", "while", "who", "who’s", "whom",
//				"why", "why’s", "with", "won’t", "would", "wouldn’t", "you", "you’d", "you’ll", "you’re", "you’ve", "your", "yours", "yourself", "yourselves",
//				"Without", "See", "Unless", "Due", "Also", "Must", "Might", "Like", "Will", "May", "Can", "Much", "Every", "The", "In", "Other", "This", "The", "Many", "Any", "An", "Or", "For", "In", "An", "An", "Is", "A", "About", "Above", "After", "Again", "Against", "All", "Am", "An", "And", "Any", "Are", "Aren’t", "As", "At", "Be", "Because", "Been", "Before", "Being", "Below", "Between", "Both", "But", "By", "Can’t", "Cannot", "Could",
//				"Couldn’t", "Did", "Didn’t", "Do", "Does", "Doesn’t", "Doing", "Don’t", "Down", "During", "Each", "Few", "For", "From", "Further", "Had", "Hadn’t", "Has", "Hasn’t", "Have", "Haven’t", "Having",
//				"He", "He’d", "He’ll", "He’s", "Her", "Here", "Here’s", "Hers", "Herself", "Him", "Himself", "His", "How", "How’s", "I", "I", "I’d", "I’ll", "I’m", "I’ve", "If", "In", "Into", "Is",
//				"Isn’t", "It", "It’s", "Its", "Itself", "Let’s", "Me", "More", "Most", "Mustn’t", "My", "Myself", "No", "Nor", "Not", "Of", "Off", "On", "Once", "Only", "Ought", "Our", "Ours", "Ourselves",
//				"Out", "Over", "Own", "Same", "Shan’t", "She", "She’d", "She’ll", "She’s", "Should", "Shouldn’t", "So", "Some", "Such", "Than",
//				"That", "That’s", "Their", "Theirs", "Them", "Themselves", "Then", "There", "There’s", "These", "They", "They’d", "They’ll", "They’re", "They’ve",
//				"This", "Those", "Through", "To", "Too", "Under", "Until", "Up", "Very", "Was", "Wasn’t", "We", "We’d", "We’ll", "We’re", "We’ve",
//				"Were", "Weren’t", "What", "What’s", "When", "When’s", "Where", "Where’s", "Which", "While", "Who", "Who’s", "Whom",
//				"Why", "Why’s", "With", "Won’t", "Would", "Wouldn’t", "You", "You’d", "You’ll", "You’re", "You’ve", "Your", "Yours", "Yourself", "Yourselves"
//				};
//		ArrayList<String> stopwords = new ArrayList<String>(Arrays.asList(stopWords)); 
//    	
//		//ArrayList<String> entity_terms = new ArrayList<String>(Arrays.asList("Stana","Katic","Jon","Heurtas"));
//		//ArrayList<String> relationship_terms = new ArrayList<String>((Arrays.asList("co-star"))); 
//		
////		ArrayList<String> entity_terms = new ArrayList<String>(Arrays.asList("Mariah","Carey","United","States","America"));
////		ArrayList<String> relationship_terms = new ArrayList<String>((Arrays.asList("nationality","citizen","citizenship"))); 
////		//<text>#band(#syn(Mariah Carey) #syn(citizenship nationality citizen ) #syn(United States of America))</text>
////		
////		ArrayList<Entity> entity_objects = new ArrayList<Entity>();	
////		
////		for(int i=0; i<entity_terms.size(); i++) {
////			Entity entity_object = getAttributes(entity_terms.get(i),passageIndex);
////			entity_objects.add(entity_object);
////		}
////		
////		ArrayList<Entity> relation_objects = new ArrayList<Entity>();	
////		for(int i=0; i<relationship_terms.size(); i++) {
////			Entity relation_object = getAttributes(relationship_terms.get(i),passageIndex);
////			relation_objects.add(relation_object);
////		}
////		
////		
////		System.out.println("EO_Size:"+entity_objects.size());
////		System.out.println("RO_Size:"+relation_objects.size());
////		
//		//Set<Integer> entity_docs= getDocs(entity_objects);
//		//Set<Integer> relation_docs = getDocs(relation_objects); 
//		//Set<Integer> passage_docnums = CombinedDocs(entity_docs,relation_docs);
//		
//		//System.out.println("Size:" + passage_docnums.size());
//		
//		
//		//ComputeScore(passage_docnums,passageIndex,articleIndex,entity_objects,relation_objects);
//		
//		
		//readDataset(stopwords);
		passage_env.close(); //close Passage index
		article_env.close(); //close Article Index 
		
		
	}
	
	public static void readDataset(ArrayList<String> stopwords) {
		
		String articleIndex = "/home/reen/Desktop/index/ArticleIndex";
		String passageIndex = "/home/reen/Desktop/index/IndriIndex";
	
			
		try {

            File f = new File("src/dataset.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            System.out.println("Reading dataset....");
            int count = 0;
            while ((readLine = b.readLine()) != null && count<5) {
                
            	String[] triple = readLine.split("\\*+");
            	System.out.println("\n");
            	System.out.println("Current Triple.....");
            	System.out.println(Arrays.toString(triple));
            	
            	//Intialise entity_objects and relationship objects
            	ArrayList<Entity> entity_objects = new ArrayList<Entity>();
            	ArrayList<Entity> relation_objects = new ArrayList<Entity>();
            	
            	// Get entity terms
            	String[] head_entity = triple[0].split("\\s+");
            	String[] tail_entity = triple[2].split("\\s+");
            	ArrayList<String> head_terms = new ArrayList<String>(Arrays.asList(head_entity)); 
            	ArrayList<String> tail_terms = new ArrayList<String>(Arrays.asList(tail_entity));
            	
            	ArrayList<String> entity_terms = new ArrayList<String>();
            	entity_terms.addAll(head_terms);
            	entity_terms.addAll(tail_terms);
            	
            	//Get relationship terms
            	String[] relations = triple[1].split("\\&+");
            	ArrayList<String> relationships = new ArrayList<String>(Arrays.asList(relations));
            	ArrayList<String> relationship_terms = new ArrayList<String>();
            	
            	for(int m=0; m<relationships.size(); m++) {
            		ArrayList<String> terms = new ArrayList<String>(Arrays.asList(relationships.get(m).split("\\s+")));
            		relationship_terms.addAll(terms);
            	}
            	
            	
            	//remove stopwords from entity as well as relationship terms
            	for(int i = 0; i < entity_terms.size(); i++) {
            		if (stopwords.contains(entity_terms.get(i))) {
            				entity_terms.remove(i);
            		}
            	}
            	
            	for(int i = 0; i < relationship_terms.size(); i++) {
            		if (stopwords.contains(relationship_terms.get(i))) {
            			relationship_terms.remove(i);
            		}
            	}
            	
            	for(int i = 0; i < relationship_terms.size(); i++) {
            		System.out.println(relationship_terms.get(i));
            	}
            	
            	
            	//formulate query
            	
            	
            	
            	 //Get Entity Objects
            	for(int i=0; i<entity_terms.size(); i++) {
        			Entity entity_object = getAttributes(entity_terms.get(i),passageIndex);
        			entity_objects.add(entity_object);
        		}
            	
            	//Get Relationship Objects
            	for(int i=0; i<relationship_terms.size(); i++) {
        			Entity relation_object = getAttributes(relationship_terms.get(i),passageIndex);
        			relation_objects.add(relation_object);
        		}
//            	
//            	// Get passages for entity_objects & relation_objects and combine them
//            	Set<Integer> entity_docs= getDocs(entity_objects);
//        		Set<Integer> relation_docs = getDocs(relation_objects); 
//        		Set<Integer> passage_docnums = CombinedDocs(entity_docs,relation_docs);
//            	
//            	//Compute Score and write resulting passages to file
//        		//ComputeScore(passage_docnums, articleIndex,passageIndex,entity_objects,relation_objects);
            	count++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		
	}
	
	//get passage doc_numbers (<DOCNUM></DOCNUM>) from array list of entity objects / relationship_objects (required for intersection)
	public static Set<Integer> getDocs(ArrayList<Entity> entity_objects ) {
		Set<Integer> passage_docnums = new HashSet<Integer>();
		
		Set<Integer> p1 = new HashSet<Integer>();
		Set<Integer> p2= new HashSet<Integer>();
		
		for(int i=0; i<entity_objects.size(); i++) {
			passage_docnums.addAll((entity_objects.get(i).docNumset));
		}
		
		return passage_docnums;
	}
	
	//intersection of relationship_terms passages with entity_terms passages 
	public static Set<Integer> CombinedDocs(Set<Integer> passage_entites, Set<Integer> passage_relations) {
		Set<Integer> passage_docnums = new HashSet<Integer>();
		
		passage_entites.retainAll(passage_relations);
		passage_docnums = passage_entites;
		
		System.out.println("Final Set Of documents size "+ passage_docnums.size());
		return passage_docnums;
	}
	
	// Form an entity or a realtionship object of type Entity
	public static Entity getAttributes(String query,String myIndex) {
		
		ParsedDocument[] parsed_results = null;
		ScoredExtentResult[] results = null;
		Set<Integer> docNums = new HashSet<Integer>();
		HashMap<Integer,ArrayList<Integer>> hashmap_counts = new HashMap<Integer,ArrayList<Integer>>();  
		Entity entity_obj = new Entity();
		System.out.println("Entity Processing: " + query);
		try {
			QueryEnvironment env = new QueryEnvironment();
			env.addIndex(myIndex);
			String myQuery = "#band(" + query + ")";
			results = env.expressionList(myQuery);
			System.out.println("Results: "+results.length);
			
			
			for(int i=0; i<results.length; i++) {
				docNums.add(results[i].document); //make a set of all docnumbers
			}
			
			for(int i=0;i<results.length;i++) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				
				if(hashmap_counts.containsKey(results[i].document)) {
					list = hashmap_counts.get(results[i].document);
					list.set(0, (list.get(0) + 1)); //increment count of term occurrance
					}
				else {
					int count = 0;
					if(results[i].begin>=0) {
						count = 1;
					}
					ArrayList<Integer> newList = new ArrayList<Integer>();
					int docLength = env.documentLength(results[i].document); //length of document to be added
					newList.add(count);
					newList.add(docLength);
					hashmap_counts.put(results[i].document,newList); // add docnum,length to hashmap as key value pair
					
				}
			}
	
			// form the entity object
			entity_obj.name = query;
			entity_obj.docNum_count = hashmap_counts;
			entity_obj.docNumset= docNums;
			entity_obj.results = results;
			System.out.println(hashmap_counts.size()+" "+" "+ docNums.size()+" "+ results.length);
			
			env.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entity_obj;
	}
	
	
	public static void ComputeScore(Set<Integer> passage_docnums,String passage_index, String article_index,ArrayList<Entity> entity_objects,ArrayList<Entity> relation_objects) {
		
		try {
			QueryEnvironment passage_env = new QueryEnvironment(); //open passage index
			QueryEnvironment article_env = new QueryEnvironment(); //open article index
			
			passage_env.addIndex(passage_index);
			article_env.addIndex(article_index);
			
			Integer[] arr = passage_docnums.toArray(new Integer[passage_docnums.size()]);
			int[] docnum_arr = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
		    //ParsedDocument[] parsed_documents =  env.documents(intArr);
		    
			
			entity_objects.addAll(relation_objects);
			
			//final score hashmap
			Map<Integer,Double> document_scores = new HashMap<Integer,Double>();  
			
			
			for(int i=0; i<passage_docnums.size(); i++) {
				
				ArrayList<ArrayList<Integer>> passages_score_list = new ArrayList<ArrayList<Integer>>();
				int docnum = docnum_arr[i];
				double vocab_size = 15130964;
				double collection_size = 34682258;
				double final_score = 1;
				double lamda1 = 0.6;
				double lambda2 = 0.2;
				double lambda3 = 0.2;
				
				for(int j=0; j<entity_objects.size(); j++) {
					
					Entity curr_entity = entity_objects.get(j);
					ArrayList<Integer> local_score_list = new ArrayList<Integer>();
					
					double entity_score = 0; // P(entity given passage)
					double passage_score = 0;  //
					double document_score = 0; //
					double collection_score = 0; //
					
					//passage score
					int entity_count = 0;
					int passage_length = 0;
					HashMap<Integer, ArrayList<Integer>> docNum_count = entity_objects.get(j).get_docNum_count();
					passage_score = computePassageScore(docNum_count,docnum,vocab_size);
					
					
					//collection score					
					collection_score = computeCollectionScore(docNum_count,vocab_size,collection_size);
					
					
					//document score
				//	document_score = computeDocumentScore(passage_env,article_env,docnum,vocab_size,curr_entity);
					//double document_score = 0;
					int[] current_doc = new int[1];
					current_doc[0] = docnum;
					try {
				
					ParsedDocument[] doc =passage_env.documents(current_doc);
					
					String document_content = doc[0].content;
					String regex = "<DOCID>(.*)</DOCID>";
					//System.out.println(document_content);
					Pattern r = Pattern.compile(regex);
					Matcher m = r.matcher(document_content);
					m.find();
					String docid = (m.group(1));
					String[] arrOfStr = docid.split("\\.",2);
					int curr_docid = Integer.valueOf(arrOfStr[0]);
					
					String field_query = "#combine[docid](" + curr_docid + ")" ;
					
					ScoredExtentResult[] query_results = article_env.runQuery(field_query,1);
					ParsedDocument[] new_parsed_results = article_env.documents(query_results);
					String current_content = (new_parsed_results[0].content).toLowerCase();

					Pattern pattern = Pattern.compile(curr_entity.name.toLowerCase());
			        Matcher  matcher = pattern.matcher(current_content);
			        
			        int doc_length = article_env.documentLength(query_results[0].document);
			        
			        int count_doc_term = 0;
			        while (matcher.find())
			            count_doc_term++;
					
					document_score = (count_doc_term + 1) / (vocab_size + doc_length) ;
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					
					//Entity Score
					entity_score = lamda1*passage_score + lambda2*document_score + lambda3*collection_score;
					
					final_score = final_score*entity_score;
					
				}
				
				document_scores.put(docnum, final_score);
				//ParsedDocument[] new_parsed_results = env.documents(query_results);
				//String current_content = (new_parsed_results[0].content).toLowerCa
				//System.out.println(final_score);
				
			}
			
			document_scores = sortByValue(document_scores);
			for (Map.Entry<Integer, Double> entry :document_scores.entrySet()) {
				System.out.println(entry.getKey() + "  " + (entry.getValue()));
				//int[] arr1 = new int[1];
				//arr1.
				//ParsedDocument[] parsed_docs = passage_env.documents(arr1);
			}
			
			
			
			passage_env.close(); //close Passage index
			article_env.close(); //close Article Index 
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//sort map containing doc_number (passgae index - docnum ) as key and computed score of that passage as value. 
	private static <K, V> Map<K, V> sortByValue(Map<K, V> map) {
	    List<Entry<K, V>> list = new LinkedList<>(map.entrySet());
	    Collections.sort(list, new Comparator<Object>() {
	        @SuppressWarnings("unchecked")
	        public int compare(Object o1, Object o2) {
	            return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
	        }
	    });

	    Map<K, V> result = new LinkedHashMap<>();
	    for (Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
	        Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
	        result.put(entry.getKey(), entry.getValue());
	    }

	    return result;
	}

	/*public static void writeResultsToFile() {
	
		
		
	}*/
	
	public static double computePassageScore(HashMap<Integer, ArrayList<Integer>> docNum_count, int docnum, double vocab_size ) {
		
		double passage_score = 0;
		
		int entity_count = 0;
		int passage_length = 0;
		
		if(docNum_count.containsKey(docnum)) {
			//System.out.println("Here"+j);
			entity_count = (docNum_count.get(docnum)).get(0);
			passage_length = (docNum_count.get(docnum)).get(1);
		}
		
		passage_score = (entity_count + 1) / (passage_length + vocab_size) ;
		//System.out.println(passage_score);
		
		
		return passage_score;
	}
	
	
	public static double computeCollectionScore(HashMap<Integer, ArrayList<Integer>> docNum_count, double vocab_size, double collection_size) {
		
		double collection_score = 0;
		double collection_occur = 0;
		
		for (Map.Entry<Integer, ArrayList<Integer>> entry : docNum_count.entrySet()) {
			collection_occur =  collection_occur + (entry.getValue()).get(0);
	    }
		
		
		collection_score = (collection_occur + 1)/ (collection_size + vocab_size);
		
		return collection_score;
	}
	
	
	public static double computeDocumentScore(QueryEnvironment passage_env,QueryEnvironment article_env, int docnum, double vocab_size, Entity curr_entity){
		

		double document_score = 0;
		int[] current_doc = new int[1];
		current_doc[0] = docnum;
		try {
	
		ParsedDocument[] doc = article_env.documents(current_doc);
		
		String document_content = doc[0].content;
		String regex = "<DOCID>(.*)</DOCID>";
		//System.out.println(document_content);
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(document_content);
		m.find();
		String docid = (m.group(1));
		String[] arrOfStr = docid.split("\\.",2);
		int curr_docid = Integer.valueOf(arrOfStr[0]);
		
		String field_query = "#combine[docid](" + curr_docid + ")" ;
		
		ScoredExtentResult[] query_results = passage_env.runQuery(field_query,1);
		ParsedDocument[] new_parsed_results = passage_env.documents(query_results);
		String current_content = (new_parsed_results[0].content).toLowerCase();

		Pattern pattern = Pattern.compile(curr_entity.name.toLowerCase());
        Matcher  matcher = pattern.matcher(current_content);
        
        int doc_length = article_env.documentLength(query_results[0].document);
        
        int count_doc_term = 0;
        while (matcher.find())
            count_doc_term++;
		
		document_score = (count_doc_term + 1) / (vocab_size + doc_length) ;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return document_score;
	}
	
}


