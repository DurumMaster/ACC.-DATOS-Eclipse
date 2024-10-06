package dam.ad.practica2.main;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import dam.ad.practica2.javabeans.Cliente;

public class AlmacenarClientes {
	
	public static void main(String[] args) {
		
        Cliente[] clientes = {
                new Cliente("Luis", "Fernandez", "luis.fernandez@gmail.com", "Calle Mayor 12", "2023-09-15", "Madrid", "Madrid"),
                new Cliente("Maria", "Gomez", "maria.gomez@hotmail.com", "Avenida de América 43", "2024-01-10", "Barcelona", "Barcelona"),
                new Cliente("Carlos", "Martinez", "carlos.martinez@yahoo.com", "Calle de Alcalá 5", "2023-07-22", "Valencia", "Valencia"),
                new Cliente("Ana", "Lopez", "ana.lopez@gmail.com", "Calle San Juan 34", "2023-11-08", "Sevilla", "Sevilla"),
                new Cliente("Jorge", "Sanchez", "jorge.sanchez@outlook.com", "Avenida Libertad 99", "2024-02-01", "Bilbao", "Vizcaya")
            };
        
        try ( ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("clientes.dat")))) {
        	
        	for (Cliente cliente : clientes) {
        		oos.writeObject(cliente);
        	}
        	System.out.println("Clientes guardados en el archivo clientes.dat");
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
