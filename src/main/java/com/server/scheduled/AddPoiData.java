package com.server.scheduled;


import com.server.dao.PoiMapper;
import com.server.entity.Poi;
import com.server.utils.OtherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AddPoiData {

    private final static String[] DX_ZM = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"
            ,"P","Q","R","S","T","U","V","W","X","Y","Z"};

    private final static String[] XX_ZM = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o"
            ,"p","q","r","s","t","u","v","w","x","y","z"};


    @Autowired
    private PoiMapper poiMapper;

    @Scheduled(cron = "0/5 * * * * ?")
    public void add(){
        //System.out.println("dsq");
       // addData();
    }

    private void addData() {
        List<Poi> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Poi poi = new Poi();
            poi.setId(OtherUtils.getUUID());
            poi.setName(createStr());
            String str = createStr();
            poi.setPassword(str);
            poi.setRemark(str);
            list.add(poi);
        }
        poiMapper.insertData(list);
        list.clear();
    }

    private String createStr(){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomNum = random.nextInt(25);
            int randomNum1 = random.nextInt(2);
            if(randomNum1 == 0){
                stringBuffer.append(DX_ZM[randomNum]);
            }else{
                stringBuffer.append(XX_ZM[randomNum]);
            }
        }
        return stringBuffer.toString();
    }

}
