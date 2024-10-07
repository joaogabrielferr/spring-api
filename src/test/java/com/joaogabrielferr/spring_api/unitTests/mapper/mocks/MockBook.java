package com.joaogabrielferr.spring_api.unitTests.mapper.mocks;

import com.joaogabrielferr.spring_api.data.VO.v1.BookVO;
import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.model.Book;
import com.joaogabrielferr.spring_api.model.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockEntity(){
        return mockEntity(0);
    }

    public BookVO mockVO(){
        return mockVO(0);
    }

    public Book mockEntity(Integer id){
        Book p = new Book();
        p.setAuthor("Author test" + id);
        p.setPrice(100.0);
        LocalDate localDate = LocalDate.of(2023, 10, 7); // Year, month, day
        Date specificDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        p.setLaunchDate(specificDate);
        p.setTitle("Title test" + id);
        p.setId(id.longValue());
        return p;
    }

    public Book mockEntityWithoutId(){
        Book p = new Book();
        p.setAuthor("Author test");
        p.setPrice(100.0);
        LocalDate localDate = LocalDate.of(2023, 10, 7); // Year, month, day
        Date specificDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        p.setLaunchDate(specificDate);
        p.setTitle("Title test");
        return p;
    }


    public List<Book> mockEntityList(){
        List<Book> l = new ArrayList<>();
        for(int i = 0;i<10;i++){
            l.add(mockEntity(i));
        }
        return l;
    }

    public BookVO mockVO(Integer id){
        BookVO p = new BookVO();
        p.setAuthor("Author test" + id);
        p.setPrice(100.0);
        LocalDate localDate = LocalDate.of(2023, 10, 7); // Year, month, day
        Date specificDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        p.setLaunchDate(specificDate);
        p.setTitle("Title test" + id);
        p.setMyId(id.longValue());
        return p;
    }

    public List<BookVO> mockVOList(){
        List<BookVO> l = new ArrayList<>();
        for(int i = 0;i<10;i++){
            l.add(mockVO(i));
        }
        return l;
    }

}
