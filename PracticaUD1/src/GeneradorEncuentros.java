public class GeneradorEncuentros {
    int dificultad, numeroJugadores,nivelJugadores;
    long crMaximoEncuentro;

    public GeneradorEncuentros(int dificultad, int numeroJugadores, int nivelJugadores, long crMaximoEncuentro) {
        this.dificultad = dificultad;
        this.numeroJugadores = numeroJugadores;
        this.nivelJugadores = nivelJugadores;
        this.crMaximoEncuentro = crMaximoEncuentro;
    }

    public Encuentro generarEncuentro(){

        //Se calculan los enemigos
        CalculadorEnemigos calcEnem = new CalculadorEnemigos(dificultad,numeroJugadores,nivelJugadores,crMaximoEncuentro);
        ListaEnemigos enemigos =  calcEnem.calc();

        CalculadorRecompensas calcRec = new CalculadorRecompensas(crMaximoEncuentro);
        ListaRecompensas recompensas = calcRec.calc();


        return new Encuentro(numeroJugadores,nivelJugadores,enemigos,recompensas);
    }

}
