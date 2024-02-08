package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import Servisofts.SConfig;
import netscape.javascript.JSException;

public class GPJSON {

    public static void saveGPJSON(String key_usuario, JSONObject data) {
        new GPJSON(key_usuario).addJSON(data);
    }

    // CLASS

    private String name;
    private String url;
    private JSONArray arrData;

    public GPJSON(String key_usuario) {
        // Root Element
        String rootPath = SConfig.getJSON("files").getString("url_gpx");
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String hora = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        this.url = rootPath + key_usuario + "/" + year + "/" + month + "/" + day + "/";
        if (hora.length() == 1) {
            hora = "0" + hora;
        }
        this.name = hora;

    }

    public JSONArray load() {
        try {
            // File f = new File("./" + name + ".gpx");
            Path fileName = Path.of(this.url + name + ".json");
            String fileStr = Files.readString(fileName);
            JSONArray json = new JSONArray(fileStr);
            this.arrData = json;
            return json;
        } catch (JSException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public void init() {
        save(new JSONArray());
    }

    public void save(JSONArray arrInsert) {
        try {
            String arr[] = url.split("/");
            String urlTemp = "";
            for (int i = 0; i < arr.length; i++) {
                urlTemp += "/" + arr[i];
                File d = new File(urlTemp);
                if (!d.exists()) {
                    d.mkdirs();
                }
            }
            File itm = new File(this.url + name + ".json");
            if (!itm.exists()) {
                itm.createNewFile();
            }
            Files.writeString(itm.toPath(), arrInsert.toString());
            this.arrData = arrInsert;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addJSON(JSONObject data) {
        if (load() == null) {
            init();
        }
        this.arrData.put(data);
        save(this.arrData);

    }
}
