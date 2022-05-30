package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

@Controller
public class EmpController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	@Autowired
	private EmpService es;
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "list")
	public String list(Emp emp, String currentPage, Model model) {
		logger.info("EmpController Start list...");
		int total = es.total();  // Emp Count -> 18
		
		System.out.println("EmpController total->" + total);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());  // 시작시 1
		emp.setEnd(pg.getEnd());   // 시작시 10
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()->"+listEmp.size());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "list";
	}
	
	// keyword(조건) 조회
	@RequestMapping(value = "listKeyword")
	public String listKeyword(Emp emp, String currentPage, Model model) {
		logger.info("EmpController Start listKeyword(조건) 조회...");
		
		//Keyword에 맞는 Count 도출
		int total = es.totalKeyword(emp);
		
		System.out.println("EmpController total->" + total);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());  // 시작시 1
		emp.setEnd(pg.getEnd());   // 시작시 10
		List<Emp> listEmpKeyword = es.listEmpKeyword(emp);
		System.out.println("EmpController list listEmp.size()->"+listEmpKeyword.size());
		model.addAttribute("listEmp", listEmpKeyword);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		model.addAttribute("keyword", emp.getKeyword());
		
		return "listKeyword";
	}
	
	@GetMapping(value = "detail")
	public String detail(int empno, Model model) {
		//Emp emp = es.detail(emp)
		//Dao -> 	
		//mapper --> tkEmpSelOne, empno
		System.out.println("EmpController Start detail...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "detail";
	}
	
	@GetMapping(value = "updateForm")
	public String updateForm(int empno, Model model) {
		System.out.println("EmpController start updateForm...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "updateForm";
	}
	
	@PostMapping(value = "update")
	public String update(Emp emp, Model model) {
		int uptCnt = es.update(emp);
		// int kkk = es.update(emp);
		// Dao -> ed.update(emp);
		// mapper --> tkEmpUpdate, emp
		System.out.println("Controller es.update(emp) Count-->"+uptCnt);
		model.addAttribute("uptCnt", uptCnt);		// Test Controller간 Data 전달
		model.addAttribute("kk3", "Message Test");	// Test Controller간 Data 전달
		//return "redirect:list";
		return "forward:list";
	}
	
	@RequestMapping(value = "writeForm")
	public String writeForm(Model model) {
		//Emp emp = null;
		//관리자 사번만 get
		List<Emp> empList = es.listManager();
		System.out.println("EmpController writeForm empList.size()"+empList.size());
		model.addAttribute("empMngList", empList);  //emp Manager List
		//부서(코드, 부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);  //dept
		return "writeForm";
	}
	
	
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(Emp emp, Model model) {
		System.out.println("EmpController start write...");
//		System.out.println("emp.getHiredate->"+emp.getHiredate());
//		Service, Dao, Mapper명[insertEmp]까지 -> insert
		int result = es.insert(emp);
		if(result>0) return "redirect:list";
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	}
	
	@GetMapping(value = "confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if(emp != null) {
			model.addAttribute("msg", "중복된 사번입니다");
			return "forward:writeForm";
		}else {
			model.addAttribute("msg", "사용 가능한 사번입니다");
			return "forward:writeForm";
		}
	}
	
	@RequestMapping(value = "delete")
	public String delete(int empno, Model model) {
		//service
		//dao
		//mapper
		int result = es.delete(empno);
		return "redirect:list";
	}
	
	// join 조회
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		EmpDept empDept = null;
		System.out.println("EmpController listEmpDept start...");
		//Service, Dao -> listEmpDept
		//Mapper만 -> TKlistEmpDept  --> EmpDept.xml
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept", listEmpDept);
		return "listEmpDept";
	}
	

//	@RequestMapping(value = "mailTransport")
//	public String mailTransport(HttpServletRequest request, Model model) {
//		System.out.println("mailSending...");
//		String tomail = "ljj870903@hanmail.net";	// 받는 사람 이메일
//		System.out.println(tomail);
//		String setfrom = "ljj870903@gmail.com";		// 보내는 사람 이메일
//		String title = "황재환조장바보";		// 제목
//		try {
//			// Mime 전자우편 Internet 표준 format
//			MimeMessage message = mailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//			messageHelper.setFrom(setfrom);		// 보내는 사람 생략하거나 하면 정상작동을 안함
//			messageHelper.setTo(tomail);		// 받는 사람 이메일
//			messageHelper.setSubject(title);	// 메일제목은 생략이 가능하다
//			String tempPassword = (int) (Math.random()*999999)+1+"";
//			messageHelper.setText("임시 비밀번호 입니다 : "+tempPassword);  // 메일 내용
//			System.out.println("임시 비밀번호 입니다 : "+tempPassword);
//			DataSource dataSource = new FileDataSource("c:\\log\\8.jpg");
//																					// B는 Base64
//			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
//			mailSender.send(message);
//			model.addAttribute("check", 1);		// 정상 전달
//			// 임시 비번 저장 로직 Service --> DAO --> mapper
//			// member.tempPassword
////			s.tempPw(u_id, tempPassword);  // db에 비밀번호를 임시비밀번호로 업데이트
//		} catch (Exception e) {
//			System.out.println(e);
//			model.addAttribute("check", 2);		// 메일 전달 실패
//		}
//		return "mailResult";
//	}
	@RequestMapping(value = "mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom("ljj870903@gmail.com");
			messageHelper.setTo("ljj870903@hanmail.net");
			messageHelper.setSubject("안녕하세유");
			messageHelper.setText("방가워유~~");
			DataSource ds = new FileDataSource("c:\\log\\8.jpg");
			messageHelper.addAttachment(MimeUtility.encodeText("사진.png", "UTF-8", "B"), ds);
			mailSender.send(message);
			model.addAttribute("check", 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("check", 2);
		}
		return "mailResult";
	}
	
	
	//Procedure Test 입력화면
	@RequestMapping(value = "writeDeptIn", method = RequestMethod.GET)
	public String writeDeptIn(Model model) {
		System.out.println("writeDeptIn start...");
		return "writeDept3";
	}
	
	// Procedure Test 입력 후 VO 전달
	@PostMapping(value = "writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		// Procedure Call
		es.insertDept(deptVO);
		if(deptVO==null) {
			System.out.println("deptVO NULL");
		}else {
			System.out.println("RdeptVO.getOdeptno()->"+deptVO.getOdeptno());
			System.out.println("RdeptVO.getOdname()->"+deptVO.getOdname());
			System.out.println("RdeptVO.getOloc()->"+deptVO.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
	@GetMapping(value = "writeDeptCursor")
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController writeDeptCursor start...");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 55);
		es.selListDept(map);
		@SuppressWarnings("unchecked")
		List<Dept> deptLists = (List<Dept>) map.get("dept");
		for(Dept dept : deptLists) {
			System.out.println("dept.getDname->"+dept.getDname());
			System.out.println("dept.getLoc->"+dept.getLoc());
		}
		System.out.println("deptLists Size->"+deptLists.size());
		model.addAttribute("deptList", deptLists);
		
		return "writeDeptCursor";
	}
	
	// Ajax List Test
	@RequestMapping(value = "listEmpAjax")
	public String listEmpAjax(Model model) {
		EmpDept empDept=null;
		System.out.println("Ajax List Test Start");
		List<EmpDept> listEmp = es.listEmp(empDept);
		System.out.println("EmpController listEmpAjax listEmp.size()->"+listEmp.size());
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjax";
	}
	
	// Ajax Sample1
	@ResponseBody
	@RequestMapping(value = "getDeptName", produces = "application/text;charset=UTF-8") // 한글코딩
	public String getDeptName(int deptno, Model model) {
		System.out.println("deptno->"+deptno);
		String deptName = es.deptName(deptno);
		System.out.println("EmpController getDeptName deptName->"+deptName);
		return deptName;
	}
	
	// Ajax List Test
	@RequestMapping(value = "listEmpAjax2")
	public String listEmpAjax2(Model model) {
		EmpDept empDept = null;
		System.out.println("listEmpAjax2 start");
		List<EmpDept> listEmp = es.listEmp(empDept);
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjax2";
	}
	
	// interCepter 시작 화면
	@RequestMapping(value = "interCeptorForm")
	public String interCeptorForm(Model model) {
		System.out.println("interCeptorForm start");
		return "interCeptorForm";
	}
	
	// interCeptor 진행 Test 2
	@RequestMapping(value = "interCeptor")
	public String interCeptor(String id, Model model) {
		System.out.println("interCeptor Test Start");
		System.out.println("interCeptor id->"+id);
		// 존재 : 1 , 비존재 : 0
		int memCnt = es.memCount(id);
		System.out.println("memCnt->"+memCnt);
		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		System.out.println("interCeptor Test End");

		return "interCeptor";    // User 존재하면 User 이용 조회 Page
	}
	
	// SampleInterceptor 내용을 받아 처리
	@RequestMapping(value = "doMemberWrite")
	public String doMemberWrite(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberWrite 부터 하세요");
//		model.addAttribute("ID", ID);
		return "doMemberWrite";
	}
	
	// InterCeptor 진행 Test
	@RequestMapping(value = "doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberList Test Start ID->"+ID);
		Member1 member1 = null;
		// Member3 List Get Service
		List<Member1> listMem = es.listMem(member1);
		model.addAttribute("ID", ID);
		model.addAttribute("listMem", listMem);
		return "doMemberList";     // User 존재하면 User 이용 조회 Page
	}
}








