package org.smc.inputmethod.indic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class Varnam{

    private String engineName;
    public String transliterate(String input) {
        String starting = "https://api.varnamproject.com/tl/";
        starting = starting + getLanguage() + "/";
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
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEngineName(String engineName1) {
        engineName = engineName1;
    }
    public String getEngineName() {
        return engineName;
    }
    public String getLanguage() {
        if(engineName.equals("ml-varnam"))
            return "ml";
        else if(engineName.equals("ta-varnam"))
            return "ta";
        else if(engineName.equals("hi-varnam"))
            return "hi";
        else if(engineName.equals("te-varnam"))
            return "te";
        else if(engineName.equals("kn-varnam"))
            return "kn";
        return "";
    }
    public int getMaxKeyLength() {
        if(engineName.equals("ml-varnam"))
            return 4;
        else if(engineName.equals("ta-varnam"))
            return 4;
        else if(engineName.equals("hi-varnam"))
            return 3;
        else if(engineName.equals("te-varnam"))
            return 4;
        else if(engineName.equals("kn-varnam"))
            return 1;
        return -1;
    }

    public int getContextLength() {
        if(engineName.equals("ml-varnam"))
            return 2;
        else if(engineName.equals("ta-varnam"))
            return 1;
        else if(engineName.equals("hi-varnam"))
            return 1;
        else if(engineName.equals("te-varnam"))
            return 1;
        else if(engineName.equals("kn-varnam"))
            return 3;
        return -1;
    }
}
