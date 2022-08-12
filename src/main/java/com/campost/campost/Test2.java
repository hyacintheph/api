package com.campost.campost;

import okhttp3.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.HmacUtils;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jwt.util.DateUtils;

public class Test2
{
	public static String hmacWithApacheCommons(String algorithm, String data, String key) {
	    String hmac = new HmacUtils(algorithm, key).hmacHex(data);
	    return hmac;
	}
	
    public static void main( String[] args ) throws Exception
    {
        OkHttpClient client = new OkHttpClient();

        String pwd       = "afrikpay"; /* password of partner given by afrikpay */
        byte[] agentpwd1 = MD5.digest(pwd.getBytes());
        String agentpwd=agentpwd1.toString();
        String operator  = "mtn"; /* possible values : camtel | mtn | nexttel | orange | yoomee              */
        String reference = "677777777"; /* 9 digits ex : 242006670 | 651354302 | 661917544 | 696991037 | 242972653 */
        int amount    = 300; /* minimum amount is XAF 100 and for orange it is a multiple of 50         */
        String mode="cash" ;
        String agentid = "3620724907638658";
        // convertion de la clé en utf8
        String secret1="7852e321c110baff04a48ba9656df54a";
        byte[] bytes = secret1.getBytes(StandardCharsets.UTF_8);
	      String secret = new String(bytes, StandardCharsets.UTF_8);
	      //iv
	      String iv=MD5.convertStringToHex("80ef4d7fad3f3a66e62e3f84e3d47e9c");
	     
	      //calcul du hash
	      String hash1=hmacWithApacheCommons("HmacMD5",operator+reference+amount+mode+agentid, secret);
	      String hash=MD5.convertStringToHex(hash1);
        String jsonRequest = "{" +
        "    \"operator\"         :\""+operator+"\","  +
        "    \"reference\"        :\""+reference+"\"," +
        "    \"amount\"           :"+amount+","        +
        "    \"mode\"             :\"cash\","          + /* possible values : cash | account | money */
        "    \"agentid\"          :\""+agentid+"\","           + /* 16 digits                                */
        "    \"agentplatform\"    :\"3620724907638658\","           + /* 16 digits                                */
        "    \"agentpwd\"         :\""+agentpwd+"\","  +
        "    \"hash\"             :\""+hash+"\","      +
         /* 16 digits                                */
        "}";
        JSONObject json = new JSONObject();
        json.put("iv",iv );
        String encryptdata=AES256.encrypt(jsonRequest, secret, json.toString());
        JSONObject bodyMap = new JSONObject();
        bodyMap.put("data", encryptdata);
        
        
        RequestBody body = RequestBody.create(
        	      MediaType.parse("application/json"), bodyMap.toString() );
        
        Request request = new Request.Builder()
        	      .url("https://sandbox.api.afrikpay.com/api/airtime/v4/")
        	      .addHeader("accountid", "65fqx2e9eqqo41vprrd0jqld764fgzlu")
        	      .post(body)
        	      .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println((response.body() != null) ? response.body().string() : "No result");  
          } catch (Exception ex) {
            System.out.println("No result");  
          }
    }
}    