import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Interface para ler o arquivo e retornar uma lista de aeroportos
interface AirportReader {
    List<Airport> airports = readFile("airports.txt");

    static List<Airport> readFile(String s) {
        return null;
    }
}

// Classe que representa o modelo de tabela de aeroportos
class AirportTableModel extends AbstractTableModel {
    private List<Airport> airports;
    // Construtor, sobrescrita dos métodos getColumnCount, getRowCount e getValueAt
    public AirportTableModel(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return airports.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Airport airport = airports.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return airport.getAeroportoid();
            case 1:
                return airport.getName();
            case 2:
                return airport.getCity();
            case 3:
                return airport.getCountry();
            case 4:
                return airport.getIata();
            case 5:
                return airport.getIcao();
            default:
                return null;
        }
    }

}

// Classe que representa a janela principal do programa
class MainFrame extends JFrame {
    private JTable table;
    // Construtor, inicialização da tabela e adição de um botão para abrir a janela de cálculo de distância

    public MainFrame() {
        super("Aeroportos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar tabela
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        // Dados dos aeroportos aqui
                        new List[]{AirportReader.airports}
                },
                new String[] {
                        "aeroportoid", "nome", "cidade", "país", "iata", "icao"
                }
        ));

        // Adicionar tabela ao painel de conteúdo
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Adicionar botão para abrir janela de cálculo de distância
        JButton btnDistance = new JButton("Calcular Distância");
        btnDistance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DistanceCalculatorFrame().setVisible(true);
            }
        });
        add(btnDistance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

}


// Classe que representa a janela de detalhes do aeroporto
class DetailFrame extends JFrame {
    // Construtor, exibição de todos os campos do aeroporto selecionado
    public DetailFrame(Airport airport) {
        setTitle("Detalhes do Aeroporto");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(0, 2));

        // Adicionando todos os campos do aeroporto
        mainPanel.add(new JLabel("aeroportoid: "));
        mainPanel.add(new JTextField(airport.getAeroportoId()));

        mainPanel.add(new JLabel("Nome: "));
        mainPanel.add(new JTextField(airport.getName()));

        mainPanel.add(new JLabel("Cidade: "));
        mainPanel.add(new JTextField(airport.getCity()));

        mainPanel.add(new JLabel("País: "));
        mainPanel.add(new JTextField(airport.getCountry()));

        mainPanel.add(new JLabel("IATA: "));
        mainPanel.add(new JTextField(airport.getIata()));

        mainPanel.add(new JLabel("ICAO: "));
        mainPanel.add(new JLabel(airport.getIcao()));

        mainPanel.add(new JLabel("latitude: "));
        mainPanel.add(new JLabel(airport.getLatitude()));

        mainPanel.add(new JLabel("Longitude: "));
        mainPanel.add(new JLabel(airport.getLongitude()));

        mainPanel.add(new JLabel("altitude: "));
        mainPanel.add(new JLabel(airport.getAltitude()));

        mainPanel.add(new JLabel("fusoHorario: "));
        mainPanel.add(new JLabel(airport.getTimezone()));

        mainPanel.add(new JLabel("Dst: "));
        mainPanel.add(new JLabel(airport.getDst()));

        mainPanel.add(new JLabel("Tz: "));
        mainPanel.add(new JLabel(airport.getTz()));

        mainPanel.add(new JLabel("Tipo: "));
        mainPanel.add(new JLabel(airport.getType()));

        mainPanel.add(new JLabel("Fonte: "));
        mainPanel.add(new JLabel(airport.getSource()));
    }
    }

// Classe que representa a janela de cálculo de distância entre aeroportos
class DistanceCalculatorFrame extends JFrame {
    // Construtor, adição de dois ComboBox para escolher o aeroporto de partida e destino e um botão para calcular a distância
    private JComboBox<String> cbOriginAirport;
    private JComboBox<String> cbDestinationAirport;
    private JButton btnCalculate;

    public DistanceCalculatorFrame() {
        setTitle("Distance Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        cbOriginAirport = new JComboBox<>();
        cbDestinationAirport = new JComboBox<>();
        btnCalculate = new JButton("Calculate");

        add(new JLabel("Origin Airport: "));
        add(cbOriginAirport);
        add(new JLabel("Destination Airport: "));
        add(cbDestinationAirport);
        add(btnCalculate);

        // Populate the combo boxes with airport names
        // Add action listener to the button to perform the calculation
    }
}

public class AirportProgram {
    public void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
