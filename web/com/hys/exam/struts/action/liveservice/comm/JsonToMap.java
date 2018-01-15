package com.hys.exam.struts.action.liveservice.comm;
/**
 * @author taoliang
 * @desc JsonToMap
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonToMap {
	public static Map jsonToMap(String jsonStr){
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);  
	    Iterator keyIter = jsonObject.keys();  
	    String key;  
	    Object value;  
	    Map valueMap = new HashMap();  
	    while (keyIter.hasNext()) {  
	      key = (String) keyIter.next();  
	      value = jsonObject.get(key);  
	      valueMap.put(key, value);  
	    }
	    return valueMap;
	}
	
//	public static Map parserToMap(String jsonStr){
//		 Map map=new HashMap();        
//		 JSONObject json=JSONObject.fromObject(jsonStr);        
//		 Iterator keys=json.keys();        
//		 while(keys.hasNext()){           
//			 String key=(String) keys.next();            
//			 String value=json.get(key).toString();           
//			 if((value.startsWith("[") || value.startsWith("{")) && value.endsWith("}") || value.endsWith("]")){              
//				 map.put(key, parserToMap(value));           
//				 }
//			 else{               
//					 map.put(key, value);           
//				}        
//			 }        
//		 return map;
//	}
	
	 public static Map<String, Object> parseJSON2Map(String jsonStr){   
			        Map<String, Object> map = new HashMap<String, Object>();   
			        //最外层解析   
			        JSONObject json = JSONObject.fromObject(jsonStr);   
			        for(Object k : json.keySet()){   
			            Object v = json.get(k);    
			           //如果内层还是数组的话，继续解析   
			            if(v instanceof JSONArray){   
			                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();   
			                Iterator<JSONObject> it = ((JSONArray)v).iterator();   
			                while(it.hasNext()){   
			                   JSONObject json2 = it.next();   
			                    list.add(parseJSON2Map(json2.toString()));   
			                }   
			                map.put(k.toString(), list);   
			            } else {   
			                map.put(k.toString(), v);   
			            }   
			        }   
			       return map;   
			    }
	 
	 public static void main(String [] agrs){
			 //\"listingInfo\":[{\"listId\":\"cc\", \"mlistId\": \"dd\",\"items\":[{\"itemid\":\"ee\",\"itemStatus\":\"ff\"}]}]
		
		 //String str1 = ",\"listingInfo\":[{\"listId\":\"cc\", \"mlistId\": \"dd\",\"items\":[{\"itemid\":\"ee\",\"itemStatus\":\"ff\"}]}]";
//		 String str ="{\"returnCode\": \"aaa\", \"returnMessage\": \"bbb\",\"listingInfo\":[{\"listId\":\"cc\", \"mlistId\": \"dd\",\"items\":[{\"itemid\":\"ee\",\"itemStatus\":\"ff\"}]}]}";

		 String str ="{\"orderList\":[{\"uleOrderId\": \"12345\", \"userId\": \"429\", \"Mobile\": \"11111111111\", \"addressId\": \"112\", \"shipDay\": \"11\", \"totalAmount\": \"111\", \"merchAmout\": \"112\", \"transFee\": \"11\", \"paymentType\": \"111\", \"shippingType\": \"111\", \"receiptType\": \"11\", \"rceiptTitle\": \"11\", \"receiptContent\": \"11\", \"channel\": \"11\", \"goodsList\":{\"ulItemId\":\"12345\", \"goodsId\": \"370\", \"number\": \"11\", \"price\": \"111\"},\"addressInfo\":{\"provinceid\":\"110000\", \"cityid\": \"110100\", \"regionId\": \"110114\", \"province\": \"湖北\", \"city\": \"十堰\", \"region\": \"竹山\"}}]}";
		 Map map = JsonToMap.parseJSON2Map(str);
		 List list = (List) map.get("orderList");
		 for(int i=0;i<list.size();i++){
			 Map map1 = (Map) list.get(i);
			 System.out.println(map1.get("uleOrderId"));
//			 String listId = (String) map1.get("listId");
//			 String mlistId = (String) map1.get("mlistId");
//			 System.out.println(listId+mlistId);
			 Map map2 = (Map) map1.get("addressInfo");
//			 for(int j=0;j<list1.size();j++){
//				 Map map2 = (Map) list1.get(j);
				 System.out.println(map2.get("province"));
//			 }
			 
		 }
		 //System.out.println(list);
//		 System.out.println(map.get("listingInfo"));
//		 System.out.println(map.get("items"));
		 //System.out.println(map.get("listingInfo"));
		 //System.out.println(JsonToMap.parseJSON2Map(str));
	 }
	
}
