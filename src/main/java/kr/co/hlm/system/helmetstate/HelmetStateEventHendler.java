package kr.co.hlm.system.helmetstate;

import com.google.gson.JsonObject;
import kr.co.hlm.system.helmet.WebSocketMap;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;

@Service
public class HelmetStateEventHendler {

    @EventListener
    public void receiveHelmetState(HelmetState helmetState) throws IOException {
        JsonObject helmetObj = new JsonObject();

        helmetObj.addProperty("no", helmetState.getNo());
        helmetObj.addProperty("dateTime", helmetState.getDateTime().toString());
        helmetObj.addProperty("latitude", helmetState.getLatitude());
        helmetObj.addProperty("longitude", helmetState.getLongitude());
        helmetObj.addProperty("helmetNo", helmetState.getHelmetNo());
        helmetObj.addProperty("loss", helmetState.getLoss());
        helmetObj.addProperty("wear", helmetState.getWear());

        for(Session s : WebSocketMap.clients) {
            s.getBasicRemote().sendText(helmetObj.toString());
        }
    }
}
