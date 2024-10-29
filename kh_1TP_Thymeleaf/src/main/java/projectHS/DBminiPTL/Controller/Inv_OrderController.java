package projectHS.DBminiPTL.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import projectHS.DBminiPTL.DAO.Inv_OrderDAO;
import projectHS.DBminiPTL.VO.Inv_OrderVO;

import java.util.List;

@Controller
@RequestMapping("/inv_order")

public class Inv_OrderController {

    // Inv_OrderDAO 주입
    @Autowired
    private Inv_OrderDAO ioDAO;

    // 메뉴 확인 처리
    @GetMapping("/hqSelect")
    public String ioSelectView(Model model) {
        List<Inv_OrderVO> ioList = ioDAO.Inv_OrderSelect();
        model.addAttribute("invMenuList", ioList);
        return "thymeleaf/hqSelect";
    }

    // 메뉴 추가
    @GetMapping("/hqInsert")
    // View로 모델을 넘겨주는 객체
    public String ioInsertView(Model model) {
        // 빈 객체를 넘겨줌
        model.addAttribute("invMenuList", new Inv_OrderVO());
        return "thymeleaf/hqInsert";
    }

    @PostMapping("hqInsert")
    public String ioInsertDB(@ModelAttribute("invMenuList") Inv_OrderVO ioVO) {
        Inv_OrderDAO ioDAO = new Inv_OrderDAO();
        ioDAO.Inv_OrderInsert(ioVO);
        return "thymeleaf/hqInsertRst"
    }





}