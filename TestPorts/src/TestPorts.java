import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class TestPorts {

  public static void main(String[] args) {
    String csv = JOptionPane.showInputDialog("Enter list of ports");
    Set<ServerSocket> sockets = new HashSet<>();
    Arrays.stream(csv.split(",")).mapToInt(Integer::parseInt).forEach((x)->{
      try{sockets.add(new ServerSocket(x));}catch(Exception e) {System.err.println("Unable to open port "+x);}
    });
    JOptionPane.showMessageDialog(null,"Servers listening on ports: "+String.join(",",sockets.stream().map(x->""+x.getLocalPort()).collect(Collectors.toList())));
    sockets.stream().forEach(t -> {
      try {
        t.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
