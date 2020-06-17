package sub;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class MQTTWeather{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/city?serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	//private static Scanner city;
	//private static Scanner myObj;

	public static void main(String[] args) throws IOException, APIException, ClassNotFoundException, SQLException 
	{
		File file = new File("C:\\xampp\\htdocs\\cityname.txt");
	      Scanner scc = new Scanner(file);
	      scc.useDelimiter("\\Z"); 
	      
	      String cityy = scc.next();
	      System.out.println(cityy);
	      System.out.println("Data Successfully Extracted!");
	      
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected database successfully...");

		int len=cityy.length();
		OWM owm = new OWM("54a8295fb6882e10472e7f0d131b5960");

		FileWriter myWriter = new FileWriter("C:\\Users\\Han Solo\\eclipse-workspace\\001\\src\\main\\java\\hello.txt");
			CurrentWeather cwd = owm.currentWeatherByCityName(cityy);
			//			String name= cwd.getCityName();
			//			String max=String.valueOf(cwd.getMainData().getTempMax());
			//			String min=String.valueOf(cwd.getMainData().getTempMin());
			String name= cwd.getCityName();
			Double max=cwd.getMainData().getTempMax();
			Double min=cwd.getMainData().getTempMin();
			Double Pressure=cwd.getMainData().getPressure();
			Double Humidity=cwd.getMainData().getHumidity();
			Double Speed=cwd.getWindData().getSpeed();
			Double Direction=cwd.getWindData().getDegree();
			Double Cloud=cwd.getCloudData().getCloud();
			max=max-273.15;
			min=min-273.15;
			
			
			String sql = "DELETE FROM temprature WHERE name = ?";
			PreparedStatement psttmt = conn.prepareStatement(sql);
	            psttmt.setString(1, name);
	            psttmt.executeUpdate();
			
			System.out.println("Inserting records into the table...");
			java.sql.PreparedStatement pstmt = conn.prepareStatement("INSERT INTO temprature VALUES (?, ?, ?,?, ?, ?, ?,? )");
			pstmt.setString(1,name);
			pstmt.setDouble(2, min);
			pstmt.setDouble(3, max);;
			pstmt.setDouble(4, Pressure);
			pstmt.setDouble(5, Humidity);
			pstmt.setDouble(6,Speed);
			pstmt.setDouble(7,Direction);
			pstmt.setDouble(8,Cloud);
			pstmt.executeUpdate();
			

			myWriter.write(name+":-"+","+" Minimum Temp: "+min+","+" Maximum Temp: "+max+","+" Pressure: "+Pressure+","+" Humidity: "+Humidity+","+" Wind Speed: "+Speed+","+" Direction: "+Direction+","+" Cloud: "+Cloud+"\n");
		
		myWriter.close();
		System.out.println("Successfully wrote to the file.");
		System.out.println("Successfully written to database");

		Scanner sc = null;
		try {
			sc = new Scanner(new File("C:\\Users\\Han Solo\\eclipse-workspace\\001\\src\\main\\java\\hello.txt"));

			// Check if there is another line of input
			while(sc.hasNextLine())
			{
				String str = sc.nextLine();
				// parse each line using delimiter
				parseData(str);
			}
		} 
		catch (IOException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}finally{
			if(sc != null)
				sc.close();
		}              
	}

	private static void parseData(String str){    
		Scanner lineScanner = new Scanner(str);
		lineScanner.useDelimiter(",");
		while(lineScanner.hasNext()) {
			String topic        = "Pressure";
			String content1      = lineScanner.next() ;
			// String content1     =  args[0]+"Pascal";
			int qos             = 1;
			String broker       = "tcp://localhost:1883";
			String PubId        = "127.0.0.1";
			MemoryPersistence persistence = new MemoryPersistence();
			// long startTime = System.nanoTime();
			
			try {

				MqttClient sampleClient = new MqttClient(broker, PubId,        persistence);
				MqttConnectOptions connOpts = new MqttConnectOptions();
				connOpts.setCleanSession(true);
				connOpts.setConnectionTimeout(60);
				connOpts.setKeepAliveInterval(60);
				connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
				System.out.println("Connecting to broker: "+broker);
				sampleClient.connect(connOpts);
				System.out.println("Connected");
				System.out.println("Publishing message: "+content1);
				MqttMessage message = new MqttMessage(content1.getBytes());
				message.setQos(qos);
				sampleClient.publish(topic,message);
				System.out.println("Message published");

			} 
			
			catch(MqttException me) {
				System.out.println("Reason "+me.getReasonCode());
				System.out.println("Message "+me.getMessage());
				System.out.println("Localized Message "+me.getLocalizedMessage());
				System.out.println("Cause "+me.getCause());
				System.out.println("Exception "+me);
				me.printStackTrace();
			}

		}
		
		lineScanner.close();
	}



}