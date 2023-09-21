package com.npl.global.controller.product;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.brand.BrandDto;
import com.npl.global.dto.product.PdtDetailDto;
import com.npl.global.dto.product.PdtDto;
import com.npl.global.dto.product.PdtImageDto;
import com.npl.global.model.product.ProductModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.product.ProductService;
import com.npl.global.service.system.StorageService;

@RestController
public class ProductRestController extends BaseProductController{
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired private ProductService service;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/2030/list")
	public  @ResponseBody List<ProductModel> getAll(@RequestParam(name = "comId1", required = false) String comId1 ) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<ProductModel> listAll = service.findAll(comId1 == null ? comId : comId1 );
			return listAll;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/2030/loadImg/{pdtId}")
	public  @ResponseBody List<ProductModel> loadImage(@PathVariable(name = "pdtId") String pdtId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<ProductModel> listAll = service.findImgExtra(pdtId, comId);
			return listAll;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/2030/save")
	public @ResponseBody ResultProcDto save(@ModelAttribute PdtDto pdtDto) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			ResultProcDto result;

			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			pdtDto.setComId(comId);
			pdtDto.setWorkUser(workUser);
			
			
			if(!pdtDto.getFileMainImage().isEmpty()) {
				if(!pdtDto.getPdtId().isEmpty()) {
					storageService.delete(service.findInfo(pdtDto.getPdtId(), comId).getFileName(), "pdt");
				}
				String fileName = storageService.store(pdtDto.getFileMainImage(), "pdt");
				
				pdtDto.setFilePath("fileupload/product/" + fileName);
				pdtDto.setFileName(fileName);
				pdtDto.setFileNameOrg(fileName);
				
			}else {
				pdtDto.setFilePath("");
				pdtDto.setFileName("");
				pdtDto.setFileNameOrg("");
			}
			
			result = this.service.savePdt(pdtDto);
			
			if(pdtDto.getPdtId().isEmpty()) {
				if(pdtDto.getFileExtraImage().length > 0) {
					int imgNo = 0;
					for (MultipartFile multipartFile : pdtDto.getFileExtraImage()) {
						if (!multipartFile.isEmpty()) {
							String fileName = storageService.store(multipartFile, "pdtExtra");
							pdtDto.setFilePath("fileupload/product/extra/" + fileName);
							pdtDto.setFileName(fileName);
							pdtDto.setFileNameOrg(fileName);
							pdtDto.setSortNo(imgNo);
							pdtDto.setPdtId(result.getKeyValue());
							
							ResultProcDto result1 = this.service.savePdtImage(pdtDto);
							if(!result1.getRetCode().equals(Constant.RETCODE_OK)) {
								return result1;
							}
						}
						imgNo++;
					}
				}
			}else {
				saveImg(pdtDto);
			}
			
			if(pdtDto.getPdtDetails().length > 0) {
				for (PdtDetailDto pdtDetailDto : pdtDto.getPdtDetails()) {
					pdtDetailDto.setComId(comId);
					pdtDetailDto.setWorkUser(workUser);
					pdtDetailDto.setPdtId(result.getKeyValue());
					ResultProcDto result2 = this.service.savePdtDetail(pdtDetailDto);
					if(!result2.getRetCode().equals(Constant.RETCODE_OK)) {
						return result2;
					}
				}
			}
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	public ResultProcDto saveImg( PdtDto pdt) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		
		ResultProcDto result = null;

		String comId = loggedUser.getUser().getCompany().getComId();
		String workUser = loggedUser.getUser().getWorkUser();
		
		pdt.setComId(comId);
		pdt.setWorkUser(workUser);
		
		/*                LARGE                */
		if (!pdt.getFileLarge0().isEmpty() && pdt.getDeletedImgLarge0() == 0) {
			MultipartFile file = pdt.getFileLarge0();

			String fileName = storageService.store(file, "pdtExtra");

			pdt.setFilePathLarge0("fileupload/product/extra/" + fileName);
			pdt.setFileNameLarge0(fileName);
			pdt.setFileNameOrgLarge0(fileName);

		} else if (pdt.getDeletedImgLarge0() == 1 && pdt.getFileNameLarge0().equals("")) {
			storageService.delete(this.service.findImgExtra1(pdt.getPdtId(), 0, comId).getFileName(), "pdtExtra");
			pdt.setFilePathLarge0("");
			pdt.setFileNameLarge0("");
			pdt.setFileNameOrgLarge0("");
		}

		pdt.setFilePath(pdt.getFilePathLarge0());
		pdt.setFileName(pdt.getFileNameLarge0());
		pdt.setFileNameOrg(pdt.getFileNameOrgLarge0());
		pdt.setWorkUser(workUser);
		pdt.setImageKind("LARGE");
		pdt.setSortNo(0);
		pdt.setRegNo(pdt.getRegNoLarge0());

		result = this.service.savePdtImage(pdt);

		if (!pdt.getFileLarge1().isEmpty() && pdt.getDeletedImgLarge1() == 0) {
			MultipartFile file = pdt.getFileLarge1();

			String fileName = storageService.store(file, "pdtExtra");

			pdt.setFilePathLarge1("fileupload/product/extra/" + fileName);
			pdt.setFileNameLarge1(fileName);
			pdt.setFileNameOrgLarge1(fileName);

		} else if (pdt.getDeletedImgLarge1() == 1 && pdt.getFileNameLarge1().equals("")) {
			storageService.delete(this.service.findImgExtra1(pdt.getPdtId(), 1, comId).getFileName(), "pdtExtra");
			pdt.setFilePathLarge1("");
			pdt.setFileNameLarge1("");
			pdt.setFileNameOrgLarge1("");
		}

		pdt.setFilePath(pdt.getFilePathLarge1());
		pdt.setFileName(pdt.getFileNameLarge1());
		pdt.setFileNameOrg(pdt.getFileNameOrgLarge1());
		pdt.setWorkUser(workUser);
		pdt.setImageKind("LARGE");
		pdt.setSortNo(1);
		pdt.setRegNo(pdt.getRegNoLarge1());

		result = this.service.savePdtImage(pdt);

		if (!pdt.getFileLarge2().isEmpty() && pdt.getDeletedImgLarge2() == 0) {
			MultipartFile file = pdt.getFileLarge2();

			String fileName = storageService.store(file, "pdtExtra");

			pdt.setFilePathLarge2("fileupload/product/extra/" + fileName);
			pdt.setFileNameLarge2(fileName);
			pdt.setFileNameOrgLarge2(fileName);

		} else if (pdt.getDeletedImgLarge2() == 1 && pdt.getFileNameLarge2().equals("")) {
			storageService.delete(this.service.findImgExtra1(pdt.getPdtId(), 2, comId).getFileName(), "pdtExtra");
			pdt.setFilePathLarge2("");
			pdt.setFileNameLarge2("");
			pdt.setFileNameOrgLarge2("");
		}

		pdt.setFilePath(pdt.getFilePathLarge2());
		pdt.setFileName(pdt.getFileNameLarge2());
		pdt.setFileNameOrg(pdt.getFileNameOrgLarge2());
		pdt.setWorkUser(workUser);
		pdt.setImageKind("LARGE");
		pdt.setSortNo(2);
		pdt.setRegNo(pdt.getRegNoLarge2());

		result = this.service.savePdtImage(pdt);

		if (!pdt.getFileLarge3().isEmpty() && pdt.getDeletedImgLarge3() == 0) {
			MultipartFile file = pdt.getFileLarge3();

			String fileName = storageService.store(file, "pdtExtra");

			pdt.setFilePathLarge3("fileupload/product/extra/" + fileName);
			pdt.setFileNameLarge3(fileName);
			pdt.setFileNameOrgLarge3(fileName);

		} else if (pdt.getDeletedImgLarge3() == 1 && pdt.getFileNameLarge3().equals("")) {
			storageService.delete(this.service.findImgExtra1(pdt.getPdtId(), 3, comId).getFileName(), "pdtExtra");
			pdt.setFilePathLarge3("");
			pdt.setFileNameLarge3("");
			pdt.setFileNameOrgLarge3("");
		}

		pdt.setFilePath(pdt.getFilePathLarge3());
		pdt.setFileName(pdt.getFileNameLarge3());
		pdt.setFileNameOrg(pdt.getFileNameOrgLarge3());
		pdt.setWorkUser(workUser);
		pdt.setImageKind("LARGE");
		pdt.setSortNo(3);
		pdt.setRegNo(pdt.getRegNoLarge3());

		result = this.service.savePdtImage(pdt);

		if (!pdt.getFileLarge4().isEmpty() && pdt.getDeletedImgLarge4() == 0) {
			MultipartFile file = pdt.getFileLarge4();

			String fileName = storageService.store(file, "pdtExtra");

			pdt.setFilePathLarge4("fileupload/product/extra/" + fileName);
			pdt.setFileNameLarge4(fileName);
			pdt.setFileNameOrgLarge4(fileName);

		} else if (pdt.getDeletedImgLarge4() == 1 && pdt.getFileNameLarge4().equals("")) {
			storageService.delete(this.service.findImgExtra1(pdt.getPdtId(), 4, comId).getFileName(), "pdtExtra");
			pdt.setFilePathLarge4("");
			pdt.setFileNameLarge4("");
			pdt.setFileNameOrgLarge4("");
		}

		pdt.setFilePath(pdt.getFilePathLarge4());
		pdt.setFileName(pdt.getFileNameLarge4());
		pdt.setFileNameOrg(pdt.getFileNameOrgLarge4());
		pdt.setWorkUser(workUser);
		pdt.setImageKind("LARGE");
		pdt.setSortNo(4);
		pdt.setRegNo(pdt.getRegNoLarge4());
		
		result = this.service.savePdtImage(pdt);
		
		/*            END    LARGE                */
		return result;
	}
	
	@GetMapping("/2030/{pdtId}")
	public  @ResponseBody ProductModel getInfo(@PathVariable(name = "pdtId") String pdtId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			ProductModel pdtInfo = service.findInfo(pdtId, comId);
			return pdtInfo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/2030/delete/{pdtId}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "pdtId") String pdtId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			
			List<ProductModel> listImg = this.service.findImgExtra(pdtId, comId);
			String fileName = this.service.findInfo(pdtId, comId).getFileName();
			
			storageService.delete(fileName, "pdt");
			for (ProductModel imgExtra : listImg) {
				storageService.delete(imgExtra.getFileName(), "pdtExtra");
			}
			
			ResultProcDto result = this.service.delPdt(pdtId);
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/2030/delDetail/{pdtId}/{sortNo}")
	public  @ResponseBody ResultProcDto  delDetail( @PathVariable(name = "pdtId") String pdtId, @PathVariable(name = "sortNo") int sortNo) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			
			ResultProcDto result = this.service.delDetail(pdtId, sortNo, comId);
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}
