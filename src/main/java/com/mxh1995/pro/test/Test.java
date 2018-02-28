package com.mxh1995.pro.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {

		while(true){
			byte[] x = new byte[20 * 1024 * 1024];
		}

	}

	public void test(){
	JSONArray jsonArray2 = new JSONArray();
		
		Map<String, String> mapp = new HashMap<>();
		mapp.put("speed", "70");
		mapp.put("duration", "60");
		
		
		Map<String, String> mapp2 = new HashMap<>();
		mapp2.put("plan", "1h");
		mapp2.put("loop", "1h");
		
		Map<String, String> mapp3 = new HashMap<>();
		mapp3.put("plan", "1h");
		mapp3.put("loop", "1h");
		
		Map<String, String> mapp4 = new HashMap<>();
		mapp4.put("plan", "5h");
		mapp4.put("loop", "1h");
		
		JSONObject ob = new JSONObject();
		ob.put("speedLimit", mapp);
		ob.put("lateDispatch", mapp2);
		ob.put("lateArrive", mapp3);
		ob.put("stop", mapp4);
		jsonArray2.add(ob);
		System.out.println(jsonArray2.toString());
		
		
		JSONArray json = JSONArray.fromObject(jsonArray2);

		for (int i = 0; i < json.size(); i++) {
			JSONObject job = json.getJSONObject(i);
			Set keySet = job.keySet();
			for (Object object : keySet) {
				switch (String.valueOf(object)) {
				case "speedLimit":{
					Object object2 = job.get(object);
					JSONObject ob1 = JSONObject.fromObject(object2);
					System.out.println(ob1.getString("speed"));
					break;
				}
				default: {
					System.out.println("321");
				}
			}
			}
		}
	}
}
