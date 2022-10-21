public class GeneradorEncuentros {
    int dificultad, numeroJugadores,nivelJugadores;
    int crMaximoEncuentro;

    public GeneradorEncuentros(int dificultad, int numeroJugadores, int nivelJugadores, int crMaximoEncuentro) {
        this.dificultad = dificultad;
        this.numeroJugadores = numeroJugadores;
        this.nivelJugadores = nivelJugadores;
        this.crMaximoEncuentro = crMaximoEncuentro;
    }

    public Encuentro generarEncuentro(){

        //Se calculan los enemigos
        CalculadorEnemigos calcEnem = new CalculadorEnemigos(dificultad,numeroJugadores,nivelJugadores);
        ListaEnemigos enemigos =  calcEnem.calc();

        CalculadorRecompensas calcRec = new CalculadorRecompensas(crMaximoEncuentro);
        ListaRecompensas recompensas = calcRec.calc();


        return new Encuentro(numeroJugadores,nivelJugadores,enemigos,recompensas);
    }

}
