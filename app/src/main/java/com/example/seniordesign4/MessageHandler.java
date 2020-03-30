package com.example.seniordesign4;

import org.json.JSONObject;
import java.io.StringWriter;

/*

{
	"type": "mode_msg",
	"mode": <"manual" | "auto">
}

{
	"type": "ctrl_msg",
	"move": move,
	"turn": turn,
}

{
    "type": "stat_msg",
    [<error>: <error value>]*

    Error Options:
        "battery": <battery %>
        "weeds": <weed %>
        "stuck": <true | false>
}

*/

public class MessageHandler {

    public static final int MODE = 0;
    public static final int CONTROL = 1;
    public static final int STATUS = 2;

    private JSONObject obj;

    public MessageHandler() {
         obj = new JSONObject();
    }

    public String encodeJSON(int mode, String msg1, String msg2) {

        try {
            obj = new JSONObject();
            switch (mode) {
                case MODE:
                    obj.put("type", "mode_msg");
                    obj.put("mode", msg1);
                    break;
                case CONTROL:
                    obj.put("type", "ctrl_msg");
                    obj.put("move", msg1);
                    obj.put("turn", msg2);
                    break;
                case STATUS:
                    obj.put("type", "stat_msg");
                    obj.put("error", msg1 + ":" + msg2);
                    break;
            }

            String str = obj.toString();
            return str;
        } catch (Exception e) {
            return "";
        }

    }

    // Receive errors 1 at a time and return the error message.
    public String checkStatus(String msg) {
        try {
            obj = new JSONObject(msg);

            String str = (String)obj.get("error");
            if (str != null) {
                String[] arr = str.split(":");
                if (arr[0].equals("stuck") || (arr[0].equals("weed") && Integer.parseInt(arr[1]) >= 95) || (arr[0].equals("battery") && Integer.parseInt(arr[1]) <= 5))
                    return str;
                else
                    return null;
            }
            else
                return null;
        } catch (Exception e) {
            return null;
        }

        /*
            weeds - error if >= 95
            battery - error if <= 5
            stuck - always error

         */

    }

}
