package dam.ad.practica3.main;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import dam.ad.practica3.javabeans.Asignatura;

public class RegistrarAsignaturas {
	
	private static final Scanner sc = new Scanner(System.in);
	static final ArrayList<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();
	
	public static void main(String[] args) {
		
		solicitarAsignaturas();
	}

	private static void solicitarAsignaturas() {
		
		int cont = 0;
		int codAsignatura;
		String nombre;
		String profesor;
		int numHoras;
		Asignatura asignatura;
		
		System.out.println("* * * REGISTRO ASIGNATURAS * * *");
		
		while (cont < 5) {
			System.out.print("\nCódigo de la asignatura: ");
			codAsignatura = sc.nextInt();
			sc.nextLine();
			System.out.print("Nombre: ");
			nombre = sc.nextLine();
			System.out.print("Profesor: ");
			profesor = sc.nextLine();
			System.out.print("Número de horas: ");
			numHoras = sc.nextInt();
			sc.nextLine();
			
			asignatura = new Asignatura(codAsignatura, nombre, profesor, numHoras);
			listaAsignaturas.add(asignatura);
			
			cont++;
			
		}
		
		escribirFicheroBinarioSecuencial();
	}

	private static void escribirFicheroBinarioSecuencial() {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream( new FileOutputStream("asignaturas.dat")))) {
			
			for (Asignatura a : listaAsignaturas) {
				oos.writeObject(a);
			}
			System.out.println("\nAsignaturas guardadas con éxito en el archivo 'asignaturas.dat'");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
