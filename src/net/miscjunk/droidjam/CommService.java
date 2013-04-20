package net.miscjunk.droidjam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class CommService {

	public JSONObject getJSON(String url){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet(url);
		    try {
		      HttpResponse response = client.execute(httpGet);
		      StatusLine statusLine = response.getStatusLine();
		      int statusCode = statusLine.getStatusCode();
		      if (statusCode == 200) {
		        HttpEntity entity = response.getEntity();
		        InputStream content = entity.getContent();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		        String line;
		        while ((line = reader.readLine()) != null) {
		          builder.append(line);
		        }
		      } else {
		        //Log.e(ParseJSON.class.toString(), "Failed to download file");
		      }
		    } catch (ClientProtocolException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    JSONObject jObject = new JSONObject();
		    try {
				jObject = new JSONObject(builder.toString());				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return jObject;
		  }
	
	public JSONObject postJSON(String url, JSONObject jObject) throws Exception 
	{
	    //instantiates httpclient to make request
	    DefaultHttpClient httpclient = new DefaultHttpClient();

	    //url with the post data
	    HttpPost httpost = new HttpPost(url);

	    //passes the results to a string builder/entity
	    StringEntity se = new StringEntity(jObject.toString());

	    //sets the post request as the resulting string
	    httpost.setEntity(se);
	    //sets a request header so the page receving the request
	    //will know what to do with it
	    httpost.setHeader("Accept", "application/json");
	    httpost.setHeader("Content-type", "application/json");

	    //Handles what is returned from the page 
	    ResponseHandler responseHandler = new BasicResponseHandler();
	    return httpclient.execute(httpost, responseHandler);
	}
	

	
}
