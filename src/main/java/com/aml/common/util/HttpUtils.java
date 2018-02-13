package com.aml.common.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;

/**
 * 处理HTTP请求 GET POST方式帮助类
 * 
 * 需引入okhttp3、gson、
 * 
 * @author zouwei
 * @since 2016年11月11日
 */
public class HttpUtils {

	public static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static final OkHttpClient client;

	static {
		OkHttpClient.Builder builder = new OkHttpClient.Builder() //
				.connectTimeout(15, TimeUnit.SECONDS) //
				.writeTimeout(20, TimeUnit.SECONDS) //
				.readTimeout(20, TimeUnit.SECONDS); //
		client = builder.build();
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @since 0512
	 */
	public static String get(String url) {
		Request request = new Request.Builder() //
				.url(url) //
				.build(); //
		Response response = null;
		String result = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()){
				throw new IOException("Unexpected code " + response);
			}
			result = response.body().string();
			logger.info("send get method request success, url:{}", url);
		} catch (Exception e) {
			logger.error("send get method request error, url:{}", url, e);
		} finally {
			Util.closeQuietly(response);
		}
		return result;
	}

	/**
	 * get请求(get byte数组 文件请求专用)
	 * 
	 * @param url
	 * @return
	 * @since 0512
	 */
	public static byte[] getBytes(String url) {
		Request request = new Request.Builder() //
				.url(url) //
				.build(); //
		Response response = null;
		byte[] result = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()){
				throw new IOException("Unexpected code " + response);
			}
			result = response.body().bytes();
			logger.info("send get method request success, url:{}", url);
		} catch (Exception e) {
			logger.error("send get method request error, url:{}", url, e);
		} finally {
			Util.closeQuietly(response);
		}
		return result;
	}

	/**
	 * post请求(默认为utf-8编码)
	 * 
	 * @param url
	 * @param msg
	 * @param contentType
	 * @return
	 * @since 0512
	 */
	public static String post(String url, String msg, String contentType) {
		MediaType mediaType = MediaType.parse(contentType);
		RequestBody requestBody = RequestBody.create(mediaType, msg);
		Request request = new Request.Builder() //
				.url(url) //
				.post(requestBody) //
				.build(); //
		Response response = null;
		String result = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()){
				throw new IOException("Unexpected code " + response);
			}
			result = response.body().string();
			logger.info("send post method request success, url:{}", url);
		} catch (Exception e) {
			logger.error("send post method request error, url:{}", url, e);
		} finally {
			Util.closeQuietly(response);
		}
		return result;
	}

	/**
	 * post一个文件
	 * 
	 * @param url
	 * @param msg
	 * @return
	 * @since 0512
	 */
	public static String postFile(String url, File msg) {
		MediaType mediaType = MediaType.parse("application/octet-stream");
		RequestBody requestBody = RequestBody.create(mediaType, msg);
		Request request = new Request.Builder() //
				.url(url) //
				.post(requestBody) //
				.build(); //
		Response response = null;
		String result = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()){
				throw new IOException("Unexpected code " + response);
			}
			result = response.body().string();
			logger.info("send postFile method request success, url:{}, file:{}", url, msg);
		} catch (Exception e) {
			logger.error("send postFile method request error, url:{}", url, e);
		} finally {
			Util.closeQuietly(response);
		}
		return result;
	}

	/**
	 * Post方式提交分块请求(支持String， File类型)
	 * 
	 * @param url
	 * @param msg
	 * @return
	 * @throws Exception
	 * @since 0512
	 */
	public static String postPartForm(String url, Map<String, Object> msg) {
		RequestBody requestBody = createMultipartBody(msg);
		Request request = new Request.Builder() //
				.url(url) //
				.post(requestBody)//
				.build();//
		Response response = null;
		String result = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()){
				throw new IOException("Unexpected code " + response);
			}
			result = response.body().string();
			logger.info("send postForm method request success, url:{}, params:{}", url, msg);
		} catch (Exception e) {
			logger.error("send postForm method request error, url:{}", url, e);
		} finally {
			Util.closeQuietly(response);
		}
		return result;
	}

	/**
	 * 以form表单的方式提交post请求(urlencoder编码)
	 * 
	 * @param url
	 * @param msg
	 * @return
	 * @since 0512
	 */
	public static String postForm(String url, Map<String, String> msg) {
		FormBody.Builder requestBodyBuilder = new FormBody.Builder();
		for (String key : msg.keySet()) {
			requestBodyBuilder.add(key, msg.get(key));
		}
		RequestBody requestBody = requestBodyBuilder.build();
		Request request = new Request.Builder() //
				.url(url) //
				.post(requestBody) //
				.build(); //
		Response response = null;
		String result = null;
		try {
			response = client.newCall(request).execute();
			if (!response.isSuccessful()){
				throw new IOException("Unexpected code " + response);
			}
			result = response.body().string();
			logger.info("send postForm method request success, url:{}", url);
		} catch (Exception e) {
			logger.error("send postForm method request error, url:{}", url, e);
		} finally {
			Util.closeQuietly(response);
		}
		return result;
	}

	/**
	 * Url转码
	 * 
	 * @param url
	 * @return
	 */
	public static String getEncodeUrl(String url) {
		String realUrl = "";
		try {
			// URLEncoder编码
			realUrl = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return realUrl;
	}

	/**
	 * Url解码
	 * 
	 * @param url
	 * @return
	 */
	public static String getDecodeUrl(String url) {
		String realUrl = "";
		try {
			// URLEncoder编码
			realUrl = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return realUrl;
	}

	/**
	 * 发送GET方式
	 * 
	 * @param url
	 * @return
	 * @deprecated replace by {@link #get(String)}
	 */
	@Deprecated
	public static String doGet(String url) {
		CloseableHttpClient httpClient = null;
		HttpGet get = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			get = new HttpGet(url);
			response = httpClient.execute(get);
			int code = response.getStatusLine().getStatusCode();
			if (code >= 200 && code < 300) {
				HttpEntity entity = (HttpEntity) response.getEntity();
				result = EntityUtils.toString(entity, "utf-8");
			}
			logger.info("send get method request success, url-->{}", url);
		} catch (Exception e) {
			logger.error("send get method request error, url-->{}", url, e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * 发送get请求，返回流
	 * 
	 * @param url
	 * @deprecated replace by {@link #getBytes(String)}
	 * @return
	 */
	@Deprecated
	public static byte[] doGetToByteArray(String url) {
		CloseableHttpClient httpClient = null;
		HttpGet get = null;
		CloseableHttpResponse response = null;
		byte[] result = null;
		try {
			httpClient = HttpClients.createDefault();
			get = new HttpGet(url);
			response = httpClient.execute(get);
			int code = response.getStatusLine().getStatusCode();
			if (code >= 200 && code < 300) {
				HttpEntity entity = (HttpEntity) response.getEntity();
				result = EntityUtils.toByteArray(entity);
			}
			logger.info("send get method request success, url-->{}", url);
		} catch (Exception e) {
			logger.error("send get method request error, url-->{}", url, e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * POST消息
	 * 
	 * @param url
	 * @param msg
	 *            post消息内容体
	 * @param contentType
	 *            内容格式型如：application/json application/xml
	 * @deprecated replace by {@link #post(String, String, String)}
	 * @return
	 */
	@Deprecated
	public static String doPost(String url, String msg, String contentType) {
		CloseableHttpClient httpClient = null;
		HttpPost post = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			post = new HttpPost(url);
			post.addHeader("content-type", contentType);
			StringEntity msgEntity = new StringEntity(msg, ContentType.create(contentType, "utf-8"));
			post.setEntity(msgEntity);
			response = httpClient.execute(post);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "utf-8");
			}
			logger.info("send post method request success, url-->{}, params-->{}", url, msg);
		} catch (Exception e) {
			logger.error("send post method request error, url-->{}", url, e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * 构建分块请求body
	 * 
	 * @param msg
	 * @return
	 */
	private static MultipartBody createMultipartBody(Map<String, Object> msg) {
		MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder() //
				.setType(MultipartBody.FORM); //
		for (String name : msg.keySet()) {
			Object data = msg.get(name);
			if (data instanceof String || data instanceof Long || data instanceof Integer) {
				String value = TypeCaseHelper.getAsString(data);
				requestBodyBuilder.addPart(createStringPart(name, value));
			} else if (data instanceof File) {
				File file = (File) data;
				requestBodyBuilder.addPart(createFilePart(name, file));
			} else {
				// do nothing;
			}
		}
		return requestBodyBuilder.build();
	}

	/**
	 * 构建String类型part
	 * 
	 * @return
	 */
	private static MultipartBody.Part createStringPart(String name, String value) {
		return Part.create(Headers.of("Content-Disposition", "form-data; name=" + name),
				RequestBody.create(null, value));
	}

	/**
	 * 构建file类型part
	 * 
	 * @param name
	 * @param file
	 * @return
	 */
	private static MultipartBody.Part createFilePart(String name, File file) {
		String fileName = String.valueOf(System.currentTimeMillis()) + "." + file.getName().split("\\.")[1];
		String suffix = file.getName().split("\\.")[1];
		MediaType mediaType = null;

		if ("png".equals(suffix)) {
			mediaType = MediaType.parse("image/png");
		} else if ("jpg".equals(suffix) || "jpeg".equals(suffix)) {
			mediaType = MediaType.parse("image/jpeg");
		} else if ("gif".equals(suffix)) {
			mediaType = MediaType.parse("image/gif");
		} else {
			mediaType = MediaType.parse("application/octet-stream");
		}

		return Part.create(Headers.of("Content-Disposition", "form-data; name=" + name + "; filename=" + fileName),
				RequestBody.create(mediaType, file));
	}
	
	/**
	 * post请求(默认为utf-8编码)
	 * 
	 * @param url
	 * @param msg
	 * @param contentType
	 * @return
	 * @since 0512
	 */
	public static Map<String, String> apiPost(String url, String msg, String contentType) {
		MediaType mediaType = MediaType.parse(contentType);
		RequestBody requestBody = RequestBody.create(mediaType, msg);
		Request request = new Request.Builder() //
				.url(url) //
				.post(requestBody) //
				.build(); //
		Response response = null;
		String result = null;
		Map<String, String> map = new HashMap<>();
		try {
			response = client.newCall(request).execute();
			map.put("response", response.code()+"");
			if (!response.isSuccessful()){
				throw new IOException("Unexpected code " + response);
			}
			result = response.body().string();
			map.put("body", result);
			logger.info("send post method request success, url:{}", url);
		} catch (Exception e) {
			logger.error("send post method request error, url:{}", url, e);
			map.put("body", e.getMessage());
		} finally {
			Util.closeQuietly(response);
		}
		return map;
	}
}
