package com.yang.web.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tan.controller.BookController;
import com.tan.model.Book;
import com.tan.service.BookService;
import org.apache.commons.collections.MultiHashMap;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<String, String>();
        valueMap.add("id", "11");
        valueMap.add("name", "yang");
        valueMap.add("author", "xin");
//        OngoingStubbing<Void> when = (OngoingStubbing<Void>) when(bookService.add(new Book(1, "yangxinzhao", "yang")));
         mockMvc.perform(put("/book.do/add")
                 .params(valueMap))
                 .andDo(print());
        System.out.printf("aaaaaaaaaaaa");
    }

}
