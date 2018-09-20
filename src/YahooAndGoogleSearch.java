import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class YahooAndGoogleSearch {

    public static void main(String[] args) {
        System.out.print("Please insert Search words: ");
        Scanner sc = new Scanner(System.in);

        YahooAndGoogleSearch fc = new YahooAndGoogleSearch();
        String query = fc.getLinkquery(sc.nextLine());
        fc.getDataFromYahoo(query);
    }

    String getLinkquery (String str){
        str = str.toLowerCase().trim().replaceAll(" ","+");
        System.out.println("The correct word is: "+str);
        return str;
    }
    private void getDataFromYahoo(String query) {


        String request1 = "https://search.yahoo.com/search;_ylc=X3oDMTFiN25laTRvBF9TAzIwMjM1MzgwNzUEaXRjAzEEc2VjA3NyY2hfcWEEc2xrA3NyY2h3ZWI-?p="+query+"&fr=yfp-t&fp=1&toggle=1&cop=mss&ei=UTF-8";
        System.out.println("Sending request..." + request1);

        String request2 = "https://www.google.com/search?q="+query;
        System.out.println("Sending request..." + request2);

        try {

            // need http protocol, set this as a Yahoo and Google bot agent :)
            Document doc1 = Jsoup
                    .connect(request1)
                    .userAgent(
                            "Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)")
                    .timeout(5000).get();
            Document doc2 = Jsoup
                    .connect(request2)
                    .userAgent(
                            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                    .timeout(5000).get();

            // Print html tag to aa.html
            String sr1 = doc1.html().toString();
            OutputStream out1 = new FileOutputStream("yahoo.html");
            DataOutputStream data1 = new DataOutputStream(out1);
            data1.writeBytes(sr1);
            data1.flush();
            data1.close();

            String sr2 = doc2.html().toString();
            OutputStream out2 = new FileOutputStream("Google.html");
            DataOutputStream data2 = new DataOutputStream(out2);
            data2.writeBytes(sr2);
            data2.flush();
            data2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}