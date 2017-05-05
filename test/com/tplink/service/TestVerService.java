
package com.tplink.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({
        "/root-context.xml"
})
public class TestVerService {

    @Autowired
    VersionService versionService;

    @Test
    public void test() {
        System.out.println(versionService.getAllAppVersions());
    }
}
