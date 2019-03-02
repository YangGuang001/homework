package com.yang.IOTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;

@Slf4j
public class UUIDTest {

    @Test
    public void testUUID() {
        UUID uuid = new UUID(1L, 2L);
        UUID uuid2 = new UUID(1L, 2L);
        log.info("uuid: " + uuid.toString());
        log.info("uuid2: " + uuid.toString());
        log.info("uuid random： " + UUID.randomUUID().toString().replace("-",""));
    }


}
