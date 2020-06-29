package com.ckmoo.demo.simple;

import com.ckmoo.demo.DemoTestAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value="demo/test")
public class DemoController {

    @Autowired
    private DemoTestAspect demoTestAspect;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Object myTest() {
        demoTestAspect.buyThings();
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()) + "是试一试啊";
    }
}
