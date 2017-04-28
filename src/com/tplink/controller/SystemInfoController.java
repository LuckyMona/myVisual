/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * SystemInfoController.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-26, TangWeicheng, Create file
 */

package com.tplink.controller;

import com.tplink.annotation.LogRequired;
import com.tplink.annotation.LoginCheck;
import com.tplink.service.ApplicationService;
import com.tplink.service.ProjectService;
import com.tplink.service.VersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("/systemInfo")
public class SystemInfoController extends BasicController {

    @Autowired
    ProjectService projectService;

    @Autowired
    VersionService versionService;

    @Autowired
    ApplicationService applicationService;

    private static final String PROJS = "projs";

    private static final String VERS = "vers";

    private static final String APPS = "apps";

    @RequestMapping("/getProjsAndVers")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object ProjectsAndVersionsAndApplications() {
        setResult(PROJS, projectService.getAllService());
        setResult(VERS, versionService.getAllRomVersions());
        setResult(APPS, applicationService.getAllApplications());
        setType(SUCCESS);
        return getResult();
    }
}
