import java.io.IOException;
import java.util.ArrayList;

public class YoutubeDL {
    public static void main(String[] args) {
        Reddit reddit = new Reddit();
        ArrayList<String> links = reddit.get64YoutubeLinks();
        try {
            for (String link : links)
                downloadVideo(link);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void downloadYoutubeVideos(ArrayList<String> links) {
		try{
			
			for (String link : links)
				downloadVideo(link);
		} catch(Exception e) {

		e.printStackTrace();
		//return null;
		}
    }

    public static void downloadVideo(String url) throws IOException, InterruptedException {
        var processBuilder = new ProcessBuilder();
        processBuilder.command("youtube-dl.exe", "-f worst",url);
        var process = processBuilder.start();
        var ret = process.waitFor();
        System.out.printf("Video ", ret);
    }
}