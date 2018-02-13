package com.aml.api.web;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aml.api.pojo.AccountTypes;
import com.aml.api.pojo.ActivityMaps;
import com.aml.api.pojo.ActivityTypes;
import com.aml.api.pojo.CloseReason;
import com.aml.api.pojo.Countries;
import com.aml.api.pojo.CustomerTypes;
import com.aml.api.pojo.Exemptions;
import com.aml.api.pojo.FormComfiguration;
import com.aml.api.pojo.Jobs;
import com.aml.api.pojo.Logs;
import com.aml.api.pojo.RelationshipTypes;
import com.aml.api.pojo.Sequences;
import com.aml.api.pojo.SystemParameters;
import com.aml.api.pojo.TemplateRules;
import com.aml.api.pojo.UserDefindRules;
import com.aml.api.service.AccountTypesService;
import com.aml.api.service.ActivityMapsService;
import com.aml.api.service.ActivityTypesService;
import com.aml.api.service.CloseReasonService;
import com.aml.api.service.CountriesService;
import com.aml.api.service.CustomerTypesService;
import com.aml.api.service.ExemptionsService;
import com.aml.api.service.FormComfigurationService;
import com.aml.api.service.JobsService;
import com.aml.api.service.LogsService;
import com.aml.api.service.RelationshipTypesService;
import com.aml.api.service.SequencesService;
import com.aml.api.service.SystemParametersService;
import com.aml.api.service.TemplateRulesService;
import com.aml.api.service.UserDefindRulesService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;

/**
 * Import list data from all settings
 * @author Novan
 * @date 2017-11-22
 *
 */
@RestController
public class ImportConfigurationController extends BaseController {
	
	@Autowired
	private AccountTypesService accountTypesService;
	
	@Autowired
	private ActivityMapsService activityMapsService;
	
	@Autowired
	private ActivityTypesService activityTypesService;
	
	@Autowired
	private CountriesService countriesService;
	
	@Autowired
	private CustomerTypesService customerTypesService;
	
	@Autowired
	private CloseReasonService closeReasonService;
	
	@Autowired
	private ExemptionsService exemptionsService;
	
	@Autowired
	private FormComfigurationService formComfigurationService;
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private LogsService logsService;
	
	@Autowired
	private RelationshipTypesService relationshipTypesService;
	
	@Autowired
	private TemplateRulesService templateRulesService;
	
	@Autowired
	private UserDefindRulesService userDefindRulesService;
	
	@Autowired
	private SequencesService sequencesService;
	
	@Autowired
	private SystemParametersService systemParametersService;
	
	/**
	 * importAccountTypes
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importAccountTypes", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importAccountTypes(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByAccountTypes(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<AccountTypes> accountTypesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					AccountTypes accountTypes = new AccountTypes();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//code
					Cell c1 = row.getCell(1);//name
					Cell c2 = row.getCell(2);//risk_factor
					Cell c3 = row.getCell(3);//created_by
					Cell c4 = row.getCell(4);//create_date
					Cell c5 = row.getCell(5);//last_modify_date
					Cell c6 = row.getCell(6);//last_modify_oper
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Name' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Name' should not exceed 255");
							return result;
						}
					}

					if(c2 != null && c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Risk factor' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'line Risk factor' should not exceed 255");
							return result;
						}
					}
					if(c3 == null ||c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Created By' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Created By' should not exceed 255");
							return result;
						}
					}
					
					if(c4 == null ||c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create Date' cannot be empty");
						return result;
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify date' cannot be empty");
						return result;
					}
					
					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify oper' cannot be empty");
						return result;
					}else{
						if(c6.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last modify oper' should not exceed 255");
							return result;
						}
					}
					accountTypes.setCode(c0.toString());
					accountTypes.setName(c1.toString());
					accountTypes.setRiskFactor(c2.toString());
					accountTypes.setCreatedBy(c3.toString());
					try {
						if(c4.getCellType() == 0 ){
							accountTypes.setCreateDate(c4.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c4.toString(); 
							Date date=sdf.parse(dstr);
							accountTypes.setCreateDate(date);
						}
						if(c5.getCellType() == 0 ){
							accountTypes.setCreateDate(c5.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c5.toString(); 
							Date date=sdf.parse(dstr);
							accountTypes.setLastModifyDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Create Date' and 'Last modify date' must be the time format");
						return result;
					}
					accountTypes.setLastModifyOper(c6.toString());
					//校验code是否存在
					int count = accountTypesService.getAccountTypesCountByCode(accountTypes);
					if(count > 0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Code' Already existed !");
						return result;
					}
					accountTypesList.add(accountTypes);
				}
				try {
					accountTypesService.insertAccountTypes(accountTypesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * importActivityMaps
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importActivityMaps", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importActivityMaps(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByActivityMaps(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<ActivityMaps> activityMapsList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					ActivityMaps activityMaps = new ActivityMaps();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Original Activity
					Cell c1 = row.getCell(1);//Job ID
					Cell c2 = row.getCell(2);//Prime code
					Cell c3 = row.getCell(3);//Created by
					Cell c4 = row.getCell(4);//last_modify_date
					Cell c5 = row.getCell(5);//last_modify_oper
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Original Activity' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Original Activity' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Job ID' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Job ID' should not exceed 255");
							return result;
						}
					}

					if(c2 != null && c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Prime code' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Prime code' should not exceed 255");
							return result;
						}
					}
					if(c3 == null ||c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Created By' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Created By' should not exceed 255");
							return result;
						}
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify date' cannot be empty");
						return result;
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify oper' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last modify oper' should not exceed 255");
							return result;
						}
					}
					activityMaps.setOriginalActivity(c0.toString());
					activityMaps.setJobId(c1.toString());
					activityMaps.setPrimeCode(c2.toString());
					activityMaps.setCreatedBy(c3.toString());
					try {
						if(c4.getCellType() == 0 ){
							activityMaps.setLastModifyDate(c4.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c4.toString(); 
							Date date=sdf.parse(dstr);
							activityMaps.setLastModifyDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify date' must be the time format");
						return result;
					}
					activityMaps.setLastModifyOper(c5.toString());
					activityMapsList.add(activityMaps);
				}
				try {
					activityMapsService.insertActivityMaps(activityMapsList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	
	
	/**
	 * importCloseReason
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importCloseReason", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importCloseReason(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByCloseReason(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<CloseReason> closeReasonList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					CloseReason closeReason = new CloseReason();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code
					Cell c1 = row.getCell(1);//Object Type
					Cell c2 = row.getCell(2);//Name
					Cell c3 = row.getCell(3);//Create Operation
					Cell c4 = row.getCell(4);//Date Of Careation
					Cell c5 = row.getCell(5);//Modified Operation
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Object Type' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Object Type' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Name' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Name' should not exceed 255");
							return result;
						}
					}
					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create Operation' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Create Operation' should not exceed 255");
							return result;
						}
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Date Of Careation' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Date Of Careation' should not exceed 255");
							return result;
						}
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Modified Operation' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Modified Operation' should not exceed 255");
							return result;
						}
					}
					closeReason.setCode(c0.toString());
					closeReason.setObjectType(c1.toString());
					closeReason.setName(c2.toString());
					closeReason.setCreateOperation(c3.toString());
					closeReason.setDateOfCareation(c4.toString());
					closeReason.setModifiedOperation(c5.toString());
					//校验code是否存在
					int count = closeReasonService.getCloseReasonCountByCode(closeReason);
					if(count > 0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Code' Already existed !");
						return result;
					}
					closeReasonList.add(closeReason);
				}
				try {
					closeReasonService.insertCloseReason(closeReasonList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * importActivityTypes
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importActivityTypes", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importActivityTypes(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByActivityTypes(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<ActivityTypes> activityTypesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					ActivityTypes activityTypes = new ActivityTypes();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code Name
					Cell c1 = row.getCell(1);//Risk factor
					Cell c2 = row.getCell(2);//Created by
					Cell c3 = row.getCell(3);//last_modify_date
					Cell c4 = row.getCell(4);//last_modify_oper
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code Name' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code Name' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Risk factor' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Risk factor' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Created By' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Created By' should not exceed 255");
							return result;
						}
					}
					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify date' cannot be empty");
						return result;
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify oper' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last modify oper' should not exceed 255");
							return result;
						}
					}
					activityTypes.setCodeName(c0.toString());
					activityTypes.setRiskFactor(c1.toString());
					activityTypes.setCreatedBy(c2.toString());
					try {
						if(c3.getCellType() == 0 ){
							activityTypes.setLastModifyDate(c3.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c3.toString(); 
							Date date=sdf.parse(dstr);
							activityTypes.setLastModifyDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify date' must be the time format");
						return result;
					}
					activityTypes.setLastModifyOper(c4.toString());
					activityTypesList.add(activityTypes);
				}
				try {
					activityTypesService.insertActivityTypes(activityTypesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * importCountries
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importCountries", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importCountries(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByCountries(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<Countries> countriesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					Countries countries = new Countries();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code Name
					Cell c1 = row.getCell(1);//Region
					Cell c2 = row.getCell(2);//Monitor
					Cell c3 = row.getCell(3);//Rist
					Cell c4 = row.getCell(4);//Case Score
					Cell c5 = row.getCell(5);//Creat By
					Cell c6 = row.getCell(6);//Creat Date
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code Name' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code Name' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Region' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Region' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Monitor' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Monitor' should not exceed 255");
							return result;
						}
					}
					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Rist' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Rist' should not exceed 255");
							return result;
						}
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Case Score' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Case Score' should not exceed 255");
							return result;
						}
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat By' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Creat Bye' should not exceed 255");
							return result;
						}
					}
					
					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat Date' cannot be empty");
						return result;
					}
					countries.setCodeName(c0.toString());
					countries.setRegion(c1.toString());
					countries.setMonitor(c2.toString());
					countries.setRist(c3.toString());
					countries.setCaseScore(c4.toString());
					countries.setCreatBy(c5.toString());
					try {
						if(c6.getCellType() == 0 ){
							countries.setCreatDate(c6.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c6.toString(); 
							Date date=sdf.parse(dstr);
							countries.setCreatDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat Date' must be the time format");
						return result;
					}
					countriesList.add(countries);
				}
				try {
					countriesService.insertCountries(countriesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * importCustomerTypes
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importCustomerTypes", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importCustomerTypes(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByCustomerTypes(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<CustomerTypes> customerTypesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					CustomerTypes customerTypes = new CustomerTypes();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code Name
					Cell c1 = row.getCell(1);//Descripition
					Cell c2 = row.getCell(2);//Risk Model
					Cell c3 = row.getCell(3);//Accepetion Model
					Cell c4 = row.getCell(4);//Case Score
					Cell c5 = row.getCell(5);//Creat By
					Cell c6 = row.getCell(6);//Creat Date
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code Name' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code Name' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Descripition' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Descripition' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Risk Model' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Risk Model' should not exceed 255");
							return result;
						}
					}
					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Accepetion Model' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Accepetion Model' should not exceed 255");
							return result;
						}
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Case Score' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Case Score' should not exceed 255");
							return result;
						}
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat By' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Creat Bye' should not exceed 255");
							return result;
						}
					}
					
					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat Date' cannot be empty");
						return result;
					}
					customerTypes.setCodeName(c0.toString());
					customerTypes.setDescripition(c1.toString());
					customerTypes.setRiskModel(c2.toString());
					customerTypes.setAccepetionModel(c3.toString());
					customerTypes.setCaseScore(c4.toString());
					customerTypes.setCreatBy(c5.toString());
					try {
						if(c6.getCellType() == 0 ){
							customerTypes.setCreatDate(c6.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c6.toString(); 
							Date date=sdf.parse(dstr);
							customerTypes.setCreatDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat Date' must be the time format");
						return result;
					}
					customerTypesList.add(customerTypes);
				}
				try {
					customerTypesService.insertCustomerTypes(customerTypesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * importExemptions
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importExemptions", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importExemptions(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByExemptions(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<Exemptions> exemptionsList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					Exemptions exemptions = new Exemptions();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code Name
					Cell c1 = row.getCell(1);//Creat By
					Cell c2 = row.getCell(2);//Creat Date
					Cell c3 = row.getCell(3);//Last modify Date
					Cell c4 = row.getCell(4);//Last modify oper
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code Name' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code Name' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat By' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Creat By' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Creat Date' cannot be empty");
						return result;
					}
					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify Date' cannot be empty");
						return result;
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify oper' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last modify oper' should not exceed 255");
							return result;
						}
					}
					
					exemptions.setCodeName(c0.toString());
					exemptions.setCreatBy(c1.toString());
					exemptions.setLastModifyOper(c4.toString());
					try {
						if(c2.getCellType() == 0 ){
							exemptions.setCreatDate(c2.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c2.toString(); 
							Date date=sdf.parse(dstr);
							exemptions.setCreatDate(date);
						}
						
						if(c3.getCellType() == 0 ){
							exemptions.setLastModifyDate(c3.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c3.toString(); 
							Date date=sdf.parse(dstr);
							exemptions.setLastModifyDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Creat Date' and 'Last modify Date' must be the time format");
						return result;
					}
					exemptionsList.add(exemptions);
				}
				try {
					exemptionsService.insertExemptions(exemptionsList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * importFormComfiguration
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importFormComfiguration", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importFormComfiguration(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByFormComfiguration(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<FormComfiguration> formComfigurationList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					FormComfiguration formComfiguration = new FormComfiguration();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Display Name
					Cell c1 = row.getCell(1);//Eorm Name
					Cell c2 = row.getCell(2);//Editable
					Cell c3 = row.getCell(3);//Table Name
					Cell c4 = row.getCell(4);//Colunm Name
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Display Name' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Display Name' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Eorm Name' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Eorm Name' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Editable' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Editable' should not exceed 255");
							return result;
						}
					}
					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Table Name' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Table Name' should not exceed 255");
							return result;
						}
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Colunm Name' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Colunm Name' should not exceed 255");
							return result;
						}
					}
					
					formComfiguration.setDisplayName(c0.toString());
					formComfiguration.setEormName(c1.toString());
					formComfiguration.setEditable(c2.toString());
					formComfiguration.setTableName(c3.toString());
					formComfiguration.setColunmName(c4.toString());
					
					formComfigurationList.add(formComfiguration);
				}
				try {
					formComfigurationService.insertFormComfiguration(formComfigurationList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}

	/**
	 * importJobs
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importJobs", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importJobs(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByJobs(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<Jobs> jobsList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					Jobs jobs = new Jobs();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code
					Cell c1 = row.getCell(1);//Name
					Cell c2 = row.getCell(2);//Precess
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Name' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Name' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Precess' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Precess' should not exceed 255");
							return result;
						}
					}
					
					jobs.setCode(c0.toString());
					jobs.setName(c1.toString());
					jobs.setPrecess(c2.toString());
					//校验code是否存在
					int count = jobsService.getJobsCountByCode(jobs);
					if(count > 0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Code' Already existed !");
						return result;
					}
					jobsList.add(jobs);
				}
				try {
					jobsService.insertJobs(jobsList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}

	/**
	 * importLogs
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importLogs", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importLogs(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByLogs(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<Logs> logsList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					Logs logs = new Logs();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code
					Cell c1 = row.getCell(1);//Name
					Cell c2 = row.getCell(2);//Precess
					Cell c3 = row.getCell(3);//Enbled
					Cell c4 = row.getCell(4);//Modiffied
					Cell c5 = row.getCell(5);//Created by
					Cell c6 = row.getCell(6);//Created
					Cell c7 = row.getCell(7);//Midiffied By
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Name' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Name' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Precess' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Precess' should not exceed 255");
							return result;
						}
					}
					
					if(c3 == null ||c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Enbled' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Enbled' should not exceed 255");
							return result;
						}
					}

					if(c4 == null ||c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Modiffied' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Modiffied' should not exceed 255");
							return result;
						}
					}

					if(c5 == null ||c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Created by' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Created by' should not exceed 255");
							return result;
						}
					}

					if(c6 == null ||c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Created' cannot be empty");
						return result;
					}else{
						if(c6.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Created' should not exceed 255");
							return result;
						}
					}

					if(c7 == null ||c7.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Midiffied By' cannot be empty");
						return result;
					}else{
						if(c7.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Midiffied By' should not exceed 255");
							return result;
						}
					}
					
					logs.setCodeName(c0.toString());
					logs.setName(c1.toString());
					logs.setPrecess(c2.toString());
					logs.setEnbled(c3.toString());
					logs.setModiffied(c4.toString());
					logs.setCreatedBy(c5.toString());
					logs.setCreated(c6.toString());
					logs.setMidiffiedBy(c7.toString());
					
					logsList.add(logs);
				}
				try {
					logsService.insertLogs(logsList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * importRelationshipTypes
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importRelationshipTypes", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importRelationshipTypes(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByRelationshipTypes(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<RelationshipTypes> relationshipTypesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					RelationshipTypes relationshipTypes = new RelationshipTypes();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code Name
					Cell c1 = row.getCell(1);//Created by
					Cell c2 = row.getCell(2);//Creat Date
					Cell c3 = row.getCell(3);//Last modify Date
					Cell c4 = row.getCell(4);//Last modify oper
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code Name' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code Name' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Created by' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Created by' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Created Date' cannot be empty");
						return result;
					}
					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify Date' cannot be empty");
						return result;
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last modify oper' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last modify oper' should not exceed 255");
							return result;
						}
					}
					
					relationshipTypes.setCodeName(c0.toString());
					relationshipTypes.setCreatedBy(c1.toString());
					relationshipTypes.setLastModifyOper(c4.toString());
					try {
						if(c2.getCellType() == 0 ){
							relationshipTypes.setCreatedDate(c2.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c2.toString(); 
							Date date=sdf.parse(dstr);
							relationshipTypes.setCreatedDate(date);
						}
						
						if(c3.getCellType() == 0 ){
							relationshipTypes.setLastModifyDate(c3.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c3.toString(); 
							Date date=sdf.parse(dstr);
							relationshipTypes.setLastModifyDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Created Date' and 'Last modify Date' must be the time format");
						return result;
					}
					relationshipTypesList.add(relationshipTypes);
				}
				try {
					relationshipTypesService.insertRelationshipTypes(relationshipTypesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}

	/**
	 * importSequences
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importSequences", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importSequences(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadBySequences(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<Sequences> sequencesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					Sequences sequences = new Sequences();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Object Type
					Cell c1 = row.getCell(1);//Width
					Cell c2 = row.getCell(2);//Conla Chart
					Cell c3 = row.getCell(3);//Create Pression
					Cell c4 = row.getCell(4);//Sequernce
					Cell c5 = row.getCell(5);//Value
					Cell c6 = row.getCell(6);//Create Peer
					Cell c7 = row.getCell(7);//Create Date
					Cell c8 = row.getCell(8);//Last Modify
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Object Type' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Object Type' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Width' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Width' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Conla Chart' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Conla Chart' should not exceed 255");
							return result;
						}
					}

					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create Pression' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Create Pression' should not exceed 255");
							return result;
						}
					}

					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Sequernce' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Sequernce' should not exceed 255");
							return result;
						}
					}

					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Value' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Value' should not exceed 255");
							return result;
						}
					}

					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create Peer' cannot be empty");
						return result;
					}else{
						if(c6.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Create Peer' should not exceed 255");
							return result;
						}
					}

					if(c7 == null || c7.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create Date' cannot be empty");
						return result;
					}

					if(c8 == null || c8.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last Modify' cannot be empty");
						return result;
					}else{
						if(c8.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last Modify' should not exceed 255");
							return result;
						}
					}
					
					sequences.setObjectType(c0.toString());
					sequences.setWidth(c1.toString());
					sequences.setConlaChart(c2.toString());
					sequences.setCreatePression(c3.toString());
					sequences.setSequernce(c4.toString());
					sequences.setValue(c5.toString());
					sequences.setCreatePeer(c6.toString());
					try {
						if(c7.getCellType() == 0 ){
							sequences.setCreateDate(c7.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c7.toString(); 
							Date date=sdf.parse(dstr);
							sequences.setCreateDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create Date' must be the time format");
						return result;
					}
					sequences.setLastModify(c8.toString());
					sequencesList.add(sequences);
				}
				try {
					sequencesService.insertSequences(sequencesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}

	/**
	 * importSystemParameters
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importSystemParameters", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importSystemParameters(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadBySystemParameters(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<SystemParameters> systemParametersList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					SystemParameters systemParameters = new SystemParameters();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Code
					Cell c1 = row.getCell(1);//Name
					Cell c2 = row.getCell(2);//Account Bussiness Date
					Cell c3 = row.getCell(3);//Last End of Day
					Cell c4 = row.getCell(4);//Date
					Cell c5 = row.getCell(5);//Profile Review Frequary
					Cell c6 = row.getCell(6);//Server
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Code' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Code' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Name' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Name' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Account Bussiness Date' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Account Bussiness Date' should not exceed 255");
							return result;
						}
					}

					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last End of Day' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last End of Day' should not exceed 255");
							return result;
						}
					}

					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Date' cannot be empty");
						return result;
					}

					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Profile Review Frequary' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Profile Review Frequary' should not exceed 255");
							return result;
						}
					}

					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Server' cannot be empty");
						return result;
					}else{
						if(c6.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Server' should not exceed 255");
							return result;
						}
					}
					
					systemParameters.setCode(c0.toString());
					systemParameters.setName(c1.toString());
					systemParameters.setAccountBussinessDate(c2.toString());
					systemParameters.setLastEndOfDay(c3.toString());
					try {
						if(c4.getCellType() == 0 ){
							systemParameters.setDate(c4.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c4.toString(); 
							Date date=sdf.parse(dstr);
							systemParameters.setDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Date' must be the time format");
						return result;
					}
					systemParameters.setProfileReviewFrequary(c5.toString());
					systemParameters.setServer(c6.toString());
					
					//校验code是否存在
					int count = systemParametersService.getSystemParametersCountByCode(systemParameters);
					if(count > 0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Code' Already existed !");
						return result;
					}
					systemParametersList.add(systemParameters);
				}
				try {
					systemParametersService.insertSystemParameters(systemParametersList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}

	/**
	 * importTemplateRules
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importTemplateRules", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importTemplateRules(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByTemplateRules(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<TemplateRules> templateRulesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					TemplateRules templateRules = new TemplateRules();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Tride
					Cell c1 = row.getCell(1);//Type
					Cell c2 = row.getCell(2);//SP Name
					Cell c3 = row.getCell(3);//Susp Type-Schedule
					Cell c4 = row.getCell(4);//Pre/Post EDD
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Tride' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Tride' should not exceed 255");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Type' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Type' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'SP Name' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'SP Name' should not exceed 255");
							return result;
						}
					}

					
					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Susp Type-Schedule' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Susp Type-Schedule' should not exceed 255");
							return result;
						}
					}

					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Pre/Post EDD' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Pre/Post EDD' should not exceed 255");
							return result;
						}
					}

					
					
					templateRules.setTride(c0.toString());
					templateRules.setType(c1.toString());
					templateRules.setSpName(c2.toString());
					templateRules.setSuspTypeSchedule(c3.toString());
					templateRules.setPrePostEdd(c4.toString());
					templateRulesList.add(templateRules);
				}
				try {
					templateRulesService.insertTemplateRules(templateRulesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}

	/**
	 * importUserDefindRules
	 * 
	 * @since 2017-11-22
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importUserDefindRules", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importUserDefindRules(@RequestParam("file") CommonsMultipartFile file, 
			  HttpServletRequest request, HttpSession session) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		// 开始读取文件...
		FileOutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
			} catch (Exception e) {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("The file format you uploaded does not meet the requirements. Please upload the correct excel file");
				return result;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			int totalLength = sheet.getPhysicalNumberOfRows();// 取excel数据条数
			boolean flag = checkTheadByUserDefindRules(sheet.getRow(0));// 校验表头
			if (flag) {
				List<UserDefindRules> userDefindRulesList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					UserDefindRules userDefindRules = new UserDefindRules();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Type
					Cell c1 = row.getCell(1);//SP Name
					Cell c2 = row.getCell(2);//Sup type
					Cell c3 = row.getCell(3);//Pre/Post doo
					Cell c4 = row.getCell(4);//Last Modify Date
					Cell c5 = row.getCell(5);//Last Modify Oper
					Cell c6 = row.getCell(6);//Create Date
					Cell c7 = row.getCell(7);//Create By
					Cell c8 = row.getCell(8);//last status
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Type' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Type' should not exceed 255");
							return result;
						}
					}
					
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'SP Name' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'SP Name' should not exceed 255");
							return result;
						}
					}

					if(c2 == null ||c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Sup type' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Sup type' should not exceed 255");
							return result;
						}
					}

					if(c3 == null || c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Pre/Post doo' cannot be empty");
						return result;
					}else{
						if(c3.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Pre/Post doo' should not exceed 255");
							return result;
						}
					}
					
					if(c4 == null || c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last Modify Date' cannot be empty");
						return result;
					}

					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Last Modify Oper' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Last Modify Oper' should not exceed 255");
							return result;
						}
					}

					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create Date' cannot be empty");
						return result;
					}
					
					if(c7 == null || c7.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Create By' cannot be empty");
						return result;
					}else{
						if(c7.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Create By' should not exceed 255");
							return result;
						}
					}
					
					if(c8 == null || c8.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'last status' cannot be empty");
						return result;
					}else{
						if(c8.toString().trim().length() > 255){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'last status' should not exceed 255");
							return result;
						}
					}
					
					userDefindRules.setType(c0.toString());
					userDefindRules.setSpName(c1.toString());
					userDefindRules.setSupType(c2.toString());
					userDefindRules.setPrePostDoo(c3.toString());
					try {
						if(c4.getCellType() == 0 ){
							userDefindRules.setLastModifyDate(c4.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c4.toString(); 
							Date date=sdf.parse(dstr);
							userDefindRules.setLastModifyDate(date);
						}
						
						if(c6.getCellType() == 0 ){
							userDefindRules.setCreateDate(c6.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c6.toString(); 
							Date date=sdf.parse(dstr);
							userDefindRules.setCreateDate(date);
						}
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Date' must be the time format");
						return result;
					}
					userDefindRules.setLastModifyOper(c5.toString());
					userDefindRules.setCreateBy(c7.toString());
					userDefindRules.setLastStatus(c8.toString());
					userDefindRulesList.add(userDefindRules);
				}
				try {
					userDefindRulesService.insertUserDefindRules(userDefindRulesList);
				} catch (Exception e) {
					result.setStatus(-99);
					result.setErrorInfo(e.getMessage());//
					return result;
				}
				
			} else {
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Header structure mismatch");
			}
		} finally {
			//redisService.delete("importAccountTypes:" + vendorId);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}
	
	/**
	 * Check header  AccountTypes
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByAccountTypes(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("code")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("name")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("risk factor")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("created by")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("create date")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("last modify date")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("last modify oper")) {
			return false;
		}	
		return true;
	}
	
	/**
	 * Check header  ActivityMaps
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByActivityMaps(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("original activity")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("job id")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("prime code")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("created by")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("last modify date")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("last modify oper")) {
			return false;
		}	
		return true;
	}
	
	/**
	 * Check header  ActivityTypes
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByActivityTypes(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("code name")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("risk factor")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("created by")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("last modify date")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("last modify oper")) {
			return false;
		}	
		return true;
	}
	
	/**
	 * Check header  CloseReason
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByCloseReason(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("code")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("object type")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("name")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("create operation")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("date of careation")) {
			return false;
		}	
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("modified operation")) {
			return false;
		}	
		return true;
	}
	
	/**
	 * Check header  Countries
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByCountries(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("code name")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Region")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Monitor")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Rist")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Case Score")) {
			return false;
		}	
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Creat By")) {
			return false;
		}	
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Creat Date")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check header  CustomerTypes
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByCustomerTypes(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("code name")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Descripition")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Risk Model")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Accepetion Model")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Case Score")) {
			return false;
		}	
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Creat By")) {
			return false;
		}	
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Creat Date")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check header  Exemptions
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByExemptions(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("code name")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Creat By")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Creat Date")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Last modify Date")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Last modify oper")) {
			return false;
		}	
		return true;
	}

	/**
	 * Check header  FormComfiguration
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByFormComfiguration(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Display Name")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Eorm Name")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Editable")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Table Name")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Colunm Name")) {
			return false;
		}	
		return true;
	}

	/**
	 * Check header  Jobs
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByJobs(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Code")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Name")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Precess")) {
			return false;
		}
		return true;
	}

	/**
	 * Check header  Logs
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByLogs(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Code Name")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Name")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Precess")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Enbled")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Modiffied")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Created by")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Created")) {
			return false;
		}
		if (!row.getCell(7).toString().trim().equalsIgnoreCase("Midiffied By")) {
			return false;
		}
		return true;
	}

	/**
	 * Check header  RelationshipTypes
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByRelationshipTypes(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Code Name")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Created by")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Created Date")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Last modify Date")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Last modify oper")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check header  Sequences
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadBySequences(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Object Type")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Width")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Conla Chart")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Create Pression")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Sequernce")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Value")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Create Peer")) {
			return false;
		}
		if (!row.getCell(7).toString().trim().equalsIgnoreCase("Create Date")) {
			return false;
		}
		if (!row.getCell(8).toString().trim().equalsIgnoreCase("Last Modify")) {
			return false;
		}
		return true;
	}

	/**
	 * Check header  SystemParameters
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadBySystemParameters(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Code")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Name")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Account Bussiness Date")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Last End of Day")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Date")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Profile Review Frequary")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Server")) {
			return false;
		}
		return true;
	}

	/**
	 * Check header  TemplateRules
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByTemplateRules(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Tride")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Type")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("SP Name")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Susp Type-Schedule")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Pre/Post EDD")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check header  UserDefindRules
	 * @param row
	 * @return boolean
	 * @since 2017-11-22
	 */
	private boolean checkTheadByUserDefindRules(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Type")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("SP Name")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Sup type")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Pre/Post doo")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Last Modify Date")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Last Modify Oper")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Create Date")) {
			return false;
		}
		if (!row.getCell(7).toString().trim().equalsIgnoreCase("Create By")) {
			return false;
		}
		if (!row.getCell(8).toString().trim().equalsIgnoreCase("last status")) {
			return false;
		}
		return true;
	}
}
