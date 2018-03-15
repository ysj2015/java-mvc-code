package com.mvc.code.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.code.form.CodeForm;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/autocode")
	public String autoCode(CodeForm form) throws IOException {
		System.out.println(form.getPackageName());
		System.out.println(form.getLocation());
		String[] cls_arr = form.getClassName().split(",");
		for(int i = 0;i < cls_arr.length;i ++) {
			String code = controllerCode(cls_arr[i],form.getPackageName());
			File file = new File(form.getLocation() + "\\" + cls_arr[i] + ".java");
			FileWriter fw = new FileWriter(file);
			fw.write(code);
			fw.flush();
			fw.close();
		}
		return "success";
	}
	private String controllerCode(String className, String packageName) {
		StringBuilder str = new StringBuilder("");
		str.append(String.format("package %s;\n\n",packageName));
		str.append("import org.springframework.beans.factory.annotation.Autowired;\n"
			+ "import org.springframework.stereotype.Controller;\n"
			+ "import org.springframework.web.bind.annotation.RequestMapping;\n\n");
		str.append("@Controller\n");
		str.append(String.format("public class %s {\n",className));
		str.append("\t//@Autowired\n\n");
		str.append("}");
		return str.toString();
	}
}
