package dam.ad.practica1.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dam.ad.practica1.javabeans.Cliente;

public class GestionClientes {

	private static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		boolean salir = false;
		cargarClientesTexto();

		while (!salir) {
			System.out.println(
					"\n* * ELIJA LA OPCIÓN QUE DESEE * *"
					+ "\n1. Añadir Cliente"
					+ "\n2. Ver Clientes"
					+ "\n3. Salir");
			
			int opt = sc.nextInt();
			sc.nextLine();

			switch (opt) {
			case 1:
				aniadirClientes();
				break;
			case 2:
				verClientes();
				break;
			case 3:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida.");
			}
		}

	}

	private static void verClientes() {
		
		System.out.println("\nLista de clientes guardados en la app");
		System.out.println("----------------------------------------");
		
		for (Cliente cliente : listaClientes) {
			System.out.println(cliente + "\n");
		}
		
	}

	private static void cargarClientesTexto() {
		
		File file = new File("clientes.txt");
		if (!file.exists()) {
			System.out.println("El archivo clientes.txt no existe. No se cargarán clientes.");
			return;
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
			
			String linea;
			
			while ((linea = reader.readLine()) != null) {
				String[] datos = linea.split(", ");
				Cliente cliente = new
						Cliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
				listaClientes.add(cliente);
			}
			System.out.println("Clientes cargados desde archivo de texto.");
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo. Los clientes no se han cargado.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al cargar los clientes.");
			e.printStackTrace();
		}
	}

	private static void aniadirClientes() {
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Apellido: ");
		String apellido = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Dirección: ");
		String direccion = sc.nextLine();
		System.out.print("Fecha Alta: ");
		String fechaAlta = sc.nextLine();
		System.out.print("Provincia: ");
		String provincia = sc.nextLine();
		System.out.print("Ciudad: ");
		String ciudad = sc.nextLine();

		Cliente cliente = new Cliente(nombre, apellido, email, direccion, fechaAlta, provincia, ciudad);
		listaClientes.add(cliente);
		System.out.println("Cliente añadido.");
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt",true))) {
			writer.write(cliente.getNombre() + ", "
						+ cliente.getApellido() + ", "
						+ cliente.getEmail() + ", "
						+ cliente.getDireccion() + ", "
						+ cliente.getFechaAlta() + ", "
						+ cliente.getProvincia() + ", "
						+ cliente.getCiudad());
			writer.newLine();
			System.out.println("Cliente guardado en archivo de texto.");
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al guardar el cliente en el archivo de texto.");
			e.printStackTrace();
		}
	}

}
