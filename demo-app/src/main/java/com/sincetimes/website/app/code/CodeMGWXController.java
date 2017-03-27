package com.sincetimes.website.app.code;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.sincetimes.website.app.file.FileManager;
import com.sincetimes.website.core.common.support.LogCore;
import com.sincetimes.website.core.common.support.Util;
import com.sincetimes.website.core.spring.controller.ControllerInterface;
import com.sincetimes.website.redis.jedis.JedisPoolTemplate;
/**
 * 微信礼包激活码快捷生成
 */
@Controller
public class CodeMGWXController implements ControllerInterface{
	
	@Autowired
	public JedisPoolTemplate jedisTemplate;
	
	@RequestMapping("/edit_code_cf＿wx")
	String  edit_code_cf＿wx(Model model, StandardMultipartHttpServletRequest req){
//		SingleFileCallBack<byte[]> consume = (m)->  ;
//		String base64Str = new String(Base64.getEncoder().encodeToString(data));
		
		byte[] data = FileManager.inst().do_single_file(FileManager.inst()::readFileBytes, req);
		if(!Util.isEmpty(data)){
			return "_jihuoma_wx";
		}
		String base64Str = new String(Base64.getEncoder().encodeToString(data));
		model.addAttribute("data", base64Str);
//		jedisTemplate.excute(jedis->jedis.set(key, value));
		return "_jihuoma_wx";
	}
	@RequestMapping("/jihuoma_wx")
	String  _jihuoma_wx(@RequestParam Optional<String> sn, Model model, HttpServletResponse resp){
		if(sn.isPresent()){
			String titlekey = sn.get()+"titl_url";
			String imgkey = sn.get()+"img_url";
			String img_url = FileManager.inst().get(imgkey);
			String title = FileManager.inst().get(titlekey);
			if(!Util.isEmpty(img_url)){
				model.addAttribute("img_url", "data:image/jpeg;base64,"+img_url);
			}
			if(!Util.isEmpty(title)){
				model.addAttribute("title", title);
			}
		}
		return "_jihuoma_wx";
	}
	/**
	 * 微信激活码页面生成
	 */
	@RequestMapping("/mg/add_wx_jhm_img")
	void  edit_code_cf＿wx(
			@RequestParam Optional<String> code_sn, 
			@RequestParam Optional<String> title, 
			StandardMultipartHttpServletRequest req,
			HttpServletResponse resp){
		if(!code_sn.isPresent()){
			LogCore.BASE.info("empty param: code_sn");
			return;
		}
		byte[] data = FileManager.inst().do_single_file(FileManager.inst()::readFileBytes, req);
		if(!Util.isEmpty(data)){
			String base64Str = new String(Base64.getEncoder().encodeToString(data));
			String imgkey = code_sn.get()+"img_url";
			FileManager.inst().set(imgkey, base64Str);
			LogCore.BASE.info("imgkey={},value={},,data.size={}", imgkey, base64Str,  data.length);

		}
		if(title.isPresent()){
			String titlekey = code_sn.get()+"titl_url";
			FileManager.inst().set(titlekey, title.get());
			LogCore.BASE.info("titlekey={},value={}", titlekey, title.get());
		}
		redirect(resp, "code?sn="+code_sn.get());
	}
}
