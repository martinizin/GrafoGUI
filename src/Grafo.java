import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    //  Busqueda en profundidad
    public String busquedaenProfundidad(int verticeInicio){
        StringBuilder resultado=new StringBuilder();
        boolean []verticeVisitado=new boolean[contarVertice];
        Stack <Integer> pila=new Stack<>();
        pila.push(verticeInicio);
        while (!pila.isEmpty()){
            int v =pila.pop();
            if (!verticeVisitado[v]){
                verticeVisitado[v]=true;
                resultado.append(vertices.get(v).etiqueta).append(" ");
                for (int i=contarVertice-1; i>=0;i--){
                    if (matrizadyacencia[v][i]&& !verticeVisitado[i]){
                        pila.push(i);
                    }
                }

            }
        }
        return resultado.toString().trim();
    }
    //RECORRIDO POR ANCHURA
    public String bfs(int verticeInicio){
        StringBuilder resultado=new StringBuilder();
        boolean []verticeVisitado=new boolean[contarVertice];
        Queue<Integer> cola=new LinkedList<>();
        verticeVisitado[verticeInicio]=true;
        cola.add(verticeInicio);

        while (!cola.isEmpty()){
            int v=cola.poll();
            resultado.append(vertices.get(v).etiqueta).append(" ");
            for (int i=0;i<=contarVertice;i++){
                if (matrizadyacencia[v][i]&& !verticeVisitado[i]){
                    verticeVisitado[i]=true;
                    cola.add(i);
                }
            }
        }
        return resultado.toString().trim();
    }

}
