package pl.itcrowd.agido.server.domain;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import org.apache.commons.io.IOUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DocumentationGenerator {
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
        //test3.setSummary("#Given #When #Then");
        usecase3.getTests().add(test3);
        test3.getUsecases().add(usecase3);
        package7.getTests().add(test3);

        Set<Usecase> usecasesSet = new HashSet<Usecase>();
        usecasesSet.add(usecase1);
        usecasesSet.add(usecase2);
        Screen screen1 = new Screen();
        screen1.setProject(project);
        screen1.setId(1L);
        screen1.setName("Image_1");
        screen1.setUsecases(usecasesSet);
        package6.getScreens().add(screen1);
        String filePath = "src/images/1.gif";
        screen1.setData(readImageToByte(filePath));

        Set<Usecase> usecasesSet2 = new HashSet<Usecase>();
        usecasesSet2.add(usecase3);
        Screen screen2 = new Screen();
        screen2.setProject(project);
        screen2.setId(2L);
        screen2.setName("Image_2");
        screen2.setUsecases(usecasesSet2);
        package7.getScreens().add(screen2);
        filePath = "src/images/2.jpg";
        screen2.setData(readImageToByte(filePath));

        Numbering numbering = new Numbering();


       try {
           freemarkerDo(package1, numbering, "src/main/resources/documentation.ftl");
        }
        catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    static void freemarkerDo(Package package_, Numbering numbering, String template) throws Exception
    {
        Configuration cfg = new Configuration();
        Template tpl = cfg.getTemplate(template);
        final StringWriter output = new StringWriter();


        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel base64 = (TemplateHashModel) staticModels.get("org.apache.commons.codec.binary.Base64");

        Map datamodel = new HashMap();
        datamodel.put("package", package_);
        datamodel.put("base64", base64);
        datamodel.put("numbering", numbering);
        tpl.process(datamodel, output);
        System.out.println(output);
        IOUtils.write(output.toString(),new FileOutputStream("output.html"));
    }

    static byte[] readImageToByte(String filePath){
        try{
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();

        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Some problem has occurred.");
            return null;
        }
    }

//    public static class Levels{
//        private List<Integer> indexes = new ArrayList<Integer>();
//        private List<String> stringIndexes = new ArrayList<String>();
//
//        public void setLevels(Package package_, String parentIndex){
//            if(indexes.isEmpty()){
//                indexes.add(1);
//                parentIndex = "1";
//                stringIndexes.add(parentIndex);
//            }
//            for (int i=1; i <= package_.getSubpackages().size(); i++){
//                indexes.add(i);
//                stringIndexes.add(parentIndex + "." + Integer.toString(i));
//                setLevels(package_.getSubpackages().iterator().next(), parentIndex + "." + Integer.toString(i));
//            }
//        }
//
//        public List<Integer> getIndexes(){
//            return indexes;
//        }
//
//        public List<String> getStringIndexes(){
//            return stringIndexes;
//        }
//
//    }
}
//    public static class Directory {
//        private String name;
//        private List<Directory> subDirectories;
//        private List<Usecase> usecases;
//        private List<Test> tests;
//        private boolean hasChildren = false;
//
//        public Directory (Package pack){
//            this.name = pack.getName();
//
//            if(pack.isHasChildren()){
//                this.hasChildren = true;
//                int childrensNumber = pack.getSubpackages().size();
//                Object[] childrens = pack.getSubpackages().toArray();
//
//                subDirectories = new ArrayList<Directory>();
//                for (int i = 0; i < childrensNumber; i++){
//                    subDirectories.add(new Directory((Package)childrens[i]));
//                }
//            }
//
//            if(pack.getUsecases().size() != 0){
//                usecases = new ArrayList<Usecase>();
//
//                for (Usecase usecase_ : pack.getUsecases()){
//                    usecases.add((usecase_));
//                }
//            }
//
//            if(pack.getTests().size() != 0){
//                tests = new ArrayList<Test>();
//
//                for (Test test_ : pack.getTests()){
//                    tests.add(test_);
//                }
//            }
//        }
//
//        public String getName(){
//            return this.name;
//        }
//
//        public List<Directory> getSubDirectories(){
//            return subDirectories;
//        }
//
//        public boolean isHasChildren(){
//            return hasChildren;
//        }
//
//        public List<Usecase> getUsecases(){
//            return usecases;
//        }
//    }

