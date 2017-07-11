package cordova.plugin.getApi;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class echoes a string called from JavaScript.
 */
public class getApi extends CordovaPlugin {
ResponseData responseObject= new ResponseData();
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("sndUrl")) {
            System.out.println("hello");
            System.out.println("args.getString(0)    ----- >>>> "+args.toString());

    JSONArray jsonArray=args.getJSONArray(0);
            int count =0;
            System.out.println("args.getString(0)    ----- >>>> "+jsonArray.toString());
            /*String[] stringArray = null;

            int length = jsonArray.length();
            stringArray = new String[length];
            for (int i = 0; i < length; i++) {
                stringArray[i] = jsonArray.optString(i);
            }*/
            try {
                JSONArray getResult = responseObject.callAPI(jsonArray,count);

                System.out.println("getResult  ----- >> "+getResult.length());
                callbackContext.success(getResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //int minutes = args.getInt(0);
            return true;
        }
        return false;
    }
}