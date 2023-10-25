package com.befoys.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.UUID;

@SuppressLint({"HardwareIds","HardwareIds"})
public class UniqueId {
	public static String GetUniqueID(Context context) {
		String m_szDevIDShort=String.valueOf((Build.BOARD.length()%10)+(getABI().length()%10));
		String serial=getSerialNumber();
		if (serial==null || serial.equals("unknown")){
			try {
				serial = Objects.requireNonNull(Build.class.getField("SERIAL").get(null)).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (serial==null || serial.equals("unknown")){
			serial = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
		}
		if (serial==null || serial.equals("unknown")){
			serial = "UNKNOWKN";
			/*
			//String saved_shared = KeyStore.getInstance("device_id").toString();
			if (TextUtils.isEmpty(saved_shared)){
				serial= UUID.randomUUID().toString();
				//KeyStore.getInstance("device_id").se.putString("device_id",serial);
			}else {
				serial= saved_shared;
			}
			 */
		}
		return encode(m_szDevIDShort+serial);
	}
	private static String getSerialNumber() {
		String serialNumber=null;
		try {
			@SuppressLint("PrivateApi")
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class);
			serialNumber =(String) get.invoke(c, "gsm.sn1");
			if (serialNumber==null||serialNumber.isEmpty()){
				serialNumber =(String) get.invoke(c, "ril.serialnumber");
			}
			if (serialNumber==null||serialNumber.isEmpty()){
				serialNumber =(String) get.invoke(c, "ro.serialno");
			}
			if (serialNumber==null||serialNumber.isEmpty()){
				serialNumber =(String) get.invoke(c, "sys.serialnumber");
			}
			if (serialNumber==null||serialNumber.isEmpty()){
				serialNumber =Build.SERIAL;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serialNumber;
	}
	private static String encode(String data){
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(data.getBytes(), 0, data.length());
			return String.format("%032x", new BigInteger(1,m.digest())).trim().toUpperCase();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private static String getABI(){
		try {
			String[] ABI= new String[0];
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				ABI = Build.SUPPORTED_ABIS;
			}
			StringBuilder final_abi=new StringBuilder();
			for (String abi : ABI) {
				final_abi.append(abi);
			}
			return final_abi.toString();
		} catch (Exception e) {
			return (Build.CPU_ABI);
		}
	}
}
