package com.npl.global;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.npl.global.dao.notice.NoticeDao;
import com.npl.global.dao.notice.NoticeDaoExtend;
import com.npl.global.dao.settings.CompanyDao;
import com.npl.global.dao.settings.ProgramDao;
import com.npl.global.dao.settings.RoleDao;
import com.npl.global.dao.settings.UserMenuDao;
import com.npl.global.dao.user.UserDao;
import com.npl.global.dao.user.UserDaoExtend;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.notice.NoticeDto;
import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.entity.Company;
import com.npl.global.entity.Notice;
import com.npl.global.entity.Program;
import com.npl.global.entity.Role;
import com.npl.global.entity.User;
import com.npl.global.entity.UserMenu;
import com.npl.global.model.settings.ProgramModel;
import com.npl.global.model.settings.UserMenuModel;
import com.npl.global.service.settings.ProgramService;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class NplTest {

	@Autowired
	private RoleDao repo;
	
	@Autowired
	private CompanyDao repoCom;
	
	@Autowired
	private ProgramDao repoPrg;
	
	@Autowired
	private UserDao repoU;
	
	@Autowired
	private NoticeDao repoNo;
	
	@Autowired
	private NoticeDaoExtend repoNoExtent;
	
	@Autowired
	private UserDaoExtend repoUserExtent;
	
	@Autowired
	private UserMenuDao repoUM;
	
	@Autowired
	private ProgramService prgService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Admin", "Quản lý tất cả");
		Role saveRole = repo.save(roleAdmin);
		assertThat(saveRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		Role roleMember = new Role("Member", "Tài khoản của công ty");
		Role roleReview = new Role("Review", "Tài khoản Demo");

		repo.saveAll(List.of(roleMember, roleReview));

	}
	
	@Test
	public void testCreateCom() {
		Company com = new Company();
		com.setComId("LXL");
		com.setComCd("LXL");
		com.setTel("0368023380");
		com.setWorkDate(new Date());
		com.setComTaxNo("9725421");
		com.setAddr1("LXL building");
		com.setComOwner("LXL Technology");
		com.setComName("LXL Technology");
		com.seteMail("LXL@gmail.com");
		com.setWorkUser("LXL");
		com.setAddrMap("tphcm");
		repoCom.save(com);
	}
	
	
	@Test
	public void testCreatePrg() {
//		Program pg = new Program();
//		pg.setPrgCd("PRG00001");
//		pg.setDeleteYn("Y");
//		pg.setExpImpYn("Y");
//		pg.setMenuCd("LOGIN");
//		pg.setMenuName("Đăng nhập");
//		pg.setPrgKind("LOGIN");
//		pg.setUseYn("Y");
//		pg.setViewYn("Y");
//		pg.setLv(new BigDecimal("1"));
//		pg.setUpdateYn("Y");
//		Program saveCom = repoPrg.save(pg);
		
		Program pg1 = new Program();
		pg1.setPrgCd("PRG00006");
		pg1.setDeleteYn("Y");
		pg1.setExpImpYn("Y");
		pg1.setMenuCd("HOME");
		pg1.setMenuName("TRANG CHỦ");
		pg1.setPrgKind("HOME");
		pg1.setHelpUrl("/");
		pg1.setUseYn("Y");
		pg1.setViewYn("Y");
		pg1.setLv(new BigDecimal("1"));
		pg1.setUpdateYn("Y");
		Program saveCom1 = repoPrg.save(pg1);
	}
	
	@Test
	public void testCreateU() {
		String thispass = "npl123";
		
		Company company = repoCom.findByComId("LXL");
		Role roleAdmin = entityManager.find(Role.class, 2);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String pass = passwordEncoder.encode(thispass);
		String checkpass = repoU.encryptPass(thispass);
		
		User u = new User();
		u.setUserId(company.getComId() + "00003");
		u.addRole(roleAdmin);
		u.setUsername("lxltech");
		u.setPassword(pass);
		u.setCheckPass(checkpass);
		u.setEmail("lxltech@gmail.com");
		u.setEnabled(true);
		u.setCreatedTime(new Date());
		u.setUpdatedTime(new Date());
		u.setPhone("0368023380");
		u.setWorkUser("lxltech");
		u.setCompany(company);
		u.setDelYN("N");
		u.setFlag("5");
		u.setLogTime(new Date());
		
		repoU.save(u);
	}
	
	@Test
	public void testCreateUser() {
		String thispass = "npl123";
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String pass = passwordEncoder.encode(thispass);
		String checkpass = repoU.encryptPass(thispass);
		
		UserDto u = new UserDto();
		u.setRoleId(2);;
		u.setUserName("lxltech1");
		u.setPasswd(pass);
		u.setCheckPw(checkpass);
		u.setEmail("lxl@gmail.com");
		u.setImg("");
		u.setBirthDay("");
		u.setAddr("");
		u.setIdenCard("");
		u.setEnable(true);
		u.setPhone("0368023380");
		u.setWorkUser("lxltech");
		u.setComId("LXL");
		u.setDelYN("N");
		u.setEditYN("N");
		u.setUseYN("N");
		
		repoUserExtent.callSaveUser(u);
	}
	
	@Test
	public void testCreateNo() {
		Company company = repoCom.findByComId("NPL");
		Notice n = new Notice();
		n.setNtId("NT00001");
		n.setCompany(company);
		n.setTitle("Đây là tin thử nghiệm 1");
		n.setContent("test");
		n.setStartDate(new Date());
		n.setEnabled("Y");
		n.setEndDate(new Date());
		n.setWorkUser("NPL");
		
		repoNo.save(n);
	}
	@Test
	public void testCallDao() {
//		Company company = repoCom.findByComId(2);
//		System.out.println("++++++++++++++++++++++++++    " +company.getComCd());
//		
//		User user = repoU.getUserByUserName("npltech1");
//		System.out.println("===========================      " + user.getUsername() + "Email:     " + user.getEmail());
//		
//		Program program = repoPrg.getPrgByPrgCd(5);
//		System.out.println(")))))))))))))))))))))))))))    " + program.getHelpUrl() + "    Name:     " + program.getMenuName());
//		
//		List<ProgramModel> listPrg = repoPrg.getListPrograms();
//		System.out.println("********************    " + listPrg);
		
		
		String pass1 = repoU.encryptPass("npl123");
		System.out.println("---------------------------------------          :       " + pass1);
	}
	
	
	@Test
	public void testCreateUserMenus() {
		Company company = repoCom.findByComId("NVN");
		UserMenu menu = new UserMenu();
		menu.setMenuId("MN00005");
		menu.setCompany(company);
		menu.setCreatedTime(new Date());
		menu.setDeleteYn("Y");
		menu.setInsertYn("Y");
		menu.setInsertYn("Y");
		menu.setPrintYn("Y");
		menu.setUpdateYn("Y");
		menu.setExpImpYn("Y");
		menu.setWorkUser("nghia");
		
		User user = repoU.getUserByUserName("nvntech");
		menu.setUser(user);
		
		Program program = repoPrg.getPrgByPrgCd("PRG00004");
		menu.setProgram(program);
		
		UserMenu save = repoUM.save(menu);
		
	}
	
	@Test
	public void testControllerAddUpd() {
		NoticeDto n = new NoticeDto();
//		n.setId("NT00005"); //truyen id neu update
		n.setTitle("test update title 1");
		n.setContent("test 123");
		n.setsDate("2023-08-08");
		n.setEnable("Y");
		n.setPopup("Y");
		n.seteDate("2023-08-08");
		n.setComId("LXL");
		n.setWokUser("loi");
		
		repoNoExtent.callNoticeAdd(n);
	}
	
	@Test
	public void testControllerDel() throws Exception {
//		NoticeDto n = new NoticeDto();
//		n.setId("NT00004");
		
		String id = "NT00004";
		
		ResultProcDto result = repoNoExtent.callNoticeDel(id);
		System.out.println("========================== : " + result.getRetStr());
		System.out.println("========================== : " + result.getRetCode());
	}
	@Test
	public void testlistPRG() throws Exception {
//		NoticeDto n = new NoticeDto();
//		n.setId("NT00004");
		
		String id = "NVN00002";
		
		List<ProgramDto> dtos = prgService.findMenuByUser(id);
		System.out.println("========================== : " + dtos);
	}
}
