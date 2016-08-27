package org.smc.inputmethod.indic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class Varnam{

    private String engineName;
    private ArrayList<String> suggestions = new ArrayList<>();

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
            suggestions.clear();
            for(int i = 0; i < dummy.length(); i++) {
                suggestions.add(dummy.getString(i));
            }
            return suggestions.get(0);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SuggestedWords.SuggestedWordInfo> getSuggestions() {
        final ArrayList<SuggestedWords.SuggestedWordInfo> suggestionsList = new ArrayList<>();
        int n = suggestions.size();
        for(int i = 0; i < suggestions.size(); i++) {
            suggestionsList.add(new SuggestedWords.SuggestedWordInfo(suggestions.get(i), i, 0, null, -1, -1));
        }
        return suggestionsList;
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
        else if(engineName.equals("as-varnam"))
            return "as";
        else if(engineName.equals("bn-varnam"))
            return "bn";
        else if(engineName.equals("gu-varnam"))
            return "gu";
        else if(engineName.equals("mr-varnam"))
            return "mr";
        else if(engineName.equals("ne-varnam"))
            return "ne";
        else if(engineName.equals("or-varnam"))
            return "or";
        else if(engineName.equals("pa-varnam"))
            return "pa";
        else if(engineName.equals("sa-varnam"))
            return "sa";
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
        else if(engineName.equals("as-varnam"))
            return 0;
        else if(engineName.equals("bn-varnam"))
            return 0;
        else if(engineName.equals("gu-varnam"))
            return 4;
        else if(engineName.equals("mr-varnam"))
            return 4;
        else if(engineName.equals("ne-varnam"))
            return 1;
        else if(engineName.equals("or-varnam"))
            return 2;
        else if(engineName.equals("pa-varnam"))
            return 4;
        else if(engineName.equals("sa-varnam"))
            return 2;
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
        else if(engineName.equals("as-varnam"))
            return 8;
        else if(engineName.equals("bn-varnam"))
            return 0;
        else if(engineName.equals("gu-varnam"))
            return 2;
        else if(engineName.equals("mr-varnam"))
            return 2;
        else if(engineName.equals("ne-varnam"))
            return 4;
        else if(engineName.equals("or-varnam"))
            return 4;
        else if(engineName.equals("pa-varnam"))
            return 2;
        else if(engineName.equals("sa-varnam"))
            return 4;
        return -1;
    }
}
