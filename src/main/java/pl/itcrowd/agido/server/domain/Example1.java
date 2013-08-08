package pl.itcrowd.agido.server.domain;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Example1 {

    public static void main(String[] args)
    {
        Project project = new Project();
        project.setId(1L);
        project.setName("Test Project");

        try {
            freemarkerDo(project, "src/main/resources/example1.ftl");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    static void freemarkerDo(Project project, String template) throws Exception
    {
        Configuration cfg = new Configuration();
        Template tpl = cfg.getTemplate(template);
        OutputStreamWriter output = new OutputStreamWriter(System.out);

        Map datamodel = new HashMap();
        datamodel.put("project", project);
        tpl.process(datamodel, output);
    }
}
