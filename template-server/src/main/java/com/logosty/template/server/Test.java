package com.logosty.template.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author logosty(ganyingle) on 2021/2/22 10:50
 */
@Slf4j
public class Test {

//  public static final String root = "/Volumes/Untitled/视频";

  public static final String root = "/Volumes/Untitled/";

  public static final String test = "/Volumes/Untitled/test";
  static int global = 1000;

  public static void main(String[] args) throws IOException {
    String fileName = test;
    File f = new File(fileName);
    log.info("f.canWrite() {}", f.canWrite());

    File oldF = new File(root + "old.txt");
    File newF = new File(root + "new.txt");

    //if file doesnt exists, then create it
    if (!oldF.exists()) {
      oldF.createNewFile();
    } else {
      oldF.delete();
      oldF.createNewFile();
    }
    if (!newF.exists()) {
      newF.createNewFile();
    } else {
      oldF.delete();
      oldF.createNewFile();
    }

    try (
        FileWriter oldFileWritter = new FileWriter(oldF, true);
        FileWriter newFileWritter = new FileWriter(newF, true);
        BufferedWriter oldB = new BufferedWriter(oldFileWritter);
        BufferedWriter newB = new BufferedWriter(newFileWritter);
    ) {
      scan(f, oldB, newB);
    }
    log.info("sdf");
  }

  private static void scan(File f, BufferedWriter oldWritter, BufferedWriter newWritter)
      throws IOException {
    if (f == null || f.isHidden()) {
      return;
    }
    if (f.isDirectory()) {
      File[] fileArray = f.listFiles();
      if (fileArray != null) {
        //目录
        oldWritter.append(f.getName());
        oldWritter.newLine();

        newWritter.append(f.getName());
        newWritter.newLine();

        for (int i = 0; i < fileArray.length; i++) {
          // 递归调用
          scan(fileArray[i], oldWritter, newWritter);
        }
      }
    } else {
      System.out.println(f);
      int num = global++;
      String newName = f.getParent() + File.separator + num + "." + f.getName();
      File newFile = new File(newName);
      //目录
      oldWritter.append(f.getName());
      oldWritter.newLine();

      newWritter.append(newFile.getName());
      newWritter.newLine();

      //rename
      boolean b = f.renameTo(newFile);
      log.info("rename [{}] [{}] [{}]", f.getName(), newName, b);
    }
  }


}

