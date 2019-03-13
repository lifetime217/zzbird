package com.luoran.zzbird.config;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.luoran.zzbird.core.XcxSessionInterceptor;

/**
 * @author lifetime
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	Environment env;
	
	@Autowired
	XcxSessionInterceptor xcxSessionInter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(xcxSessionInter).addPathPatterns("/api/**");
	}

	@Bean
	public HttpMessageConverters fastJsonConfigure() {
		StringHttpMessageConverter strconverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		converter.setFastJsonConfig(fastJsonConfig);
		return new HttpMessageConverters(strconverter,converter);
	}

	@Bean
	public HandlerExceptionResolver customerExceptionPage() {
		HandlerExceptionResolver exceptionResolver = new HandlerExceptionResolver() {
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception ex) {
				ModelAndView mav = new ModelAndView("/error-500");
				mav.addObject("errorMsg", ex.getMessage());
				return mav;
			}
		};
		return exceptionResolver;
	}

	@Bean
	@ConditionalOnMissingBean({ BeetlSpringViewResolver.class })
	@ConditionalOnProperty(name = { "spring.beetl.enabled" }, matchIfMissing = true)
	public BeetlSpringViewResolver beetlView() {
		BeetlGroupUtilConfiguration configuration = new BeetlGroupUtilConfiguration();
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
		String viewPath = null;
		try {
			viewPath = resourceLoader.getResource(env.getProperty("spring.beetl.viewDir", "classpath:/views")).getFile()
					.getPath();
			configuration.setResourceLoader(new WebAppResourceLoader(viewPath));
		} catch (IOException e) {
			throw new RuntimeException("spring.beetl.viewDir : " + viewPath + " ,that view path is not exists.");
		}

		Resource conf = null;
		if (!(StringUtils.isEmpty(env.getProperty("spring.beetl.confFile"))))
			conf = resourceLoader.getResource(env.getProperty("spring.beetl.confFile"));

		if (conf != null)
			configuration.setConfigFileResource(conf);

		configuration.init();

		BeetlSpringViewResolver springViewResolver = new BeetlSpringViewResolver();
		springViewResolver.setSuffix(env.getProperty("spring.beetl.suffix", ".html"));
		springViewResolver.setContentType("text/html;charset=UTF-8");

		springViewResolver.setOrder(env.getProperty("spring.beetl.order", Integer.class, 0));
		springViewResolver.setConfig(configuration);
		return springViewResolver;
	}

}
