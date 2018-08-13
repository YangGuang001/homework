package com.yang.mojo;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @goal count1
 * @author yang
 *
 */
@Mojo(name = "count1")
public class MojoTest
    extends AbstractMojo
{
    private static final String[] INCLUDES_DEFAULT = {"java","xml","properties"};
    //以下是获取maven内置隐含变量。
//	@Parameter( defaultValue = "${project.basedir}",property = "basedir", readonly = true, required = true )
    /**
     * @parameter expression = "${project.basedir}"
     * @readonly
     * @required
     */
    private File baseDir;

    /**
     * @parameter expression = "${project.build.sourceDirectory}"
     * @readonly
     * @required
     */
    private File sourceDirectory;


    /**
     * @parameter expression = "${project.build.testSourceDirectory}"
     * @readonly
     * @required
     */
    private File testSourceDirectory;


    /**
     * @parameter expression = "${project.build.resources}"
     * @readonly
     * @required
     */
    private List<Resource> resources;

    /**
     * @parameter expression = "${project.build.testResources}"
     * @readonly
     * @required
     */
    private List<Resource> testResources;

    /**
     * The file types which will be incluede for counting
     * @parameter
     */
    private String[] includes;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if(includes == null || includes.length == 0){   //如果没有在pom.xml中说明includes，就是用默认的includes。
            includes = INCLUDES_DEFAULT;
        }
        try {
            //分别统计四种目录下的代码行数。
            countDir(sourceDirectory);
            countDir(testSourceDirectory);
            for(Resource resource:resources){
                countDir(new File(resource.getDirectory()));
            }
            for(Resource resource:testResources){
                countDir(new File(resource.getDirectory()));
            }
        } catch (Exception e) {
            throw new MojoExecutionException("Unable to count lines of code.",e);
        }
    }

    /**
     * 统计该目录下所有文件的代码行数之和。
     * @param dir
     * @throws IOException
     */
    private void countDir(File dir) throws IOException{
        if(!dir.exists()){
            return;
        }
        List<File> collected = new ArrayList<File>();
        collectFiles(collected,dir);
        int lines = 0;
        for(File sourceFile : collected){
            lines += countLine(sourceFile);
        }
        String path = dir.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        getLog().info(path+":"+lines+" lines of code in " + collected.size()+" files");
    }

    /**
     * 递归的统计该目录下的所有文件，并放入list
     * @param collected
     * @param file
     */
    private void collectFiles(List<File> collected, File file){
        if(file.isFile()){
            for(String include : includes){
                if(file.getName().endsWith("."+include)){
                    collected.add(file);
                    break;
                }
            }
        }else{
            for(File sub : file.listFiles()){
                collectFiles(collected,sub);
            }
        }
    }

    /**
     * 统计代码行数。
     * @param file
     * @return
     * @throws IOException
     */
    private int countLine(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int line = 0;
        try {
            while(reader.ready()){
                reader.readLine();
                line++;
            }
        } finally{
            reader.close();
        }
        return line;
    }
}
