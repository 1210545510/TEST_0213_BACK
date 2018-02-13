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

import com.aml.api.pojo.Data314A;
import com.aml.api.pojo.ExtUserRequest;
import com.aml.api.pojo.GrandJurySubpoena;
import com.aml.api.service.Data314AService;
import com.aml.api.service.ExtUserRequestService;
import com.aml.api.service.GrandJurySubpoenaService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;

/**
 * Import DataCenter
 * @author Novan
 * @date 2017-11-30
 *
 */
@RestController
public class DataCenterImportController extends BaseController {
	
	@Autowired
	private ExtUserRequestService extUserRequestService;

	@Autowired
	private Data314AService data314AService;

	@Autowired
	private GrandJurySubpoenaService grandJurySubpoenaService;
	
	/**
	 * importExtUserRequest
	 * 
	 * @since 2017-11-30
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importExtUserRequest", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importExtUserRequest(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpSession session) throws Exception {
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
			// 取excel数据条数
			int totalLength = sheet.getPhysicalNumberOfRows();
			// 校验表头
			boolean flag = checkTheadByExtUserRequest(sheet.getRow(0));
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<ExtUserRequest> extUserRequestList = new ArrayList<>();
				// 从1开始 去掉表头
				for (int i = 1; i < totalLength ; i++) {
					ExtUserRequest extUserRequest = new ExtUserRequest();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					//External request ID
					Cell c0 = row.getCell(0);
					Cell c1 = row.getCell(1);
					//Requesting party
					Cell c2 = row.getCell(2);//Name
					Cell c3 = row.getCell(3);//Birth date
					Cell c4 = row.getCell(4);//ID number
					Cell c5 = row.getCell(5);//Address
					Cell c6 = row.getCell(6);//Bank Name
					Cell c7 = row.getCell(7);//Account Number
					Cell c8 = row.getCell(8);//AKA or DBA
					Cell c9 = row.getCell(9);//Internal BSA
					Cell c10 = row.getCell(10);//Customer Status
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'External request ID' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 12){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'External request ID' should not exceed 12");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Requesting party' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Requesting party' should not exceed 35");
							return result;
						}
					}

					if(c2 != null && c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Name' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 70){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'Name' should not exceed 70");
							return result;
						}
					}
					if(c3 == null ||c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Birth date' cannot be empty");
						return result;
					}
					
					if(c4 == null ||c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'ID number' cannot be empty");
						return result;
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Address' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 34){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'Address' should not exceed 34");
							return result;
						}
					}
					
					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Bank Name' cannot be empty");
						return result;
					}else{
						if(c6.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Bank Name' should not exceed 35");
							return result;
						}
					}
					
					if(c7 == null ||c7.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Account Number' cannot be empty");
						return result;
					}
					if(c8 == null || c8.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'AKA or DBA' cannot be empty");
						return result;
					}else{
						if(c8.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'AKA or DBA' should not exceed 35");
							return result;
						}
					}
					if(c9 == null || c9.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Internal BSA' cannot be empty");
						return result;
					}else{
						if(c9.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Internal BSA' should not exceed 35");
							return result;
						}
					}
					if(c10 == null || c10.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Customer Status' cannot be empty");
						return result;
					}else{
						if(c10.toString().trim().length() > 5){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Customer Status' should not exceed 5");
							return result;
						}
					}
					
					
					try {
						if(c3.getCellType() == 0 ){
							extUserRequest.setBirthdate(c3.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c3.toString(); 
							Date date=sdf.parse(dstr);
							extUserRequest.setBirthdate(date);
						}
						extUserRequest.setIdNum(Integer.parseInt(c4.toString()));
						extUserRequest.setAccountNumber(Integer.parseInt(c7.toString()));
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Account Number' and 'ID number' must be the time format");
						return result;
					}
					extUserRequest.setRequestingParty(c1.toString());
					extUserRequest.setName(c2.toString());
					extUserRequest.setAddress(c5.toString());
					extUserRequest.setBankName(c6.toString());
					extUserRequest.setAkaOrDba(c8.toString());
					extUserRequest.setInternalBsa(c9.toString());
					extUserRequest.setCustStatus(c10.toString());
					extUserRequestList.add(extUserRequest);
				}
				try {
					extUserRequestService.insertExtUserRequest(extUserRequestList);
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
	 * importData314A
	 * 
	 * @since 2017-11-30
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importData314A", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importData314A(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpSession session) throws Exception {
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
			boolean flag = checkTheadByData314A(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<Data314A> data314AList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					Data314A data314A = new Data314A();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//Tracking_number
					Cell c1 = row.getCell(1);//Subject type
					Cell c2 = row.getCell(2);//Name
					Cell c3 = row.getCell(3);//Number
					Cell c4 = row.getCell(4);//Number_type
					Cell c5 = row.getCell(5);//street
					Cell c6 = row.getCell(6);//City
					Cell c7 = row.getCell(7);//State
					Cell c8 = row.getCell(8);//Zip
					Cell c9 = row.getCell(9);//Country
					Cell c10 = row.getCell(10);//Internal BSA
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Tracking_number' cannot be empty");
						return result;
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Subject type' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Subject type' should not exceed 35");
							return result;
						}
					}

					if(c2 != null && c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Name' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 50){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'Name' should not exceed 50");
							return result;
						}
					}
					if(c3 == null ||c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Number' cannot be empty");
						return result;
					}
					
					if(c4 == null ||c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Number_type' cannot be empty");
						return result;
					}else{
						if(c4.toString().trim().length() > 50){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'Number_type' should not exceed 50");
							return result;
						}
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Address' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 34){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'street' should not exceed 34");
							return result;
						}
					}
					
					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'City' cannot be empty");
						return result;
					}else{
						if(c6.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'City' should not exceed 35");
							return result;
						}
					}
					
					if(c7 == null ||c7.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'State' cannot be empty");
						return result;
					}else{
						if(c7.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'State' should not exceed 35");
							return result;
						}
					}
					if(c8 == null || c8.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Zip' cannot be empty");
						return result;
					}else{
						if(c8.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Zip' should not exceed 35");
							return result;
						}
					}
					if(c9 == null || c9.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Country' cannot be empty");
						return result;
					}else{
						if(c9.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Country' should not exceed 35");
							return result;
						}
					}
					if(c10 == null || c10.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Internal BSA' cannot be empty");
						return result;
					}else{
						if(c10.toString().trim().length() > 5){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Internal BSA' should not exceed 5");
							return result;
						}
					}
					
					
					try {
							data314A.setTrackingNum(Integer.parseInt(c0.toString()));
							data314A.setNumber(Integer.parseInt(c3.toString()));
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Tracking_number' and 'Number' must be a number");
						return result;
					}
					data314A.setSubjectType(c1.toString());
					data314A.setName(c2.toString());
					data314A.setNumberType(c4.toString());
					data314A.setStreet(c5.toString());
					data314A.setCity(c6.toString());
					data314A.setState(c7.toString());
					data314A.setZip(c8.toString());
					data314A.setCountry(c9.toString());
					data314A.setInternalBsa(c10.toString());
					data314AList.add(data314A);
				}
				try {
					data314AService.insertData314A(data314AList);
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
	 * importGrandJurySubpoena
	 * 
	 * @since 2017-11-30
	 * @param file
	 * @param request
	 * @param response
	 * @return    
	 */
	@RequestMapping(value = "/importGrandJurySubpoena", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Void> importGrandJurySubpoena(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpSession session) throws Exception {
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
			boolean flag = checkTheadByGrandJurySubpoena(sheet.getRow(0));// 校验表头
			if (flag) {
				if(totalLength == 1){
					result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
					result.setErrorInfo("There is no data in the table");
					return result; 
				}
				List<GrandJurySubpoena> grandJurySubpoenaList = new ArrayList<>();
				for (int i = 1; i < totalLength ; i++) {// 从1开始 去掉表头
					GrandJurySubpoena grandJurySubpoena = new GrandJurySubpoena();
					Row row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					Cell c0 = row.getCell(0);//External request ID
					Cell c1 = row.getCell(1);//Requesting party
					Cell c2 = row.getCell(2);//Name
					Cell c3 = row.getCell(3);//Birth date
					Cell c4 = row.getCell(4);//ID number
					Cell c5 = row.getCell(5);//Address
					Cell c6 = row.getCell(6);//Bank Name
					Cell c7 = row.getCell(7);//Account Number
					Cell c8 = row.getCell(8);//AKA or DBA
					Cell c9 = row.getCell(9);//Internal BSA
					Cell c10 = row.getCell(10);//Customer Status
					
					if(c0 == null || c0.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'External request ID' cannot be empty");
						return result;
					}else{
						if(c0.toString().trim().length() > 12){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'External request ID' should not exceed 12");
							return result;
						}
					}
					if(c1 == null || c1.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Requesting party' cannot be empty");
						return result;
					}else{
						if(c1.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Requesting party' should not exceed 35");
							return result;
						}
					}

					if(c2 != null && c2.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Name' cannot be empty");
						return result;
					}else{
						if(c2.toString().trim().length() > 70){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'Name' should not exceed 70");
							return result;
						}
					}
					if(c3 == null ||c3.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'Birth date' cannot be empty");
						return result;
					}
					
					if(c4 == null ||c4.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'ID number' cannot be empty");
						return result;
					}
					
					if(c5 == null || c5.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Address' cannot be empty");
						return result;
					}else{
						if(c5.toString().trim().length() > 34){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" 'Address' should not exceed 34");
							return result;
						}
					}
					
					if(c6 == null || c6.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Bank Name' cannot be empty");
						return result;
					}else{
						if(c6.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Bank Name' should not exceed 35");
							return result;
						}
					}
					
					if(c7 == null ||c7.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Account Number' cannot be empty");
						return result;
					}
					if(c8 == null || c8.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'AKA or DBA' cannot be empty");
						return result;
					}else{
						if(c8.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'AKA or DBA' should not exceed 35");
							return result;
						}
					}
					if(c9 == null || c9.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Internal BSA' cannot be empty");
						return result;
					}else{
						if(c9.toString().trim().length() > 35){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Internal BSA' should not exceed 35");
							return result;
						}
					}
					if(c10 == null || c10.toString().trim().length() ==0){
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line 'Customer Status' cannot be empty");
						return result;
					}else{
						if(c10.toString().trim().length() > 5){
							result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
							result.setErrorInfo("The length of the "+ (i+1) +" line 'Customer Status' should not exceed 5");
							return result;
						}
					}
					
					
					try {
							
						if(c3.getCellType() == 0 ){
							grandJurySubpoena.setBirthdate(c3.getDateCellValue());
						}else{
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
							String dstr =c3.toString(); 
							Date date=sdf.parse(dstr);
							grandJurySubpoena.setBirthdate(date);
						}
							grandJurySubpoena.setIdNum(Integer.parseInt(c4.toString()));
							grandJurySubpoena.setAccountNum(Integer.parseInt(c7.toString()));
					} catch (Exception e) {
						result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
						result.setErrorInfo("The "+ (i+1) +" line  'ID number' and 'Account Number' must be a number");
						return result;
					}
					grandJurySubpoena.setExtRequestId(c0.toString());
					grandJurySubpoena.setRequestingParty(c1.toString());
					grandJurySubpoena.setName(c2.toString());
					grandJurySubpoena.setAddress(c5.toString());
					grandJurySubpoena.setBankName(c6.toString());
					grandJurySubpoena.setAkaOrDba(c8.toString());
					grandJurySubpoena.setInternalBsa(c9.toString());
					grandJurySubpoena.setCustStatus(c10.toString());
					grandJurySubpoenaList.add(grandJurySubpoena);
				}
				try {
					grandJurySubpoenaService.insertGrandJurySubpoena(grandJurySubpoenaList);
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
	 * Check header  ExtUserRequest
	 * @param row
	 * @return boolean
	 * @since 2017-11-30
	 */
	private boolean checkTheadByExtUserRequest(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("External request ID")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Requesting party")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Name")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Birth date")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("ID number")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Address")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Bank Name")) {
			return false;
		}	
		if (!row.getCell(7).toString().trim().equalsIgnoreCase("Account Number")) {
			return false;
		}
		if (!row.getCell(8).toString().trim().equalsIgnoreCase("AKA or DBA")) {
			return false;
		}
		if (!row.getCell(9).toString().trim().equalsIgnoreCase("Internal BSA")) {
			return false;
		}
		if (!row.getCell(10).toString().trim().equalsIgnoreCase("Customer Status")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check header  Data314A
	 * @param row
	 * @return boolean
	 * @since 2017-11-30
	 */
	private boolean checkTheadByData314A(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("Tracking_number")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Subject type")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Name")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Number")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("Number_type")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("street")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("City")) {
			return false;
		}	
		if (!row.getCell(7).toString().trim().equalsIgnoreCase("State")) {
			return false;
		}
		if (!row.getCell(8).toString().trim().equalsIgnoreCase("Zip")) {
			return false;
		}
		if (!row.getCell(9).toString().trim().equalsIgnoreCase("Country")) {
			return false;
		}
		if (!row.getCell(10).toString().trim().equalsIgnoreCase("Internal BSA")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check header GrandJurySubpoena
	 * @param row
	 * @return boolean
	 * @since 2017-11-30
	 */
	private boolean checkTheadByGrandJurySubpoena(Row row) {
		if (!row.getCell(0).toString().trim().equalsIgnoreCase("External request ID")) {
			return false;
		}
		if (!row.getCell(1).toString().trim().equalsIgnoreCase("Requesting party")) {
			return false;
		}
		if (!row.getCell(2).toString().trim().equalsIgnoreCase("Name")) {
			return false;
		}
		if (!row.getCell(3).toString().trim().equalsIgnoreCase("Birth date")) {
			return false;
		}
		if (!row.getCell(4).toString().trim().equalsIgnoreCase("ID number")) {
			return false;
		}
		if (!row.getCell(5).toString().trim().equalsIgnoreCase("Address")) {
			return false;
		}
		if (!row.getCell(6).toString().trim().equalsIgnoreCase("Bank Name")) {
			return false;
		}	
		if (!row.getCell(7).toString().trim().equalsIgnoreCase("Account Number")) {
			return false;
		}
		if (!row.getCell(8).toString().trim().equalsIgnoreCase("AKA or DBA")) {
			return false;
		}
		if (!row.getCell(9).toString().trim().equalsIgnoreCase("Internal BSA")) {
			return false;
		}
		if (!row.getCell(10).toString().trim().equalsIgnoreCase("Customer Status")) {
			return false;
		}
		return true;
	}
}
