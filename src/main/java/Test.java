import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("The choosen path does not exist, please insert another:");
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
                break;
              }else{
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
                break;
              }else{
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
      }

      try{
        storageSpecification.createRootFolder();
        System.out.println("Storage is ready!");
      }
      catch (MyException e)
      {
        System.out.println(e.getMessage());
      }



    } catch (ClassNotFoundException exc) {
      exc.printStackTrace();
    }

    //String path = args[0];
    //StorageSpecification storage = StorageManager.getStorage(path);
    //System.out.println("Storage path: " + storage.getRootFolderPath());

  }
}
