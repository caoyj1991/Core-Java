package com.io.bio.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Random;

/**
 * Author Mr.Pro
 * Date   9/22/17 = 10:48 AM
 */
public class VoCreator {

    public static void main(String args[]) throws IOException {
        String data = FileUtils.readFile("test.txt");
        JSONObject jsonObject = JSON.parseObject(data);
        JSONArray jsonArray = jsonObject.getJSONArray("productLists");
        System.out.println("List<OfferVendorItem> vos = new ArrayList<OfferVendorItem>();");
        for (int i=0;i<jsonArray.size();i++){
            JSONObject item = jsonArray.getJSONObject(i);
            String nn = "vo"+i;
            System.out.println("OfferVendorItem "+nn+" = new OfferVendorItem();");
            System.out.println(nn+".setVendorItemId("+item.get("vendorItemId")+"L);");
            System.out.println(nn+".setUsedImageList(Lists.newArrayList(new String[]{\""+item.get("imageURL")+"\"}));");
            System.out.println(nn+".setRemainCount(1);");
            String t = "OfferVendorItemStatus.";
            switch (new Random().nextInt(5)){
                case 0:
                    t+="NEW";
                    break;
                case 1:
                    t+="PACKAGE_DAMAGED";
                    break;
                case 2:
                    t+="REFURBISHED";
                    break;
                case 3:
                    t+="USED_BEST";
                    break;
                case 4:
                    t+="USED_GOOD";
                    break;
                default:
                    t+="USED_NORMAL";
            }
            System.out.println(nn+".setOfferStatus("+t+");");
            System.out.println(nn+".setUsedConditionDescription(\"VIID:"+item.get("vendorItemId")+"----description\");");
            System.out.println(nn+".setSalePrice(Double.valueOf("+item.getString("salePrice").replace(",","")+"));");
            System.out.println(nn+".setShippingFee(Double.valueOf("+(new Random().nextBoolean()?"500D":"0D")+"));");

            System.out.println("vos.add("+nn+");");
        }

    }
}
