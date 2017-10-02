package com.io.bio.file.coupang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.io.bio.file.FileUtils;

import java.io.IOException;
import java.util.Random;

/**
 * Author Mr.Pro
 * Date   9/22/17 = 10:48 AM
 */
public class VoCreator {

    public static void main(String args[]) throws IOException {
        String data = FileUtils.readFile("test.txt");
        String[] companyCode = new String []{"COUPANG","D000001","D000002","D000003","D000004","D000005","D000006","D000008","D000009","D000010","D000011","D000012","D000013","D000014","D000015","D000016","D000017","D000018","D000019","D000020","D000021","D000022","D000023","D999998","IQS","TNT","USPS","IPARCEL","GSMNTON","SWGEXP","PANTOS","ACIEXPRESS","DAEWOON","AIRBOY","KGLNET","KUNYOUNG","HONAM","LINEEXPRESS","SFEXPRESS","SLX","2FASTEXP","D999999"};
        String[] deliveryMethod = new String []{"VENDOR_DIRECT","MAKE_ORDER","INSTRUCTURE","AGENT_BUY","COLD_FRESH","MAKE_ORDER_DIRECT"};
        JSONObject jsonObject = JSON.parseObject(data);
        JSONArray jsonArray = jsonObject.getJSONArray("productLists");
        System.out.println("List<OfferVendorItem> vos = new ArrayList<OfferVendorItem>();");
        String[] imageList = new String[20];
        for (int i=0;i<20;i++){
            JSONObject item = jsonArray.getJSONObject(i);
            imageList[i] = item.getString("imageURL");
        }
        for (int i=0;i<7;i++){
            JSONObject item = jsonArray.getJSONObject(i);
            String nn = "vo"+i;
            System.out.println("OfferVendorItem "+nn+" = new OfferVendorItem();");
            System.out.println(nn+".setVendorItemId("+item.get("vendorItemId")+"L);");
            int imageCount = new Random().nextInt(3)+1;
            String temp = "new String[]{";
            for (int e=0;e<imageCount;e++){
                int index = new Random().nextInt(20);
                temp += "\""+imageList[index]+"\",";
            }
            temp = temp.substring(0, temp.length()-1);
            temp += "}";
            System.out.println(nn+".setUsedImageList(Lists.newArrayList("+temp+"));");
            System.out.println(nn+".setRemainCount(1);");
            String t = "OfferVendorItemStatus.";
            switch (i){
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
            System.out.println(nn+".setFreeShipOverAmount(Double.valueOf("+(Integer.valueOf(item.getString("salePrice").replace(",",""))+(new Random().nextBoolean()?500:0))+"));");
            System.out.println(nn+".setLogisticsType("+new Random().nextBoolean()+");");
            System.out.println(nn+".setCoupangGlobal("+item.getBoolean("coupangGlobal")+");");
            System.out.println(nn+".setDeliveryCompanyCode(\""+companyCode[new Random().nextInt(companyCode.length-1)]+"\");");
            System.out.println(nn+".setDeliveryMethod(\""+deliveryMethod[new Random().nextInt(deliveryMethod.length-1)]+"\");");
            System.out.println(nn+".setShippingFee(Double.valueOf("+(new Random().nextBoolean()?"500D":"0D")+"));");

            System.out.println("vos.add("+nn+");");
        }

    }
}
