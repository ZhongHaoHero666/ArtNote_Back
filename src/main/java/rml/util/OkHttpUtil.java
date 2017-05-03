package rml.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;



public class OkHttpUtil {
	
	/**
	 * 调用http，post请求的方法，头部信息为空 返回NULL字符串
	 * 
	 * @author liying
	 * @Date 2016-07-15
	 * @param url
	 *            http请求地址
	 * @param requestHeader
	 *            请求头部信息，系统参数
	 * @param params
	 *            请求应用参数
	 * @return
	 * @throws IOException
	 */
	public static String run(String url, Map<String, Object> requestHeader,
			HashMap<String, String> params) throws IOException {
		Request request = null;
		if (requestHeader != null && !requestHeader.isEmpty()) {
			Request.Builder builder = new Request.Builder();
			for (String key : requestHeader.keySet()) {
				if (key != null && requestHeader.get(key) != null) {
					builder.addHeader(key, requestHeader.get(key).toString());
				}
			}
			if (params != null && !params.isEmpty()) {
				FormEncodingBuilder formbody = new FormEncodingBuilder();
				for (String key : params.keySet()) {
					if (key != null && params.get(key) != null) {
						formbody.add(key, params.get(key).toString());
					}
				}
				RequestBody postBody = formbody.build();
				request = builder.url(url).post(postBody).build();
			} else {
				request = builder.url(url).build();
			}
			 OkHttpClient client = new OkHttpClient();
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		}
		return null;
	}
}
