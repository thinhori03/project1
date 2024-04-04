package org.project1.nhom8.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


public class HTMLResolver {
    private ClassLoaderTemplateResolver templateResolver;
    private TemplateEngine templateEngine;

    public HTMLResolver() {
        templateEngine = new TemplateEngine();

        templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("template/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        templateEngine.setTemplateResolver(templateResolver);
    }

    public String revolve(String htmlFile, Context context) {
        return templateEngine.process(htmlFile, context);
    }
}
