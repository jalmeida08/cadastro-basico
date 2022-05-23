package br.com.jsa;

import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class CadastroBasicoApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
		return new RestTemplate(requestFactory);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(CadastroBasicoApplication.class, args);
	}

}
