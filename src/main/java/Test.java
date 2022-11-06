
public class Test {
  public static void main(String[] args) {
    try {
      Class.forName("LocalStorageImplementation");
      //Class.forName("GoogleDriveStorage");
    } catch (ClassNotFoundException exc) {
      exc.printStackTrace();
    }

    //String path = args[0];
    //StorageSpecification storage = StorageManager.getStorage(path);
    //System.out.println("Storage path: " + storage.getRootFolderPath());

    System.out.println("Odaberite opciju: ");
  }
}
