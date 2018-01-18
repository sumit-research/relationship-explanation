/*
			//Index theIndex=IndexManager.openIndex("/home/reen/Desktop/index/IndriIndex");
			QueryEnvironment env = new QueryEnvironment();
			String myIndex = "/home/reen/Desktop/index/IndriIndex";
			String myQuery = "#band(stana)";
            ScoredExtentResult[] results = null;
            String[] names = null;
            // open an Indri repository
            env.addIndex(myIndex);
            PonteExpander ponte = new PonteExpander(env, new HashMap());
            // run an Indri query, returning 10 results
            results = env.runQuery(myQuery, 34000000);
            
            //results = env.expressionList("purusharth");
            //System.out.println(results.length);
            System.out.println(results.length);
            //String newQuery = ponte.expand(myQuery, results);
            System.out.println("New query");
            //results = env.runQuery(newQuery, 1000);
             //fetch the names of the retrieved documents
            //names = env.documentMetadata(results, "docno");
            ParsedDocument[] docs  = env.documents(results);
            System.out.println(docs.length);
           for (int i = 0; i < docs.length; i++) {
                System.out.println(docs[i].content);
            }
            env.close();
            */

public class DumpQuery {

}
