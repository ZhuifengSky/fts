package com.file.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.file.service.HessianAPI;
import com.util.Cryptos;

public class HessianAPIServiceImpl implements HessianAPI{

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> userInfo(Map<String, byte[]> req) {
		Map<String,Object> req2=new HashMap<String, Object>();
		final String _alarm="2444090442568848"; //¹Ì¶¨²ÎÊý
		req2.put("STATE", Cryptos.aesEncrypt("200".getBytes(), _alarm.getBytes()));//×´Ì¬
		req2.put("CYZGZT", Cryptos.aesEncrypt("200".getBytes(), _alarm.getBytes()));//×´Ì¬
		List<Map<String,byte[]>> lnxs=new ArrayList<Map<String,byte[]>>();
		Map<String,byte[]> bean = new HashMap<String, byte[]>();
		byte[] nf = Cryptos.aesEncrypt("2010".getBytes(), _alarm.getBytes());
		byte[] xf = Cryptos.aesEncrypt("10".getBytes(), _alarm.getBytes());
		bean.put("NF",nf);
		bean.put("XS", xf);
		lnxs.add(bean);
		Map<String,byte[]> bean2 = new HashMap<String, byte[]>();
		byte[] nf1 = Cryptos.aesEncrypt("2011".getBytes(), _alarm.getBytes());
		byte[] xf1 = Cryptos.aesEncrypt("15".getBytes(), _alarm.getBytes());
		bean2.put("NF",nf1);
		bean2.put("XS", xf1);
		lnxs.add(bean2);
		Map<String,byte[]> bean3 = new HashMap<String, byte[]>();
		byte[] nf11 = Cryptos.aesEncrypt("2016".getBytes(), _alarm.getBytes());
		byte[] xf11 = Cryptos.aesEncrypt("20".getBytes(), _alarm.getBytes());
		bean3.put("NF",nf11);
		bean3.put("XS", xf11);
		lnxs.add(bean3);
		req2.put("LNXS", lnxs);
		return req2;			
		
	}

	@Override
	public Map<String, Object> periodInfo(Map<String, byte[]> req) {
		Map<String,Object> req2=new HashMap<String, Object>();
		final String _alarm="2444090442568848"; //¹Ì¶¨²ÎÊý
		req2.put("STATE", Cryptos.aesEncrypt("200".getBytes(), _alarm.getBytes()));//×´Ì¬
		req2.put("CYZGZT", Cryptos.aesEncrypt("200".getBytes(), _alarm.getBytes()));//×´Ì¬		
		req2.put("STATE", Cryptos.aesEncrypt("200".getBytes(), _alarm.getBytes()));//×´Ì¬
		req2.put("CYZGZT", Cryptos.aesEncrypt("200".getBytes(), _alarm.getBytes()));//×´Ì¬
		byte[] nf = Cryptos.aesEncrypt("2010".getBytes(), _alarm.getBytes());
		byte[] xf = Cryptos.aesEncrypt("10".getBytes(), _alarm.getBytes());
		req2.put("NF",nf);
		req2.put("XS", xf);
		return req2;			
	}

	@Override
	public Map<String, Object> education(Map<String, Object> req) {
		Map<String,Object> req2=new HashMap<String, Object>();
		final String _alarm="2444090442568848"; //¹Ì¶¨²ÎÊý
		List<Map<String,byte[]>> edus = (List<Map<String, byte[]>>) req.get("EDUS");
		for (Map<String, byte[]> map : edus) {
			System.out.println(map.get("RYID"));
		}
		req2.put("STATE", Cryptos.aesEncrypt("200".getBytes(), _alarm.getBytes()));//×´Ì¬
		req2.put("NUM", Cryptos.aesEncrypt("5".getBytes(), _alarm.getBytes()));//×´Ì¬
		return req2;	
	}

	@Override
	public Map<String, Object> heartbeat(Map<String, byte[]> req) {
		// TODO Auto-generated method stub
		return null;
	}

}
