package MQTT;

import java.util.UUID;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

public class MQTTClient {
	public static void main(String[] args) {
		try {
			Mqtt3AsyncClient client = MqttClient.builder()
			        .useMqttVersion3()
			        .identifier(UUID.randomUUID().toString())
			        .serverHost("broker.hivemq.com")
			        .serverPort(8000)
			        .buildAsync();
			
			client.connect()
	        .whenComplete((connAck, throwable) -> {
	            if (throwable != null) {
	                // Handle connection failure
	            	System.out.println("failed");
	            } else {
	                // Setup subscribes or start publishing
	            	System.out.println("ok");
	            }
	        });
			
		} catch (Exception e) {
			e.printStackTrace();
		} //Persistence
		
		
	}
}
