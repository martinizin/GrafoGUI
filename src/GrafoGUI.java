import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrafoGUI {
    private JPanel pGeneral;
    private JTable tbMatrizAdyacencia;
    private JTextArea txtResultado;
    private JButton AGREGARVERTICEButton;
    private JButton AGREGARLADOButton;
    private JButton DIBUJARGRAFOButton;
    private JTextField txtx;
    private JTextField txty;
    private JTextField txtv1;
    private JTextField txtv2;
    private JPanel pGrafo;
    private JButton BFSButton;
    private JTextField txtInicio;
    private JButton DFSButton;

    public Grafo grafo=new Grafo(20);
    public GrafoPanel grafoPanel = new GrafoPanel(grafo);

    public DefaultTableModel modeloTabla;

    public GrafoGUI() {
        AGREGARVERTICEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int x=Integer.parseInt(txtx.getText());
                    int y=Integer.parseInt(txty.getText());

                    String etiqueta=grafo.getEtiquetaVerticeSiguiente();
                    Vertice vertice=new Vertice(x,y,etiqueta);
                    grafo.anadirVertice(vertice);
                    actualizarGrafo();
                    imprimirGrafo();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"ERROR DE INGRESO!");
                }
            }
        });
        AGREGARLADOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int v1=Integer.parseInt(txtv1.getText());
                    int v2=Integer.parseInt(txtv2.getText());


                    grafo.anadirLado(v1,v2);
                    actualizarGrafo();
                    imprimirGrafo();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"ERROR DE INGRESO!");
                }

            }
        });
        DIBUJARGRAFOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grafoPanel.setVisible(true);
                grafoPanel.repaint();
                grafoPanel.paintComponent(pGrafo.getGraphics());

            }
        });
        BFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vInicio=Integer.parseInt(txtInicio.getText());
                    String resultado=grafo.bfs(vInicio);
                    txtResultado.append("BFS desde el vértice"+vInicio+": "+resultado+" \n");
                    JOptionPane.showMessageDialog(null, "Datos erróneos");


                } catch (Exception ex) {

                }

            }
        });
        DFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int vInicio=Integer.parseInt(txtInicio.getText());
                    String resultado=grafo.busquedaenProfundidad(vInicio);
                    txtResultado.append("BFS desde el vértice"+vInicio+": "+resultado+" \n");
                    JOptionPane.showMessageDialog(null, "Datos erróneos");

                } catch (Exception ex) {

                }


            }
        });
    }
    public void imprimirGrafo(){
        txtResultado.setText("");
        txtResultado.append("Vertices\n");
        for(int i=0;i<grafo.getVertices().size();i++){
            txtResultado.append(i+": "+grafo.getVertices().get(i).toString()+"\n");

        }
        txtResultado.append("\n Lados\n");
        boolean [][]adjmatrix= grafo.getMatrizadyacencia();
        for(int i=0;i<grafo.getContarVertice();i++){
            for (int j=i;j<grafo.getContarVertice();j++){
                if(adjmatrix[i][j]){
                    txtResultado.append(grafo.getVertices().get(i).etiqueta+"-->"+grafo.getVertices().get(j).etiqueta+"\n");
                }
        }

        }
    }
    public void actualizarGrafo(){
        boolean[][]adjmatrix=grafo.getMatrizadyacencia();
        int vertexCount=grafo.getContarVertice();
        modeloTabla=new DefaultTableModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        for(int i=0;i<vertexCount;i++){
            modeloTabla.addColumn(grafo.getVertices().get(i).etiqueta);
        }
        //Informacion matriz adyacencia
        for (int i=0;i<vertexCount;i++){
            Object [] row=new Object[vertexCount];
            for (int j=0;j<vertexCount;j++){
                row[j]=adjmatrix[i][j]?1:0; //OPERADOR TERRNARIO
            }
            modeloTabla.addRow(row);
        }
        tbMatrizAdyacencia.setModel(modeloTabla);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GrafoGUI");
        frame.setContentPane(new GrafoGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
