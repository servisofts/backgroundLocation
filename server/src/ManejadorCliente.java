import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import Servisofts.SConsole;
import Servisofts.SPGConect;
import SocketCliente.SocketCliente;

public class ManejadorCliente {
    public static void onMessage(JSONObject data, JSONObject config) {
        if (data.isNull("component")) {
            data.put("error", "No existe el componente");
            return;
        }
        if (data.has("estado")) {
            if (data.getString("estado").equals("error")) {
                if (data.has("error")) {
                    SConsole.log("ERROR: " + data.get("error").toString());
                } else {
                    SConsole.log("Error not found::" + data.toString());
                }
            }
        }

        componentes(data, config);
    }

    public static void componentes(JSONObject data, JSONObject config) {
        switch (data.getString("component")) {

        }
    }

}