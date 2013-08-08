package pl.itcrowd.agido.server.domain;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoverageReportGenerator {
    public static void main(String[] args)
    {
        Project project = new Project();
        project.setId(1L);
        project.setName("Test Project");

        Package package1 = new Package();
        package1.setProject(project);
        package1.setId(1L);
        package1.setName("World");

        Package package2 = new Package();
        package2.setProject(project);
        package2.setId(2L);
        package2.setName("Plants");
        package1.getSubpackages().add(package2);
        package1.setHasChildren(true);

        Package package3 = new Package();
        package3.setProject(project);
        package3.setId(3L);
        package3.setName("Animals");
        package1.getSubpackages().add(package3);

        Package package4 = new Package();
        package4.setProject(project);
        package4.setId(4L);
        package4.setName("Continents");
        package1.getSubpackages().add(package4);

        Package package5 = new Package();
        package5.setProject(project);
        package5.setId(5L);
        package5.setName("Trees");
        package2.getSubpackages().add(package5);
        package2.setHasChildren(true);

        Package package6 = new Package();
        package6.setProject(project);
        package6.setId(6L);
        package6.setName("Flowers");
        package2.getSubpackages().add(package6);

        Package package7 = new Package();
        package7.setProject(project);
        package7.setId(7L);
        package7.setName("Dogs");
        package3.getSubpackages().add(package7);
        package3.setHasChildren(true);

        Usecase usecase1 = new Usecase();
        usecase1.setProject(project);
        usecase1.setId(1L);
        usecase1.setName("Flowers usecase one");
        usecase1.setSummary("I am first usecase and this is my summary.");
        package6.getUsecases().add(usecase1);

        Usecase usecase2 = new Usecase();
        usecase2.setProject(project);
        usecase2.setId(2L);
        usecase2.setName("Flowers usecase two");
        usecase2.setSummary("I have summary too. I am second usecase.");
        package6.getUsecases().add(usecase2);

        Usecase usecase3 = new Usecase();
        usecase3.setProject(project);
        usecase3.setId(3L);
        usecase3.setName("Dogs usecase 1");
        usecase3.setSummary("Usecase summary");
        package7.getUsecases().add(usecase3);

        Test test1 = new Test();
        test1.setProject(project);
        test1.setId(1L);
        test1.setName("Stroke the dog");
        test1.setSummary("#Given #When #Then");
        usecase3.getTests().add(test1);
        test1.getUsecases().add(usecase3);
        package7.getTests().add(test1);

        Test test2 = new Test();
        test2.setProject(project);
        test2.setId(2L);
        test2.setName("Water the flower");
        test2.setSummary("#Given #When #Then");
        usecase1.getTests().add(test2);
        test2.getUsecases().add(usecase1);
        test2.getUsecases().add(usecase2);
        package6.getTests().add(test2);

        Test test3 = new Test();
        test3.setProject(project);
        test3.setId(3L);
        test3.setName("Feed the dog");
//        test3.setSummary("#Given #When #Then");
        usecase3.getTests().add(test3);
        test3.getUsecases().add(usecase3);
        package7.getTests().add(test3);

        Test test4 = new Test();
        test4.setProject(project);
        test4.setId(4L);
        test4.setName("Unassigned test");
        test4.setSummary("#Given    #When    #Then");
        package3.getTests().add(test4);
//        Set<Usecase> usecasesSet = new HashSet<Usecase>();
//        usecasesSet.add(usecase1);
//        usecasesSet.add(usecase2);
        Screen screen1 = new Screen();
        screen1.setProject(project);
        screen1.setId(1L);
        screen1.setName("Image_1");
//        screen1.setUsecases(usecasesSet);
        package6.getScreens().add(screen1);

        Set<Usecase> usecasesSet2 = new HashSet<Usecase>();
        usecasesSet2.add(usecase3);
        Screen screen2 = new Screen();
        screen2.setProject(project);
        screen2.setId(2L);
        screen2.setName("Image_2");
        screen2.setUsecases(usecasesSet2);
        package7.getScreens().add(screen2);

        List<UsecaseInfo> usecasesInfo = new ArrayList<UsecaseInfo>();
        List<TestInfo> testsInfo = new ArrayList<TestInfo>();
        List<Structure> unassignedObjects = new ArrayList<Structure>();
        List<Structure> emptyPackages = new ArrayList<Structure>();

        //Creating summary of Packages, Usecases, and Tests
        getEmptyPackages(package1, emptyPackages);
        getUnassignedObjects(package1, unassignedObjects);
        getUsecasesCoverage(package1, usecasesInfo);
        getTestsCoverage(package1, testsInfo);

        try {
            freemarkerDo(emptyPackages, unassignedObjects, usecasesInfo, testsInfo, project, "src/main/resources/coverageReport.ftl");
        }
        catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    static void getEmptyPackages (Package currentPackage, List<Structure> emptyPackagesList)
    {
        if ((currentPackage.getTests().size() == 0) && (currentPackage.getUsecases().size() == 0) && (currentPackage.getSubpackages().size() == 0)) {
            emptyPackagesList.add(new Structure(currentPackage));
        }
        for (Package subpackage : currentPackage.getSubpackages()) {
            getEmptyPackages(subpackage, emptyPackagesList);
        }
    }

    static void getUnassignedObjects (Package currentPackage, List<Structure> unassignedObjects)
    {
        for(Test test : currentPackage.getTests()) {
            if(test.getUsecases().size() == 0) {
                unassignedObjects.add(new Structure(test));
            }
        }
        for(Usecase usecase : currentPackage.getUsecases()) {
            if(usecase.getTests().size() == 0) {
                unassignedObjects.add(new Structure(usecase));
            }
        }
        for(Screen screen : currentPackage.getScreens()) {
            if(screen.getUsecases().size() == 0) {
                unassignedObjects.add(new Structure(screen));
            }
        }
        for (Package subpackage : currentPackage.getSubpackages()) {
            getUnassignedObjects(subpackage, unassignedObjects);
        }
    }

    static void getUsecasesCoverage (Package currentPackage, List<UsecaseInfo> usecasesInfoList)
    {
        for(Usecase usecase : currentPackage.getUsecases()) {
            usecasesInfoList.add(new UsecaseInfo(usecase));
        }
        for (Package subpackage : currentPackage.getSubpackages()) {
            getUsecasesCoverage(subpackage, usecasesInfoList);
        }
    }

    static void getTestsCoverage (Package currentPackage, List<TestInfo> testsInfoList)
    {
        for(Test test : currentPackage.getTests()) {
            testsInfoList.add(new TestInfo(test));
        }
        for (Package subpackage : currentPackage.getSubpackages()) {
            getTestsCoverage(subpackage, testsInfoList);
        }
    }

    static void freemarkerDo(List<Structure> emptyPackages, List<Structure> unassignedObjects, List<UsecaseInfo> usecasesInfo,
                             List<TestInfo> testsInfo, Project project, String template) throws Exception
    {
        Configuration cfg = new Configuration();
        Template tpl = cfg.getTemplate(template);
        final StringWriter output = new StringWriter();

        Map datamodel = new HashMap();
        datamodel.put("project", project);
        datamodel.put("emptyPackages", emptyPackages);
        datamodel.put("unassignedObjects", unassignedObjects);
        datamodel.put("usecasesInfo", usecasesInfo);
        datamodel.put("testsInfo", testsInfo);
        tpl.process(datamodel, output);
        System.out.println(output);
        IOUtils.write(output.toString(), new FileOutputStream("coverage_report.html"));
    }

    public static class UsecaseInfo{
        private String name;
        private Integer testsNumber;
        private Integer screensNumber;
        private Integer summaryLength;

        public  UsecaseInfo()
        {}

        public UsecaseInfo(Usecase usecase)
        {
            name = usecase.getName();
            testsNumber = usecase.getTests().size();
            screensNumber = usecase.getScreens().size();
            if(usecase.getSummary() == null) {
                this.summaryLength = 0;
            }
            else {
                this.summaryLength = usecase.getSummary().length();
            }
        }

        public String getName()
        {
            return name;
        }

        public Integer getScreensNumber()
        {
            return screensNumber;
        }

        public Integer getSummaryLength()
        {
            return summaryLength;
        }

        public Integer getTestsNumber()
        {
            return testsNumber;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setScreensNumber(Integer screensNumber)
        {
            this.screensNumber = screensNumber;
        }

        public void setSummaryLength(Integer summaryLength)
        {
            this.summaryLength = summaryLength;
        }

        public void setTestsNumber(Integer testsNumber)
        {
            this.testsNumber = testsNumber;
        }
    }

    public static class TestInfo{
        private String name;
        private Integer usecasesNumber;
        private Integer summaryLength;

        public TestInfo()
        {}

        public TestInfo(Test test)
        {
            this.name = test.getName();
            this.usecasesNumber = test.getUsecases().size();
            if(test.getSummary() == null) {
                this.summaryLength = 0;
            }
            else {
                this.summaryLength = test.getSummary().length();
            }
        }

        public String getName()
        {
            return name;
        }

        public Integer getSummaryLength()
        {
            return summaryLength;
        }

        public Integer getUsecasesNumber()
        {
            return usecasesNumber;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setSummaryLength(Integer summaryLength)
        {
            this.summaryLength = summaryLength;
        }

        public void setUsecasesNumber(Integer usecasesNumber)
        {
            this.usecasesNumber = usecasesNumber;
        }
    }

    public static class Structure{
        private String name;
        private String type;

        Structure(Package package_)
        {
            this.name = package_.getName();
            this.type = "package";
        }

        Structure(Usecase usecase)
        {
            this.name = usecase.getName();
            this.type = "usecase";
        }

        Structure(Screen screen)
        {
            this.name = screen.getName();
            this.type = "screen";
        }

        Structure(Test test)
        {
            this.name = test.getName();
            this.type = "test";
        }

        public String getName()
        {
            return name;
        }

        public String getType()
        {
            return type;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setType(String type)
        {
            this.type = type;
        }
    }

}
