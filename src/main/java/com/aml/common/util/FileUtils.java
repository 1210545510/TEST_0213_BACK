package com.aml.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @className: FileUtils
 * @description: 文件上传/下载工具类
 * @author huangliangbao
 * @date 2017年11月8日 下午6:12:11
 *
 */
public class FileUtils {

	/**
	 * 
	 * @title: upload
	 * @description: (form)表单文件上传
	 *
	 * @param host 服务器域名
	 * @param path 上传文件目录
	 * @param files 单个或多个文件
	 * @return List
	 * @date 2017年11月29日 下午6:31:51
	 */
	public static List<Map<String, String>> upload ( String host, String path, MultipartFile[] files ) {
		// 返回值（key=保存的URL, value=原文件名称）
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (StringUtils.isEmpty(path) || ObjectUtils.isEmpty(files)
				|| StringUtils.isBlank(files[0].getOriginalFilename())) {
			return list;
		}
		// 循环临时文件(对单文件上传同样效果)
		for (MultipartFile tmpFile : files) {
			// 取得当前上传文件的文件名称
			String originalFileName = tmpFile.getOriginalFilename();

			// 文件名前缀
			String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
			// 时间戳
			long timeStr = (new Date()).getTime();
			// 4位随机数
			int random = new Random().nextInt(10000);
			// fileName = name + "_" + dateStr + random + "." + suffix;
			// 文件后缀名
			String prefix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			// 新文件名称(文件名前缀 +时间戳+ 4位随机数)
			String fileName = name + timeStr + random + "." + prefix;
			File rootFile = new File(path);
			String parentPath = rootFile.getParent();
			// 判断指定文件目录是否存在
			String realPath = parentPath + "/amlfile/";
			File realFile = new File(realPath);
			if (!realFile.exists()) {
				realFile.mkdir();
			}
			File localFile = new File(realPath + fileName);
			try {
				// 保存到指定路径
				tmpFile.transferTo(localFile);
				Map<String, String> map = new HashMap<String, String>();
				map.put("url", realPath + fileName);
				map.put("fileName", originalFileName);
				list.add(map);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
