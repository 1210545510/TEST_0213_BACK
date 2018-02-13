package com.aml.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可以重复读取request body流的request包装类 <br>
 * 针对request.getReader()和request.getInputStream()只能读取一次的问题，重写request包装类
 * 
 * @author zouwei
 * @since 2017年1月13日
 */
public class RepeatReadHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private static final Logger logger = LoggerFactory.getLogger(RepeatReadHttpServletRequestWrapper.class);

	private byte[] body;

	public RepeatReadHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		InputStream is = null;
		try {
			is = request.getInputStream();
			body = IOUtils.toByteArray(is);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

	/**
	 * 获取request body里面的内容
	 * 
	 * @return
	 */
	public byte[] getBody() {
		return body;
	}
}
