package tatteam.com.app_common.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import tatteam.com.app_common.entity.MyAppEntity;
import tatteam.com.app_common.entity.MyExtraAppsEntity;

/**
 * Created by ThanhNH-Mac on 10/3/15.
 */
public class AppParseUtil {


    public static MyExtraAppsEntity parseExtraApps(String json) {
        MyExtraAppsEntity extraApps = null;
        if (json != null && !json.trim().isEmpty()) {
            try {
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(json, JsonElement.class);
                JsonObject jsonObj = element.getAsJsonObject();
                extraApps = parseExtraApps(jsonObj);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return extraApps;

    }

    public static MyExtraAppsEntity parseExtraApps(JsonObject jsonObj) {
        MyExtraAppsEntity extraApps = null;
        if (jsonObj != null) {
            try {
                extraApps = new MyExtraAppsEntity();
                extraApps.myPubName = jsonObj.get("my_pub_name").getAsString();

                JsonArray my_apps = jsonObj.getAsJsonArray("my_apps");
                for (int i = 0; i < my_apps.size(); i++) {
                    JsonObject extra_app = my_apps.get(i).getAsJsonObject();
                    MyAppEntity myApp = new MyAppEntity();
                    myApp.name = extra_app.get("name").getAsString();
                    myApp.description = extra_app.get("description").getAsString();
                    myApp.image = extra_app.get("image").getAsString();
                    myApp.packageName = extra_app.get("package_name").getAsString();
                    extraApps.appList.add(myApp);
                }

                JsonArray my_games = jsonObj.getAsJsonArray("my_games");
                for (int i = 0; i < my_games.size(); i++) {
                    JsonObject my_game = my_games.get(i).getAsJsonObject();
                    MyAppEntity myGame = new MyAppEntity();
                    myGame.name = my_game.get("name").getAsString();
                    myGame.description = my_game.get("description").getAsString();
                    myGame.image = my_game.get("image").getAsString();
                    myGame.packageName = my_game.get("package_name").getAsString();
                    extraApps.gameList.add(myGame);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return extraApps;
    }
}
