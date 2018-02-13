package com.aml.api.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.util.GetWebProjectRealPathTool;

/**
 * 
 * @className: FileController
 * @description: 文件上传
 * @author huangliangbao
 * @date 2017年12月9日 下午1:12:56
 *
 */
@RestController
public class FileController extends BaseController {
	@Autowired
	private CommonsMultipartResolver multipartResolver;
	private final static String FILE_DIRECTORY = "/amlfile/";

	/**
	 * 
	 * @title: upload
	 * @description: 上传文件
	 *
	 * @param request
	 * @param response
	 * @param token
	 * @param userId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @date 2017年12月12日 下午8:30:43
	 */
	@RequestMapping(value = "/upload/files", method = RequestMethod.POST)
	public ApiResult<List<Map<String, String>>> upload ( HttpServletRequest request, HttpServletResponse response,
			String token, Long userId ) throws IllegalStateException, IOException {
		logger.info("request url：{}, token：{}，userId：{}", new Object[] { request.getRequestURL(), token, userId });
		ApiResult<List<Map<String, String>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (!hashToken(result, token, userId)) {
			return result;
		}
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 文件上传目录
			String path = GetWebProjectRealPathTool.getRootPath();

			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile tmpFile = multiRequest.getFile(iter.next());
				if (tmpFile != null) {
					// 取得当前上传文件的文件名称
					String originalFileName = tmpFile.getOriginalFilename();

					// 文件名前缀
					String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
					// 时间戳
					long timeStr = (new Date()).getTime();
					// 4位随机数
					int random = new Random().nextInt(10000);
					// 文件后缀名
					String prefix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
					// 新文件名称(文件名前缀 +时间戳+ 4位随机数)
					String fileName = name + timeStr + random + "." + prefix;
					File rootFile = new File(path);
					String parentPath = rootFile.getParent();
					// 判断指定文件目录是否存在
					String realPath = parentPath + FILE_DIRECTORY;
					File realFile = new File(realPath);
					if (!realFile.exists()) {
						realFile.mkdir();
					}
					File localFile = new File(realPath + fileName);
					try {
						// 保存到指定路径
						tmpFile.transferTo(localFile);
						Map<String, String> map = new HashMap<String, String>();
						map.put("fileName", originalFileName);
						map.put("filePath",
								request.getScheme() + "://" + request.getServerName() + FILE_DIRECTORY + fileName);
						data.add(map);
					} catch (IOException e) {
						e.printStackTrace();
						result.setErrorType(ErrorTypeEnum.ERROR_UPFILE_ERROR);
					}
				}
			}
		}
		result.setData(data);
		return result;
	}
}
