package com.ideabox.tests.utils;

import com.google.gson.JsonObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by tdvoryanchenko on 4/26/17.
 */
public class REST {
  HttpClient client;
  HttpPost post ;
  HttpPut put;
  HttpResponse response;

  public REST(){
    client = new DefaultHttpClient();

  }
  public void setPostUrl(String url){
    post =new HttpPost(url);
  }

  public void setPutUrl(String url){
    put =new HttpPut(url);
  }

  public void setPostHeader(Header header){
    post.setHeader(header);
  }

  public void setPutHeader(Header header){
    put.setHeader(header);
  }

  public HttpResponse doPost( StringEntity body)  {
    client = new DefaultHttpClient();
    post.setEntity(body);
    try {
      return response = client.execute(post);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  public HttpResponse doPut( StringEntity body)  {
    client = new DefaultHttpClient();
    put.setEntity(body);
    try {
      return response = client.execute(put);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public StringEntity prepareBodyFromJson(JsonObject json){
    StringEntity body = null;
    try {
      body = new StringEntity( json.toString());
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    body.setContentType("application/json");
    return body;
  }

  public Header getHeader(String headerName){
    return response.getFirstHeader(headerName);
  }

  public void checkResponseCode(int expectedRespCode){
    Assert.assertEquals(response.getStatusLine().getStatusCode(),expectedRespCode, "Response code does not match");
  }

  public HttpResponse getResponse() {
    return response;
  }

}
