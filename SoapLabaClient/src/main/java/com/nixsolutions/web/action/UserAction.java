package com.nixsolutions.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/userroom")
@ResultPath("/")
@Actions(value={
        @Action(value="hello", results={
                @Result(name="success", location="pages/hello.jsp")
        })
})
public class UserAction extends ActionSupport {
    
    
    
}
