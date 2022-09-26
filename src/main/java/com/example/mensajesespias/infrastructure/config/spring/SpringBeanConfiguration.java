package com.example.mensajesespias.infrastructure.config.spring;

import com.example.mensajesespias.application.network.find.ICommunicationNetworkFinder;
import com.example.mensajesespias.application.network.find.impl.CommunicationNetworkFinder;
import com.example.mensajesespias.application.network.save.ICommunicationNetworkSaver;
import com.example.mensajesespias.application.network.save.impl.CommunicationNetworkSaver;
import com.example.mensajesespias.domain.network.ICommunicationNetworkRepository;
import com.example.mensajesespias.domain.network.mst.MinimumCommunicationNetworkSpanningSearcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

  @Bean
  public ICommunicationNetworkSaver communicationNetworkSaver(
      ICommunicationNetworkRepository communicationNetworkRepository) {
    return new CommunicationNetworkSaver(communicationNetworkRepository);
  }

  @Bean
  public ICommunicationNetworkFinder communicationNetworkFinder(
      ICommunicationNetworkRepository communicationNetworkRepository) {
    return new CommunicationNetworkFinder(communicationNetworkRepository);
  }

  @Bean
  public MinimumCommunicationNetworkSpanningSearcherFactory solverFactory(ICommunicationNetworkFinder communicationNetworkFinder) {
    return new MinimumCommunicationNetworkSpanningSearcherFactory(communicationNetworkFinder);
  }

}
