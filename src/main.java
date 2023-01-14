import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AirportData extends JFrame {

    private List<Airport> airports;

    public AirportData() {
        airports = new ArrayList<>();
        readFile();
        createTable();
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("airports.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String city = data[2];
                String country = data[3];
                String iata = data[4];
                String icao = data[5];
                double latitude = Double.parseDouble(data[6]);
                double longitude = Double.parseDouble(data[7]);
                int altitude = Integer.parseInt(data[8]);
                double timezone = Double.parseDouble(data[9]);
                String dst = data[10];
                String tz = data[11];
                String type = data[12];
                String source = data[13];
                Airport airport = new Airport(id, name, city, country, iata, icao, latitude, longitude, altitude, timezone, dst, tz, type, source);
                airports.add(airport);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String[] columnNames = {"aeroportoid", "nome", "cidade", "país", "iata", "icao"};
        Object[][] data = new Object[airports.size()][6];
        for (int i = 0; i < airports.size(); i++) {
            data[i][0] = airports.get(i).getId();
            data[i][1] = airports.get(i).getName();
            data[i][2] = airports.get(i).getCity();
            data[i][3] = airports.get(i).getCountry();
            data[i][4] = airports.get(i).getIata();
            data[i][5] = airports.get(i).getIcao();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        add(table);
        setSize(600, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AirportData();
    }
}
