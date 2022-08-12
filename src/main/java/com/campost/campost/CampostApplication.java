package com.campost.campost;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.nimbusds.jose.crypto.impl.HMAC;
import com.nimbusds.jose.shaded.json.JSONObject;

import Airtime.Airtime_status;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.UnsupportedEncodingException;
@SpringBootApplication
public class CampostApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CampostApplication.class, args);
		// request url
		String url = "https://sandbox.api.afrikpay.com/api/ecommerce/collect/v4/";

    // create an instance of RestTemplate
		
		RestTemplate restTemplate = new RestTemplate();
  // create headers
		HttpHeaders headers = new HttpHeaders();
 // set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// insertion de accoundid dans header
		headers.set("accountid", "65fqx2e9eqqo41vprrd0jqld764fgzlu");    
     //calcule du hash
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
		      String hash1=Test2.hmacWithApacheCommons("HmacMD5",operator+reference+amount+mode+agentid, secret);
		      String hash=MD5.convertStringToHex(hash1);
		      String agentplatform = "3620724907638658";

      Map<String, Object> map = new HashMap<>();
      map.put("operator", operator);
      map.put("reference", reference);
      map.put("amount",amount);
      map.put("mode", mode);
      map.put("agentid", agentid);
      map.put("agentplatform",agentplatform);
      map.put("agentpwd", agentpwd);
      map.put("hash",hash );
		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		JSONObject json = new JSONObject();
        json.put("iv",iv );
        String encryptdata=AES256.encrypt(entity.toString(), secret, json.toString());
        JSONObject bodyMap = new JSONObject();
        bodyMap.put("data", encryptdata);
		// send POST request
	        ResponseEntity<String> response = restTemplate.postForEntity(url,bodyMap, String.class);
	     
	       
	    
		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
		    System.out.println("Request Successful");
		    System.out.println(response.getBody());
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		}


	}
	
   
}
