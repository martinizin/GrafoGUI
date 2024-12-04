import java.util.ArrayList;

public class Grafo {
    public ArrayList<Vertice> vertices;
    public boolean[][]matrizadyacencia;
    public int contarVertice;

    public Grafo(int maxVertices) {
        vertices=new ArrayList<>();
        matrizadyacencia=new boolean[maxVertices][maxVertices];
        contarVertice=0;
    }



    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public boolean[][] getMatrizadyacencia() {
        return matrizadyacencia;
    }

    public int getContarVertice() {
        return contarVertice;
    }
    public void anadirVertice(Vertice v){
        vertices.add(v);

    }
    public void anadirLado(int v1, int v2){

        if (v1<contarVertice && v2<contarVertice){
            matrizadyacencia[v1][v2]=true;
            matrizadyacencia[v2][v1]=true; //Grafos no dirigidos
        }

    }
    public String getEtiquetaVerticeSiguiente(){
        return String.valueOf((char)('A'+contarVertice++));
    }
}
