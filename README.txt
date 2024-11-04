PruebasUF1 --> TODA LA TEORÍA

Secuencial --> BufferWriter / BufferReader / FileWriter(archivo, true[no sobreescribir]) / FileReader
	.write / .readLine
	
	- P1 --> Menú / ArrayList / escribir txt 

Binario Secuencial --> FileOutputStream / FileInputStream / DataOutputStream / DataInputStream
	write y read según tipo de variable

Binario Objeto --> ObjectOutputStream / ObjectInputStream / BufferOutputStream / BufferInputStream / FileOutputStream / FileInputStream
	.writeObject / .readObject

	- P2 --> Array a escribir por objetos (eficiente)
	- P3 --> Solicitar Datos
	- P6 --> Pojo dentro de Pojo

Binario Aleatorio --> RandomAccesFile(ruta, permisos [rw o r]
	write y read según tipo de variable / StringBuffer

	int - 4 bytes
	char - 2 bytes
	double - 8 bytes
	boolean - 1 byte
	
	- P4 --> Uso tamaños bytes / Uso StringBuffer para tam Strings
	- P5 --> MÁS COMPLETO / Validación Datos / Conseguir ID / StringBuffer


 - P7 --> De txt secuencial a binario aleatorio / MEJOR CONSEGUIR ID / Completo

--------------------------------------------------------------------------------------------

XML-DOM
	- P9 --> Buen ejemplo escribir DOM y mostrar por consola / Doble Pojo
	- P10 --> Escribir leer Binario Objetos / Escribir DOM mostrar consola
	- PruebasUF1.2 --> También hay info

SAX
	- PruebasUF1.2 --> Hay info

XStream
	- P10XStream --> Mejor ejemplo y más completo con todo
	- PruebasXStream --> También hay info