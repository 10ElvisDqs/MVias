package mvias;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ArbolMVias implements IArbolMVias
{   
    int vias;
    Nodo raiz;

   
    public class Nodo{
        int dato[];// el vector de datos que tendra cada nodo
        Nodo hijo[];// vector de hijos que tenda el nodo
        int nD;// cantidad de datos que tendra el vector que estara dentro del nodo
        public Nodo(){
            dato=new int [vias-1];
            hijo= new Nodo[vias];
            nD=0;
           int i=0;
           while(i<vias-1){
               dato[i]=-999;
               hijo[i]=null;
               i++;
           }   
           hijo[i]=null;
        }
        
    }
    
    public ArbolMVias(int _vias){
        if(_vias>2){
            vias=_vias;
        }else{
            System.out.println("las vias tienen que ser mayor a 2");
        }
    }
    
    public Nodo getRaiz(){
        return raiz;
    }
   
    public JPanel getdibujo() 
    {
        return new Grafica(this);
    }
    
     public void insertarSinRepetir(int dato){
        raiz=insertarSinRepetir(raiz, dato);
    }
     
    public String mostrarDatosDelNodo(Nodo p){
        String c="";
        int i=0;
        while(i<p.nD){
            c=c+p.dato[i]+"-";
            i++;
        }
        return c;
    }  
     
    private Nodo insertarSinRepetir(Nodo a, int dato){
        boolean insertoDato=false;
        if(a==null){// el arbol no tiene datos
            Nodo nuevo=new Nodo();
            nuevo.dato[nuevo.nD]=dato;
            nuevo.nD++;
            a=nuevo;    
            
            
        }else{
            if (buscar(dato)==false)
            {
                if(hayEspacio(a) ){//(a.nD <vias-1){
                        a.dato[a.nD]=dato;
                        a.nD++;
                        ordenar(a);    
                    }else{
                        int i=0;
                        while(i<a.nD && insertoDato==false){
                            if(dato<a.dato[i]){
                                a.hijo[i]=insertarSinRepetir(a.hijo[i], dato);
                                insertoDato=true;
                            }
                            i++;
                        }
                        if(insertoDato==false){
                            a.hijo[i]=insertarSinRepetir(a.hijo[i], dato);
                        }
                    }
            }else{
                JOptionPane.showMessageDialog(null,"No se acepta valores repetidos");
            }
                    
        
        }
        return a;
    }
    
    public boolean DatoRepetido(Nodo p,int dato){
        int i=0;
        while(i<=p.nD){
            if (p.dato[i]==dato)
            {
                return true;
            }
            i++;
        }
        return false;
    };
    public void insertarElemento(int dato){
        raiz=insertarElemento(raiz, dato);
    }
    private Nodo insertarElemento(Nodo a, int dato){
        boolean insertoDato=false;
        if(a==null){// el arbol no tiene datos
            Nodo nuevo=new Nodo();
            nuevo.dato[nuevo.nD]=dato;
            nuevo.nD++;
            a=nuevo;
        }else{         
            if(hayEspacio(a)){//(a.nD <vias-1){
                a.dato[a.nD]=dato;
                a.nD++;
                ordenar(a);
            }else{
                int i=0;
                while(i<a.nD && insertoDato==false){
                    if(dato<a.dato[i]){
                        a.hijo[i]=insertarElemento(a.hijo[i], dato);
                        insertoDato=true;
                    }
                    i++;
                }
                if(insertoDato==false){
                    a.hijo[i]=insertarElemento(a.hijo[i], dato);
                }
            }
        
        }
        return a;
    }
    public boolean hayEspacio(Nodo p){
        return p.nD <vias-1 ;
    }
    
    private void ordenar(Nodo p){
       // for(int i=0; i<vias-2; i++){
       int i=0;
       while(i<vias-2){
            if(p.dato[i]>p.dato[i+1]){
                int auxiliar=p.dato[i+1];
                p.dato[i+1]=p.dato[i];
                p.dato[i]=auxiliar;
            }
           /* if (p.dato[i]==-999)
           {
               int aux=p.dato[i];
               p.dato[i]=p.dato[i+1];
               p.dato[i+1]=aux;
           }*/
       i++;
       }
      //  }
    }
    //contar Cuantos Nodos Hay en el Arbol
    public int contar(){
        return contar(raiz);
    }
    
    private int contar(Nodo p){
        int c=0;
        if (p==null) {            
            c=0;
        }else{
            int i=0;
            int contarHijo=0;
            int contarTotal=0;
            while(i<=p.nD){
                contarHijo=contar(p.hijo[i]);
                contarTotal=contarTotal+contarHijo;
                i++;
            }
            c=contarTotal +1;
        }
        return c;
    }
    // fin de contar Cuantos Nodos Hay en el Arbol
    
    // contar los Datos  que hay en el arbolMVias
    public int contarDatos(){
        return contarDatos(raiz);
    }
    
    private int contarDatos(Nodo p){
      
        int cantidad=0;
        if(p!=null){
            for(int i=0; i<p.nD; i++){
                cantidad++;
            }
            for(int j=0; j<vias;j++){//vias
                cantidad=contarDatos(p.hijo[j])+cantidad;
            }
        }
        return cantidad;
    }
    // fin de contar Datos en el arbol
    
  
    
        //contr pares
    public int contarPares(){
        return contarPares(raiz);
    }
    private int contarPares(Nodo p){
        int c=0;
        if(p==null){
            c=0;
        }else{
            if (esHoja(p)) {
                int i=0;
                while(i<p.nD){
                    if (p.dato[i]!=-999) {
                        if (p.dato[i]%2==0) {
                            c++;   
                        }                    
                    }                  
                    i++;
                }
            }else{
                int i=0;
                while(i<p.nD){
                     if (p.dato[i]!=-999) {
                       if (p.dato[i]%2==0) {
                            c++;   
                        }
                     }
                    i++;
                }
                int j=0;
                while(j<vias) {
                    c=c+contarPares(p.hijo[j]);
                    j++;
                }
            }    
        }
     return c;   
    }
    //fin de contar pares
    
    //contar numeros impares en el arbol
    public int contarImpares(){
        return contarImpares(raiz);
    }
    private int contarImpares(Nodo p){
        int inpares=0;
        if (p==null)
        {
            inpares=0;
        }else{
            if (esHoja(p))
            {
                int i=0;
                while(i<p.nD){
                    if (p.dato[i]%2 != 0)
                    {
                        inpares++;
                    }
                    i++;
                }
            }else{
                int i=0;
                while(i<p.nD){
                    if (p.dato[i]%2 != 0)
                    {
                        inpares++;
                    }
                    i++;
                }
                int j=0;
                while(j<=p.nD){
                    inpares=inpares+contarImpares(p.hijo[j]);                
                    j++;
                }
            }
        }
           
        return inpares;
    }
    // fin decontar numeros impares en el arbol 
    
    //metodo sumar
    public int sumar(){
        return sumar(raiz);
    }
    
    private int sumar(Nodo p){
        int sum=0;
        if(p==null){
            sum=0;
        }else{
            if (esHoja(p)) {
                int i=0;
                while(i<p.nD){
                    if (p.dato[i]!=-999) {
                        sum=sum+p.dato[i];                   
                    }                  
                    i++;
                }
            }else{
                int i=0;
                while(i<p.nD){
                     if (p.dato[i]!=-999) {
                        sum=sum+p.dato[i];
                     }
                    i++;
                }
                int j=0;
                while(j<vias) {
                    sum=sum+sumar(p.hijo[j]);
                    j++;
                }
            }    
        }
        return sum;
    }
    // fin de sumar
    
    // contar los nodos imcompletos en el arbol
    public int contarIncompletos(){
        return contarIncompletos(raiz);
    }
    
    private int contarIncompletos(Nodo p){
        int contarInc=0;
        if (p==null) {
            contarInc=0;
        }else{
            if (esHoja(p)) {
                contarInc=0;
            }else{
                int i=0;
                boolean incompleto=false;
                while(i<=p.nD){
                        if (p.hijo[i]==null  && incompleto==false) {
                            contarInc=1;
                            incompleto=true;
                        }
                     i++;
                }           
                int j=0;
                while(j<=p.nD) {                      
                            contarInc=contarInc+contarIncompletos(p.hijo[j]);       
                  j++;
                } 
            }
        }
        return contarInc;
    }
    // fin de contar los nodos completos en el arbol
    public int contarCompletos(){
        return contarCompletos(raiz);
    }
    
    private int contarCompletos(Nodo p){
        int contarCom=0;
        if (p==null) {
            contarCom=0;
        }else{
            if (esHoja(p)) {
                contarCom=0;
            }else{
                int i=0;
                boolean completo=true;
                while(i<=p.nD) {
                        if (p.hijo[i]==null  && completo==true) {                       
                            completo=false;
                        }
                     i++;
                }     
                if (completo==true) {
                    contarCom=1;
                }
                int j=0;
                while(j<=p.nD) {                      
                            contarCom=contarCom+contarCompletos(p.hijo[j]);        
                     j++;
                }  
            }
        }
        return contarCom;
    }
    // fin de contar los nodos completos en el arbol
    
    // buscar dato mayor en el arbol
    public int DatoMayor(){
        return DatoMayor(raiz);
    }
    
    private int DatoMayor(Nodo p){
        int mayor=0;
        if (p==null) {
            mayor=0;
        }else{
            if (esHoja(p)) {
                mayor=p.dato[p.nD-1];
            }else{
                if (p.hijo[p.nD]==null) {
                    mayor=p.dato[p.nD-1];
                }else{
                    mayor=DatoMayor(p.hijo[p.nD]);
                }
            }
        }
        
        return mayor;
    }
    // fin de buscar dato mayor en el arbol
    
    // buscar el dato menor en el arbol
    public int DatoMenor(){
        return DatoMenor(raiz);
    }
    private int DatoMenor(Nodo p){
        int datoMenor=0;
        if (p==null) {
            datoMenor=0;
        }else {
            if (esHoja(p)) {
                datoMenor=p.dato[0];
            }else {
                if (p.hijo[0]==null) {
                    datoMenor=p.dato[0];
                }else {
                    datoMenor=DatoMenor(p.hijo[0]);
                }
            }
        }
        return datoMenor;
    }
    //fin de buscar el dato menor en el arbol
    
    // buscar el nodo con mayor dato
    public Nodo buscarMayor(){
        return buscarMayor(raiz);
    }
    private Nodo buscarMayor(Nodo p){
        Nodo t=new Nodo();
        if (p==null) {
            t=null;
        }else {
            if (esHoja(p)) {
                t=p;
            }else {
                if (p.hijo[p.nD]==null) {
                    t=p;
                }else{
                    t=buscarMayor(p.hijo[p.nD]);
                }
            }
        }
        return t;
    }
    // fin de buscar el nodo con mayor dato
    
     // buscar el nodo con menor dato
    public Nodo buscarMenor(){
        return buscarMenor(raiz);
    }
    private Nodo buscarMenor(Nodo p){
        Nodo t=new Nodo();
        if (p==null) {
            t=null;
        }else {
            if (esHoja(p)) {
                t=p;
            }else {
                if (p.hijo[0]==null) {
                    t=p;
                }else{
                    t=buscarMenor(p.hijo[0]);
                }
            }
        }
        return t;
    }
    // fin de buscar el nodo con menor dato
    
     //  metodo Es Hoja
    private boolean esHoja(Nodo p){
        boolean h=true;
        if (p==null) {
            h=false;
        }else{
            int i=0;
            while (i<=p.nD && h==true){
                if (p.hijo[i]==null) {
                    h=true;
                }else{
                     h=false;
                }
                i++;
            }   
        }
        return h;
    } 
    // fin de EsHoja
    
    //metodo buscar dato |si se encuentra el el dato en el nodo |
    public boolean buscar(int d){
        return buscar(raiz,d);
    }
    
    private boolean buscar(Nodo p,int d){
        boolean esta=true;
        if (p==null) {
            esta=false;
        }
        else{
            if (esHoja(p)) {
                int pos=buscarDato(p,d);//buscar entre el rango |0 a 2| caso contrario -1
                if (esValido(pos)) {//otra forma de preguntar seria |si(pos>=0 && pos<p.nDato)|
                    esta=true;
                }
                else{
                    esta=false;
                }
            }
            else{//si no es hoja 
                int pos=buscarDato(p,d);
                if (esValido(pos)) {
                    esta=true;
                }
                else{
                    int i=0;
                    boolean ingreso=false;
                    while(i<p.nD && ingreso==false){
                        if (d<p.dato[i]) {
                            esta=buscar(p.hijo[i],d);
                            ingreso=true;
                        }
                        i++;
                    }
                    if (ingreso==false) {
                        esta=buscar(p.hijo[i],d);
                    }
                }
            }
        }
        return esta;
    }
    //fin de buscar
    
    // metodo modificar considerando la coeherencia del Arbol
    public void modificar(int anterior,int nuevo){
        if (raiz==null){
            //nada
        }
        else{
            Nodo t=new Nodo();
            t=Buscar(raiz,anterior);
            if (t != null)
            {
                int pos=buscarDato(t,anterior);
                if (pos>=0 && pos<t.nD)//esValido(pos)
                {
                    t.dato[pos]=nuevo;
                }
            }
        }
    }
    //fin de modificar
    
    //metodo buscar Nodo
    private Nodo Buscar(Nodo p,int d) {
       Nodo t=new Nodo();
        if (p==null) {
            t=null;
        }else{
            if (esHoja(p)) {
                int pos=buscarDato(p,d);
                if (esValido(pos)) {
                    t=p;
                }else{
                    t=null;
                }
            }else{
                int pos=buscarDato(p,d);
                if (esValido(pos)) {
                    t=p;
                }else{
                    int i=0;
                    boolean ingreso=false;
                    while(i<p.nD && ingreso==false) {
                        if (d<p.dato[i]) {
                           t=Buscar(p.hijo[i],d);
                           ingreso=true;
                        }
                        i++;
                    }
                    if (ingreso==false) {
                       t=Buscar(p.hijo[i],d); 
                    }
                }
            }
        }
        return t;
    }
    //fin de buscarNodo
    
    //metodo altura del Arbol
    public int altura(){
        return altura(raiz);
    }
    //enmascarando
    private int altura(Nodo p){
        int a;//altura
        if (p==null) {
            a=0;
        }else{
            if (esHoja(p)) {
                a=1;
            }else{
                int i=0;
                int mayor=0;
                int ah=0;
                while(i<=p.nD) {
                     ah=altura(p.hijo[i]);
                    if (ah>mayor) {
                        mayor=ah;
                    }
                    i++;
                }                 
                a=mayor+1;                  
            }
        }                 
        return a;
    }
    // fin de altura
    
    // altura apartir de un dato
    public int AlturaDato(int d)
    {
        return AlturaDato(raiz, d);
    }
    private int AlturaDato(Nodo p,int d)
    {  
       int altura=0;
        if (p==null )
        {
            altura=0;
        }else{
            if (esHoja(p) && buscar(p,d)==true)
            {
                altura=1;
            }else{
                int pos=buscarDato(p,d);
                if(esValido(pos)){         
                    altura=1;
                    int i=0;
                    int mayor=0;
                    int h=0;
                    while(i<=p.nD){
                        h=altura(p.hijo[i]);
                        if (h>mayor)
                        {
                            mayor=h;
                        }
                        i++;
                    }
                    altura=altura+mayor;
                }else{
                    int i=0;
                    boolean encontro=false;
                    while(i<=p.nD && encontro==false){
                   altura=AlturaDato(p.hijo[i],d);
                        if (altura!=0)
                        {
                            encontro=true;
                        }
                   i++;
                    }
                }
            }
            
        }
        
     return altura;
    }

    
    // fin
    
     // metodo  tipos de recorido
    // recorido InOrden
    public void InOrden(JTextArea jta){
        InOrden(raiz,jta);
    }
    private void InOrden(Nodo p,JTextArea jta){
        if (p==null)
        {
            //no hacer nada
        }else{
            if (esHoja(p))
            {
                mostrar(p,jta);//en prueva
                
            }else{
                int i=0;
                while(i<p.nD){
                    InOrden(p.hijo[i],jta);
                    jta.append(String.valueOf(p.dato[i])+"-");//imprime el dato en el JtextArea  
             //       mostrar(p.dato[i]);
                    i++;
                }
                InOrden(p.hijo[i],jta);
            }
        }
    }
    // fin de InOrden
    
    // recorido PostOrden
    public void PostOrden(JTextArea jta){
        PostOrden(raiz,jta);
    }
    private void PostOrden(Nodo p,JTextArea jta){
        if (p==null)
        {
            //no hacer nada
        }else{
            if (esHoja(p))
            {
               mostrar(p,jta);//falta este metodo
            }else{
                int i=0;
                while(i<p.nD){
                    if (i==0) {
                        PostOrden(p.hijo[i],jta);
                    }
                    PostOrden(p.hijo[i+1],jta);
                //    mostrar(p.dato[i]);
                    jta.append(String.valueOf(p.dato[i])+"-");//imprime el dato en el JtextArea  
                    i++;
                }
            }
        }
    } 
    // fin de PostOrden
    
    // recorido Preorden
    // tarea
    public void PreOrden(JTextArea jta){
        PreOrden(raiz,jta);
    }
    private void PreOrden(Nodo p,JTextArea jta){
        if (p==null) {
            //nada
        }else{
            if (esHoja(p)) {
                mostrar(p,jta);
            }else{
                int i=0;
                while(i<p.nD) {
                    jta.append(String.valueOf("("+p.dato[i])+")-");//imprime el dato en el JtextArea       
                    if (i==0) {
                      PreOrden(p.hijo[i],jta);  
                    }
                    PreOrden(p.hijo[i+1],jta);
                    i++;
                }
            }
        }
    }
    // fin de PreOrden
    
    // metodo eliminar
    public Nodo eliminar(int d){
        raiz=eliminar(raiz,d);
        return raiz;
    }
    private Nodo eliminar(Nodo p,int d){
        if (p==null) {
            //no hacer nada
        }else{
            if (esHoja(p)) {
                p=eliminarDato(p,d);
                ordenar(p);
                if(p.nD==0){
                    p=null;
                }
            }else{
              int pos=buscarDato(p,d);
                if (esValido(pos)) {
                    int i=pos;
                    boolean eliminado=false;
                    while(i>=0 && eliminado==false) {
                        if (existeHijo(i)) {
                            Nodo pM=new Nodo();
                            pM=buscarMayor(p.hijo[i]);
                            int mayor=pM.dato[pM.nD-1];
                            p.dato[pos]=mayor;
                            p.hijo[i]=eliminar(p.hijo[i],mayor);
                            ordenar(p);
                            eliminado=true;           
                        }
                        i--;
                    }
                    
                    if (eliminado==false) {
                        i=pos+1;
                        while(i<=p.nD && eliminado==false){
                            if (existeHijo(i)) {
                                Nodo pMen=new Nodo();
                                pMen=buscarMenor(p.hijo[i]);
                                int menor=pMen.dato[0];
                                p.dato[pos]=menor;
                                p.hijo[i]=eliminar(p.hijo[i],menor);
                                eliminado=true;
                                ordenar(p);
                                
                            }
                            i++;
                        }
                    }
                }else{
                    int i=0;
                    boolean eliminado=false;
                    while(i<p.nD && eliminado==false) {
                        if (d<p.dato[i]) {
                            p.hijo[i]=eliminar(p.hijo[i],d);
                            eliminado=true;
                        }
                        i++;
                    }
                    if (eliminado==false) {
                        p.hijo[i]=eliminar(p.hijo[i],d);
                    }
                }
            }
        }
        return p;
           
    }
    // fin de eliminar
    
    // metodo si existe hijo en cierta poscicion
    private boolean existeHijo(int i){
        return raiz.hijo[i]!=null;
    } 
    // fin del metodo si existe hijo en cierta poscicion
    public void eliminarDato(int d){
        raiz=eliminarDato(raiz,d);
    }
    //Metodo eliminar dato del nodo
    private Nodo eliminarDato(Nodo p,int d){
        if (p==null) {
            //no hacer nada
        }else{
            if (esHoja(p)) {
                int pos=buscarDato(p,d);//posicionn del dato
                if (esValido(pos)) {
                 int i=pos;
                 while(i<p.nD) {
                        if (i+1==p.nD) { 
                            p.dato[i]=-999;
                        }else{
                          p.dato[i]=p.dato[i+1];
                        }
                     i++;
                 }
                 p.nD--;
                }
                ordenar(p);
            }else{
                    int pos=buscarDato(p,d);//posicionn del dato
                    if (esValido(pos)) {
                     int i=pos;
                     while(i<p.nD) {
                            if (i+1==p.nD) { 
                                p.dato[i]=-999;
                            }else{
                              p.dato[i]=p.dato[i+1];
                            }
                         i++;
                     }
                     p.nD--;
                     ordenar(p);
                    }
            }
        }
        return p;
    }
    //fin de Metodo eliminar dato del nodo
     ////////////////////////////////////////////////////////////////////////////////
    public void mostrar(JTextArea jta){
        mostrar(raiz,jta);
    }
    private void mostrar(Nodo p,JTextArea jta){
                
                if (esHoja(p))
        {     int i=0;
            while(i<p.nD) {    
                    jta.append(String.valueOf(p.dato[i])+"-");//imprime el dato en el JtextArea  
                    i++;
                }
        }else{
                    int i=0;
                while(i<p.nD) {    
                    jta.append(String.valueOf(p.dato[i])+"-");//imprime el dato en el JtextArea  
                    i++;
                }
                int j=0;
               while(j<=p.nD){
                   mostrar(p.hijo[j],jta);
                   j++;
               }
                }
       }
     ////////////////////////////////////////////////////////////////////////////////
    
     ////////////////////////////////////////////////////////////////////////////////
    public int buscarDato(Nodo p,int d){//buscar entre el rango |0 a 2| caso contrario -1
        int n=-1;
        int i=0;
        boolean pillo=false;
        while(i<p.nD && pillo==false) {  /*fualta verificar*/
            if(p.dato[i]==d) {
                n=i;
                pillo=true;
            }
            i++;
        }
        return n;
    }
    
    public boolean esValido(int pos){
        return (pos>=0 && pos<raiz.nD);/*fualta verificar*/
    }
    ////////////////////////////////////////////////////////////
    public String Mostrar(){
        return Mostrar(raiz);
    }
    private String Mostrar(Nodo p){
        String cadena=" ";
        if (p==null) {
            cadena=" ";
        }else{
            if (esHoja(p)) {
                int i=0;
                while(i<p.nD){
                    cadena=cadena+p.dato[i]+"--";
                    i++;
                }
            }else{
                int j=0;
                while(j<=p.nD){
                    cadena=cadena+Mostrar(p.hijo[j]);
                    j++;
                }
                int i=0;
                while(i<p.nD){
                    cadena=cadena+p.dato[i]+"--";
                    i++;
                }
                
            }
        }
        return cadena;
    }
    //////////////////// EXAMEN 2 ///////////////////
    
    public void resetear(){
        resetear(raiz);
    }
    private void resetear(Nodo p){
        if (p==null)
        {     
        }else{
            if (esHoja(p))
            {
                int i=0;
                while(i<p.nD){
                    p.dato[i]=0;
                    i++;
                    //p.nD--;
                }
            }else{
                int i=0;
                while(i<=p.nD){
                    resetear(p.hijo[i]);
                    i++;
                }
            }
        }
        
    }
    public String MostrarDatosNodo(){
        return MostrarDatosNodo(raiz);
    }
    private String MostrarDatosNodo(Nodo p){
        String cadena=" ";
        if (p==null)
        {
            cadena=" ";
        }else{
            int i=0;
            while(i<p.nD){///  |8|9|10|
                cadena=cadena+p.dato[i]+"-";
                i++;
            }
        }
        return cadena;
    }
    
    public int sumarPar(){
        return sumarPar(raiz);
    }
    
    private int sumarPar(Nodo p){
        int suma=0;
        if (p==null)
        {
            suma=0;
        }else{
           int i=0;
           while(i<p.nD){
               if (p.dato[i]%2==0)
               {
                   suma=suma+p.dato[i];
               }
               i++;
           }
        }
        return suma;
    
    }
    
        public void podar(){
            raiz=podar(raiz);
        }
        private Nodo podar(Nodo p){
            Nodo copia=new Nodo();
            copia=p;
            if (copia==null){

            }else{
                if (esHoja(copia))
                {
                    copia=null;
                }else{
                    int i=0;
                    while(i<=copia.nD){
                        copia.hijo[i]=podar(copia.hijo[i]);
                        i++;
                    }
                }
            }
           p=copia;
           return p;
        }
 
}

