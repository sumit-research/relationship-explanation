
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lemurproject.indri.DocumentVector;
import lemurproject.indri.ParsedDocument;
import lemurproject.indri.QueryEnvironment;
import lemurproject.indri.ScoredExtentResult;

public class BaseLine {
	
	public static ScoredExtentResult[] PerformBaseline(QueryEnvironment env, String query) throws Exception{
		//Perform query
		String field_query = "#combine[passage600:400](" + query + ")" ;
		ScoredExtentResult[] query_results = env.runQuery(field_query,100);
		int[] docNums = new int[query_results.length];
		
		//Print results
		for(int i=0;i<query_results.length;i++){
			docNums[i] = query_results[i].document;
			//System.out.println("Begin:"+query_results[i].begin+" End:"+query_results[i].end+" id:"+query_results[i].score);
		}
		
		//Retrieve Documents
		//Write ranked set to file
		String name = query.replace(" ", "_");
		FileWriter fw = new FileWriter("C:/Users/Purusharth/Desktop/IBM/Selected_Results/Results/Q5/Baseline/"+name+".txt");
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
			
				
		ParsedDocument[] passages = env.documents(docNums);
		for(int i=0;i<passages.length;i++) {
			String document_content = passages[i].content;
			String passage_doc = document_content.substring(query_results[i].begin, query_results[i].end);
			//System.out.println(passage_doc);
			out.println(passage_doc+"\n");
		}
		out.close();
		return query_results;
	}
	
	
	public static void performQuery(QueryEnvironment article_env, String query){
		try{
			PerformBaseline(article_env, query);
		}catch(Exception e){
			System.out.println("Problem in baseline!!");
			e.printStackTrace();
		}
	}
	
	
	
}
