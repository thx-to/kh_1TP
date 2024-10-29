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
@RequestMapping("/main/hq")

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

    // 메뉴 추가 요청 처리
    @GetMapping("/hqInsert")
    // View로 모델을 넘겨주는 객체
    public String ioInsertView(Model model) {
        // 빈 객체를 넘겨줌
        model.addAttribute("invMenuList", new Inv_OrderVO());
        return "thymeleaf/hqInsert";
    }

    // 메뉴 추가 메소드
    @PostMapping("/hqInsert")
    public String ioInsertDB(@ModelAttribute("invMenuList") Inv_OrderVO ioVO, Model model) {
        boolean isSuccess = ioDAO.Inv_OrderInsert(ioVO);
        model.addAttribute("isSuccess", isSuccess);
        return "thymeleaf/hqInsertRst";
    }

    // 메뉴 수정 요청 처리
    @GetMapping("/hqUpdate")
    public String ioUpdateView(Model model) {
        model.addAttribute("invMenuList", new Inv_OrderVO());
        return "thymeleaf/hqUpdate";
    }

    // 수정을 원하는 메뉴 찾기
    @PostMapping("/hqUpdate/findMenu")
    public String findMenu(@ModelAttribute("menuName") String menuName, Model model) {
        // DAO 메소드 호출
        Inv_OrderVO ioVO = ioDAO.Inv_OrderUpdateByName(menuName);
        if (ioVO != null) {
            // 메뉴가 없지 않다면 검색된 메뉴 정보를 모델에 추가
            model.addAttribute("invMenuList", ioVO);
            return "thymeleaf/hqUpdate";
        } else {
            model.addAttribute("error", "해당 메뉴를 찾을 수 없습니다.");
            return "thymeleaf/hqUpdate";
        }
    }

    // 메뉴 수정 메소드
    @PostMapping("/hqUpdate")
    public String ioUpdateDB(@ModelAttribute("invMenuList") Inv_OrderVO ioVO, Model model) {
        boolean isSuccess = ioDAO.Inv_OrderUpdate(ioVO);
        model.addAttribute("isSucceess", isSuccess);
        return "thymeleaf/hqUpdateRst";
    }







}