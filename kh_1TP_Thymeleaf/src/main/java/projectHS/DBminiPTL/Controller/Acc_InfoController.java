package projectHS.DBminiPTL.Controller;

import projectHS.DBminiPTL.DAO.Acc_InfoDAO;
import projectHS.DBminiPTL.VO.Acc_InfoVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/acc_info")

public class Acc_InfoController {

    @GetMapping("/select")
    public String selectAccInfo(Model model) {
        Acc_InfoDAO aiDAO = new Acc_InfoDAO();
        List<Acc_InfoVO> aiVO = aiDAO.Acc_InfoSelect();
        model.addAttribute("accountInfo", aiVO);
        return "thymeleaf/accInfoSelect";
    }

    @GetMapping("/insert")
    public String insertViewAccInfo(Model model) {
        model.addAttribute("accountInfo", new Acc_InfoVO());
        return "thymeleaf/accInfoInsert";
    }

    @PostMapping("/insert")
    public String insertDBAccInfo(@ModelAttribute("accountInfo") Acc_InfoVO accInfoVO) {
        Acc_InfoDAO aiDAO = new Acc_InfoDAO();
        aiDAO.Acc_InfoInsert();
        return "thymeleaf/accInfoInsertRst";
    }

}
