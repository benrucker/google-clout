import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;

public class Reddit
{
  String after;
  int count;

  public Reddit() 
  {
    this.after = "";
    this.count = 0;
  }

	public static void main(String[] args) 
	{
		Reddit r = new Reddit();
		r.get64YoutubeLinks();
    }

  public ArrayList<String> get64YoutubeLinks() 
  {
    ArrayList<String> out = new ArrayList<>(64);
    while (out.size() < 64) 
	{
      for (String link : getYoutubeLinks()) 
	  {
        out.add(link);
      }
    }
    return out;
  }

  public ArrayList<String> getYoutubeLinks() 
  {
		String subReddit = getRequest();
    ArrayList<String> subRedditPosts  = new ArrayList<String>();
    while(subReddit.contains("www.youtube.com/embed")) //while subreddit contains the link
    {
      int L = subReddit.indexOf("youtube.com/embed"); //int L = subreddit.indexOf(youtube.com/embed)
	  int length = (int) (Math.log10(L) + 1);
      String link =  subReddit.substring(L, length);		//string link = subreddit.subString(l, length of l)
      subRedditPosts.add(link);				//arraylist.add(link)
      subReddit = subReddit.substring( L, link.length() );				//subreddit = subreddit.subString(l, length of link)
    }
		return subRedditPosts;
  }

  public String getRequest() 
  {
    String urlParameters =
      "count=" + Integer.toString(count) +
      after == "" ? "" : "&after=" + after;
    String response = executeGet("https://reddit.com/r/youtubesyllables.json", urlParameters);
    
    int last = response.indexOf("\"after\": \"");
    this.after = response.substring(last+10, last+10+9);
    this.count += 25;
    
    return response;
  }

  public String executeGet(String targetURL, String urlParameters)
  {
    URL url;
    HttpURLConnection connection = null;  
    try 
	{
      //Create connection
      url = new URL(targetURL);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Type", 
          "application/x-www-form-urlencoded");
            
      connection.setRequestProperty("Content-Length", "" + 
                Integer.toString(urlParameters.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");  
            
      connection.setUseCaches (false);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      connection.setInstanceFollowRedirects(true);

      //Send request
      DataOutputStream wr = new DataOutputStream (
                  connection.getOutputStream ());
      wr.writeBytes (urlParameters);
      wr.flush ();
      wr.close ();

      //Get Response	
      System.out.println(connection.getResponseCode());
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      String line;
      StringBuffer response = new StringBuffer(); 
      while((line = rd.readLine()) != null) 
	  {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();

    } catch (Exception e) {

      e.printStackTrace();
      return null;

    } finally {

      if(connection != null) {
        connection.disconnect(); 
      }
    }
  }
    
	
}
