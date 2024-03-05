package com.study.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.study.springboot.dto.SimpleBbsDto;

@Repository
public class SimpleBbsDao implements ISimpleBbsDao {

    @Autowired
    JdbcTemplate template;
    
    @Override
    public List<SimpleBbsDto> listDao() {
        System.out.println("listDao()");

        String query = "select * from simple_bbs order by id desc";
        List<SimpleBbsDto> dtos = template.query(
                query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
        
        return dtos;
    }

    @Override
    public SimpleBbsDto viewDao(String id) {
        System.out.println("viewDao()");
        
        String query = "select * from simple_bbs where id = " + id;
        SimpleBbsDto dto = template.queryForObject(
                query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
        return dto;
    }

    @Override
    public int writeDao(final String writer, final String title, final String content) {
        System.out.println("writeDao()");
        
        // SimpleBbsDto 생성
        SimpleBbsDto simpleBbsDto = new SimpleBbsDto();
        simpleBbsDto.setWriter(writer);
        simpleBbsDto.setTitle(title);
        simpleBbsDto.setContent(content);

        // 날짜 자동 설정
        simpleBbsDto.setCreatedAt(LocalDateTime.now().toString());
        
     // DateTimeFormatter를 사용하여 LocalDateTime을 문자열로 변환
     //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     //   String formattedDateTime = simpleBbsDto.getCreatedAt().format(formatter);

        String query = 
                "insert into simple_bbs (writer, title, content, writeTime) " +
                " values (?, ?, ?, ?)";
        return template.update(query, writer, title, content, simpleBbsDto.getCreatedAt());
    }
// 삭제
    @Override
    public int deleteDao(final String id) {
        System.out.println("deleteDao()");
        
        String query = "delete from simple_bbs where id = ?";
        return template.update(query, Integer.parseInt(id));
    }

    // 수정
    @Override
    public int updateDao(final String id, final String writer, final String title, final String content) {
        System.out.println("updateDao()");
        
        String query = "update simple_bbs set writer = ?, title = ?, content = ? where id = ?";
        return template.update(query, writer, title, content, Integer.parseInt(id));
    }

}


