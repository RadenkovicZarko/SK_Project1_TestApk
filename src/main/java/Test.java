import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {


  private static boolean isNumeric(String s)
  {
    try
    {
      Integer.parseInt(s);
      return true;
    }
    catch (Exception e)
    {
      return false;
    }

  }
  public static void main(String[] args) {
    try {
      //String path=args[0];
      System.out.println("Welcome to our program!(1 or 2)\nChoose option:\n1.Local storage\n2.Google Drive storage");
      Scanner sc=new Scanner(System.in);

      //ODABIR DA LI CEMO LOKAL ILI DRIVE DA KORISTIMO
      while(true) {
        int odabran=Integer.parseInt(sc.nextLine());
        if (odabran == 1) {
          Class.forName("LocalStorageImplementation");
          break;
        }
        else if (odabran == 2) {
          Class.forName("GoogleDriveStorage");
          break;
        }
        else
        {
          System.out.println("Selected option does not exist");
        }
      }
      //C:/Users/mega/Radna površina/Test2
      StorageSpecification storageSpecification=StorageManager.getStorage();

      //Za potrebe testiranja pisem path ovde da se procita sa konzole ali prvi pet se inace cita iz poziva jar funkcije iz cmd
      System.out.println("Choose the path for root folder:");
      while(true)
      {
          try {
            String path = sc.nextLine();
            storageSpecification.setRootFolderPathInitialization(path);
            System.out.println("The choosen path exist, root folder is ready to be created.");
            break;
          }
          catch (MyException e)
          {
//            System.out.println("The choosen path does not exist, please insert another:");
            System.out.println(e.getMessage());
          }
      }

      System.out.println("Do you want to set configuration, or you want to use default one?(1 or 2)\n1.Default\n2.Custom ");

      int odabran=Integer.parseInt(sc.nextLine());
      if(odabran==1)
      {
          try{
            storageSpecification.createRootFolder();
          }
          catch (MyException e)
          {
            System.out.println(e.getMessage());
          }
      }
      else if(odabran==2)
      {
          String odluka;
          System.out.println("Do you want to set limit size of storage in bytes?(Yes or No)");
          while(true) {
            odluka = sc.nextLine();
            if (odluka.equalsIgnoreCase("Yes")) {
              System.out.println("Insert limit size:");
              while (true) {
                String l = sc.nextLine();
                if (isNumeric(l)) {
                  int limit = Integer.parseInt(l);
                  storageSpecification.setConfigurationSizeOfStorage(limit);
                  break;
                } else {
                  System.out.println("Wrong input, try again.");
                }
              }
              break;
            } else if (odluka.equalsIgnoreCase("No")) {
              System.out.println("Nice.");
              break;
            }
            else
            {
              System.out.println("Wrong input, try again.");
            }
          }

        System.out.println("Do you want to set forbidden extension of storage?(Yes or No)");
        while(true) {
          odluka = sc.nextLine();
          if (odluka.equalsIgnoreCase("Yes")) {
            System.out.println("Insert forbidden extensions:(Example: .exe .pdf .jpg)");
            System.out.println("If you want to stop, type 'stop'.");
            List<String> list=new ArrayList<>();
            while (true) {
              String l = sc.nextLine();
              if (l.length()>0 && l.contains(".") && l.charAt(0)=='.') {
                list.add(l);
                System.out.println("Next limit.");
              } else if(l.equalsIgnoreCase("stop"))
              {
                System.out.println("Compiling...");
                break;
              }else{
                System.out.println("Wrong input, try again.");
              }
            }
            storageSpecification.getConfiguration().setForbiddenExtensions(list);
            break;
          } else if (odluka.equalsIgnoreCase("No")) {
            System.out.println("Nice.");
            break;
          }
          else
          {
            System.out.println("Wrong input, try again.");
          }
        }

        System.out.println("Do you want to set file limit of folders in storage?(Yes or No)");
        while(true) {
          odluka = sc.nextLine();
          if (odluka.equalsIgnoreCase("Yes")) {
            System.out.println("First insert relativ path to folder and then limit od files:(Example: /Zarko/Vladan 2)");
            System.out.println("If you want to stop, type 'stop'.");
            HashMap<String,Integer> mapa=new HashMap<>();
            while (true) {
              String l = sc.nextLine();
              String[] str=l.split(" +");
              if (str.length==2 && isNumeric(str[1])) {
                mapa.put(str[0],Integer.parseInt(str[1]));
                System.out.println("Next limit.");
              } else if(l.equalsIgnoreCase("stop"))
              {
                System.out.println("Compiling...");
                break;
              }else{
                System.out.println("Wrong input, try again.");
              }
            }
            storageSpecification.getConfiguration().setNumberOfFilesInFolder(mapa);
            break;
          } else if (odluka.equalsIgnoreCase("No")) {
            System.out.println("Nice.");
            break;
          }
          else
          {
            System.out.println("Wrong input, try again.");
          }
        }
        try{
          storageSpecification.createRootFolder();
          System.out.println("Storage is ready!");
        }
        catch (MyException e)
        {
          System.out.println(e.getMessage());
        }
      }



      while(true)
      {
        System.out.println("Choose the action you want to perform:(1 or 2)");
        System.out.println("1.Standard operations");
        System.out.println("2.Search");
        System.out.println("3.Exit");

        String unet=sc.nextLine();
        if(isNumeric(unet))
        {
            odabran=Integer.parseInt(unet);
            if (odabran == 1) {
              while(true)
              {
                  System.out.println("1.Create folder on specified path");
                  System.out.println("2.Put files on specified path");
                  System.out.println("3.Delete file or directory");
                  System.out.println("4.Move file from directory to another");
                  System.out.println("5.Download file or directory");
                  System.out.println("6.Rename file or directory\n");
                  System.out.println("7.Back");
                  unet=sc.nextLine();
                  if(isNumeric(unet))
                  {
                    odabran=Integer.parseInt(unet);
                    if(odabran == 1)
                    {
                      System.out.println("Insert path to new folder and name of new folder:");
                      String str=sc.nextLine();
                      String[] input=str.split(" ");
                      if(input.length != 2)
                      {

                        System.out.println("Something went wrong");
                        continue;
                      }
                      try{
                        System.out.println(input[0]+" "+input[1]);
                        storageSpecification.createFolderOnSpecifiedPath(input[0],input[1]);
                        System.out.println("This action is completed");
                      }
                      catch (MyException e)
                      {
                        System.out.println(e.getMessage());
                      }
                    }
                    else if(odabran == 2)
                    {
                      System.out.println("Insert relative path to folder from root:");
                      String path=sc.nextLine();
                      System.out.println("Insert absoulte paths of files or file that you want to insert in storage folder:(when you want to stop type 'stop')");
                      List<String> list=new ArrayList<>();
                      while(true)
                      {
                        String file=sc.nextLine();
                        if(file.equalsIgnoreCase("stop"))
                        {System.out.println("Compiling...");break;}
                        System.out.println("Next file.");
                        list.add(file);
                      }
                      try{
                        storageSpecification.putFilesOnSpecifiedPath(list,path);
                        System.out.println("This action is completed");
                      }catch (MyException e)
                      {
                        System.out.println(e.getMessage());
                      }

                    }
                    else if(odabran == 3)
                    {
                      System.out.println("Insert path of file or folder to be deleted:");
                      String path=sc.nextLine();
                      try{
                        storageSpecification.deleteFileOrDirectory(path);
                        System.out.println("This action is completed");
                      }
                      catch (MyException e)
                      {
                        System.out.println(e.getMessage());
                      }
                    }
                    else if(odabran == 4)
                    {
                      System.out.println("Insert path of file to be moved:");
                      String filePath=sc.nextLine();
                      System.out.println("Insert path of destination folder:");
                      String pathTo=sc.nextLine();

                      try{
                        storageSpecification.moveFileFromDirectoryToAnother(filePath,pathTo);
                        System.out.println("This action is completed");
                      }
                      catch (MyException e)
                      {
                        System.out.println(e.getMessage());
                      }
                    }
                    else if(odabran == 5)
                    {
                      System.out.println("Insert path of file or folder that should be downloaded:");
                      String pathFrom=sc.nextLine();
                      System.out.println("Insert path of destination folder on your computer:");
                      String pathTo=sc.nextLine();

                      try {
                        storageSpecification.downloadFileOrDirectory(pathFrom,pathTo);
                        System.out.println("This action is completed");
                      }
                      catch (MyException e)
                      {
                        System.out.println(e.getMessage());
                      }
                    }
                    else if(odabran == 6)
                    {
                      System.out.println("Insert path of file or folder that should be renamed:");
                      String path=sc.nextLine();
                      System.out.println("Insert new name of that file or folder:");
                      String name=sc.nextLine();
                      try {
                        storageSpecification.renameFileOrDirectory(path,name);
                        System.out.println("This action is completed");
                      }
                      catch (MyException e)
                      {
                        System.out.println(e.getMessage());
                      }
                    }
                    else if(odabran == 7)
                    {
                      break;
                    }
                    else
                    {
                      System.out.println("Selected option does not exist");
                    }
                  }
                  else
                  {
                    System.out.println("Selected option does not exist");
                  }
              }
            }
            else if (odabran == 2) {
              while(true) {
                System.out.println("1.Files from directory");
                System.out.println("2.Files from subdirectory");
                System.out.println("3.All files from directory and subdirectory");
                System.out.println("4.Files from directory with extensions");
                System.out.println("5.Files from subdirectory with extensions");
                System.out.println("6.All files from directory and subdirectory with extensions");
                System.out.println("7.Files from directory with substring in name");
                System.out.println("8.Files from subdirectory with substring in name");
                System.out.println("9.Files from directory and subdirectory with substring in name");
                System.out.println("10.Does directory contain files");
                System.out.println("11.Return folder name by file name");
                System.out.println("12.Return created files in date interval");
                System.out.println("13.Return modified files in date interval");
                System.out.println("14.Return modified files from date");
                System.out.println("15.Return modified files defore date\n");
                System.out.println("16.Back");
                unet = sc.nextLine();

                if(isNumeric((unet)))
                {
                    odabran=Integer.parseInt(unet);
                    if(odabran>=1 && odabran<=9)
                    {
                      System.out.println("Insert path to folder:");
                      String path=sc.nextLine();
                      Map<String,FileMetadata> map=new LinkedHashMap<>();
                      try{

                        if(odabran==1)
                          map=storageSpecification.filesFromDirectory(path);
                        else if(odabran==2)
                          map=storageSpecification.filesFromChildrenDirectory(path);
                        else if(odabran==3)
                          map=storageSpecification.allFilesFromDirectoryAndSubdirectory(path);
                        else if( odabran<=6)
                        {
                          System.out.println("Insert extensions that are acceptable: (Example: .txt .pdf ...)");
                          System.out.println("If you want to stop, type 'stop'");
                          List<String> list=new ArrayList<>();
                          while(true)
                          {
                            String s=sc.nextLine();
                            if(s.equalsIgnoreCase("stop"))
                            {System.out.println("Starting search...");break;}
                            list.add(s);
                          }

                          if(odabran==4)
                            map=storageSpecification.filesFromDirectoryExt(path,list);
                          else if(odabran==5)
                            map=storageSpecification.filesFromChildrenDirectoryExt(path,list);
                          else
                            map=storageSpecification.allFilesFromDirectoryAndSubdirectoryExt(path,list);
                        }
                        else
                        {
                          System.out.println("Insert substring that file name should contains:");
                          String substring=sc.nextLine();
                          if(odabran==7)
                            map=storageSpecification.filesFromDirectorySubstring(path,substring);
                          else if(odabran==8)
                            map=storageSpecification.filesFromChildrenDirectorySubstring(path,substring);
                          else
                            map=storageSpecification.filesFromDirectoryAndSubdirectorySubstring(path,substring);
                        }

                        System.out.println("Do you want to sort map:\n1.Sort by name\n2.Sort by creating date\n3.Sort by file size\n4.Nothing");
                        int sort=0;
                        while(true)
                        {
                          String s=sc.nextLine();
                          if(isNumeric(s) && (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")))
                          {
                            sort=Integer.parseInt(s);
                            break;
                          }
                          else
                          {
                            System.out.println("Selected option does not exist, try again");
                          }
                        }

                        boolean desOrAsc=false;
                        if(sort!=4)
                        {
                          System.out.println("Do you want ascending or descending: (1 or 2)");
                          while(true)
                          {
                            String s=sc.nextLine();
                            if(isNumeric(s))
                            {
                              if(Integer.parseInt(s)==1)
                              {
                                desOrAsc=false;
                                break;
                              }
                              else if(Integer.parseInt(s)==2)
                              {
                                desOrAsc=true;
                                break;
                              }
                              else
                              {
                                System.out.println("Try again");
                              }
                            }
                            else
                            {
                              System.out.println("Selected option does not exist, try again");
                            }
                          }
                        }

                        if(sort==1)
                        {
                          map=storageSpecification.sortFilesByName(map,desOrAsc);
                        }
                        else if(sort==2)
                        {
                          map=storageSpecification.sortFilesByCreatedDate(map,desOrAsc);
                        }
                        else if(sort==3)
                        {
                          map=storageSpecification.sortFilesBySize(map,desOrAsc);
                        }

                        System.out.println("Do you want to set attributes that are going to be shown as result: (Yes or No)");
                        boolean attributes=false;
                        while(true)
                        {
                          String s=sc.nextLine();
                          if(s.equalsIgnoreCase("Yes"))
                          {
                            attributes=true;
                            break;
                          }
                          else if(s.equalsIgnoreCase("No"))
                          {
                            break;
                          }
                          else
                          {
                            System.out.println("Try again, bad input");
                          }
                        }

                        if(attributes)
                        {
                          System.out.println("Whole path of file: (Yes or No)");
                          while(true)
                          {
                            String s=sc.nextLine();
                            if(s.equalsIgnoreCase("Yes"))
                            {
                              storageSpecification.setWholePathAttribute();
                              break;
                            }
                            else if(s.equalsIgnoreCase("No"))
                            {
                              break;
                            }
                            else
                            {
                              System.out.println("Try again, bad input");
                            }
                          }
                          System.out.println("Size of file: (Yes or No)");
                          while(true)
                          {
                            String s=sc.nextLine();
                            if(s.equalsIgnoreCase("Yes"))
                            {
                              storageSpecification.setFileSizeAttribute();
                              break;
                            }
                            else if(s.equalsIgnoreCase("No"))
                            {
                              break;
                            }
                            else
                            {
                              System.out.println("Try again, bad input");
                            }
                          }
                          System.out.println("Created date of file: (Yes or No)");
                          while(true)
                          {
                            String s=sc.nextLine();
                            if(s.equalsIgnoreCase("Yes"))
                            {
                              storageSpecification.setDateAttribute();
                              break;
                            }
                            else if(s.equalsIgnoreCase("No"))
                            {
                              break;
                            }
                            else
                            {
                              System.out.println("Try again, bad input");
                            }
                          }
                          System.out.println("Last modification date of file: (Yes or No)");
                          while(true)
                          {
                            String s=sc.nextLine();
                            if(s.equalsIgnoreCase("Yes"))
                            {
                              storageSpecification.setModificationDateAttribute();
                              break;
                            }
                            else if(s.equalsIgnoreCase("No"))
                            {
                              break;
                            }
                            else
                            {
                              System.out.println("Try again, bad input");
                            }
                          }
                        }

                        System.out.println(storageSpecification.returnStringForOutput(map));


                      }
                      catch (Exception e)
                      {
                        System.out.println(e.getMessage());
                      }
                    }
                    else if(odabran==10)
                    {
                      System.out.println("Insert path of folder");
                      String path=sc.nextLine();
                      System.out.println("Insert name of files: (type 'stop' to stop)");
                      List<String> list=new ArrayList<>();
                      while(true)
                      {
                        String name=sc.nextLine();
                        if(name.equalsIgnoreCase("stop")){
                          System.out.println("Starting search...");break;}
                        list.add(name);
                      }
                      try {
                        String res = storageSpecification.doesDirectoryContainFiles(path, list);
                        if(res==null)
                        {
                          System.out.println("Path is not correct");
                        }
                        else
                          System.out.println(res);
                      }
                      catch (Exception e)
                      {
                        System.out.println(e.getMessage());
                      }


                    }
                    else if(odabran==11)
                    {
                      System.out.println("Insert name of file to search");
                      String name=sc.nextLine();
                      try {
                        String res = storageSpecification.folderNameByFileName(name);
                        if (res == null)
                          System.out.println("File not founded or path is bad");
                        else
                          System.out.println(res);
                      }
                      catch (Exception e)
                      {
                        System.out.println(e.getMessage());
                      }
                    }
                    else if(odabran==12 || odabran==13 || odabran==14 || odabran==15)
                    {
                      System.out.println("Insert path to folder:");
                      String path=sc.nextLine();
                      if(odabran==12 || odabran==13)
                      {
                        Date dateFrom=null;
                        System.out.println("Insert date from: (format: dd/mm/yyyy)");
                        while(true) {
                          String d=sc.nextLine();
                          String d1=d;
                          String str[]=d.split("/");
                          if (d.contains("/") && str.length == 3 && str[0].length() == 2 && isNumeric(str[0]) && str[1].length() == 2 && isNumeric(str[1]) && str[2].length() == 4 && isNumeric(str[2])) {
                            try {
                               dateFrom= new SimpleDateFormat("dd/mm/yyyy").parse(d1);
                              break;
                            } catch (Exception e) {
                              System.out.println("Bad date");
                            }
                          } else {
                            System.out.println("Wrong date format, try again");
                          }
                        }
                        Date dateTo=null;
                        System.out.println("Insert date to: (format: dd/mm/yyyy)");
                        while(true) {
                          String d=sc.nextLine();
                          String d1=d;
                          String str[]=d.split("/");
                          if (d.contains("/") && str.length == 3 && str[0].length() == 2 && isNumeric(str[0]) && str[1].length() == 2 && isNumeric(str[1]) && str[2].length() == 4 && isNumeric(str[2])) {
                            try {
                               dateTo= new SimpleDateFormat("dd/mm/yyyy").parse(d1);
                              break;
                            } catch (Exception e) {
                              System.out.println("Bad date");
                            }
                          } else {
                            System.out.println("Wrong date format, try again");
                          }
                        }
                        try {
                          Map<String, FileMetadata> map=new LinkedHashMap<>();
                          if(odabran==12)
                          {
                            map=storageSpecification.returnCreatedFilesInDateInterval(path,dateFrom,dateTo);
                          }
                          else {
                            map=storageSpecification.returnModifiedFilesInDateInterval(path,dateFrom,dateTo);
                          }
                          System.out.println(storageSpecification.returnStringForOutput(map));
                        }
                        catch (MyException e)
                        {
                          System.out.println(e.getMessage());
                        }
                      }
                      else if(odabran==14)
                      {
                        Date dateFrom=null;
                        System.out.println("Insert date from: (format: dd/mm/yyyy)");
                        while(true) {
                          String d=sc.nextLine();
                          String d1=d;
                          String str[]=d.split("/");
                          if (d.contains("/") && str.length == 3 && str[0].length() == 2 && isNumeric(str[0]) && str[1].length() == 2 && isNumeric(str[1]) && str[2].length() == 4 && isNumeric(str[2])) {
                            try {
                              dateFrom= new SimpleDateFormat("dd/mm/yyyy").parse(d1);
                              break;
                            } catch (Exception e) {
                              System.out.println("Bad date");
                            }
                          } else {
                            System.out.println("Wrong date format, try again");
                          }
                        }
                        try {
                          Map<String, FileMetadata> map=new LinkedHashMap<>();
                          map=storageSpecification.returnModifiedFilesFromDate(path,dateFrom);
                          System.out.println(storageSpecification.returnStringForOutput(map));
                        }
                        catch (MyException e)
                        {
                          System.out.println(e.getMessage());
                        }

                      }
                      else {
                        Date dateTo=null;
                        System.out.println("Insert date from: (format: dd/mm/yyyy)");
                        while(true) {
                          String d=sc.nextLine();
                          String d1=d;
                          String str[]=d.split("/");
                          if (d.contains("/") && str.length == 3 && str[0].length() == 2 && isNumeric(str[0]) && str[1].length() == 2 && isNumeric(str[1]) && str[2].length() == 4 && isNumeric(str[2])) {
                            try {
                              dateTo= new SimpleDateFormat("dd/mm/yyyy").parse(d1);
                              break;
                            } catch (Exception e) {
                              System.out.println("Bad date");
                            }
                          } else {
                            System.out.println("Wrong date format, try again");
                          }
                        }
                        try {
                          Map<String, FileMetadata> map=new LinkedHashMap<>();
                          map=storageSpecification.returnModifiedFilesBeforeDate(path,dateTo);
                          System.out.println(storageSpecification.returnStringForOutput(map));
                        }
                        catch (MyException e)
                        {
                          System.out.println(e.getMessage());
                        }
                      }
                    }
                    else if(odabran==16)
                    {
                      break;
                    }
                    else{
                      System.out.println("Selected option does not exist,try again");
                    }
                }
                else {
                  System.out.println("Selected option does not exist");
                }

              }

            }
            else if(odabran == 3)
            {
              break;
            }
            else
            {
              System.out.println("Selected option does not exist");
            }
        }
        else
        {
          System.out.println("Wront input, try again");
        }
        System.out.println("Thanks for using out aplication");
      }
    } catch (ClassNotFoundException exc) {
      exc.printStackTrace();
    }

    //String path = args[0];
    //StorageSpecification storage = StorageManager.getStorage(path);
    //System.out.println("Storage path: " + storage.getRootFolderPath());

  }
}
