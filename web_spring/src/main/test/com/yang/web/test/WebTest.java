package com.yang.web.test;

import com.tan.controller.BookController;
import com.tan.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class WebTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();//这个对象是Controller单元测试的关键
    }

    @Test
    public void addBook() throws Exception {
//        OngoingStubbing<Void> when = (OngoingStubbing<Void>) when(bookService.add(new Book(1, "yangxinzhao", "yang")));
         mockMvc.perform(get("/book.do/add")
                .param("code","code-1001")
                 .param("name","name-wangxindong"))
                 .andDo(print());
        System.out.printf("aaaaaaaaaaaa");
    }

}
