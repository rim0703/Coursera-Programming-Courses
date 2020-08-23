import edu.duke.URLResource;

public class Part4 {
    public static void main(String[] args) {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        String str = "youtube.com";
        for (String item : url.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");

            if (pos != -1) {
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));

            }

        }
    }
}
