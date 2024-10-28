package projectHS.DBminiPTL.DAO;

import com.sun.tools.javac.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import projectHS.DBminiPTL.Common.Common;
import projectHS.DBminiPTL.DBminiPtlApplication;
import projectHS.DBminiPTL.VO.Inv_OrderVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Repository
@Slf4j

public class Inv_OrderDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public Inv_OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    boolean isSuccess = false;


    // 메뉴 확인 (Select)
    public List<Inv_OrderVO> Inv_OrderSelect() {

        String query = "SELECT * FROM INV_ORDER";
        return jdbcTemplate.query(query, new Inv_OrderRowMapper());

    }

    private static class Inv_OrderRowMapper implements RowMapper<Inv_OrderVO> {

        @Override
        public Inv_OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Inv_OrderVO(
                    rs.getString("MENU_NAME"),
                    rs.getInt("PRICE"),
                    rs.getString("CATEGORY"),
                    rs.getString("DESCR")
            );
        }
    }

    public boolean Inv_OrderInsert(Inv_OrderVO ioVO) {
        int result = 0;
        String query = "INSERT INTO INV_ORDER(MENU_NAME, PRICE, CATEGORY, DESCR) VALUES (?, ?, ?, ?)";

        try {
            result = jdbcTemplate.update(query, ioVO.getMenuName(), ioVO.getPrice(), ioVO.getCategory(), ioVO.getDescr());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }

    public boolean Inv_OrderUpdate(Inv_OrderVO ioVO) {
        String query = "UPDATE INV_ORDER SET PRICE = ?, CATEGORY = ?, DESCR = ? WHERE MENU_NAME = ?";
        int result = jdbcTemplate.update(query, ioVO.getPrice(), ioVO.getDescr(), ioVO.getMenuName());
        return result > 0;
    }

    public boolean Inv_OrderDelete(String menuName) {
        String query = "DELETE FROM INV_ORDER WHERE MENU_NAME = ?";
        int result = 0;
        try {
            result = jdbcTemplate.update(query, menuName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }




}