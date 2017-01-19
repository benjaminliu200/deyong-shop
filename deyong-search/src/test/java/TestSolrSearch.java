import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

/**
 * Created by benjamin on 2017/1/19.
 */
public class TestSolrSearch {
    //@Test
    public void testSearchDocument() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.224:8080/solr");
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:手");
        query.setStart(20);
        query.setRows(50);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        System.out.println("供查询记录：" + results.getNumFound());

        for(SolrDocument solrDocument: results) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
        }
    }
}
