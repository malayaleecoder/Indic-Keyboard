package org.smc.inputmethod.indic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class Varnam{

    public String readfromurl(String input) {
        String starting = "https://api.varnamproject.com/tl/ml/";
        String urlstring = starting + input;

        try {
            URL url = new URL(urlstring);
            Scanner scan = new Scanner(url.openStream());
            String finalresult = new String();
            while(scan.hasNext())
                finalresult += scan.nextLine();
            scan.close();
            JSONObject json = new JSONObject(finalresult);
            JSONArray dummy = json.getJSONArray("result");
            return dummy.getString(0);
        } catch (MalformedURLException e) {
            throw new RuntimeExceptio(e);
        } catch (IOException e) {
            throw new RuntimeExceptio(e);
        } catch (JSONException e) {
            throw new RuntimeExceptio(e);
        }
        return "";
    }
}
