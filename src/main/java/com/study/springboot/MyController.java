package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.ISimpleBbsDao;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {

	
	
    @Autowired
    ISimpleBbsDao dao;

    @RequestMapping("/")
    public String root() throws Exception{
        // JdbcTemplate : SimpleBBS
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String userlistPage(Model model) {
    	System.out.println(dao.listDao());
        model.addAttribute("list", dao.listDao());
        return "list";
    }

    @RequestMapping("/view")
    public String view(HttpServletRequest request, Model model) {
        String sId = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(sId));
        return "view";
    }
    
    @RequestMapping("/writeForm")
    public String writeForm() {
        
        return "writeForm";
    }
    
    @RequestMapping("/write")
    public String write(Model model, HttpServletRequest request) {
        dao.writeDao(request.getParameter("writer"),
                     request.getParameter("title"),
                     request.getParameter("content"));

        // 리스트 페이지로 리다이렉트
        return "redirect:list";
    }
    
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        dao.deleteDao(request.getParameter("id"));
        return "redirect:list";
    }
    

    @RequestMapping("/writeForm2")
    public String correctionForm(HttpServletRequest request, Model model) {
        String sId = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(sId));
        return "writeForm2";
    }
    
    @RequestMapping("/update")
    public String update(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // updateDao 메서드를 사용하여 데이터베이스 업데이트
        dao.updateDao(id, writer, title, content);

        // 수정이 완료되면 리스트 페이지로 리다이렉트
        return "redirect:list";
    }
}
