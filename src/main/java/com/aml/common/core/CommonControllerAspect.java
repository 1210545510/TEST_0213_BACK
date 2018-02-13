package com.aml.common.core;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * 统一处理XML配置的controller异常 <br>
 * spring-servlet.xml添加如下配置
 * 
 * <pre>
	{@code
	<!-- 自定义controller异常处理切面�? -->
	<bean id="commonControllerAspect" class=
"com.ar.common.core.CommonControllerAspect"></bean>
	<aop:config>
		<!-- 切入点表达式 -->
		<aop:pointcut
			expression=
"execution(* com.ar.common.web.*.*(..)) and args(request,response)"
			id="controllerPointCut" />
		<aop:aspect id="c" ref="commonControllerAspect">
			<aop:after-throwing method="exceptionProcess"
				pointcut-ref="controllerPointCut" throwing="ex" />
		</aop:aspect>
	</aop:config>
	}
 * </pre>
 * 
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class CommonControllerAspect {
	public static final Logger logger = LoggerFactory.getLogger(CommonControllerAspect.class);

	/**
	 * 统一处理异常
	 * 
	 * @param j
	 * @param ex
	 * @param request
	 * @param response
	 */
	public void exceptionProcess ( JoinPoint j, Throwable ex, HttpServletRequest request,
			HttpServletResponse response ) {
		logger.error(ex.getMessage(), ex);
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		result.setErrorType(ErrorTypeEnum.SERVER_EXCEPTION);
		write(response, result.toJson());
	}

	/**
	 * response写出
	 * 
	 * @param response
	 * @param str
	 */
	private void write ( HttpServletResponse response, String str ) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (Exception e) {
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

}
