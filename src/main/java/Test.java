
public class Test {
    public static void main(String[] args) {
        StorageSpecification storageSpecification=null;

        try
        {
            Class.forName("com-komponente-projekat1.GoogleDriveImplementation");
            storageSpecification=StorageManager.getStorage("/Zarko");
            storageSpecification.createRootFolder();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
