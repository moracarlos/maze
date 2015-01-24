/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maze {
    //Atributos
    private int f;
    private int c;
    private int[][] laberinto;
    private Stack p;
    
    //Metodos
    public Maze(){
        Scanner in = new Scanner(System.in);
        f=in.nextInt();
        c=in.nextInt();
        laberinto= new int[(f+f-1)][(c+c-1)];
        p=new Stack();
    }
    
    public void init(){
        for (int i=0; i<f+f-1; i++){
            for (int j=0; j<c+c-1; j++){
                laberinto[i][j]=-1;
            }
        }
    }
    public void dfs (Par nodo){
        Par par;
        Par base=new Par(0,0);
        Par origen, destino;
        int id=0;
        int rand;
        float prob;
        char dir;
        char ant='X';
        Stack q= new Stack();
        List direcciones= new ArrayList();
        p.push(nodo);
        q.push(base);
        while(!p.empty()){
            direcciones.add('N');
            direcciones.add('S');
            direcciones.add('O');
            direcciones.add('E');
            nodo=(Par)p.peek();
            laberinto[nodo.x()][nodo.y()]=id++;
            q.pop();
            p.pop();
            while (!direcciones.isEmpty()){
                
                rand= (int)(Math.random()*direcciones.size());
                dir=direcciones.get(rand).toString().charAt(0);
                direcciones.remove(rand);
                
                if (dir=='N'){ //Revisar valores porque hay error
                    if (nodo.x()>1 && laberinto[nodo.x()-2][nodo.y()]==-1){
                        par= new Par ((nodo.x())-2,nodo.y());
                        base= new Par (nodo.x(), nodo.y());
                        p.push(par);
                        q.push(base);
                        //laberinto[nodo.x()-1][nodo.y()]=id;
                        //laberinto[nodo.x()-2][nodo.y()]=-2;
                        }
                }
                if (dir=='S'){
                    if (nodo.x()<f+f-2 && laberinto[nodo.x()+2][nodo.y()]==-1){
                        par= new Par(nodo.x()+2, nodo.y());
                        base= new Par (nodo.x(), nodo.y());
                        p.push(par);
                        q.push(base);
                        //laberinto[nodo.x()+1][nodo.y()]=id;
                        //laberinto[nodo.x()+2][nodo.y()]=-2;
                        }
                }
                if (dir=='O'){
                    if (nodo.y()>0 && laberinto[nodo.x()][nodo.y()-2]==-1){
                        par= new Par(nodo.x(), nodo.y()-2);
                        base= new Par (nodo.x(), nodo.y());
                        p.push(par);
                        q.push(base);
                        //laberinto[nodo.x()][nodo.y()-1]=id;
                        //laberinto[nodo.x()][nodo.y()-2]=-2;
                        }
                }
                if (dir=='E'){
                    if (nodo.y()<c+c-2 && laberinto[nodo.x()][nodo.y()+2]==-1){
                        par= new Par(nodo.x(), nodo.y()+2);
                        base= new Par (nodo.x(), nodo.y());
                        p.push(par);
                        q.push(base);
                        //laberinto[nodo.x()][nodo.y()+1]=id;
                        //laberinto[nodo.x()][nodo.y()+2]=-2;
                        }
                }
            }
            if (!q.isEmpty()){
            origen=(Par)q.peek(); //error
            destino=(Par)p.peek();
            if ( laberinto[destino.x()][destino.y()]==-1){
            if (origen.x()>destino.x() && origen.y()==destino.y()){ //Norte
                    laberinto[origen.x()-1][origen.y()]=id;
            }
            if (origen.x()<destino.x() && origen.x()==origen.x()){ //Sur
                    laberinto[origen.x()+1][origen.y()]=id;
            }
            if (origen.x()==destino.x() && origen.y()>destino.y()){
                laberinto[origen.x()][origen.y()-1]=id;
            }
            if (origen.x()==destino.x() && origen.y()<destino.y()){
                laberinto[origen.x()][origen.y()+1]=id;
            }
         
            }
            }
        }
     }
     public void pr(){
        PrintWriter out = null;
        int i;
        try {
            out = new PrintWriter(new FileWriter("Lab.pbm"));
            out.println("P1");
            out.println((2*c-1+2)+" "+(2*f-1+2));
            for (i=0; i<2*c-1+2;i++){
                out.print("1");
            }
            for (i=0; i<2*f-1; i++){
                out.print("\n");
                out.print("1");
                for (int j=0; j<2*c-1; j++){
                    if (laberinto[i][j]!=-1) out.print("0");
                    if (laberinto[i][j]==-1) out.print("1");
                }
                out.print("1");
            }
            out.print("\n");
            for (i=0; i<2*c-1+2;i++){
                out.print("1");
            }
        } catch (IOException ex) {
            Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
     }

    public static void main(String[] args) {
        Maze laberinto= new Maze();
        laberinto.init();
        Par nodo=new Par(0,0);
        laberinto.dfs(nodo);
        laberinto.pr();
    }
}

class Par{
    private int x;
    private int y;
    
    public Par(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int x(){
        return x;
    }
    public int y(){
        return y;
    }
    public void x(int a){
        x=a;
    }
    public void y(int a){
        y=a;
    }
}
