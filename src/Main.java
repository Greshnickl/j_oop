import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\Greshnick\\Desktop\\oop2";
        FileMonitor fileMonitor = new FileMonitor(folderPath);


        RealTimeMonitor realTimeMonitor = new RealTimeMonitor(fileMonitor);
        Thread monitorThread = new Thread(realTimeMonitor);
        monitorThread.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: (commit, info <filename>, status, exit)");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];

            switch (command) {
                case "commit":
                    fileMonitor.commit();
                    break;
                case "info":
                    if (parts.length > 1) {
                        String filename = parts[1];
                        fileMonitor.printFileInfo(filename);
                    } else {
                        System.out.println("Filename is required for 'info' command.");
                    }
                    break;
                case "status":
                    fileMonitor.printStatus();
                    break;
                case "exit":
                    realTimeMonitor.stop();
                    try {
                        monitorThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
