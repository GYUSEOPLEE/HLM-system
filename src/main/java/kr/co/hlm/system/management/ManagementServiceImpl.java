package kr.co.hlm.system.management;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.hlm.system.helmet.Helmet;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService{
    @Override
    public void sendHelmetLoss(Helmet helmet) {
        String url = "http://" + helmet.getIp() + "/helmet/loss";

        Gson helmetWear = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("loss", 'Y');

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), helmetWear.toJson(jsonObject));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
