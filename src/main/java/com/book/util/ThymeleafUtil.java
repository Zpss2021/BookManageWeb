package com.book.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

public class ThymeleafUtil {
    private static final TemplateEngine engine;

    static {
        engine = new TemplateEngine();
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        r.setCharacterEncoding("UTF-8");
        engine.setTemplateResolver(r);
    }

    private ThymeleafUtil() {
    }

    public static void process(String template, IContext context, Writer writer) {
        engine.process(template, context, writer);
    }

    @Deprecated
    public static TemplateEngine getEngine() {
        return engine;
    }
}
