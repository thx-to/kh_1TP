package projectHS.DBminiPTL.DAO;

import com.sun.tools.javac.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return jdbcTemplate.query(query, new ioVOSelectRowMapper());

    }

    private static class ioVOSelectRowMapper implements RowMapper<Inv_OrderVO> {

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

    public void Inv_OrderUpdate(Inv_OrderVO ioVO) {
        String query = "UPDATE INV_ORDER SET PRICE = ?, CATEGORY = ?, DESCR = ? WHERE MENU_NAME = ?";
        jdbcTemplate.update(query, ioVO.getPrice(), ioVO.getCategory(), ioVO.getDescr(), ioVO.getMenuName());
    }

    public void Inv_OrderDelete(String menuName) {
        String query = "DELETE FRON INV_ORDER WHERE MENU_NAME = ?";
        jdbcTemplate.update(query, menuName);
    }

    /*
    public boolean Inv_OrderUpdate(Inv_OrderVO ioVO) {
        String query = "UPDATE INV_ORDER SET PRICE = ?, CATEGORY = ?, DESCR = ? WHERE MENU_NAME = ?";
        int result = jdbcTemplate.update(query, ioVO.getPrice(), ioVO.getCategory(), ioVO.getDescr(), ioVO.getMenuName());
        return result > 0;
    }

    // 메뉴 이름으로 메뉴 정보를 조회하는 메소드
    public Inv_OrderVO getMenuByName(String menuName) {

        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM INV_ORDER WHERE MENU_NAME = ?",
                    new Object[]{menuName},
                    new BeanPropertyRowMapper<>(Inv_OrderVO.class)
            );

        } catch (Exception e) {
            return null;
        }
    }


        String sql = "SELECT * FROM INV_ORDER WHERE MENU_NAME = ?"; // SQL 쿼리
        return jdbcTemplate.queryForObject(sql, new Object[]{menuName}, new BeanPropertyRowMapper<>(Inv_OrderVO.class));
        // 메뉴 이름을 기반으로 데이터베이스에서 정보를 조회하여 Inv_OrderVO 객체로 반환


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

    public List<Inv_OrderVO> getAllInventory() {
        String query = "SELECT * FROM INV_ORDER";
        Inv_OrderVO ioVO = new Inv_OrderVO();
        return jdbcTemplate.query(query, new MenuRowMapper());
    }

    public class MenuRowMapper implements RowMapper<Inv_OrderVO> {
        @Override
        public Inv_OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inv_OrderVO ioVO = new Inv_OrderVO();
            ioVO.setMenuName(rs.getString("MENU_NAME"));
            ioVO.setPrice(rs.getInt("PRICE"));
            ioVO.setCategory(rs.getString("CATEGORY"));
            ioVO.setDescr(rs.getString("DESCR"));
            return ioVO;
        }
    }
*/
}